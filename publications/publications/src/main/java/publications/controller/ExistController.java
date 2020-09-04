package publications.controller;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import publications.model.paper.ScientificPaper;
import publications.model.user.User;
import publications.repository.CoverLetterRepository;
import publications.repository.ReviewRepository;
import publications.repository.ScientificPaperRepository;
import publications.repository.UserRepository;
import publications.util.db.exist_db.ExistDBManagement;
import publications.util.marshalling.MarshallPaper;
import publications.util.marshalling.MarshallUser;

@RestController
@RequestMapping("/api/exist")
public class ExistController {
	
	@Autowired
	public ExistDBManagement existManager;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public ScientificPaperRepository paperRepository;
	

	@Autowired
	public ReviewRepository reviewRepository;
	

	@Autowired
	public CoverLetterRepository letterRepository;
	
	@GetMapping(value = "/initiateDb")
	public void initiateDate()
			throws Exception {
		Resource resource = new ClassPathResource("data/xml");
		URI a = resource.getURI();
		
		//initate users
        File fu1 = new File(a.getPath() + "/user1.xml");  
        File fu2 = new File(a.getPath() + "/user2.xml");  
        File fu3 = new File(a.getPath() + "/user3.xml");  
        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);  
   
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
        User u1= (User) jaxbUnmarshaller.unmarshal(fu1);  
        User u2= (User) jaxbUnmarshaller.unmarshal(fu2);  
        User u3= (User) jaxbUnmarshaller.unmarshal(fu3);  
        //userRepository.save(u1); 
        //userRepository.save(u2); 
        //userRepository.save(u3);
        
        //todo, make xml files more reasonable
        
        //initiate papers
        System.out.println(a.getPath() + "/paper1.xml");
        String p1 = new String(Files.readAllBytes(Paths.get(new URI(a.getPath() + "/paper1.xml")))); 
        String p2 = new String(Files.readAllBytes(Paths.get(new URI(a.getPath() + "/paper2.xml")))); 
     
        //paperRepository.save(p1);
        //paperRepository.save(p2);
        

        //initiate letters
        String l1 = new String(Files.readAllBytes(Paths.get(new URI(a.getPath() + "/letter1.xml")))); 
        String l2 = new String(Files.readAllBytes(Paths.get(new URI(a.getPath() + "/letter2.xml")))); 
     
        letterRepository.save(l1);
        letterRepository.save(l2);
        

        //initiate review
        String r1 = new String(Files.readAllBytes(Paths.get(new URI(a.getPath() + "/review1.xml")))); 
        String r2 = new String(Files.readAllBytes(Paths.get(new URI(a.getPath() + "/review2.xml")))); 
     
        reviewRepository.save(r1);
        reviewRepository.save(r2);

	}

}
