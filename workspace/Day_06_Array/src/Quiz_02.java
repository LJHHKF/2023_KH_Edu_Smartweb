import java.util.Scanner;

public class Quiz_02 {
	public static void main(String[] args) {
		//3���� �л��� �̸�/����/��� �Է¹޾� �հ�/��� ����.
		
		Scanner sc = new Scanner(System.in);
		
		int size = 3;
		String[] names = new String[size];
		int[] s_kors = new int[size];
		int[] s_engs = new int[size];
		
		//�Է�
		for(int i = 0; i < size; i++) {
			System.out.print((i+1) +" ��° �л� �̸� : ");
			names[i] = sc.nextLine();
			System.out.print(names[i] + " �л� ���� : ");
			s_kors[i] = Integer.parseInt(sc.nextLine());
			System.out.print(names[i] + " �л� ���� : ");
			s_engs[i] = Integer.parseInt(sc.nextLine());
		}
		
		//���
		System.out.println("�̸�\t����\t����\t �հ�\t ���");
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
