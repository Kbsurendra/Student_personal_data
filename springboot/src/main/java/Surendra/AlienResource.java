package Surendra;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class AlienResource {
	@Autowired
	AlienRepository repo;
	@GetMapping("/aliens")
  public List<Alien> getAliens(){
	  List<Alien> aliens=(List<Alien>)repo.findAll();
//	  Alien a1=new Alien();
//	  a1.setId(11);
//	  a1.setName("suri");
//	  a1.setLang("spring");
//	  aliens.add(a1);
//	  Alien a2=new Alien();
//	  a2.setId(12);
//	  a2.setName("sup");
//	  a2.setLang("springboot");
//	  aliens.add(a2);
	return aliens;
	  
  }
}
