
public class Exam_06 {
	public static void main(String[] args) {
		//1~5 까지의 숫자가 있고, 이 중 3개의 숫자를 뽑아낼 것.
		//랜덤하게 3번. 뭐가 나올 지 모름.
		
		//중복 무관의 경우
//		System.out.println((int)(Math.random() * 5)+ 1);
//		System.out.println((int)(Math.random() * 5)+ 1);
//		System.out.println((int)(Math.random() * 5)+ 1);
		
		//중복되지 않은 수 뽑기
		//정석은 이중 for문 돌리면서 하는건데... 어렵기도 하고 굳이 그럴 필요가 없음.
		//스왑, 배열, 난수를 섞으면 중복되지 않는 수를 뽑을 수 있음.
		
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
		
		//세로로 1칸씩 나오는 복권 상자서, 일정횟수 무작위로 상자를 뒤섞어 주다가 꺼내주는 것.
		//나온 것을 가지고 비교할 필요 없고, 뽑은 것을 제외하는 것도 아님. (제외하는 방식처럼 쓰이는거지만)
		
		//=> 카드섞기 알고리즘
	}
}
