package Surendra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins ="http://localhost:5173")
public class AlienController {
	@Autowired
	AlienService AS;
	
	@GetMapping("/token_verify")
	public void Token_verify() {
		System.out.println("token verification completed");
	}
	@PostMapping("/login_data")
	public String login_data(@RequestBody Login login) {
		String str=AS.login_data(login);
		//for(Login l:lst) System.out.println(l);
		return str;
	}
    @GetMapping("/show")
    public List<Alien> show_data() {
        try {
        	List lst=AS.show_data();
        	return lst;
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		return null;
    }
    @PostMapping("/insert")
    public String insert_data(@RequestBody Alien A) {
        try {
        	  String str=AS.insert_data(A);	
        	 return str;
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		return null;
    }
    @DeleteMapping("/delete_by_id/{id}")
    public String delete_by_id(@PathVariable int id) {
    	try {
    		String str=AS.delete_by_id(id);
    		return str;
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return "not deleted";
    }
    @PutMapping("/update_by_id/{id}")
    public String update_by_id(@PathVariable int id,@RequestBody Alien A) {
    	try {
    		String str=AS.update_by_id(id,A);
    		return str;
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
		return null;
    }
    @PostMapping("/create_table")
    public String create_table() {
    	String str=AS.create_table();
    	return str;
    }
    @GetMapping("/show_suri")
    public List<Object[]> show_suri() {
    	return AS.show_suri();
    }
    @PostMapping("/create_left_join")
    public String create_left_join() {
    	AS.create_left_join();
    	return "left join excuted";
    }
}