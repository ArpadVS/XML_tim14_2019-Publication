package publications.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import publications.exceptions.NotFoundException;
import publications.model.paper.ScientificPaper;
import publications.model.user.DTO.ScientificPaperDTO;
import publications.model.user.DTO.SubmitPaperLetterDTO;
import publications.service.CoverLetterService;
import publications.service.ScientificPaperService;

@RestController
@RequestMapping("/api/scientificPaper")
public class ScientificPaperController {

	@Autowired
	ScientificPaperService scientificPaperService;
	
	@Autowired
	CoverLetterService coverLetterService;
	
	@GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> getById(@PathVariable(value = "id") String id) throws Exception{
		return new ResponseEntity<>(scientificPaperService.findByID(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/obj/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScientificPaperDTO> getObjById(@PathVariable(value = "id") String id) throws Exception{
		return new ResponseEntity<>(scientificPaperService.getOne(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/title/{title}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<?> getByTitle(@PathVariable(value = "title") String title) throws NotFoundException{
		return new ResponseEntity<>(scientificPaperService.findByTitle(title), HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> geAll() throws Exception{
		return new ResponseEntity<>(scientificPaperService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(value="/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> geAllByStatus(@PathVariable(value = "status") String status) throws Exception{
		return new ResponseEntity<>(scientificPaperService.getAllByStatus(status), HttpStatus.OK);
	}

	
	@GetMapping(value = "/html/{id}", produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<?> getScientificPaperHTML(@PathVariable(value = "id") String id) throws Exception{
		// TODO nedostaje xslt fajl
		return new ResponseEntity<>(scientificPaperService.getByHTML(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getScientificPaperPDF(@PathVariable(value = "id") String id) throws Exception{
		// TODO nedostaje xsl_fo fajl
		return new ResponseEntity<>(scientificPaperService.getByPDF(id).toByteArray(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('REVIEWER', 'AUTHOR', 'EDITOR')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create1(@RequestBody SubmitPaperLetterDTO dto) throws Exception{
		String paper_id = scientificPaperService.save(dto.getPaper());
		String letter_id = coverLetterService.save(dto.getLetter());
		ArrayList<String> ids = new ArrayList<>();
		ids.add(paper_id);
		ids.add(letter_id);
		String jsonRetVal = "{\"paper_id\": \"" + paper_id + "\"}"; //ako posaljem samo id, na frontu ne moze da parsira string???
		System.out.println(jsonRetVal);
		return new ResponseEntity<>(ids, HttpStatus.CREATED);
	}
	
	/*@PreAuthorize("hasAnyRole('REVIEWER', 'AUTHOR', 'EDITOR')")
	@PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> create(@RequestBody String scientificPaper) throws Exception{
		String id = scientificPaperService.save(scientificPaper);
		String jsonRetVal = "{\"id\": \"" + id + "\"}"; //ako posaljem samo id, na frontu ne moze da parsira string???
		System.out.println(jsonRetVal);
		return new ResponseEntity<>(jsonRetVal, HttpStatus.CREATED);
	}*/
}
