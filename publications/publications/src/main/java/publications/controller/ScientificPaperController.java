package publications.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import publications.exceptions.NotFoundException;
import publications.model.DTO.ScientificPaperDTO;
import publications.model.DTO.SearchDTO;
import publications.model.DTO.SearchTextDTO;
import publications.model.DTO.SubmitPaperLetterDTO;
import publications.model.user.User;
import publications.service.CoverLetterService;
import publications.service.ScientificPaperService;
import publications.service.UserService;

@RestController
@RequestMapping("/api/scientificPaper")
public class ScientificPaperController {

	@Autowired
	ScientificPaperService scientificPaperService;
	
	@Autowired
	CoverLetterService coverLetterService;
	
	@Autowired
	UserService userService;
	
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
	
	@PreAuthorize("hasAnyRole('REVIEWER', 'AUTHOR', 'EDITOR')")
	@GetMapping(value="/myPapers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> geByLoggedUser(Principal user) throws Exception{
		User found = userService.findByUsername(user.getName());
		return new ResponseEntity<>(scientificPaperService.getByLoggedUser(found), HttpStatus.OK);
	}
	
	
	@GetMapping(value="/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> geAllByStatus(@PathVariable(value = "status") String status) throws Exception{
		return new ResponseEntity<>(scientificPaperService.getAllByStatus(status), HttpStatus.OK);
	}

	
	@GetMapping(value = "/html/{id}", produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<?> getScientificPaperHTML(@PathVariable(value = "id") String id) throws Exception{
		return new ResponseEntity<>(scientificPaperService.getByHTML(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getScientificPaperPDF(@PathVariable(value = "id") String id) throws Exception{
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
		//String jsonRetVal = "{\"paper_id\": \"" + paper_id + "\"}"; //ako posaljem samo id, na frontu ne moze da parsira string???
		//System.out.println(jsonRetVal);
		return new ResponseEntity<>(ids, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('EDITOR')")
	@GetMapping(value = "/forReview", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPapersForReview(){
		return new ResponseEntity<>(scientificPaperService.getAllForReview(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('AUTHOR','REVIEWER', 'EDITOR')")
	@PutMapping(value="/revise/{id}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> revise(@PathVariable(value = "id") String id, @RequestBody String revision) throws Exception{
		return new ResponseEntity<>(scientificPaperService.revise(revision, id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('AUTHOR','REVIEWER', 'EDITOR')")
	@PutMapping(value="/update/{id}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable(value = "id") String id, @RequestBody String update) throws Exception{
		return new ResponseEntity<>(scientificPaperService.update(update, id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('REVIEWER', 'EDITOR')")
	@PutMapping(value="/accept/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> accept(@PathVariable(value = "id") String id) throws Exception{
		return new ResponseEntity<>(scientificPaperService.updateStatusAccept(id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('REVIEWER', 'EDITOR')")
	@PutMapping(value="/reject/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> reject(@PathVariable(value = "id") String id) throws Exception{
		return new ResponseEntity<>(scientificPaperService.updateStatusReject(id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('REVIEWER', 'EDITOR')")
	@PutMapping(value="/reviewed/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> reviewed(@PathVariable(value = "id") String id) throws Exception{
		return new ResponseEntity<>(scientificPaperService.updateStatusReviewed(id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('REVIEWER', 'EDITOR')")
	@PutMapping(value="/revisionNeeded/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> revisionNeeded(@PathVariable(value = "id") String id) throws Exception{
		return new ResponseEntity<>(scientificPaperService.updateStatusRevisionNeeded(id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('REVIEWER', 'EDITOR')")
	@PutMapping(value="/inReviewProcess/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> inReviewProcess(@PathVariable(value = "id") String id) throws Exception{
		return new ResponseEntity<>(scientificPaperService.updateStatusInReviewProcess(id), HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/searchByMetadata")
	public ResponseEntity<List<String>> searchByMetadata(@RequestBody SearchDTO dto) throws IOException {
		List<String> result = scientificPaperService.searhByMetadata(dto);
		return new ResponseEntity<List<String>>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/searchByText")
	public ResponseEntity<?> searchByText(@RequestBody SearchTextDTO dto) throws IOException {
		return new ResponseEntity<>(scientificPaperService.searchByText(dto), HttpStatus.OK);
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
