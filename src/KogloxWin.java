import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class KogloxWin extends JFrame implements ActionListener, KeyListener, MouseListener, ItemListener, ChangeListener {
	
	Server selectedServer;
	
	JMenuBar menuBar = new JMenuBar();
	
	JMenu fileMenu = new JMenu("File");
	JMenu terminalMenu = new JMenu("Terminal");
	
	JMenuItem exitItem = new JMenuItem("Exit");
	JMenuItem openTerminalItem = new JMenuItem("Open Terminal");
	JMenuItem newTerminalItem = new JMenuItem("New Terminal");
	
	JPanel optionsPanel = new JPanel();
	JPanel optionsNavPanel = new JPanel();
	
	JButton playButton = new JButton("Play");
	JButton optionsButton = new JButton("Options");
	JButton backButton = new JButton("Back");
	JButton setNameButton = new JButton("Set");
	JButton joinServerButton = new JButton("Join server");
	JButton createServerButton = new JButton("Create server");
	JButton respawnButton = new JButton("Respawn");
	JButton masterOptionsButton = new JButton("Master");
	JButton audioOptionsButton = new JButton("Audio");
	
	JCheckBox showMenuBar = new JCheckBox("Menubar");
	
	JTextField nameField = new JTextField("Player");
	
	JSlider volumeSlider = new JSlider(0, 100, 100);
	
	JLabel mainLabel = new JLabel("Koglox");
	JLabel setNameLabel = new JLabel("Player name:");
	JLabel nameLabel = new JLabel("Player");
	JLabel levelLabel = new JLabel(1 + ". level (" + 0.00f + " xp)");
	//TODO normális játékot csinálni
	JLabel youSlainLabel = new JLabel("You've been slain!");
	JLabel masterVolumeLabel = new JLabel("Master volume: " + volumeSlider.getValue());
	JLabel game = new JLabel("A JÁTÉK HELYE");
	
	ImageIcon icon = new ImageIcon("C:\\Koglox\\Icon\\koglox.png");
	Image labelIconImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	ImageIcon labelIcon = new ImageIcon(labelIconImage);
	
	KogloxWin() {
		
		exitItem.addActionListener(this);
		exitItem.setMnemonic('E');
		
		openTerminalItem.addActionListener(this);
		newTerminalItem.addActionListener(this);
		
		fileMenu.add(exitItem);
		
		terminalMenu.add(openTerminalItem);
		terminalMenu.add(newTerminalItem);
		
		menuBar.add(fileMenu);
		menuBar.add(terminalMenu);
		menuBar.setVisible(false);
		
		optionsPanel.setBackground(new Color(255, 255, 255));
		optionsPanel.setBounds(new Rectangle(250, 0, 1030, 720));
		optionsPanel.setVisible(false);
		optionsPanel.setLayout(null);
		
		optionsNavPanel.setBounds(new Rectangle(0,0, 160, 1080));
		optionsNavPanel.setBackground(new Color(255, 255, 255));
		optionsNavPanel.setLayout(new GridLayout(25, 1, 0, 10));
		
		playButton.setBounds(new Rectangle(10, 70, 100, 30));
		playButton.setFocusable(false);
		playButton.addActionListener(this);
		
		optionsButton.setBounds(new Rectangle(10, 110, 100, 30));
		optionsButton.setFocusable(false);
		optionsButton.addActionListener(this);
		
		backButton.setBounds(new Rectangle(10, 637, 100, 30));
		backButton.setFocusable(false);
		backButton.addActionListener(this);
		backButton.setVisible(false);
		
		setNameButton.setBounds(1198, 637, 50, 19);
		setNameButton.addActionListener(this);
		setNameButton.setFont(new Font(Font.DIALOG, Font.BOLD, 10));
		setNameButton.setFocusable(false);
		
		joinServerButton.setBounds(new Rectangle(10, 70, 140, 30));
		joinServerButton.setFocusable(false);
		joinServerButton.addActionListener(this);
		joinServerButton.setVisible(false);
		joinServerButton.setEnabled(false);
		joinServerButton.setToolTipText("Select a server first!");
		
		createServerButton.setBounds(new Rectangle(10, 110, 140, 30));
		createServerButton.setFocusable(false);
		createServerButton.addActionListener(this);
		createServerButton.setVisible(false);
		
		respawnButton.setBounds(new Rectangle(560, 160, 100, 30));
		respawnButton.setFocusable(false);
		respawnButton.addActionListener(this);
		respawnButton.setVisible(false);
		
		masterOptionsButton.setHorizontalTextPosition(JLabel.CENTER);
		masterOptionsButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		masterOptionsButton.setBorderPainted(false); 
		masterOptionsButton.setContentAreaFilled(false); 
		masterOptionsButton.setFocusPainted(false); 
		masterOptionsButton.setOpaque(false);
		masterOptionsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		masterOptionsButton.addActionListener(this);
				
		audioOptionsButton.setHorizontalTextPosition(JLabel.CENTER);
		audioOptionsButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		audioOptionsButton.setBorderPainted(false); 
		audioOptionsButton.setContentAreaFilled(false); 
		audioOptionsButton.setFocusPainted(false); 
		audioOptionsButton.setOpaque(false);
		audioOptionsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		audioOptionsButton.addActionListener(this);
		
		showMenuBar.setBounds(new Rectangle(170, 10, 100, 15));
		showMenuBar.setBackground(null);
		showMenuBar.setFocusable(false);
		showMenuBar.addItemListener(this);
		
		nameField.setBounds(1050, 637, 150, 20);
		
		volumeSlider.setBackground(null);
		volumeSlider.setBounds(new Rectangle(200, 30, 50, 130));
		volumeSlider.setOrientation(SwingConstants.VERTICAL);
		volumeSlider.setMinorTickSpacing(10);
		volumeSlider.setMajorTickSpacing(50);
		volumeSlider.setPaintTicks(true);
		volumeSlider.setPaintLabels(true);
		volumeSlider.setVisible(false);
		volumeSlider.addChangeListener(this);
		
		mainLabel.setBounds(new Rectangle(10, 0, 250, 50));
		mainLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 35));
		mainLabel.setIcon(labelIcon);
		mainLabel.setVerticalTextPosition(JLabel.CENTER);
		mainLabel.setHorizontalTextPosition(JLabel.RIGHT);
		mainLabel.setIconTextGap(10);
		
		setNameLabel.setBounds(new Rectangle(970, 637, 150, 20));
		
		nameLabel.setBounds(new Rectangle(1120, 10, 100, 20));
		
		levelLabel.setBounds(1120, 25, 150, 20);
		levelLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 10));
		
		youSlainLabel.setBounds(465, 110, 400, 50);
		youSlainLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 35));
		youSlainLabel.setVisible(false);

		game.setBounds(new Rectangle(10, 10, 40, 40));
		game.setFont(new Font(Font.DIALOG, Font.BOLD, 75));
		game.setText("");
		game.setIcon(labelIcon);
		game.setVisible(false);
		
		masterVolumeLabel.setBounds(new Rectangle(200, 10, 130, 10));
		masterVolumeLabel.setVisible(false);
		
		optionsNavPanel.add(masterOptionsButton);
		optionsNavPanel.add(audioOptionsButton);
		
		optionsPanel.add(optionsNavPanel);
		optionsPanel.add(showMenuBar);
		optionsPanel.add(volumeSlider);
		optionsPanel.add(masterVolumeLabel);
		
		this.setJMenuBar(menuBar);
		
		this.setVisible(true);
		this.setSize(new Dimension(1280, 720));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Koglox");
		this.setLayout(null);
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.setIconImage(icon.getImage());
		
		this.add(optionsPanel);
		
		this.add(playButton);
		this.add(optionsButton);
		this.add(backButton);
		this.add(setNameButton);
		this.add(joinServerButton);
		this.add(createServerButton);
		this.add(respawnButton);
		this.add(nameField);
		this.add(mainLabel);
		this.add(setNameLabel);
		this.add(nameLabel);
		this.add(levelLabel);
		this.add(youSlainLabel);
		this.add(game);
		
		this.requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitItem) {
			System.exit(0);
		}
		else if (e.getSource() == openTerminalItem) {
			Main.terminal.setVisible(true);
		}
		else if (e.getSource() == newTerminalItem) {
			Main.terminal = new Terminal();
			Main.terminal.setVisible(true);
		}
		else if (e.getSource() == playButton) {
			mainLabel.setText("Servers");
			playButton.setVisible(false);
			optionsButton.setVisible(false);
			backButton.setVisible(true);
			backButton.setVisible(true);
			setNameLabel.setVisible(false);
			nameField.setVisible(false);
			setNameButton.setVisible(false);
			joinServerButton.setVisible(true);
			createServerButton.setVisible(true);
		}
		else if (e.getSource() == optionsButton) {
			mainLabel.setText("Options");
			playButton.setVisible(false);
			optionsButton.setVisible(false);
			optionsPanel.setVisible(true);
			backButton.setVisible(true);
			setNameLabel.setVisible(false);
			nameField.setVisible(false);
			setNameButton.setVisible(false);
		}
		else if (e.getSource() == backButton) {
			mainLabel.setText("Koglox");
			playButton.setVisible(true);
			optionsButton.setVisible(true);
			optionsPanel.setVisible(false);
			backButton.setVisible(false);
			setNameLabel.setVisible(true);
			setNameButton.setVisible(true);
			joinServerButton.setVisible(false);
			createServerButton.setVisible(false);
			nameField.setVisible(true);
		}
		else if (e.getSource() == setNameButton) {
			Main.thisPlayer.playerName = nameField.getText();
			nameLabel.setText(Main.thisPlayer.playerName);
			nameField.setEnabled(false);
			nameField.setEnabled(true);
			this.requestFocus();
		}
		else if (e.getSource() == joinServerButton) {
			selectedServer.join(Main.thisPlayer);
			selectedServer.PAIREDSERVERPANEL.serverPlayerCountLabel.setText("Online: " + selectedServer.PAIREDSERVERPANEL.PAIREDSERVER.onlinePlayers + "/" + selectedServer.PAIREDSERVERPANEL.PAIREDSERVER.MAXPLAYERCOUNT);
		}
		else if (e.getSource() == createServerButton) {
			CreateServerWindow crsw = new CreateServerWindow();
			Server server = new Server("Firegoose", true);
			this.add(server.PAIREDSERVERPANEL);			
			server.PAIREDSERVERPANEL.setVisible(true);
		}
		else if (e.getSource() == respawnButton) {
			youSlainLabel.setVisible(false);
			respawnButton.setVisible(false);
			
			game.setBounds(new Rectangle(10, 10, 40, 40));
			Main.thisPlayer.canMove = true;
		}
		else if (e.getSource() == masterOptionsButton) {
			volumeSlider.setVisible(false);
			masterVolumeLabel.setVisible(false);
			showMenuBar.setVisible(true);
			
			masterOptionsButton.setBorderPainted(true);
			audioOptionsButton.setBorderPainted(false);
		}
		else if (e.getSource() == audioOptionsButton) {
			volumeSlider.setVisible(true);
			masterVolumeLabel.setVisible(true);
			showMenuBar.setVisible(false);
			
			masterOptionsButton.setBorderPainted(false);
			audioOptionsButton.setBorderPainted(true);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// F2 billentyű lenyomása
		if (e.getKeyCode() == 113) {
			if (Main.terminal.isVisible()) {
				Main.terminal.setVisible(false);
			}
			else {
				Main.terminal.setVisible(true);
			}
		}
		else if (e.getKeyChar() == 'r') {
			Main.thisPlayer.kill();
		}
		else if (e.getKeyChar() == 'w') {
			if (Main.thisPlayer.canMove) {
				game.setBounds(new Rectangle(game.getX(), game.getY() - 15, 40, 40));				
			}
		}
		else if (e.getKeyChar() == 's') {
			if (Main.thisPlayer.canMove) {
				game.setBounds(new Rectangle(game.getX(), game.getY() + 15, 40, 40));				
			}
		}
		else if (e.getKeyChar() == 'a') {
			if (Main.thisPlayer.canMove) {
				game.setBounds(new Rectangle(game.getX() - 15, game.getY(), 40, 40));				
			}
		}
		else if (e.getKeyChar() == 'd') {
			if (Main.thisPlayer.canMove) {
				game.setBounds(new Rectangle(game.getX() + 15, game.getY(), 40, 40));				
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == this) {
			this.requestFocus();
		}
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
	
	public void showRespawnScreen(Player p) {
		youSlainLabel.setVisible(true);
		respawnButton.setVisible(true);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == showMenuBar) {
			if (menuBar.isVisible()) {
				menuBar.setVisible(false);
				
				backButton.setBounds(new Rectangle(10, 637, 100, 30));
				setNameLabel.setBounds(new Rectangle(970, 637, 150, 20));
				setNameButton.setBounds(new Rectangle(1198, 637, 50, 19));
				nameField.setBounds(new Rectangle(1050, 637, 150, 20));			
			} 
			else {
				menuBar.setVisible(true);

				backButton.setBounds(new Rectangle(10, 615, 100, 30));
				setNameLabel.setBounds(new Rectangle(970, 615, 150, 20));
				setNameButton.setBounds(new Rectangle(1198, 615, 50, 19));
				nameField.setBounds(new Rectangle(1050, 615, 150, 20));		
			}
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == volumeSlider) {
			masterVolumeLabel.setText("Master volume: " + volumeSlider.getValue());
		}
	}
}
