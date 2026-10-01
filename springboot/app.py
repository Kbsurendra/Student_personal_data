import os

from flask import Flask, jsonify, request
from flask_sqlalchemy import SQLAlchemy
from sqlalchemy import text


db = SQLAlchemy()


class Alien(db.Model):
    __tablename__ = "alien"

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    name = db.Column(db.String(255), nullable=True)
    lang = db.Column(db.String(255), nullable=True)

    def to_dict(self):
        return {"id": self.id, "name": self.name, "lang": self.lang}


def create_app(test_config=None):
    app = Flask(__name__)
    app.config.from_mapping(
        SQLALCHEMY_DATABASE_URI=os.getenv("DATABASE_URL", "sqlite:///alien.db"),
        SQLALCHEMY_TRACK_MODIFICATIONS=False,
    )
    if test_config:
        app.config.update(test_config)

    db.init_app(app)
    @app.after_request
    def add_cors_headers(response):
        response.headers["Access-Control-Allow-Origin"] = "http://localhost:5173"
        response.headers["Access-Control-Allow-Headers"] = "Content-Type"
        response.headers["Access-Control-Allow-Methods"] = "GET, POST, PUT, DELETE, OPTIONS"
        return response

    with app.app_context():
        db.create_all()

    @app.get("/show")
    def show_data():
        aliens = Alien.query.filter_by(name="Surendra").all()
        return jsonify([alien.to_dict() for alien in aliens])

    @app.get("/aliens")
    def get_aliens():
        return jsonify([alien.to_dict() for alien in Alien.query.all()])

    @app.post("/insert")
    def insert_data():
        data = request.get_json(silent=True) or {}
        if data.get("name") == "Supriya":
            return "data not valid to insert"

        alien = Alien(name=data.get("name"), lang=data.get("lang"))
        db.session.add(alien)
        db.session.commit()
        return "data inserted"

    @app.delete("/delete_by_id/<int:alien_id>")
    def delete_by_id(alien_id):
        alien = db.session.get(Alien, alien_id)
        if alien is None:
            return "data not deleted"

        db.session.delete(alien)
        db.session.commit()
        return "data deleted"

    @app.put("/update_by_id/<int:alien_id>")
    def update_by_id(alien_id):
        alien = db.session.get(Alien, alien_id)
        if alien is None:
            return "data is empty"

        data = request.get_json(silent=True) or {}
        if data.get("name") is not None:
            alien.name = data["name"]
        if data.get("lang") is not None:
            alien.lang = data["lang"]
        db.session.commit()
        return "data updated"

    @app.post("/create_table")
    def create_table():
        db.session.execute(
            text("CREATE TABLE IF NOT EXISTS alien1 AS SELECT * FROM alien")
        )
        db.session.commit()
        return "table created"

    @app.get("/show_suri")
    def show_suri():
        rows = db.session.execute(text("SELECT * FROM alien2")).mappings().all()
        return jsonify([dict(row) for row in rows])

    @app.post("/create_left_join")
    def create_left_join():
        db.session.execute(
            text(
                "CREATE TABLE IF NOT EXISTS alien2 AS "
                "SELECT a.id, a1.name, a1.lang FROM alien a "
                "LEFT JOIN alien1 a1 ON a.id = a1.id"
            )
        )
        db.session.commit()
        return "left join excuted"

    return app


app = create_app()


if __name__ == "__main__":
    app.run(debug=os.getenv("FLASK_DEBUG", "false").lower() == "true")
