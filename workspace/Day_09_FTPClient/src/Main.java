
import java.io.File;
import java.util.Scanner;

import it.sauronsoftware.ftp4j.FTPClient;

public class Main {	
	public static void main(String[] args) {
		FTPClient client = new FTPClient();
		Scanner sc = new Scanner(System.in);

		//client.setCharset("MS949");
		//client.setCharset("utf-8");
		//client.setCharset("UTF-8");
		client.setType(FTPClient.TYPE_BINARY);
		


		TOPLOOP : while(true) {
			connectLoop(client, sc);
			loginLoop(client, sc);
			//bruteForcerLoginLoop(client, sc);


			while(true) {
				System.out.println("========== File =========");
				System.out.println("1. Upload File");
				System.out.println("2. Download File");
				System.out.println("3. Disconnect FTP Server");
				System.out.println("4. Delete File");
				System.out.print(">>>");
				int select = 0;
				String fileName = null;
				try {
					select = Integer.parseInt(sc.nextLine());
				}catch(Exception e) {
					System.out.println("type error. is not num");
					continue;
				}
				switch(select) {
				case 1:
					while(true) {
						try {
							System.out.println("경로를 포함한 파일 이름을 입력해주세요.");
							System.out.println("ex)D:\\example.txt");
							System.out.print(">>>");
							fileName = sc.nextLine();
							break;
						}catch(Exception e) {
							System.out.println("file name error");
						}
					}
					try {
						client.upload(new File(fileName));
					}catch(Exception e) {
						System.out.println("파일 업로드 중 에러가 났습니다. 연결을 끊고 다시 시작합니다.");
						disconnect(client);
						e.printStackTrace();
						continue TOPLOOP;
					}
					break;
				case 2:
					printList(client);
					while(true) {
						try {
							System.out.println("확장자를 포함한 파일 이름을 입력해주세요.");
							System.out.println("ex)example.txt");
							System.out.print(">>>");
							fileName = sc.nextLine();
							break;
						}catch(Exception e) {
							System.out.println("파일 이름이 적절한 형식이 아닙니다.");
						}
					}
					try {
						client.download(fileName, new File("D:\\download\\" + fileName));
					}catch(Exception e) {
						System.out.println("파일을 열던 중 오류가 났습니다. 연결을 끊고 재시작합니다.");
						disconnect(client);
						e.printStackTrace();
						continue TOPLOOP;
					}
					break;
				case 3:
					System.out.println("");
					disconnect(client);
					continue TOPLOOP;
				case 4:
					printList(client);
					while(true) {
						try {
							System.out.println("확장자를 포함한 파일 이름을 써 주세요.");
							System.out.println("ex)D:\\기밀문서.txt");
							System.out.print(">>>");
							fileName = sc.nextLine();
							break;
						}catch(Exception e) {
							System.out.println("파일 이름 형식 오류입니다.");
						}
					}
					try {
						client.deleteFile(fileName);
					}catch(Exception e) {
						System.out.println("파일을 삭제하던 중 오류가 났습니다. 연결을 끊고 재시작합니다.");
						disconnect(client);
						e.printStackTrace();
						System.exit(0);
					}
					break;
				default:
					System.out.println("메뉴 중 선택해주세요.");
				}
			}
		}

	}

	private static void disconnect(FTPClient client) {
		try {
			client.disconnect(true);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private static void printList(FTPClient client) {
		while(true) {
			try {
				String[] names = client.listNames();
				System.out.println("listName");
				for(int i = 0; i < names.length; i++) {
					System.out.println(names[i]);
				}
				break;
			}catch(Exception e) {
				System.out.println("목록을 탐색하던 중 오류가 났습니다. 종료합니다.");
				disconnect(client);
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	private static void connectLoop(FTPClient client, Scanner sc) {
		while(true) {
			System.out.println("=== FTP Client Program ===");
			System.out.println("1. Connect FTP Server");
			System.out.println("2. Exit Program");
			System.out.print(">>>");
			int select = 0;
			while(true) {
				try {
					select = Integer.parseInt(sc.nextLine());
					break;
				}catch(Exception e) {
					System.out.println("메뉴 선택으로는 숫자를 입력해주세요.");
				}
			}
			if(select == 1)
			{
				while(true) {
					try {
						System.out.println("input url. (시스템 종료는 0)");
						System.out.print(">>>");
						String url = sc.nextLine();
						if(url.equals("0"))
							System.exit(0);
						else
							client.connect(url, 21);
						break;
					}catch(Exception e) {
						System.out.println("url 입력 중 오류가 났습니다.");
						e.printStackTrace();
					}
				}
				break;
			}else if(select == 2) {
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}else {
				System.out.println("메뉴 중에서 골라주세요.");
			}
		}
		System.out.println("FTP Server is connected");
		return;
	}

	private static void loginLoop(FTPClient client, Scanner sc) {
		while(true) {
			System.out.println("=== login ===");
			String id, password;
			try {
				System.out.print("Input ID(종료는 0) : ");
				id = sc.nextLine();
				if(id.equals("0")) {
					disconnect(client);
					System.exit(0);
				}
				System.out.print("Input Password : ");
				password = sc.nextLine();
			}catch(Exception e){
				System.out.println("로그인 중 오류가 났습니다.");
				e.printStackTrace();
				continue;
			}

			try {
				client.login(id, password);
				break;
			}catch(Exception e) {
				System.out.println("로그인 중 오류가 났습니다.");
				e.printStackTrace();
			}
		}
		System.out.println("Login Success");
		return;
	}

	private static void bruteForcerLoginLoop(FTPClient client, Scanner sc) {
		TOPLOOP : while(true) {
			System.out.println("=== login ===");
			String id, password = null;
			try {
				System.out.print("Input ID : ");
				id = sc.nextLine();
			}catch(Exception e){
				System.out.println("입력 형식에서 오류가 났습니다.");
				e.printStackTrace();
				continue;
			}

			for(int first = 1; first < 10; first++) {
				for(int second = 0; second < 10; second++) {
					for(int third = 0; third < 10;  third++) {
						for(int fourth = 0; fourth < 10; fourth++) {
							password = Integer.toString(first)
									+ Integer.toString(second)
									+ Integer.toString(third)
									+ Integer.toString(fourth);

							try {
								client.login(id, password);
								System.out.println(password);
								break TOPLOOP;
							}catch(Exception e) {
								//System.out.println("");
								//e.printStackTrace();
							}
						}
					}
				}
			}


		}
		System.out.println("Login Success");
		return;
	}

}


