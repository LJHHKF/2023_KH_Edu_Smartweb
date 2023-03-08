
public class Quiz_02 {
	public static void main(String[] args) {
		// 3-1 : 1325와 9327을 메모리에 저장하고, 두 수를 곱한 결과를 출력하세요.
		int num1 = 1325,
			num2 = 9327;
		System.out.println(num1 * num2);
		
		// 3-2 : 100억과 5000을 더하여 메모리에 저장하고 출력하세요.
		long sum = 10000000000L + 5000;
		System.out.println(sum); // 묵인 Long 업캐스팅
		
		// 3-3 : 'A'와 'B를 메모리에 저장하고 화면에 출력하세요.
		char a = 'A',
			 b = 'B';
		System.out.print(a);
		System.out.println(b);
	}
}
