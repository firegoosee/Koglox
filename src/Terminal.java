import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Terminal extends JFrame implements KeyListener{
	private boolean upperCase = false;
	private boolean capsLocked = false;
	
	private boolean shouldMakeNewLine = true;
	
	private String textColor = "green";
	
	private JLabel arrow = new JLabel(">>> ");
	private JLabel field = new JLabel("");
	
	private ArrayList<JLabel> arrows = new ArrayList<JLabel>();
	private ArrayList<JLabel> fields = new ArrayList<JLabel>();
	
	ImageIcon icon = new ImageIcon("C:\\Koglox\\Icon\\koglox_terminal.png");
	
	Terminal() {
		arrow.setForeground(new Color(0,255,0));
		arrow.setBounds(10, 10, 30, 20);
		
		arrows.add(arrow);
		
		field.setForeground(new Color(0,255,0));
		field.setBounds(40, 10, 500, 20);
		
		fields.add(field);
		
		this.setVisible(false);
		this.setResizable(false);
		this.setBounds(10, 10, 720, 360);
		this.setLayout(null);
		this.setTitle("Koglox terminal");
		this.setAlwaysOnTop(true);
		this.getContentPane().setBackground(Color.black);
		this.addKeyListener(this);
		this.setIconImage(icon.getImage());
		
		this.add(arrow);
		this.add(field);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//EDCAPE gomb lenyomása
		if (e.getKeyCode() == 27 || e.getKeyCode() == 112) {
			this.setVisible(false);
		}
		else if (e.getKeyCode() == 16) {
			upperCase = true;
		}
		else if (e.getKeyCode() == 8) {
			fields.get(fields.size() - 1).setText(fields.get(fields.size() - 1).getText().substring(0, fields.get(fields.size() - 1).getText().length() - 1));
		}
		else if (e.getKeyCode() == 20) {
			capsLocked = !capsLocked;
		}
		else if (e.getKeyCode() == 10) {
			String input = fields.get(fields.size() - 1).getText();
			System.out.println(input);
			
			if (input.equals("q")) {
				System.exit(1);
			}
			else if (input.length() > 9 && input.substring(0, 9).equals("setName(\"") && input.substring((input.length() - 2)).equals("\")")) {
				Main.thisPlayer.playerName = input.substring(9, input.length() - 2);
				Main.kogloxWin.nameLabel.setText(Main.thisPlayer.playerName);
				Main.kogloxWin.nameField.setText(Main.thisPlayer.playerName);
			}
			else if (input.equals("newt")) {
				this.setVisible(false);
				Main.terminal = new Terminal();
				Main.terminal.setVisible(true);
			}
			else if (input.equals("clean")) {
				for (int i = 0; i < arrows.size(); i++) {
					arrows.get(i).setVisible(false);
				}
				
				for (int i = 0; i < fields.size(); i++) {
					fields.get(i).setVisible(false);
				}
				
				arrows = new ArrayList<JLabel>();
				fields = new ArrayList<JLabel>();
				
				JLabel rarrow = new JLabel(">>>");
				JLabel rfield = new JLabel("");
				
				rarrow.setBounds(10, 10, 30, 20);
				rfield.setBounds(40, 10, 500, 20);
				
				switch (textColor) {
				case "white":
					rarrow.setForeground(new Color(255, 255, 255));
					rfield.setForeground(new Color(255, 255, 255));
					break;
				
				case "red":
					rarrow.setForeground(new Color(255, 0, 0));
					rfield.setForeground(new Color(255, 0, 0));
					break;
					
				case "green":
					rarrow.setForeground(new Color(0, 255, 0));
					rfield.setForeground(new Color(0, 255, 0));
					break;
				
				case "blue":
					rarrow.setForeground(new Color(0, 0, 255));
					rfield.setForeground(new Color(0, 0, 255));
					break;
				}
				
				arrows.add(rarrow);
				fields.add(rfield);
				
				this.add(rarrow);
				this.add(rfield);
				
				shouldMakeNewLine = false;
			}
			else if (input.equals("cl")) {
				this.setVisible(false);
			}
			else if (input.length() > 14 && input.substring(0, 14).equals("setTextColor(\"") && input.substring((input.length() - 2)).equals("\")")) {
				switch (input.substring(14, input.length() - 2).toLowerCase()) {
				case "white":
					for (int i = 0; i < fields.size(); i++) {
						fields.get(i).setForeground(new Color(255, 255, 255));
					}
					for (int i = 0; i < arrows.size(); i++) {
						arrows.get(i).setForeground(new Color(255, 255, 255));
					}
					
					textColor = "white";
					break;
				
				case "red":
					for (int i = 0; i < fields.size(); i++) {
						fields.get(i).setForeground(new Color(255, 0, 0));
					}
					for (int i = 0; i < arrows.size(); i++) {
						arrows.get(i).setForeground(new Color(255, 0, 0));
					}
					
					textColor = "red";
					break;
				
				case "blue":
					for (int i = 0; i < fields.size(); i++) {
						fields.get(i).setForeground(new Color(0, 0, 255));
					}
					for (int i = 0; i < arrows.size(); i++) {
						arrows.get(i).setForeground(new Color(0, 0, 255));
					}
					
					textColor = "blue";
					break;
					
				case "green":
					for (int i = 0; i < fields.size(); i++) {
						fields.get(i).setForeground(new Color(0, 255, 0));
					}
					for (int i = 0; i < arrows.size(); i++) {
						arrows.get(i).setForeground(new Color(0, 255, 0));
					}
					
					textColor = "green";
					break;
				}
				
			}
			
			if (shouldMakeNewLine) {
				JLabel narrow = new JLabel(">>> ");
				JLabel nfield = new JLabel("");
				
				narrow.setBounds(new Rectangle(arrows.get(arrows.size() -1).getX(), arrows.get(arrows.size() - 1).getY() + 20, arrows.get(arrows.size() - 1).getWidth(), arrows.get(arrows.size() -1).getHeight()));
				nfield.setBounds(new Rectangle(arrows.get(arrows.size() - 1).getX() + 30, fields.get(fields.size() - 1).getY() + 20, 500, 20));
				
				switch (textColor) {
				case "white":
					narrow.setForeground(new Color(255, 255, 255));
					nfield.setForeground(new Color(255, 255, 255));
					break;
				
				case "red":
					narrow.setForeground(new Color(255, 0, 0));
					nfield.setForeground(new Color(255, 0, 0));
					break;
					
				case "green":
					narrow.setForeground(new Color(0, 255, 0));
					nfield.setForeground(new Color(0, 255, 0));
					break;
				
				case "blue":
					narrow.setForeground(new Color(0, 0, 255));
					nfield.setForeground(new Color(0, 0, 255));
					break;
				}
				
				narrow.setVisible(false);
				nfield.setVisible(false);
				
				arrows.add(narrow);
				fields.add(nfield);
				
				this.add(arrows.get(arrows.size() - 1));
				this.add(fields.get(fields.size() -1));
				
				arrows.get(arrows.size() - 1).setVisible(true);
				fields.get(fields.size() -1).setVisible(true);
			}
			else {
				shouldMakeNewLine = true;
			}
		}
		else {
			if (upperCase && !capsLocked) {
				char letter = e.getKeyChar();
				String letterS = String.valueOf(letter).toUpperCase();
				fields.get(fields.size() - 1).setText(fields.get(fields.size() - 1).getText() + letterS);
			}
			else if (upperCase && capsLocked) {			
				fields.get(fields.size() - 1).setText(fields.get(fields.size() - 1).getText() + e.getKeyChar());	
			}
			else if (!upperCase && capsLocked) {
				char letter = e.getKeyChar();
				String letterS = String.valueOf(letter).toUpperCase();
				fields.get(fields.size() - 1).setText(fields.get(fields.size() - 1).getText() + letterS);
			}
			else {
				fields.get(fields.size() - 1).setText(fields.get(fields.size() - 1).getText() + e.getKeyChar());	
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 16) {
			upperCase = false;
		}
	}
	
}
