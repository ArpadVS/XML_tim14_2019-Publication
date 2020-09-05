package publications.controller;

import java.io.File;
import java.net.URI;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import publications.model.letter.CoverLetter;
import publications.model.paper.ScientificPaper;
import publications.model.paper.TPaperStatus;
import publications.model.review.Review;
import publications.model.user.User;
import publications.repository.CoverLetterRepository;
import publications.repository.ReviewRepository;
import publications.repository.ScientificPaperRepository;
import publications.repository.UserRepository;
import publications.util.db.exist_db.ExistDBManagement;
import publications.util.marshalling.MarshallLetter;
import publications.util.marshalling.MarshallPaper;
import publications.util.marshalling.MarshallReview;

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
        userRepository.save(u1); 
        userRepository.save(u2); 
        userRepository.save(u3);
        
        //todo, make xml files more reasonable
        
        //initiate papers
        File fp1 = new File(a.getPath() + "/paper1.xml");  
        File fp2 = new File(a.getPath() + "/paper2.xml");  
        File fp3 = new File(a.getPath() + "/paper3.xml"); 
        jaxbContext = JAXBContext.newInstance(ScientificPaper.class);  
        jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
        ScientificPaper pp1 = (ScientificPaper) jaxbUnmarshaller.unmarshal(fp1);
        ScientificPaper pp2 = (ScientificPaper) jaxbUnmarshaller.unmarshal(fp2);
        ScientificPaper pp3 = (ScientificPaper) jaxbUnmarshaller.unmarshal(fp3);
        pp1.setStatus(TPaperStatus.TO_BE_REVIEWED);
        pp2.setStatus(TPaperStatus.TO_BE_REVIEWED);
        pp3.setStatus(TPaperStatus.TO_BE_REVIEWED);
        
        paperRepository.save(MarshallPaper.marshall(pp1));
        paperRepository.save(MarshallPaper.marshall(pp2));
        paperRepository.save(MarshallPaper.marshall(pp3));
        

        //initiate letters
        File fl1 = new File(a.getPath() + "/letter1.xml");  
        File fl2 = new File(a.getPath() + "/letter2.xml");  
        jaxbContext = JAXBContext.newInstance(CoverLetter.class);  
        jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
        CoverLetter pl1 = (CoverLetter) jaxbUnmarshaller.unmarshal(fl1);
        CoverLetter pl2 = (CoverLetter) jaxbUnmarshaller.unmarshal(fl2);
        
        letterRepository.save(MarshallLetter.marshall(pl1));
        letterRepository.save(MarshallLetter.marshall(pl2));
        

        //initiate review
        File fr1 = new File(a.getPath() + "/review1.xml");  
        File fr2 = new File(a.getPath() + "/review2.xml");  
        jaxbContext = JAXBContext.newInstance(Review.class);  
        jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
        Review pr1 = (Review) jaxbUnmarshaller.unmarshal(fr1);
        Review pr2 = (Review) jaxbUnmarshaller.unmarshal(fr2);
        
        reviewRepository.save(MarshallReview.marshall(pr1));
        reviewRepository.save(MarshallReview.marshall(pr2));

	}

}
