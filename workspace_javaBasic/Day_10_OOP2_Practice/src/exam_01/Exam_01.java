package exam_01;

public class Exam_01 {
	public static void main(String[] args) {
		
		//Movie 클래스 설계
		//영화제목 , 장르, 평점
		//정보 은닉 적용 후 getter / setter / constructor 모두 작성
		
//		Movie m1 = new Movie("아바타2", "SF/액션", 8.82);
//		Movie m2 = new Movie("영웅", "드라마", 8.42);
		
		//System.out.println(m1.getTitle() + " : " + m1.getGenre() + " : " + m1.getGrade());		
		//System.out.println(m2.getTitle() + " : " + m2.getGenre() + " : " + m2.getGrade());
//		m1.myPrint();
//		System.out.println();
//		m2.myPrint();
		
//		m1.setTitle("아바타:물의 길");
//		//System.out.println(m1.getTitle() + " : " + m1.getGenre() + " : " + m1.getGrade());
//		System.out.println();
//		m1.myPrint();
		
		//Movie[] movies = new Movie[2]; //이 단계에선 Movie 인스턴스가 만들어진 것이 아님.
										//담아놓을 배열의(동일한 타입인 변수들의 집합의) 자리만 잡아둔 것.
//		movies[0] = new Movie("아바타2", "SF/액션", 8.82);
//		movies[1] = new Movie("영웅", "드라마", 8.42);
		Movie[] movies = new Movie[] {
				new Movie("아바타2", "SF/액션", 8.82),
				new Movie("영웅", "드라마", 8.42)
		};
		
		movies[1].setGrade(10.0);
		
		for(int i = 0; i < movies.length; i++) {
			//movies[i].myPrint();
			//System.out.println();
			
			System.out.println(movies[i].getTitle() + "\t" +
								movies[i].getGenre() + "\t" +
								movies[i].getGrade());
		}
	}
}
