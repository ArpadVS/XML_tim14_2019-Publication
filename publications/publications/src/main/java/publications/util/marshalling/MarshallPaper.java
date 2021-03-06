package publications.util.marshalling;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import publications.exceptions.MarshallingFailedException;
import publications.model.paper.ScientificPaper;

public class MarshallPaper {

	public static String marshall(ScientificPaper paper) throws MarshallingFailedException  {
		try {
			StringWriter sw = new StringWriter();
			
			// DefiniÅ¡e se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
			JAXBContext context = JAXBContext.newInstance("publications.model.paper");
			
			
			// Marshaller je objekat zaduÅ¾en za konverziju iz objektnog u XML model
			Marshaller marshaller = context.createMarshaller();
			
			// PodeÅ¡avanje marshaller-a
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			// Umesto System.out-a, moÅ¾e se koristiti FileOutputStream
			marshaller.marshal(paper, sw);
			return sw.toString();
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return null;
	}
}
