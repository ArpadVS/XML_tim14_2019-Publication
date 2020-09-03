package publications.service;

import static publications.util.constants.ApplicationConstants.*;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;

import publications.exceptions.NotFoundException;
import publications.model.paper.TScientificPaper;
import publications.model.user.User;
import publications.model.user.DTO.ScientificPaperDTO;
import publications.repository.ScientificPaperRepository;
import publications.util.marshalling.MarshallUser;
import publications.util.marshalling.UnmarshallingUtil;
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
		ArrayList<ScientificPaperDTO> all = scientificPaperRepository.getAll();
		return all;
	}
	
	public TScientificPaper getOne(String id) throws NotFoundException {
		return scientificPaperRepository.getOneObj(id);
	}
}
