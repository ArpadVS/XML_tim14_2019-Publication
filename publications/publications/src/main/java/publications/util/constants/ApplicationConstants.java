package publications.util.constants;

public class ApplicationConstants {

	public static final String TARGET_NAMESPACE_TIM14 = "http://www.ftn.uns.ac.rs/tim14";
	public static final String TARGET_NAMESPACE_PUBLICATION = "https://github.com/ArpadVS/XML_tim14_2019-Publication";
	
	public static final String USER_COLLECTION_ID = "/db/sample/users";
	public static final String COVER_LETTER_COLLECTION_ID = "/db/sample/coverLetters";
	public static final String SCIENTIFIC_PAPER_COLLECTION_ID = "/db/sample/scientificPapers";
	public static final String REVIEW_COLLECTION_ID = "/db/sample/reviews";
	
	public static final String ID_SEPARATOR = "-";
	public static final String USER_ID_PREFIX = "user"+ID_SEPARATOR;
	public static final String COVER_LETTER_ID_PREFIX = "coverLetter"+ID_SEPARATOR;
	public static final String SCIENTIFIC_PAPER_ID_PREFIX = "scientificPaper"+ID_SEPARATOR;
	public static final String REVIEW_ID_PREFIX = "review"+ID_SEPARATOR;
	
	
	public static final String RESOURCES_PATH = "src/main/resources";
	public static final String DATA_PATH = RESOURCES_PATH + "/data";
	public static final String RDF_FILE_PATH = DATA_PATH + "/rdf/metadata.rdf";
	public static final String GRAPH_URI = "";
	
	public static final String FOP_XCONF_PATH = RESOURCES_PATH + "/fop.xconf";
	
	/*
	 * XSLT paths
	 */
	public static final String XSLT_PATH_PREFIX = DATA_PATH + "/xslt";
	public static final String USER_XSLT = XSLT_PATH_PREFIX + "/user.xsl";
	public static final String COVER_LETTER_XSLT = XSLT_PATH_PREFIX + "/CoverLetter.xsl";
	public static final String SCIENTIFIC_PAPER_XSLT = XSLT_PATH_PREFIX + "/ScientificPaper.xsl";
	public static final String REVIEW_XSLT = XSLT_PATH_PREFIX + "/review.xsl";
	
	/*
	 * XSLT_FO paths
	 */
	public static final String XSLT_FO_PATH_PREFIX = DATA_PATH + "/xslt_fo";
	public static final String USER_XSLT_FO = XSLT_FO_PATH_PREFIX + "/user.xsl";
	public static final String COVER_LETTER_XSLT_FO = XSLT_FO_PATH_PREFIX + "/CoverLetter.xsl";
	public static final String SCIENTIFIC_PAPER_XSLT_FO = XSLT_FO_PATH_PREFIX + "/ScientificPaper.xsl";
	public static final String REVIEW_XSLT_FO = XSLT_FO_PATH_PREFIX + "/review.xsl";
	
	/*
	 * XSD paths
	 */
	public static final String XSD_PATH_PREFIX = DATA_PATH + "/xsd";
	public static final String USER_XSD = XSD_PATH_PREFIX + "/user.xsd";
	public static final String COVER_LETTER_XSD = XSD_PATH_PREFIX + "/CoverLetter.xsd";
	public static final String SCIENTIFIC_PAPER_XSD = XSD_PATH_PREFIX + "/ScientificPaper.xsd";
	public static final String REVIEW_XSD = XSD_PATH_PREFIX + "/review.xsd";
	
}
