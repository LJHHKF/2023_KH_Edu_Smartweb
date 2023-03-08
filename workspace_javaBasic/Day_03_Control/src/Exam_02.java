
public class Exam_02 {
	public static void main(String[] args) {
		int i = 49; // 초기값 지정
		
		while(i<100) // 조건문
		{
			i++; //증감문
			
			//또는
			//i += 2; & 조건문 '<100' 이 아닌 '<99' 변화.
			
			if(i % 2 == 1)
			{
				System.out.println("i = " + i);
			}
		}
	}
}
