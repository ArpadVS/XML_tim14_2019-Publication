package publications.model.DTO;

import java.util.ArrayList;

import publications.model.paper.TAuthor;

public class ScientificPaperDTO {

	private String id;
	private String title;
	private ArrayList<String> authors;
	public ScientificPaperDTO(String id, String title, ArrayList<String> authors) {
		super();
		this.id = id;
		this.title = title;
		this.authors = authors;
	}
	public ScientificPaperDTO() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<String> getAuthors() {
		if (this.authors == null) {
            this.authors = new ArrayList<>();
        }
        return this.authors;
	}
	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}
	
	
	
}
