package publications.repository;

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
import publications.model.user.User;
import publications.model.user.DTO.ScientificPaperDTO;
import publications.util.IdGenerator;
import publications.util.db.exist_db.ExistDBManagement;
import publications.util.dom_parser.DOMParser;
import publications.util.marshalling.UnmarshallingUser;

import static publications.util.constants.ApplicationConstants.*;

import java.util.ArrayList;

@Repository
public class ScientificPaperRepository {

	@Autowired
	ExistDBManagement dbManagement;

	@Autowired
	IdGenerator idGenerator;

	@Autowired
	DOMParser domParser;
	
	public String save(String scientificPaper) throws Exception {
		String id = idGenerator.generateRandomID(SCIENTIFIC_PAPER_COLLECTION_ID, SCIENTIFIC_PAPER_ID_PREFIX);
		Document document = domParser.buildDocument(scientificPaper, XSD_PATH_PREFIX + "/ScientificPaper.xsd");
		dbManagement.save(SCIENTIFIC_PAPER_COLLECTION_ID, id, scientificPaper);
		return id;
	}
	
	public String update(String scientificPaper, String id) throws Exception {
		Document document = domParser.buildDocument(scientificPaper, XSD_PATH_PREFIX + "/ScientificPaper.xsd");
		dbManagement.save(SCIENTIFIC_PAPER_COLLECTION_ID, id, scientificPaper);
		return scientificPaper;
	}
	
	public String findByID(String id) throws Exception {
		XMLResource res = dbManagement.findOne(SCIENTIFIC_PAPER_COLLECTION_ID, id);
		return res.getContent().toString();
	}
	
	public ArrayList<ScientificPaperDTO> getAll(){
		ArrayList<ScientificPaperDTO> all = new ArrayList<>();
		
		try {
			
			ResourceSet result = dbManagement.executeXPath(SCIENTIFIC_PAPER_COLLECTION_ID, "data(//scientificPaper)");
			if (result == null) {
				throw new Exception();
			}
			
			ResourceIterator i = result.getIterator();
			Resource res = null;
			ScientificPaperDTO dto = new ScientificPaperDTO();
			while (i.hasMoreResources()) {
				System.out.println("******");
				try {
					res = i.nextResource();
					
					System.out.println(res.getContent().toString());
					// TODO svaki xml pretvoriti u dto i dodati u listu
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
			ResourceSet result = dbManagement.executeXPath(SCIENTIFIC_PAPER_COLLECTION_ID, expression);

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
}
