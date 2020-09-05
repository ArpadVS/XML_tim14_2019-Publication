package publications.service;

import static publications.util.constants.ApplicationConstants.SCIENTIFIC_PAPER_XSLT;
import static publications.util.constants.ApplicationConstants.SCIENTIFIC_PAPER_XSLT_FO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.util.ResourceSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import publications.exceptions.NotFoundException;
import publications.model.paper.ScientificPaper;
import publications.model.paper.TPaperStatus;
import publications.model.DTO.PaperViewDTO;
import publications.model.DTO.ScientificPaperDTO;
import publications.model.DTO.SearchDTO;
import publications.model.DTO.SearchTextDTO;
import publications.repository.ScientificPaperRepository;
import publications.util.db.fuseki_jena.FusekiManagement;
import publications.util.transformations.HTMLTransformer;
import publications.util.transformations.XSLFOTransformer;

@Service
public class ScientificPaperService {

	@Autowired
	ScientificPaperRepository scientificPaperRepository;

	@Autowired
	HTMLTransformer htmlTransformer;

	@Autowired
	XSLFOTransformer xslfoTransformer;

	public String save(String scientificPaper) throws Exception {
		return scientificPaperRepository.save(scientificPaper);
	}

	public String update(String scientificPaper, String id) throws Exception {
		return scientificPaperRepository.update(scientificPaper, id);
	}

	public String revise(String scientificPaper, String id) throws Exception {
		return scientificPaperRepository.revise(scientificPaper, id);
	}

	public String findByID(String id) throws Exception {
		return scientificPaperRepository.findByID(id);
	}

	public String getByHTML(String id) throws Exception {
		String scientificPaper = scientificPaperRepository.findByID(id);
		String res = htmlTransformer.generateHTML(scientificPaper, SCIENTIFIC_PAPER_XSLT);
		return res;
	}

	public ByteArrayOutputStream getByPDF(String id) throws Exception {
		String scientificPaper = scientificPaperRepository.findByID(id);
		return xslfoTransformer.generatePDF(scientificPaper, SCIENTIFIC_PAPER_XSLT_FO);
	}

	public String findByTitle(String text) throws NotFoundException {
		String xPathExpression = String.format("//scientificPaper[title='%s']", text);
		// TODO ispisati expression za pretragu.
		// Potrebno je da u nekom tekstualnom delu (naslov, podnaslov,
		// paragraf...) sadrzi prosledjeni tekst
		String scientificPaper = scientificPaperRepository.findByExpression(xPathExpression);
		return scientificPaper;
	}

	public String findByAuthor(String author) throws NotFoundException {
		// TODO ispisati expression za pretragu.
		// Potrebno je pronaci naucni rad po datom autoru
		String scientificPaper = scientificPaperRepository.findByExpression("");
		return scientificPaper;
	}

	public ArrayList<ScientificPaperDTO> getAll() {
		ArrayList<ScientificPaperDTO> all = scientificPaperRepository.findMultipleByExpression("/");
		return all;
	}

	public ArrayList<ScientificPaperDTO> getAllByStatus(String status) {
		String xPathExpression = String.format("//scientificPaper[@status='%s']", status);
		ArrayList<ScientificPaperDTO> all = scientificPaperRepository.findMultipleByExpression(xPathExpression);
		return all;
	}

	public ArrayList<ScientificPaperDTO> getAllForReview() {
		String xPathExpression = String.format("//scientificPaper[@status='%s' or @status='%s']", "toBeReviewed",
				"revisionDone");
		ArrayList<ScientificPaperDTO> all = scientificPaperRepository.findMultipleByExpression(xPathExpression);
		return all;
	}

	public ScientificPaperDTO getOne(String id) throws NotFoundException {
		return scientificPaperRepository.getOneObj(id);
	}

	public ArrayList<String> searhByMetadata(SearchDTO dto) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("title", dto.getTitle());
		params.put("language", dto.getLanguage());
		params.put("recievedDate", dto.getDate());
		params.put("author", dto.getAuthors());
		params.put("keyword", dto.getKeywords());
		System.out.println(params.toString());
		ArrayList<String> titles = FusekiManagement.executeQuery(params);
		return titles;
	}

	private String getLoggedUser() {
		String username = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			username = authentication.getName();
		}
		return username;
	}

	public String updateStatusInReviewProcess(String id) throws Exception {
		return scientificPaperRepository.updateStatus(TPaperStatus.IN_REVIEW_PROCESS, id);
	}

	public String updateStatusReviewed(String id) throws Exception {
		return scientificPaperRepository.updateStatus(TPaperStatus.REVIEWED, id);
	}

	public String updateStatusAccept(String id) throws Exception {
		return scientificPaperRepository.updateStatus(TPaperStatus.ACCEPTED, id);
	}

	public String updateStatusReject(String id) throws Exception {
		return scientificPaperRepository.updateStatus(TPaperStatus.REJECTED, id);
	}

	public String updateStatusRevisionNeeded(String id) throws Exception {
		return scientificPaperRepository.updateStatus(TPaperStatus.REVISION_NEEDED, id);
	}

	public List<ScientificPaperDTO> searchByText(SearchTextDTO dto) {
		String text= dto.getText();
		String expression = String.format(
				"//scientificPaper[title[contains(text(), '%s')] or keywords/keyword[contains(text(), '%s')]"
				+ " or abstract/paragraph[contains(text(), '%s')] or content/chapter/title[contains(text(), '%s')] "
				+ "or content/chapter/paragraph[contains(text(), '%s')]]", text,  text,  text,  text,  text);
		ArrayList<ScientificPaperDTO> result= scientificPaperRepository.findMultipleByExpression(expression);
		return result;
	}

}
