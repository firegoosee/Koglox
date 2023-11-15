import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Player {
	private Random random = new Random();
	public String playerName;
	public int level = 1;
	public float xp = 0.00f;
	public int state;
	public int hp = 100;
	public boolean canMove = true;
	public String id;
	
	public void kill() {
		if (this == Main.thisPlayer) {
			canMove = false;
			Main.kogloxWin.showRespawnScreen(this);
		}
		else {
			canMove = false;
		}
	}
	
	private class LevelUpr implements Runnable {
		private float[] levelUps = {
				-1.00f,
				200.00f,
				600.00f,
				1000.00f,
				1500.00f,
				2000.00f,
				2500.00f,
				3000.00f,
				3500.00f,
				4000.00f,
				5000.00f,
				6000.00f,
				7000.00f,
				8000.00f,
				9000.00f,
				10000.00f,
				11000.00f,
				12000.00f,
				13000.00f,
				14000.00f,
				15000.00f,
				16000.00f,
				17000.00f,
				18000.00f,
				19000.00f,
				20000.00f,
				21000.00f,
				22000.00f,
				23000.00f,
				24000.00f,
				25000.00f,
				26500.00f,
				28000.00f,
				29500.00f,
				31000.00f,
				32500.00f,
				34000.00f,
				35500.00f,
				37000.00f,
				38500.00f,
				40000.00f,
				41500.00f,
				43000.00f,
				44500.00f,
				46000.00f,
				47500.00f,
				49500.00f,
				51000.00f,
				52500.00f,
				54000.00f,
				55500.00f,
				57500.00f,
				59500.00f,
				61500.00f,
				63500.00f,
				65500.00f,
				67500.00f,
				69500.00f,
				71500.00f,
				73500.00f,
				75500.00f,
				77500.00f,
				79500.00f,
				81500.00f,
				83500.00f,
				85500.00f,
				87500.00f,
				89500.00f,
				91500.00f,
				93500.00f,
				95500.00f,
				97500.00f,
				99500.00f,
				101500.00f,
				103500.00f,
				105500.00f,
				107500.00f,
				109500.00f,
				111500.00f,
				113500.00f,
				115500.00f,
				117500.00f,
				119500.00f,
				121500.00f,
				123500.00f,
				125500.00f,
				127500.00f,
				129500.00f,
				131500.00f,
				133500.00f,
				135500.00f,
				140500.00f,
				145500.00f,
				150500.00f,
				155500.00f,
				160500.00f,
				165500.00f,
				170500.00f,
				175500.00f,
				185000.00f,
		};
		
		@Override
		public void run() {
			while (true) {
				for (int i = 1; i < levelUps.length; i++) {
					if (levelUps[i] > Main.thisPlayer.xp) {}
					else if (Main.thisPlayer.xp >= levelUps[i] && Main.thisPlayer.level == i) {
						Main.thisPlayer.levelUp();
						break;
					}
				}
				Main.kogloxWin.levelLabel.setText(Main.thisPlayer.level + ". level (" + Main.thisPlayer.xp + " xp)");
			}
		}
		
	}
	
	public Thread levelUpper = new Thread(new LevelUpr());
	
	Player(String n, int s) {
		playerName = n;
		
		switch (s) {
		case 0:
			state = ALLY;
			break;
			
		case 1:
			state = ENEMY;
			break;
			
		case 2:
			state = THIS_PLAYER;
			break;
		}
		
		id = String.valueOf(random.nextInt(100000, 999999) + 1);
		
		KogloxData.playersList.add(this);
	}
	
	public void levelUp() {
		this.level += 1;
		return;
	}
	
	public static int ALLY = 0;
	public static int ENEMY = 1;
	public static int THIS_PLAYER = 2;
}
