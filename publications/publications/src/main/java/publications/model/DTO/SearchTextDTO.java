package publications.model.DTO;

public class SearchTextDTO {
	private String text;

	public SearchTextDTO() {
		super();
	}

	public SearchTextDTO(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
