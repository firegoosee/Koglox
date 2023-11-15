import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ServerPanel extends JPanel implements ActionListener, MouseListener{
	public final Server PAIREDSERVER;
	
	private JLabel serverNameLabel;
	public JLabel serverPlayerCountLabel;
	private JButton joinButton = new JButton("Join");
	
	ServerPanel(Server p) {
		this.setBackground(new Color(255, 255, 255));
		this.setBounds(250, 35 * (KogloxData.serverPanelsList.size() - 1) + 35, 780, 25);
		this.setLayout(null);
		
		PAIREDSERVER = p;
		
		if (PAIREDSERVER.isPrivate) {
			serverNameLabel = new JLabel(PAIREDSERVER.name + " [PRIVATE]");	
		}
		else {
			serverNameLabel = new JLabel("  " + PAIREDSERVER.name);			
		}
		
		serverNameLabel.setBounds(new Rectangle(10, 5, 400, 15));
		
		serverPlayerCountLabel = new JLabel("Online: " + PAIREDSERVER.onlinePlayers + "/" + PAIREDSERVER.MAXPLAYERCOUNT);
		serverPlayerCountLabel.setBounds(350, 5, 100, 15);
		
		joinButton.setBounds(new Rectangle(710, 0, 70, 25));
		joinButton.setFocusable(false);
		joinButton.addActionListener(this);
		joinButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

		this.add(serverNameLabel);
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(serverPlayerCountLabel);
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(joinButton);
		this.addMouseListener(this);
		
		this.setVisible(false);
		KogloxData.serverPanelsList.add(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == joinButton) {
			this.PAIREDSERVER.join(Main.thisPlayer);
			serverPlayerCountLabel.setText("Online: " + PAIREDSERVER.onlinePlayers + "/" + PAIREDSERVER.MAXPLAYERCOUNT);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Main.kogloxWin.selectedServer = this.PAIREDSERVER;
		Main.kogloxWin.joinServerButton.setEnabled(true);
		
		this.setBackground(new Color(3, 132, 252));
		serverNameLabel.setForeground(new Color(255, 255, 255));
		serverPlayerCountLabel.setForeground(new Color(255, 255, 255));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
