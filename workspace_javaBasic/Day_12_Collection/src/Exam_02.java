import java.util.ArrayList;

public class Exam_02 {
	public static void main(String[] args) {
		ArrayList<String> arr = new ArrayList<>();
		arr.add("Hello");
		arr.add("World");
		arr.add("Collection");
		
		//다형성
//		arr.add(10);
//		arr.add(3.14);
		//...
		
//		System.out.println(arr.get(0));
//		System.out.println(arr.get(1));
//		System.out.println(arr.get(2));
//		
		arr.remove(0);
//		
//		System.out.println(arr.get(0));
//		System.out.println(arr.get(1));
		
		arr.add(1, "Java");
		
//		System.out.println(arr.get(0));
//		System.out.println(arr.get(1));
//		System.out.println(arr.get(2));
		
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
		for(String a : arr) {
			System.out.println(a);
		}
		
		//System.out.println( ((String)arr.get(2)).length() ) ;
//		System.out.println(arr.get(2).length());
		
		arr.size(); //현재 크기(갯수)
	}
}
