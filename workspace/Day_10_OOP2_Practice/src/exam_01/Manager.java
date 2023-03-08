package exam_01;

import java.util.ArrayList;

public class Manager {
	private ArrayList<Movie> movies = new ArrayList<>();
	
	public void addMovie(Movie movie) {
		movies.add(movie);
	}
	
	public void updateMovie(int targetIndex, Movie newMovie) {
		movies.set(targetIndex, newMovie);
	}
	
	public void deleteMovie(int index) {
		movies.remove(index);
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}
	
	public ArrayList<Movie> searchMultiContainsToTitle(String title) {
		ArrayList<Movie> result = new ArrayList<Movie>();
		for(Movie m : movies) {
			if(m.getTitle().contains(title)){
				// equals : 정확히 일치하는 것 찾기
				// contains : 포함된 것 찾기
				//검색하려는 제목이 영화제목에 포함된 것들은, result ArrayList에 옮겨 저장.
				result.add(m);
			}
		}
		return result;
	}
	
	public int getIndexByTitle(String title) {
		for(int i = 0; i < movies.size(); i++) {
			if(movies.get(i).getTitle().equals(title)) {
				return i;
			}
		}
		return -1;
	}
	
//	public Movie searchSingleEqualsToTitle(String title) {		
//		for(Movie m : movies) {
//			if(m.getTitle().equals(title)) {
//				return m; //제목이 같은 영화를 찾아낸다면, 가장 먼저 찾아낸 Movie instance를 반환
//			}
//		}
//		return null;
//	}
	

}
