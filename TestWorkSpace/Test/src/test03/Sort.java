package test03;

import java.util.Scanner;

public class Sort {
	public void sort() {
		Scanner scanner = new Scanner(System.in);
		int[] arr = new int[5];
		int sum = 0;
		
		//반복문을 통한 입력
		for(int i = 0; i < arr.length; i++) {
			System.out.print((i+1) + "번째 숫자 입력 : ");
			arr[i] = scanner.nextInt();
			sum += arr[i];
		}
		//내림차순 정렬 코드
		int temp = 0;
		for(int i = 0; i < arr.length-1; i++) {
			for(int j = i+1; j < arr.length; j++) {
				if(arr[i] < arr[j]) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		//출력코드
		System.out.print("정렬 결과 : ");
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
		System.out.println();
		System.out.println("입력한 수의 총 합은 : " + sum);
		
	}
}
