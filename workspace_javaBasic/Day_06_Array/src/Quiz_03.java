
public class Quiz_03 {
	public static void main(String[] args) {
		//1~45 ������ ������ ��ø���� �ʰ� 7���� �̾Ƽ� ����ϼ���.
		// ������ ��÷ �ζ� ��ȣ
		
		//�ʱ�ȭ
		int[] nums = new int[45];
		for(int i = 0; i < nums.length; i++) {
			nums[i] = i + 1;
		}
		
		//ī�� ���� �˰���
		for(int i = 0; i < nums.length * 10; i++) {
			int rand1 = (int)(Math.random() * nums.length);
			int rand2 = (int)(Math.random() * nums.length);
			
			int temp = nums[rand1];
			nums[rand1] = nums[rand2];
			nums[rand2] = temp;
		}
		
		//���
		for(int i = 0; i < 7; i++) {
			System.out.print(nums[i] + ", ");
		}
		
		//�ָ��� �߰������� �����غ��� ���� ����,
		//�̰� ��� �ݺ��ϸ鼭 1���� �� ������ �������鼭
		//n���� n�� ���, �� n�� �����ߴٸ� ���� Ȯ�� ����ϴ� �ùķ����ͷ� �������Ѻ� ��.
	}
}
