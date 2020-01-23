package publications.util.marshalling;

import java.io.File;
import java.io.StringWriter;

import javax.naming.spi.DirStateFactory.Result;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import publications.exceptions.MarshallingFailedException;
import publications.model.user.User;

public class MarshallUser {

	public static String marshall(User user) throws MarshallingFailedException  {
		try {
			StringWriter sw = new StringWriter();
			
			// DefiniÅ¡e se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
			JAXBContext context = JAXBContext.newInstance("publications.model.user");
			
			
			// Marshaller je objekat zaduÅ¾en za konverziju iz objektnog u XML model
			Marshaller marshaller = context.createMarshaller();
			
			// PodeÅ¡avanje marshaller-a
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			// Umesto System.out-a, moÅ¾e se koristiti FileOutputStream
			marshaller.marshal(user, sw);
			return sw.toString();
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return null;
	}
}
