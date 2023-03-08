import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Main {
	public static void main(String[] args) {
		try{
		    FileInputStream fis = new FileInputStream("mysong.mp3");
		    Player playMP3 = new Player(fis);
		    playMP3.play();
		}
		catch(Exception exc){
		    exc.printStackTrace();
		    System.out.println("Failed to play the file.");
		}	
	}
}
