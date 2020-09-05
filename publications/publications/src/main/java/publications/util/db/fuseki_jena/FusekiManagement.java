package publications.util.db.fuseki_jena;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

import publications.util.db.fuseki_jena.AuthenticationUtilitiesFuseki.ConnectionPropertiesFuseki;
//import static publications.util.constants.ApplicationConstants.RDF_FILE_PATH;

public class FusekiManagement {

	private static final String RDF_FILEPATH = "src/main/resources/data/rdf/paper_metadata.rdf";
	private static final String PAPERS_METADATA_GRAPH_URI = "/papersMetadata";
	private static final String QUERY_FILEPATH = "src/main/resources/data/rdf/searchQuery.rq";
	

	public static void saveRDF() throws IOException {

		System.out.println("[INFO] Loading triples from an RDF/XML to a model...");

		ConnectionPropertiesFuseki conn = AuthenticationUtilitiesFuseki.loadProperties();
		// RDF triples which are to be loaded into the model

		// Creates a default model
		Model model = ModelFactory.createDefaultModel();
		model.read(RDF_FILEPATH);

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
		System.out.println("[INFO] Writing the triples to a named graph \"" + PAPERS_METADATA_GRAPH_URI + "\".");
		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + PAPERS_METADATA_GRAPH_URI,
				new String(out.toByteArray()));
		System.out.println(sparqlUpdate);

		// UpdateRequest represents a unit of execution
		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();

	}
	

	public static ArrayList<String> executeQuery( Map<String, String> params) throws IOException {
		
		ConnectionPropertiesFuseki conn = AuthenticationUtilitiesFuseki.loadProperties();
		
		String sparqlQueryTemplate = readFile(QUERY_FILEPATH, StandardCharsets.UTF_8);
		System.out.println("Query: " + sparqlQueryTemplate);
		String sparqlQuery = StringSubstitutor.replace(sparqlQueryTemplate, params, "{{", "}}");
		System.out.println("Query: " + sparqlQuery);
		
		// Create a QueryExecution that will access a SPARQL service over HTTP
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
		// Query the SPARQL endpoint, iterate over the result set...
		ResultSet results = query.execSelect();
		
		String varName;
		RDFNode varValue;
		ArrayList<String> foundTitles = new ArrayList<String>();
		
		while(results.hasNext()) {
		    
			// A single answer from a SELECT query
			QuerySolution querySolution = results.next() ;
			Iterator<String> variableBindings = querySolution.varNames();
			
			// Retrieve variable bindings
		    while (variableBindings.hasNext()) {
		    	varName = variableBindings.next();
		    	varValue = querySolution.get(varName);
		    	System.out.println(varName + ": " + varValue);
		    	if (varName.contains("title")) {
		    		String value = varValue.toString();
			    	value = value.substring(0, value.lastIndexOf("^")-1);
			    	if (!foundTitles.contains(value)) {
			    		foundTitles.add(value);
			    	}
		    	}
		    }
		    System.out.println();
		}
		
	    ResultSetFormatter.outputAsXML(System.out, results);
		query.close() ;
		System.out.println("[INFO] SPARQL Query End.");
		return foundTitles;
	}
	
	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	public static void deleteRDF() throws Exception
    {
        ConnectionPropertiesFuseki conn = AuthenticationUtilitiesFuseki.loadProperties();
        String spargDelete = SparqlUtil.dropGraph(conn.dataEndpoint +  PAPERS_METADATA_GRAPH_URI);
        
		// UpdateRequest represents a unit of execution
		UpdateRequest update = UpdateFactory.create(spargDelete);

		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();
    }
}
