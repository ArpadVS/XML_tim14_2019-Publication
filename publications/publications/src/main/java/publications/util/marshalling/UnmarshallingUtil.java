package publications.util.marshalling;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import publications.model.letter.CoverLetter;
import publications.model.paper.ScientificPaper;
import publications.model.review.Review;
import publications.model.user.User;

@Component
public class UnmarshallingUtil {

	public User unmarshall(String xml) {
		try {
			// DefiniÅ¡e se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
			JAXBContext context = JAXBContext.newInstance("publications.model.user");

			// Unmarshaller je objekat zaduÅ¾en za konverziju iz XML-a u objektni model
			Unmarshaller unmarshaller = context.createUnmarshaller();

			User user = (User) unmarshaller.unmarshal(new StringReader(xml));

			return user;
		} catch (JAXBException e) {
			e.printStackTrace();		 
		}

		return null;
	}
	
	public ScientificPaper unmarshallScientificPaper(String xml) {
		try {
			// DefiniÅ¡e se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
			JAXBContext context = JAXBContext.newInstance("publications.model.paper");

			// Unmarshaller je objekat zaduÅ¾en za konverziju iz XML-a u objektni model
			Unmarshaller unmarshaller = context.createUnmarshaller();

			ScientificPaper scientificPaper = (ScientificPaper) unmarshaller.unmarshal(new StringReader(xml));

			return scientificPaper;
		} catch (JAXBException e) {
			e.printStackTrace();		 
		}

		return null;
	}
	
	public CoverLetter unmarshallCoverLetter(String xml){
		try {
			// DefiniÅ¡e se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
			JAXBContext context = JAXBContext.newInstance("publications.model.letter");

			// Unmarshaller je objekat zaduÅ¾en za konverziju iz XML-a u objektni model
			Unmarshaller unmarshaller = context.createUnmarshaller();

			CoverLetter letter = (CoverLetter) unmarshaller.unmarshal(new StringReader(xml));

			return letter;
		} catch (JAXBException e) {
			e.printStackTrace();		 
		}

		return null;
	}
	
	public Review unmarshallReview(String xml){
		try {
			// DefiniÅ¡e se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
			JAXBContext context = JAXBContext.newInstance("publications.model.review");

			// Unmarshaller je objekat zaduÅ¾en za konverziju iz XML-a u objektni model
			Unmarshaller unmarshaller = context.createUnmarshaller();

			Review rev = (Review) unmarshaller.unmarshal(new StringReader(xml));

			return rev;
		} catch (JAXBException e) {
			e.printStackTrace();		 
		}

		return null;
	}
	
	
	public User unmarshallFromFile(String filePath) {
		try {
			// DefiniÅ¡e se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
			JAXBContext context = JAXBContext.newInstance("publications.model.user");

			// Unmarshaller je objekat zaduÅ¾en za konverziju iz XML-a u objektni model
			Unmarshaller unmarshaller = context.createUnmarshaller();
			 
			String actual = readLineByLine(filePath);
			User user = (User) unmarshaller.unmarshal(new StringReader(actual));

			return user;
		} catch (JAXBException e) {
			e.printStackTrace();		 
		}

		return null;
	}
	
	private static String readLineByLine(String filePath) 
    {
        StringBuilder contentBuilder = new StringBuilder();
 
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)) 
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
 
        return contentBuilder.toString();
    }
}
