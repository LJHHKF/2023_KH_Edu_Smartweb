
public class Exam_01 {
	public static void main(String[] args) {
		String[] arr = new String[10];
		arr[0] = "Hello";
		arr[1] = "World";
		arr[2] = "Collection";
		
//		System.out.println(arr[0]);
//		System.out.println(arr[1]);
//		System.out.println(arr[2]);
//		
//		//데이터 삭제
		arr[0] = arr[1];
		arr[1] = arr[2];
		arr[2] = null;
//		
//		System.out.println(arr[0]);
//		System.out.println(arr[1]);
//		System.out.println(arr[2]);
		
		//데이터 앞단 삽입
		arr[3] = arr[2];
		arr[2] = arr[1];
		arr[1] = "Java";
		
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
		
		//arr.length; //배열의 총 갯수
					//현재 갯수를 알고 싶으면 별도로 count 하거나 null값이 나올 때 까지 세야 함.
	}
}
