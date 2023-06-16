package kh.spring.dto;

public class MoviesDTO {

	private int id;
	private String title;
	private String genre;
	public MoviesDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MoviesDTO(int id, String title, String genre) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	@Override
	public String toString() {
		return "MoviesDTO [id=" + id + ", title=" + title + ", genre=" + genre + "]";
	}
	
	
}
