
public class Quiz_01 {
	
	public static int countA(String str, char ch) {
		int result = 0;
		char ch2 = 0;
		if(Character.isUpperCase(ch)) {
			ch2 = Character.toLowerCase(ch);
		}else {
			ch2 = Character.toUpperCase(ch);
		}
		
		String[] arr = str.split(Character.toString(ch));
		String[] arr2 = str.split(Character.toString(ch2));
		
		//마지막 글자가 구분자이면 하나 빼먹어지므로, 개별 계산.
		if(str.endsWith(Character.toString(ch)) || str.endsWith(Character.toString(ch2))) { 
			result += 1;
		}
		
		//문자열 모두가 구분자로만 구성되서 완전히 제거된 경우.
		//문자열이 모두 해당한다는거니 길이를 반환하면 됨.
		if(arr.length == 0 || arr2.length == 0) { 
			return str.length();
		}
		else { //보통의 경우
			//없어도 통짜는 하나 나옴.
			//result로 따로 체크하는 이유는 맨마지막 글자 포함 계산 위해서.
			result += (arr.length - 1) + (arr2.length - 1); 
			return result; 
		}
	}
	
	public static int countB(String str, char ch) {
		int count = 0;
		char ch2 = 0;
		if(Character.isUpperCase(ch)) {
			ch2 = Character.toLowerCase(ch);
		}else {
			ch2 = Character.toUpperCase(ch);
		}
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == ch || str.charAt(i) == ch2) {
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		
		//특수문자나 숫자 안 들어가게, 영어만 마구 쳐볼 것.
		String str = "awerlnqpwruadlnfmcznvihriatjklarklnghfaklhiahuihjkqbrjkhakdhbvkjhuzxhueraadfbvzxgcqkr";
		
		String test = "abacadaeaf"; //5
		String test2 = "sdfghjl";
		String test3 = "aaa";
		String test4 = "abacada";
		String test5 = "AAA";
		
		int count = countA(str, 'a');
		System.out.println("알파벳 A의 개수 : " + count);
		
	}
}
