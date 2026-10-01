package Surendra;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AlienRepository extends CrudRepository<Alien,Integer> {
	@Query("SELECT a FROM Alien a WHERE name='Surendra'")
	//@Query("select a from Alien a")
	 List<Alien> Elements();
//	 @Query(value="CREATE TABLE suri AS SELECT * from alien",nativeQuery=true)
//	 void createtable();
}
