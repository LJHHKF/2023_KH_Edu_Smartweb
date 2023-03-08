
public class Exam_01 {
	
	//메서드명 왼쪽은 return 데이터 타입을 입력해야 한다.
	//오른쪽은 매개변수
	//내부에는 동작
	//이하는 '메서드 정의부'(definition)
	//일단은 메서드를 만들면 무작정 덮어놓고, 'public static'을 붙여줄 것. 
	public static int plus(int num1, int num2){
		int result = num1 + num2;
		return result;
	}
	
	//두 정수를 인자로 전달받아 발생한 결과를 반환하는 minus 메서드
	public static int minus(int num1, int num2) {
		return num1 - num2;
	}
	
	//두 정수를 인자로 전달받아 나눗셈한 결과를 실수로 반환하는 divide 메서드
	public static double divide(int num1, int num2) {
		return (double)num1 / num2;
	}
	
	//두 정수를 인자로 전달받아 더 큰 수를 반환하는 bigger 메서드
	//(두 수가 같다면 0을 반환)
	public static int bigger(int num1,int num2) {
		if(num1 > num2) {
			return num1;
		}else if(num1 < num2) {
			return num2;
		}else {
			return 0;
		}
	}
	
	//"사과"를 인자로 전달하면 Apple 을, "딸기"를 인자로 전달하면 Strawberry 를
	//반환하는 translate 메서드 제작
	//(사과나 딸기가 아닌 다른 값이 전달되면 none 반환)
	public static String translate(String word) {
		if(word.equals("사과")) {
			return "Apple";
		}else if(word.equals("딸기")) {
			return "Strawberry";
		}else {
			return "none";
		}
			
	}
	
	public static void main(String[] args) {
		System.out.println(plus(10,5)); //메서드 호출부
		int result = minus(10,5);
		System.out.println(result);
		System.out.println(divide(10,5));
		System.out.println(bigger(10,5));
		System.out.println(translate("사과"));
		System.out.println(translate("딸기"));
		System.out.println(translate("포도"));
		
		//plus method에 전달하는 10과 5를 인자값(또는 인수값, 매개변수) 이라고 부른다.
		//영어로는 parameter, argument
		//메서드에 전달되어지는 값을 받는 변수를 매개변수라고 부른다. (영어 이름은 동일)
		//메서드의 반환 값은 메서드가 call 된 지점으로 return 된다.
	}
	
}
