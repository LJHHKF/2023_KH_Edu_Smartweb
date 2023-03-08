
public class Exam_02 {
	public static void main(String[] args) {
//		for(int i = 1; i <= 10; i++){
//			if(i == 5) {
//				continue;
//			}
//			System.out.println(i);
//		}
		
		for(int i = 1; i <= 10; i++) {
			if(i == 2 || i == 5) {continue;}
			System.out.println("i = " + i);
			if(i == 8) {break;}
		}
	}
}
