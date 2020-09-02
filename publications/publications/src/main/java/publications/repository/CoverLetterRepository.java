package publications.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.xmldb.api.modules.XMLResource;

import publications.util.IdGenerator;
import publications.util.db.exist_db.ExistDBManagement;
import publications.util.dom_parser.DOMParser;
import static publications.util.constants.ApplicationConstants.*;

@Repository
public class CoverLetterRepository {

	@Autowired
	ExistDBManagement dbManagement;

	@Autowired
	IdGenerator idGenerator;

	@Autowired
	DOMParser domParser;
	
	public String save(String coverLetter) throws Exception {
		String id = idGenerator.generateRandomID(COVER_LETTER_COLLECTION_ID, COVER_LETTER_ID_PREFIX);
		
		Document document = domParser.buildDocument(coverLetter, COVER_LETTER_XSD);
		dbManagement.save(COVER_LETTER_COLLECTION_ID, id, coverLetter);
		return id;
	}
	
	public String findOneByID(String id) throws Exception {
		XMLResource res = dbManagement.findOne(COVER_LETTER_COLLECTION_ID, id);
		return res.getContent().toString();
	}
}
