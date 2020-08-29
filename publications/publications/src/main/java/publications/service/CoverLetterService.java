package publications.service;

import static publications.util.constants.ApplicationConstants.XSLT_FO_PATH_PREFIX;
import static publications.util.constants.ApplicationConstants.XSLT_PATH_PREFIX;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import publications.model.user.User;
import publications.repository.CoverLetterRepository;
import publications.util.marshalling.MarshallUser;
import publications.util.transformations.HTMLTransformer;
import publications.util.transformations.XSLFOTransformer;

@Service
public class CoverLetterService {

	@Autowired
	CoverLetterRepository coverLetterRepository;
	
	@Autowired
	HTMLTransformer htmlTransformer;
	
	@Autowired 
	XSLFOTransformer xslfoTransformer;
	
	public String save(String coverLetter) throws Exception {
		return coverLetterRepository.save(coverLetter);
	}
	
	public String findOneByID(String id) throws Exception {
		return coverLetterRepository.findOneByID(id);
	}
	
	public String getByHTML(String id) throws Exception {
		String coverLetter = findOneByID(id);
		// TODO izraditi xsl fajl
		String res = htmlTransformer.generateHTML(coverLetter, XSLT_PATH_PREFIX+"/CoverLetter.xsl");
		System.out.println(res);
		return res;
	}
	
	public ByteArrayOutputStream getByPDF(String id) throws Exception {
		String coverLetter = findOneByID(id);
		// TODO izraditi xsl fo fajl
		return xslfoTransformer.generatePDF(coverLetter, XSLT_FO_PATH_PREFIX + "/CoverLetter_fo.xsl");
	}
}
