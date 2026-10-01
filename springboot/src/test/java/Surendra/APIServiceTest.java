package Surendra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
//
//@ExtendWith(MockitoExtension.class)
public class APIServiceTest {
	
	@Autowired
	private AlienRepository repository;
	
	@Test
	void testElements() {
		List<Alien> result=repository.Elements();
		System.out.println("recoreds found:"+result.size());
		for(Alien alien:result) {
			System.out.println(alien);
		}
		//assertEquals(3,result.size());
		assertEquals(16,result.size());
//		assertEquals("surendra",result.get(0).getName());
//		assertEquals("surendra",result.get(1).getName());
	}
	@Test
	void testElementbyName() {
		List<Alien> result=repository.Elements();
		System.out.println("recoreds found:"+result.size());
		for(Alien alien:result) {
			System.out.println(alien);
		}
		//assertEquals(3,result.size());
	//	assertEquals(16,result.size());
		assertEquals("surendra",result.get(0).getName());
		assertEquals("surendra",result.get(1).getName());
	}
	
}
//	@Mock
//	private AlienRepository repository;
//	
//	@InjectMocks
//	private AlienService service;
//	
//	@Test
//	void getAllbooks() {
////		List<Alien> alien=List.of(
////				new Alien(1,"suri","Spring"),
////				new Alien(2,"ravi","Boot")
////			);
//	    Alien a1 = new Alien();
//	    a1.setId(1);
//	    a1.setName("surendra");
//	    a1.setLang("Spring");
//
//	    Alien a2 = new Alien();
//	    a2.setId(2);
//	    a2.setName("ravi");
//	    a2.setLang("Boot");
//	    List<Alien> aliens=List.of(a1,a2);
//	    when(repository.Elements()).thenReturn(aliens);
//	    List<Alien> result=service.show_data();
//	    assertEquals(2,result.size());
//	   // assertEquals("surendra",result.get(0).getName());
//	    verify(repository).Elements();
//	}
//	
//}
