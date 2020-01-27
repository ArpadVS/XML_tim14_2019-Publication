package publications.util.transformations;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import net.sf.saxon.TransformerFactoryImpl;
import static publications.util.constants.ApplicationConstants.FOP_XCONF_PATH;

@Component
public class XSLFOTransformer {

	/**
	 * 
	 * Primer demonstrira koriscenje Apache FOP programskog API-a za renderovanje
	 * PDF-a primenom XSL-FO transformacije na XML dokumentu.
	 *
	 */

	private FopFactory fopFactory;

	private TransformerFactory transformerFactory;

	public XSLFOTransformer() throws SAXException, IOException {

		// Initialize FOP factory object
		fopFactory = FopFactory.newInstance(new File(FOP_XCONF_PATH));

		// Setup the XSLT transformer factory
		transformerFactory = new TransformerFactoryImpl();
	}

	public ByteArrayOutputStream generatePDF(String xml, String xsl_foPath) throws Exception {

		System.out.println("[INFO] " + XSLFOTransformer.class.getSimpleName());

		// Point to the XSL-FO file
		File xslFile = new File(xsl_foPath);

		// Create transformation source
		StreamSource transformSource = new StreamSource(xslFile);

		// Initialize the transformation subject
		StreamSource source = new StreamSource(new StringReader(xml));

		// Initialize user agent needed for the transformation
		FOUserAgent userAgent = fopFactory.newFOUserAgent();

		// Create the output stream to store the results
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		// Initialize the XSL-FO transformer object
		Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);

		// Construct FOP instance with desired output format
		Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);

		// Resulting SAX events
		Result res = new SAXResult(fop.getDefaultHandler());

		// Start XSLT transformation and FOP processing
		xslFoTransformer.transform(source, res);

		return outStream;
	}

}
