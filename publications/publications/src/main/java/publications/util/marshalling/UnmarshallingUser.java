package publications.util.marshalling;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import publications.model.user.User;

public class UnmarshallingUser {

	public static User unmarshall(String xml) {
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
	
	public static User unmarshallFromFile(String filePath) {
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
