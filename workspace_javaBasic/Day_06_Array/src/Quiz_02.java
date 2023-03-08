import java.util.Scanner;

public class Quiz_02 {
	public static void main(String[] args) {
		//3명의 학생의 이름/국어/영어를 입력받아 합계/평균 내기.
		
		Scanner sc = new Scanner(System.in);
		
		int size = 3;
		String[] names = new String[size];
		int[] s_kors = new int[size];
		int[] s_engs = new int[size];
		
		//입력
		for(int i = 0; i < size; i++) {
			System.out.print((i+1) +" 번째 학생 이름 : ");
			names[i] = sc.nextLine();
			System.out.print(names[i] + " 학생 국어 : ");
			s_kors[i] = Integer.parseInt(sc.nextLine());
			System.out.print(names[i] + " 학생 영어 : ");
			s_engs[i] = Integer.parseInt(sc.nextLine());
		}
		
		//출력
		System.out.println("이름\t국어\t영어\t 합계\t 평균");
		for(int i = 0; i < size; i++) {
			System.out.print(names[i]+"\t"
							+s_kors[i]+"\t"
							+s_engs[i]+"\t"
							+(s_kors[i] + s_engs[i])+"\t"
							+((s_kors[i] + s_engs[i]) / 2.0f));
			System.out.println();
		}
		
	}
}
