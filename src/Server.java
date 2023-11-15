import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Server {
	public String name;
	public boolean isPrivate;
	public String password;
	public final int MAXPLAYERCOUNT = 30;
	public int onlinePlayers = 0;
	public final ServerPanel PAIREDSERVERPANEL;
	
	public ArrayList<Player> onlinePlayersList = new ArrayList<Player>();
	
	private class GameThreadr implements Runnable{
		private final Server PAIREDSERVER;
		
		GameThreadr (Server s) {
			PAIREDSERVER = s;
		}
		
		@Override
		public void run() {
			while (true) {
				for (int i = 0; i < PAIREDSERVER.onlinePlayersList.size(); i++) {
					if (PAIREDSERVER.onlinePlayersList.get(i).hp <= 0) {
						onlinePlayersList.get(i).kill();
					}
				}
			}
		}
		
	}
	
	public Thread gameThread;
	
	Server(String n, boolean isP) {
		name = n;
		isPrivate = isP;
		KogloxData.serversList.add(this);
		gameThread = new Thread(new GameThreadr(KogloxData.serversList.get(KogloxData.serversList.size() - 1)));
		ServerPanel serverPanel = new ServerPanel(this);
		PAIREDSERVERPANEL = serverPanel;
	}
	
	Server(String n, boolean isP, String p) {
		name = n;
		KogloxData.serversList.add(this);
		gameThread = new Thread(new GameThreadr(KogloxData.serversList.get(KogloxData.serversList.size() - 1)));
		ServerPanel serverPanel = new ServerPanel(this);
		PAIREDSERVERPANEL = serverPanel;
	}
	
	public void join(Player p) {
		for (int i = 0; i < onlinePlayersList.size(); i++) {
			if (onlinePlayersList.get(i).id == p.id) {
				JOptionPane.showMessageDialog(null, "You cannot join a server, you're already playing on!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		onlinePlayersList.add(p);
		onlinePlayers++;
		
		Main.kogloxWin.mainLabel.setVisible(false);
		Main.kogloxWin.backButton.setVisible(false);
		Main.kogloxWin.joinServerButton.setVisible(false);
		Main.kogloxWin.createServerButton.setVisible(false);
		Main.kogloxWin.menuBar.setVisible(false);
		Main.kogloxWin.nameLabel.setVisible(false);
		Main.kogloxWin.levelLabel.setVisible(false);
		
		for (int i = 0; i < KogloxData.serverPanelsList.size(); i++) {
			KogloxData.serverPanelsList.get(i).setVisible(false);
		}
		
		Main.kogloxWin.game.setVisible(true);
	}
}
