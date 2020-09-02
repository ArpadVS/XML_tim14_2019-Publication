package publications.service;

import static publications.util.constants.ApplicationConstants.XSLT_FO_PATH_PREFIX;
import static publications.util.constants.ApplicationConstants.XSLT_PATH_PREFIX;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import publications.exceptions.NotFoundException;
import publications.repository.ReviewRepository;
import publications.util.transformations.HTMLTransformer;
import publications.util.transformations.XSLFOTransformer;

@Service
public class ReviewService {

	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	HTMLTransformer htmlTransformer;
	
	@Autowired 
	XSLFOTransformer xslfoTransformer;
	
	public String save(String review) throws Exception {
		return reviewRepository.save(review);
	}
	
	public String update(String review, String id) throws Exception {
		return reviewRepository.update(review, id);
	}
	
	public String findByID(String id) throws Exception {
		return reviewRepository.findByID(id);
	}
	
	public String getByHTML(String id) throws Exception {
		String review = reviewRepository.findByID(id);
		String res = htmlTransformer.generateHTML(review, XSLT_PATH_PREFIX+"/ScientificPaper.xsl");
		return res;
	}
	
	public ByteArrayOutputStream getByPDF(String id) throws Exception {
		String review = reviewRepository.findByID(id);
		return xslfoTransformer.generatePDF(review, XSLT_FO_PATH_PREFIX + "/ScientificPaper_fo.xsl");
	}
	
	
}
