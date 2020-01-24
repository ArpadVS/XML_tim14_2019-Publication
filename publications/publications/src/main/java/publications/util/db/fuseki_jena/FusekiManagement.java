package publications.util.db.fuseki_jena;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

import publications.util.db.fuseki_jena.AuthenticationUtilitiesFuseki.ConnectionPropertiesFuseki;
//import static publications.util.constants.ApplicationConstants.RDF_FILE_PATH;

public class FusekiManagement {

	public void save(String rdfFilePath, String graphUri) throws IOException {

		System.out.println("[INFO] Loading triples from an RDF/XML to a model...");

		ConnectionPropertiesFuseki conn = AuthenticationUtilitiesFuseki.loadProperties();
		// RDF triples which are to be loaded into the model

		// Creates a default model
		Model model = ModelFactory.createDefaultModel();
		model.read(rdfFilePath);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		model.write(out, SparqlUtil.NTRIPLES);

		System.out.println("[INFO] Rendering model as RDF/XML...");
		model.write(System.out, SparqlUtil.RDF_XML);

		// Delete all of the triples in all of the named graphs
		UpdateRequest request = UpdateFactory.create();
		request.add(SparqlUtil.dropAll());

		/*
		 * Create UpdateProcessor, an instance of execution of an UpdateRequest.
		 * UpdateProcessor sends update request to a remote SPARQL update service.
		 */
		UpdateProcessor processor = UpdateExecutionFactory.createRemote(request, conn.updateEndpoint);
		processor.execute();

		// Creating the first named graph and updating it with RDF data
		System.out.println("[INFO] Writing the triples to a named graph \"" + graphUri + "\".");
		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + graphUri,
				new String(out.toByteArray()));
		System.out.println(sparqlUpdate);

		// UpdateRequest represents a unit of execution
		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();

	}
}
