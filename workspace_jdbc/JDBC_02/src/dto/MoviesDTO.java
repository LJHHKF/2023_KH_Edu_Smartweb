package dto;

public class MoviesDTO {
	private int id;
	private String title;
	private String genre;
	
	public MoviesDTO() {}
	public MoviesDTO(int id, String title, String genre) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
	}
	
	public int getId() {return id;}
	public String getTitle() {return title;}
	public String getGenre() {return genre;}
	public void setId(int id) {this.id = id;}
	public void setTitle(String title) {this.title = title;}
	public void setGenre(String genre) {this.genre = genre;}
}