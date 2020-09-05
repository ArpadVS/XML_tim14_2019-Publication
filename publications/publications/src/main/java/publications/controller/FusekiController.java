package publications.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import publications.util.db.fuseki_jena.FusekiManagement;

@RestController
@RequestMapping("/api/fuseki")
public class FusekiController {
	
	@GetMapping(value = "/saveRDF")
	public void saveRdf() throws IOException {
		FusekiManagement.saveRDF();
	}
	
}
