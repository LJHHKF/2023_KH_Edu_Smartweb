
public class Quiz_03 {
	public static void main(String[] args) {
		//1~45 사이의 난수를 중첩되지 않게 7개를 뽑아서 출력하세요.
		// 오늘의 추첨 로또 번호
		
		//초기화
		int[] nums = new int[45];
		for(int i = 0; i < nums.length; i++) {
			nums[i] = i + 1;
		}
		
		//카드 섞기 알고리즘
		for(int i = 0; i < nums.length * 10; i++) {
			int rand1 = (int)(Math.random() * nums.length);
			int rand2 = (int)(Math.random() * nums.length);
			
			int temp = nums[rand1];
			nums[rand1] = nums[rand2];
			nums[rand2] = temp;
		}
		
		//출력
		for(int i = 0; i < 7; i++) {
			System.out.print(nums[i] + ", ");
		}
		
		//주말에 추가적으로 도전해보면 좋을 것이,
		//이걸 계속 반복하면서 1등이 될 때까지 돌려보면서
		//n등이 n번 됬다, 총 n번 수행했다를 통해 확률 계산하는 시뮬레이터로 발전시켜볼 것.
	}
}
