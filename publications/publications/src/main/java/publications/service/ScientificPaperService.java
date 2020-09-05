package publications.service;

import static publications.util.constants.ApplicationConstants.SCIENTIFIC_PAPER_XSLT;
import static publications.util.constants.ApplicationConstants.SCIENTIFIC_PAPER_XSLT_FO;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import publications.exceptions.NotFoundException;
import publications.model.paper.ScientificPaper;
import publications.model.paper.TPaperStatus;
import publications.model.user.DTO.ScientificPaperDTO;
import publications.repository.ScientificPaperRepository;
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
		// Potrebno je da u nekom tekstualnom delu (naslov, podnaslov, paragraf...) sadrzi prosledjeni tekst
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
		String xPathExpression = String.format("//scientificPaper[@status='%s' or @status='%s']", "toBeReviewed", "revisionDone");
		ArrayList<ScientificPaperDTO> all = scientificPaperRepository.findMultipleByExpression(xPathExpression);
		return all;
	}
	
	
	public ScientificPaperDTO getOne(String id) throws NotFoundException {
		return scientificPaperRepository.getOneObj(id);
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
	
	
}
