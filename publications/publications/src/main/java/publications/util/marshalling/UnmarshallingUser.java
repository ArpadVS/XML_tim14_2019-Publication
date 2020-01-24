package publications.util.marshalling;

import java.io.File;
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
}
