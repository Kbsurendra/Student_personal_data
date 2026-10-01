package Surendra;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<Login,String>{

	//Optional<Login> findByUname(String uname);
    @Query("select l from Login l where l.username=:username")
    List<Login> findByUsername(@Param("username") String username);
// findByName(String uname);
}
