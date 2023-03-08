package exam_01;

public class Movie {
	private String title;
	private String genre;
	private double grade;
	
	public Movie() {}
	public Movie(String title, String genre, double grade) {
		this.title = title;
		this.genre = genre;
		this.grade = grade;
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
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	public void myPrint() {
		System.out.println("영화 제목: " + this.title);
		System.out.println("영화 장르: " + this.genre);
		System.out.println("영화 평점: " + this.grade);
	}
	
}
