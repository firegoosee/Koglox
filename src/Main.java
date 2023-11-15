import java.math.*;
import java.io.*;

public class Main {
	public static KogloxWin kogloxWin;
	public static Terminal terminal;
	public static Player thisPlayer;
	
	public static void main(String[] args) {
		File kogloxFolder = new File("C:\\Koglox");
		
		if (!kogloxFolder.exists()) {
			kogloxFolder.mkdir();
		}
		
		kogloxWin = new KogloxWin();
		terminal = new Terminal();
		thisPlayer = new Player(kogloxWin.nameField.getText(), 2);
		thisPlayer.levelUpper.start();
		
	}

}
