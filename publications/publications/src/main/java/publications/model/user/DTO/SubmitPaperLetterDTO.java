package publications.model.user.DTO;

public class SubmitPaperLetterDTO {
	private String paper;
	private String letter;
	public SubmitPaperLetterDTO(String paper, String letter) {
		super();
		this.paper = paper;
		this.letter = letter;
	}
	public SubmitPaperLetterDTO() {
		super();
	}
	public String getPaper() {
		return paper;
	}
	public void setPaper(String paper) {
		this.paper = paper;
	}
	public String getLetter() {
		return letter;
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
	
	

}
