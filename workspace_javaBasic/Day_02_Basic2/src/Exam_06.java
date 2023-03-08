
public class Exam_06 {
	public static void main(String[] args) throws Exception{
		
		System.out.print("한 글자를 입력해주세요 : ");
		
		int input = System.in.read();
		
		//System.out.println("입력하신 문자 " + (char)input +" 의 아스키코드 10진수는 " + input + " 입니다.");		
		
		System.out.println("입력한 문자는 : " + (char)input);
		
		System.in.read();
		System.in.read();
		
		//1번 더 (기본 안됨)
		//why? 입력 버퍼에 의한 입력 무시 현상.
		System.out.print("한 글자를 더 입력해주세요 : ");
		input = System.in.read();
		System.out.println("입력한 문자는 :" + (char)input);
		
		//1. System.in.read()는 버퍼가 비어있으면, 가져갈 코드가 없으니 입력으로 턴을 넘기고 기다림. 
		//2. 사람 입력 A는 버퍼에 'A \r \n'  으로 저장됨. 
		//3. 여기서 맨 앞의 A를 System.in.read()가 읽어들여 가져감. 
		//4. 두번째의 System.in.read()가 실행되면, 버퍼를 먼저 찾는데, 가져갈 문자가 이미 있으니 입력으로 대기를 넘길 것도 없이 그냥 바로 꺼내 감.
			//\r을 가져간 것. 
				//눈에 보이는 아이가 아닌 기능 문자. 볼려면 (char) 캐스팅을 빼면, ASCII int 값이 나옴.
		//5. 버퍼에 이미 \n이 남아있으므로 3번째 System.in.read()가 불려도 마찬가지로 동작하지 못 함. 
		
	}
}
