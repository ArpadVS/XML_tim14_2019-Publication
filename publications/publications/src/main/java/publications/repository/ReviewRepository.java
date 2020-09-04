package publications.repository;

import static publications.util.constants.ApplicationConstants.REVIEW_COLLECTION_ID;
import static publications.util.constants.ApplicationConstants.REVIEW_ID_PREFIX;
import static publications.util.constants.ApplicationConstants.REVIEW_XSD;
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
import publications.model.review.Review;
import publications.model.user.DTO.ReviewDTO;
import publications.util.IdGenerator;
import publications.util.db.exist_db.ExistDBManagement;
import publications.util.dom_parser.DOMParser;

import publications.util.marshalling.UnmarshallingUtil;


@Repository
public class ReviewRepository {

	@Autowired
	ExistDBManagement dbManagement;

	@Autowired
	IdGenerator idGenerator;

	@Autowired
	DOMParser domParser;
	
	public String save(String review) throws Exception {
		String id = idGenerator.generateRandomID(REVIEW_COLLECTION_ID, REVIEW_ID_PREFIX);
		Document document = domParser.buildDocument(review, REVIEW_XSD);
		dbManagement.save(REVIEW_COLLECTION_ID, id, review);
		return id;
	}
	
	public String update(String review, String id) throws Exception {
		Document document = domParser.buildDocument(review, REVIEW_XSD);
		dbManagement.save(REVIEW_COLLECTION_ID, id, review);
		return review;
	}
	
	public String findByID(String id) throws NotFoundException{
		XMLResource res;
		try {
			res = dbManagement.findOne(REVIEW_COLLECTION_ID, id);
			return res.getContent().toString();
		} catch (Exception e) {
			throw new NotFoundException("Could not find requested review");
		}
		
	}
	
	public ArrayList<ReviewDTO> getAll(){
		// TODO proveriti da li radi
		ArrayList<ReviewDTO> all = new ArrayList<>();
		
		try {
			
			ResourceSet result = dbManagement.executeXPath(REVIEW_COLLECTION_ID, "data(//review)", TARGET_NAMESPACE_PUBLICATION);
			if (result == null) {
				throw new Exception();
			}
			
			ResourceIterator i = result.getIterator();
			Resource res = null;
			ReviewDTO dto = new ReviewDTO();
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
			ResourceSet result = dbManagement.executeXPath(REVIEW_COLLECTION_ID, expression, TARGET_NAMESPACE_PUBLICATION);

			if (result == null) {
				return null;
			}

			ResourceIterator i = result.getIterator();
			Resource res = null;
			String review = "";

			if (!i.hasMoreResources()) {
				System.out.println("Not found");
				throw new NotFoundException("Could not find requested review");
			}
			while (i.hasMoreResources()) {

				try {
					res = i.nextResource();
					review = res.getContent().toString();

				} finally {
					// don't forget to cleanup resources
					try {
						((EXistResource) res).freeResources();
					} catch (XMLDBException e) {
						e.printStackTrace();
					}
				}
			}

			return review;

		} catch (Exception e) {
			throw new NotFoundException("Could not find requested review");
		}
	}

	public Review getOneObj(String id) throws NotFoundException {
		String xml = findByID(id);
		UnmarshallingUtil s = new UnmarshallingUtil();
		Review obj = s.unmarshallReview(xml);
		return obj;
	}
}
