
public class Exam_06 {
	public static void main(String[] args) {
		//1~5 ������ ���ڰ� �ְ�, �� �� 3���� ���ڸ� �̾Ƴ� ��.
		//�����ϰ� 3��. ���� ���� �� ��.
		
		//�ߺ� ������ ���
//		System.out.println((int)(Math.random() * 5)+ 1);
//		System.out.println((int)(Math.random() * 5)+ 1);
//		System.out.println((int)(Math.random() * 5)+ 1);
		
		//�ߺ����� ���� �� �̱�
		//������ ���� for�� �����鼭 �ϴ°ǵ�... ��Ʊ⵵ �ϰ� ���� �׷� �ʿ䰡 ����.
		//����, �迭, ������ ������ �ߺ����� �ʴ� ���� ���� �� ����.
		
		int[] nums = new int[] {1,2,3,4,5};
		
		for(int i = 0; i < nums.length * 10; i++) {
			int x = (int)(Math.random() * nums.length);
			int y = (int)(Math.random() * nums.length);
			
			int temp = nums[x];
			nums[x] = nums[y];
			nums[y] = temp;
		}
		
		System.out.println(nums[0]);
		System.out.println(nums[1]);
		System.out.println(nums[2]);
		
		//���η� 1ĭ�� ������ ���� ���ڼ�, ����Ƚ�� �������� ���ڸ� �ڼ��� �ִٰ� �����ִ� ��.
		//���� ���� ������ ���� �ʿ� ����, ���� ���� �����ϴ� �͵� �ƴ�. (�����ϴ� ���ó�� ���̴°�����)
		
		//=> ī�弯�� �˰���
	}
}
