package publications.util.constants;

public class ApplicationConstants {

	public static final String TARGET_NAMESPACE = "http://www.ftn.uns.ac.rs/tim14";
	
	public static final String USER_COLLECTION_ID = "/db/sample/users";
	public static final String COVER_LETTER_COLLECTION_ID = "/db/sample/coverLetters";
	public static final String SCIENTIFIC_PAPER_COLLECTION_ID = "/db/sample/scientificPapers";
	
	public static final String ID_SEPARATOR = "-";
	public static final String USER_ID_PREFIX = "user"+ID_SEPARATOR;
	public static final String COVER_LETTER_ID_PREFIX = "coverLetter"+ID_SEPARATOR;
	public static final String SCIENTIFIC_PAPER_ID_PREFIX = "scientificPaper"+ID_SEPARATOR;
	
	
	public static final String RESOURCES_PATH = "src/main/resources";
	public static final String DATA_PATH = RESOURCES_PATH + "/data";
	public static final String RDF_FILE_PATH = DATA_PATH + "/rdf/metadata.rdf";
	public static final String GRAPH_URI = "";
	
	public static final String FOP_XCONF_PATH = RESOURCES_PATH + "/fop.xconf";
	public static final String XSLT_PATH_PREFIX = DATA_PATH + "/xslt";
	public static final String XSLT_FO_PATH_PREFIX = DATA_PATH + "/xslt_fo";
	
	public static final String XSD_PATH_PREFIX = DATA_PATH + "/xsd";
}
