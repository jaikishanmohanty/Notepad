package notepad;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;


//ActionListener is an interface and there is only one class "actionPerformed" which we have override
public class Notepad extends JFrame implements ActionListener{
	JTextArea area;
	String text;
	
	Notepad() {
		setTitle("NotePad");
		 ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepad.png"));
		 Image icon = notepadIcon.getImage();  //From awt
		 setIconImage(icon); //Set the icon
		 
		 //Create Menu bar
		 JMenuBar menubar = new JMenuBar();
		 menubar.setBackground(Color.WHITE);
		 
		 //************************************************************************//
	//File Menu 
		 
		 JMenu file = new JMenu("File");
		 file.setFont(new Font("AERIAL", Font.PLAIN, 14));
		 
		 JMenuItem newdoc = new JMenuItem("New");
		 newdoc.addActionListener(this);
		 newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); //Add Neumonics
		 
		 JMenuItem open = new JMenuItem("Open");
		 open.addActionListener(this);
		 open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK)); 

		 JMenuItem save = new JMenuItem("Save");
		 save.addActionListener(this);
		 save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK)); 
		 
		 JMenuItem print = new JMenuItem("Print");
		 print.addActionListener(this);
		 print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK)); 
		 
		 JMenuItem exit = new JMenuItem("Exit");
		 exit.addActionListener(this);
		 exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.CTRL_MASK)); 
		 
		 file.add(newdoc);
		 file.add(open);
		 file.add(save);
		 file.add(print);
		 file.add(exit);
		 
		 menubar.add(file);
	
		 //************************************************************************//
	 //Edit Menu 
		 
		 JMenu edit = new JMenu("Edit");
		 edit.setFont(new Font("AERIAL", Font.PLAIN, 14));
		 
		 JMenuItem copy = new JMenuItem("Copy");
		 copy.addActionListener(this);
		 copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK)); //Add Neumonics
		 
		 JMenuItem paste = new JMenuItem("Paste");
		 paste.addActionListener(this);
		 paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK)); 

		 JMenuItem cut = new JMenuItem("Cut");
		 cut.addActionListener(this);
		 cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK)); 
		 
		 JMenuItem selectAll = new JMenuItem("Select All");
		 selectAll.addActionListener(this);
		 selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK)); 
		 
		 edit.add(copy);
		 edit.add(paste);
		 edit.add(cut);
		 edit.add(selectAll);
	
		 
		 menubar.add(edit);
		 
		 //************************************************************************//
	//About Menu 
			 
			 JMenu about = new JMenu("About");
			 about.setFont(new Font("AERIAL", Font.PLAIN, 14));
			 
			 JMenuItem aboutNotepad = new JMenuItem("About Notepad");
			 aboutNotepad.addActionListener(this);
			
			 aboutNotepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK)); //Add Neumonics
			 
			 
			 about.add(aboutNotepad);
			 
			 menubar.add(about);
		 
	//--------------------------------------------------------------------------------//
			  setJMenuBar(menubar);
			  
			  //Setting text area
			  area = new JTextArea();
			  area.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
			  area.setLineWrap(true);
			  area.setWrapStyleWord(true);
			  
			  //For adding scroll Bar
			  JScrollPane pane = new JScrollPane(area);
			  pane.setBorder(BorderFactory.createEmptyBorder());
			  add(pane);
			  
			  
		 
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		 setVisible(true);  //For displaying frame, this should be last statement in constructor
	}

	//Action performed on each button
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("New")) //For New Action
			area.setText("");
		else if(e.getActionCommand().equals("Open")) { //Open Action
			JFileChooser chooser = new JFileChooser();
			chooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
			chooser.addChoosableFileFilter(restrict);
			int action = chooser.showOpenDialog(this);
			
			if(action != JFileChooser.APPROVE_OPTION)
				return;
			
			File file = chooser.getSelectedFile();
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				area.read(reader, null);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		else if(e.getActionCommand().equals("Save")) { //Save action
			JFileChooser saveas = new JFileChooser();
			saveas.setApproveButtonText("Save");
			
			int action = saveas.showOpenDialog(this);
			
			if(action != JFileChooser.APPROVE_OPTION)
				return;
			
			File file = new File(saveas.getSelectedFile() + ".txt");
			BufferedWriter outFile = null;
			try {
				outFile = new BufferedWriter(new FileWriter(file));
				area.write(outFile);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}else if(e.getActionCommand().equals("Print")) {
			try {
				area.print();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}else if(e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}else if(e.getActionCommand().equals("Copy")) {
			text = area.getSelectedText();
		}else if(e.getActionCommand().equals("Paste")) {
			area.insert(text, area.getCaretPosition());
		}else if(e.getActionCommand().equals("Cut")) {
			text = area.getSelectedText();
			area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
		}else if(e.getActionCommand().equals("Select All")) {
			area.selectAll();
		}else if(e.getActionCommand().equals("About Notepad")) {
			new About().setVisible(true);
		}
			
		
	}
	
	public static void main(String[] args) {
		
		Notepad notepad = new Notepad();

	}

	

}
