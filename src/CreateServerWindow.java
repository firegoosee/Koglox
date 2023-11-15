import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class CreateServerWindow extends JFrame implements ActionListener, ItemListener, ChangeListener{
	
	private int operation;
	
	JButton createButton = new JButton("Create server");
	JButton cancelButton = new JButton("Cancel");
	
	CreateServerWindow() {
		this.setSize(new Dimension(500, 600));
		this.setAlwaysOnTop(true);
		this.setIconImage(null);
		this.setResizable(false);
		
		this.setVisible(true);
	}
	
	public int resp() {
		if (operation == -1) {
			return -1;
		}
		else if (operation == 1) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelButton) {
			operation = -1;
		}
		else if (e.getSource() == createButton) {
			operation = 1;
		}
	}
}
