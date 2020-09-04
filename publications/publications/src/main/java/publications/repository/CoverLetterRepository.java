package publications.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.xmldb.api.modules.XMLResource;

import publications.exceptions.NotFoundException;
import publications.model.letter.CoverLetter;
import publications.model.paper.ScientificPaper;
import publications.util.IdGenerator;
import publications.util.db.exist_db.ExistDBManagement;
import publications.util.dom_parser.DOMParser;
import publications.util.marshalling.UnmarshallingUtil;

import static publications.util.constants.ApplicationConstants.*;

@Repository
public class CoverLetterRepository {

	@Autowired
	ExistDBManagement dbManagement;

	@Autowired
	IdGenerator idGenerator;

	@Autowired
	DOMParser domParser;

	@Autowired
	UnmarshallingUtil unmarshallingUtil;
	
	public String save(String coverLetter) throws Exception {
		String id = idGenerator.generateRandomID(COVER_LETTER_COLLECTION_ID, COVER_LETTER_ID_PREFIX);
		
		Document document = domParser.buildDocument(coverLetter, COVER_LETTER_XSD);
		dbManagement.save(COVER_LETTER_COLLECTION_ID, id, coverLetter);
		return id;
	}
	
	public String findOneByID(String id) throws NotFoundException{
		XMLResource res;
		try {
			res = dbManagement.findOne(COVER_LETTER_COLLECTION_ID, id);
			return res.getContent().toString();
		} catch (Exception e) {
			throw new NotFoundException("Could not find requested Cover letter");
		}
		
	}

	public CoverLetter getOneObj(String id) throws NotFoundException {
		String xml = findOneByID(id);
		CoverLetter obj = unmarshallingUtil.unmarshallCoverLetter(xml);
		return obj;
	}
}
