package Surendra;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

@Service
public class AlienService {
	
    @Autowired
	private AlienRepository AR;
    @Autowired
    private EntityManager entitymanager;
    public List show_data(){
    	try {
    		List lst=AR.Elements();
    		return lst;
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
		return null;
    }
	public String insert_data(Alien A) {
		if(!"Supriya".equals(A.getName())) {
		if(AR.save(A) != null) {
		return "data inserted";
		}
		else {
			return "data not inserted";
		}
	}
		else {
			return "data not valid to insert";
		}
	
	}
	@Transactional
	public String delete_by_id(int id) {
//		if(AR.deleteById(id) !=null)
//			return "data deleted";
//		else "data not deleted";
		if(AR.existsById(id)) {
		AR.deleteById(id);
		return "data deleted";
		}
		else {
			return "data not deleted";
		}
	}
	public String update_by_id(int id,Alien A) {
		Alien EX=AR.findById(id).orElse(null);
		if(EX!=null) {
			if(A.getName()!=null) EX.setName(A.getName());
			if(A.getLang()!=null) EX.setLang(A.getLang());
			AR.save(EX);
			return "data updated";
		}
		else return "data is empty";
	}
	@Transactional
	public String create_table() {
	 //   AR.createtable();
		entitymanager.createNativeQuery("CREATE TABLE alien1 AS SELECT * from alien").executeUpdate();
		return "table created";
	}
	public List<Object[]> show_suri(){
		//return EM.createNativeQuery("select * from alien1").getResultList();
		return entitymanager.createNativeQuery("select * from alien2").getResultList();
	}
	@Transactional
	public void create_left_join() {
		entitymanager.createNativeQuery("create TABLE alien2 AS(select a.id,a1.name,a1.lang from alien a LEFT JOIN alien1 a1 ON a.id=a1.id)").executeUpdate();
	}
	@Autowired
	private LoginRepository loginrepo;
	public String login_data(Login log) {
		// TODO Auto-generated method stub
		List<Login> lst=loginrepo.findByUsername(log.getUsername());
		if(lst.size()==0) {
		   //  System.out.println("User Not Found");
		     return "User Not Found";
		}
		Login login=lst.get(0);
		if(!login.getPassword().equals(log.getPassword())) {
			//System.out.println("Password matches");
			return "Password Not Matched";
		}
		if(!login.getRole().equals(log.getRole())){
			return "Role Not Matched";
		}
		return "Details Matched";
//		System.out.println(lst);
//		System.out.println(login.getPassword());
		
	}
}
