package publications.repository;

import static publications.util.constants.ApplicationConstants.SCIENTIFIC_PAPER_COLLECTION_ID;
import static publications.util.constants.ApplicationConstants.SCIENTIFIC_PAPER_ID_PREFIX;
import static publications.util.constants.ApplicationConstants.SCIENTIFIC_PAPER_XSD;
import static publications.util.constants.ApplicationConstants.TARGET_NAMESPACE_PUBLICATION;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import publications.exceptions.MarshallingFailedException;
import publications.exceptions.NotFoundException;
import publications.model.DTO.ScientificPaperDTO;
import publications.model.paper.ScientificPaper;
import publications.model.paper.TAuthor;
import publications.model.paper.TPaperStatus;
import publications.util.IdGenerator;
import publications.util.db.exist_db.ExistDBManagement;
import publications.util.dom_parser.DOMParser;
import publications.util.marshalling.MarshallPaper;
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
		ScientificPaper scp = unmarshallingUtil.unmarshallScientificPaper(scientificPaper);
		scp.setId(id);
		// ukoliko je autor pokvaren i hoce da brlja po ovome:
		scp.setStatus(TPaperStatus.TO_BE_REVIEWED);
		scp.setAccepted(null);
		scp.setRevised(null);
		
		final GregorianCalendar now = new GregorianCalendar();
		scp.setRecieved(DatatypeFactory.newInstance().newXMLGregorianCalendar(now));
		
		scientificPaper = MarshallPaper.marshall(scp);
		Document document = domParser.buildDocument(scientificPaper, SCIENTIFIC_PAPER_XSD);
		dbManagement.save(SCIENTIFIC_PAPER_COLLECTION_ID, id, scientificPaper);
		return id;
	}

	public String update(String scientificPaper, String id) throws Exception {
		// ovo koristi autor da izmeni rad ciji je status to be reviewed
		String found = findByID(id);
		ScientificPaper scp_found = unmarshallingUtil.unmarshallScientificPaper(found);
		if (scp_found.getStatus().equals(TPaperStatus.TO_BE_REVIEWED)) {
			ScientificPaper scp_update = unmarshallingUtil.unmarshallScientificPaper(scientificPaper);
			scp_update.setStatus(scp_found.getStatus());
			scp_update.setAccepted(scp_found.getAccepted());
			scp_update.setRevised(scp_found.getRevised());
			scp_update.setRecieved(scp_found.getRecieved());
			scientificPaper = MarshallPaper.marshall(scp_update);
			Document document = domParser.buildDocument(scientificPaper, SCIENTIFIC_PAPER_XSD);
			dbManagement.save(SCIENTIFIC_PAPER_COLLECTION_ID, id, scientificPaper);
			return scientificPaper;
		}
		return null;
	}

	public String revise(String scientificPaper, String id) throws Exception {
		String found = findByID(id);
		ScientificPaper scp_found = unmarshallingUtil.unmarshallScientificPaper(found);
		if (scp_found.getStatus().equals(TPaperStatus.REVISION_NEEDED)) {
			ScientificPaper scp_revision = unmarshallingUtil.unmarshallScientificPaper(scientificPaper);
			scp_revision.setStatus(TPaperStatus.REVISION_DONE);
			scp_revision.setAccepted(scp_found.getAccepted());
			
			final GregorianCalendar now = new GregorianCalendar();
			scp_revision.setRevised(DatatypeFactory.newInstance().newXMLGregorianCalendar(now));
			
			scp_revision.setRecieved(scp_found.getRecieved());
			scientificPaper = MarshallPaper.marshall(scp_revision);
			Document document = domParser.buildDocument(scientificPaper, SCIENTIFIC_PAPER_XSD);
			dbManagement.save(SCIENTIFIC_PAPER_COLLECTION_ID, id, scientificPaper);
			return scientificPaper;
		}
		return "Revision not needed";
		
	}
	public String updateStatus(TPaperStatus status, String id) throws Exception {
		if(status.equals(TPaperStatus.TO_BE_REVIEWED) || status.equals(TPaperStatus.REVISION_DONE)) {
			return null;
		}
		String xml = findByID(id);
		ScientificPaper scPaper = unmarshallingUtil.unmarshallScientificPaper(xml);
		scPaper.setStatus(status);
		
		if(status.equals(TPaperStatus.ACCEPTED)) {
			final GregorianCalendar now = new GregorianCalendar();
			scPaper.setAccepted(DatatypeFactory.newInstance().newXMLGregorianCalendar(now));
		}
		xml = MarshallPaper.marshall(scPaper);
		Document document = domParser.buildDocument(xml, SCIENTIFIC_PAPER_XSD);
		dbManagement.save(SCIENTIFIC_PAPER_COLLECTION_ID, id, xml);
		return xml;

	}
	
	

	public String findByID(String id) throws NotFoundException {
		XMLResource res;
		try {
			res = dbManagement.findOne(SCIENTIFIC_PAPER_COLLECTION_ID, id);
			return res.getContent().toString();
		} catch (Exception e) {
			throw new NotFoundException("Could not find requested Scientific Paper");
		}

	}

//	public ArrayList<ScientificPaperDTO> getAll1() {
//		ArrayList<ScientificPaperDTO> all = new ArrayList<>();
//		ArrayList<String> authors = new ArrayList<>();
//		authors.add("Author1");
//		ScientificPaperDTO dto = new ScientificPaperDTO("scientificPaper-6WB-ZG0", "titleeeaa", authors);
//		all.add(dto);
//		return all;
//	}

	public ArrayList<ScientificPaperDTO> getAll() {
		ArrayList<ScientificPaperDTO> all = new ArrayList<>();

		try {

			ResourceSet result = dbManagement.executeXPath(SCIENTIFIC_PAPER_COLLECTION_ID, "/",
					TARGET_NAMESPACE_PUBLICATION);
			if (result == null) {
				return all;
			}

			ResourceIterator i = result.getIterator();
			Resource res = null;
			ScientificPaper scPaper = null;

			while (i.hasMoreResources()) {
				// System.out.println("******");
				ScientificPaperDTO dto = new ScientificPaperDTO();
				try {
					res = i.nextResource();
					// System.out.println(res.getContent().toString());
					scPaper = unmarshallingUtil.unmarshallScientificPaper((res.getContent().toString()));
					dto.setId(scPaper.getId());
					dto.setTitle(scPaper.getTitle());
					dto.setStatus(scPaper.getStatus().value());
					for (TAuthor author : scPaper.getAuthors().getAuthor()) {
						dto.getAuthors().add(author.getFullName());
					}
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
			return all;

		}

		return all;
	}

	public ArrayList<ScientificPaperDTO> findMultipleByExpression(String expression) {
		ArrayList<ScientificPaperDTO> found = new ArrayList<>();
		try {
			ResourceSet result = dbManagement.executeXPath(SCIENTIFIC_PAPER_COLLECTION_ID, expression,
					TARGET_NAMESPACE_PUBLICATION);

			if (result == null) {
				return found;
			}

			ResourceIterator i = result.getIterator();
			Resource res = null;
			ScientificPaper scPaper = null;

			if (!i.hasMoreResources()) {
				System.out.println("Not found");
				throw new NotFoundException("Could not find requested scientific paper");
			}
			while (i.hasMoreResources()) {
				ScientificPaperDTO dto = new ScientificPaperDTO();
				try {
					res = i.nextResource();
					scPaper = unmarshallingUtil.unmarshallScientificPaper((res.getContent().toString()));
					dto.setId(scPaper.getId());
					dto.setTitle(scPaper.getTitle());
					dto.setStatus(scPaper.getStatus().value());
					for (TAuthor author : scPaper.getAuthors().getAuthor()) {
						dto.getAuthors().add(author.getFullName());
					}
					found.add(dto);

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
			// throw new NotFoundException("Could not find requested scientific paper");
			return found;
		}

		return found;
	}

	public String findByExpression(String expression) throws NotFoundException {
		try {
			ResourceSet result = dbManagement.executeXPath(SCIENTIFIC_PAPER_COLLECTION_ID, expression,
					TARGET_NAMESPACE_PUBLICATION);

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

	public ScientificPaperDTO getOneObj(String id) throws NotFoundException {
		String xml = findByID(id);
		ScientificPaper scPaper = unmarshallingUtil.unmarshallScientificPaper(xml);
		ScientificPaperDTO dto = new ScientificPaperDTO();
		dto.setTitle(scPaper.getTitle());
		dto.setId(scPaper.getId());
		for (TAuthor author : scPaper.getAuthors().getAuthor()) {
			dto.getAuthors().add(author.getFullName());
		}
		return dto;
	}
	
	
}
