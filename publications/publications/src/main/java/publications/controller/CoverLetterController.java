package publications.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import publications.exceptions.NotFoundException;
import publications.model.user.User;
import publications.service.CoverLetterService;

@RestController
@RequestMapping("/api/coverLetter")
public class CoverLetterController {
	
	@Autowired
	CoverLetterService coverLetterService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> getCoverLetterById(@PathVariable(value = "id") String id) throws Exception{
		return new ResponseEntity<>(coverLetterService.findOneByID(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/html/{id}", produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<?> getCoverLetterHTML(@PathVariable(value = "id") String id) throws Exception{
		return new ResponseEntity<>(coverLetterService.getByHTML(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getCoverLetterPDF(@PathVariable(value = "id") String id) throws Exception{
		return new ResponseEntity<>(coverLetterService.getByPDF(id).toByteArray(), HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> create(@RequestBody String coverLetter) throws Exception{
		return new ResponseEntity<>(coverLetterService.save(coverLetter), HttpStatus.CREATED);
	}

}
