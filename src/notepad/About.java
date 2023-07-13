package notepad;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class About extends JFrame implements ActionListener{
	
	 About() {
		 setBounds(400, 100, 600, 500);
		 setLayout(null);
		 
			 setTitle("About NotePad");
			 ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepad.png"));
			 Image icon = notepadIcon.getImage();  //From awt
			 setIconImage(icon); //Set the icon
			 
			 ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/windowLogo.png"));
			 Image i2 = i1.getImage().getScaledInstance(300, 60, Image.SCALE_DEFAULT);
			 JLabel headerIcon = new JLabel(new ImageIcon(i2));
			 headerIcon.setBounds(70, 40, 450, 80);
			 add(headerIcon);
			 
			 ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepad.png"));
			 Image i4 = i3.getImage().getScaledInstance(300, 60, Image.SCALE_DEFAULT);
			 JLabel areaIcon = new JLabel(new ImageIcon(i4));
			 areaIcon.setBounds(50, 180, 70, 70);
			 add(areaIcon);
			 
			 JLabel text = new JLabel("<html>Jaikishan Mohanty NotePad<br>Version 0.1.0 (OS Build Java)<br> All right reserved to Jaikishan Mohanty </html>");
			 text.setBounds(150, 100, 500, 200);
			 text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
			 add(text);
			 
			 
			 JButton button = new JButton("OK");
			 button.setBounds(150, 350, 120, 25);
			 button.addActionListener(this);
			 add(button);
		 
		 
		 setVisible(true);
	}
	 
	 @Override 
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			this.setVisible(false);
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		About about = new About();

	}

	

}
