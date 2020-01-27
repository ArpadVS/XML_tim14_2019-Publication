package publications.util.transformations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;

@Component
public class HTMLTransformer {

	private static TransformerFactory transformerFactory;
	private static DocumentBuilderFactory documentFactory;

	static {

		/* Inicijalizacija DOM fabrike */
		documentFactory = DocumentBuilderFactory.newInstance();
		documentFactory.setNamespaceAware(true);
		documentFactory.setIgnoringComments(true);
		documentFactory.setIgnoringElementContentWhitespace(true);
		
		/* Inicijalizacija Transformer fabrike */
		transformerFactory = TransformerFactory.newInstance();
		
	}
	
	public String generateHTML(String xml, String xslPath) throws Exception {

		System.out.println("[INFO] generating HTML");
		System.out.println(xml);
		// Initialize Transformer instance
		StreamSource transformSource = new StreamSource(new File(xslPath));
		Transformer transformer = transformerFactory.newTransformer(transformSource);
		transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		// Generate XHTML
		// transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

		// Transform DOM to HTML
		// DOMSource source = new DOMSource(buildDocument(xml));
		StringWriter sw = new StringWriter();
		StreamSource source = new StreamSource(new StringReader(xml));
		StreamResult result = new StreamResult(sw);
		transformer.transform(source, result);

		return sw.toString();

	}

	public org.w3c.dom.Document buildDocument(String xml) {

		org.w3c.dom.Document document = null;
		try {

			DocumentBuilder builder = documentFactory.newDocumentBuilder();
			document = builder.parse(new InputSource(new StringReader(xml)));

			if (document != null)
				System.out.println("[INFO] File parsed with no errors.");
			else
				System.out.println("[WARN] Document is null.");

		} catch (Exception e) {
			return null;

		}

		return document;
	}
}
