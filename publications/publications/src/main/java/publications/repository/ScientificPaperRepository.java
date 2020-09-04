package publications.repository;

import static publications.util.constants.ApplicationConstants.SCIENTIFIC_PAPER_COLLECTION_ID;
import static publications.util.constants.ApplicationConstants.SCIENTIFIC_PAPER_ID_PREFIX;
import static publications.util.constants.ApplicationConstants.SCIENTIFIC_PAPER_XSD;
import static publications.util.constants.ApplicationConstants.TARGET_NAMESPACE_PUBLICATION;

import java.util.ArrayList;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import publications.exceptions.NotFoundException;
import publications.model.paper.ScientificPaper;
import publications.model.user.DTO.ScientificPaperDTO;
import publications.util.IdGenerator;
import publications.util.db.exist_db.ExistDBManagement;
import publications.util.dom_parser.DOMParser;
import publications.util.marshalling.UnmarshallingUtil;

@Repository
public class ScientificPaperRepository {

	@Autowired
	ExistDBManagement dbManagement;

	@Autowired
	IdGenerator idGenerator;

	@Autowired
	DOMParser domParser;
	
	@Autowired
	UnmarshallingUtil unmarshallingUtil;
	
	public String save(String scientificPaper) throws Exception {
		String id = idGenerator.generateRandomID(SCIENTIFIC_PAPER_COLLECTION_ID, SCIENTIFIC_PAPER_ID_PREFIX);
		Document document = domParser.buildDocument(scientificPaper, SCIENTIFIC_PAPER_XSD);
		dbManagement.save(SCIENTIFIC_PAPER_COLLECTION_ID, id, scientificPaper);
		return id;
	}
	
	public String update(String scientificPaper, String id) throws Exception {
		Document document = domParser.buildDocument(scientificPaper, SCIENTIFIC_PAPER_XSD);
		dbManagement.save(SCIENTIFIC_PAPER_COLLECTION_ID, id, scientificPaper);
		return scientificPaper;
	}
	
	public String findByID(String id) throws NotFoundException{
		XMLResource res;
		try {
			res = dbManagement.findOne(SCIENTIFIC_PAPER_COLLECTION_ID, id);
			return res.getContent().toString();
		} catch (Exception e) {
			throw new NotFoundException("Could not find requested Scientific Paper");
		}
		
	}
	
	public ArrayList<ScientificPaperDTO> getAll(){
		// TODO proveriti da li radi
		ArrayList<ScientificPaperDTO> all = new ArrayList<>();
		
		try {
			
			ResourceSet result = dbManagement.executeXPath(SCIENTIFIC_PAPER_COLLECTION_ID, "data(//scientificPaper)", TARGET_NAMESPACE_PUBLICATION);
			if (result == null) {
				throw new Exception();
			}
			
			ResourceIterator i = result.getIterator();
			Resource res = null;
			ScientificPaper scPaper = null;
			ScientificPaperDTO dto = new ScientificPaperDTO();
			
			while (i.hasMoreResources()) {
				System.out.println("******");
				try {
					res = i.nextResource();
					scPaper = unmarshallingUtil.unmarshallScientificPaper((res.getContent().toString()));
					// System.out.println(res.getContent().toString());
					//dto.setTitle(scPaper.getTitle());
					dto.setId(scPaper.getId());
					/*for (TAuthor author: scPaper.getAuthors().getAuthor()) {
						dto.getAuthors().add(author.getFullName());
					}*/
					all.add(dto);
				} finally {
					// don't forget to cleanup resources
					try {
						((EXistResource) res).freeResources();
					} catch (XMLDBException e) {
						e.printStackTrace();
					}
				}
			}

			

		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
			return null;
			
		}
		
		return all;
	}
	public String findByExpression(String expression) throws NotFoundException {
		try {
			ResourceSet result = dbManagement.executeXPath(SCIENTIFIC_PAPER_COLLECTION_ID, expression, TARGET_NAMESPACE_PUBLICATION);

			if (result == null) {
				return null;
			}

			ResourceIterator i = result.getIterator();
			Resource res = null;
			String scientificPaper = "";

			if (!i.hasMoreResources()) {
				System.out.println("Not found");
				throw new NotFoundException("Could not find requested scientific paper");
			}
			while (i.hasMoreResources()) {

				try {
					res = i.nextResource();
					scientificPaper = res.getContent().toString();

				} finally {
					// don't forget to cleanup resources
					try {
						((EXistResource) res).freeResources();
					} catch (XMLDBException e) {
						e.printStackTrace();
					}
				}
			}

			return scientificPaper;

		} catch (Exception e) {
			throw new NotFoundException("Could not find requested scientific paper");
		}
	}
	
	public ScientificPaper getOneObj(String id) throws NotFoundException {
		String xml = findByID(id);
		ScientificPaper obj = unmarshallingUtil.unmarshallScientificPaper(xml);
		return obj;
	}
}
