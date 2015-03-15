import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.FlowLayout;


public class PasswordScheme extends JFrame {

	private JPanel contentPane;
	private String user;

	/**
	 * Launch the application.
	 */
	
	/*public void run() {
		try {
			PasswordScheme frame = new PasswordScheme();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} */
		
	

	/**
	 * Create the frame.
	 */
	public PasswordScheme(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(10, 8, 3, 3));
		user = username;
		this.setTitle("User: " + user);
		GenerateTable();
		
	}
	
	public void GenerateTable(){
		for (int i = 0; i < 80; i++) {
            JLabel l = new JLabel("" + i, JLabel.CENTER);
            //JLabel l = new JLabel(new ImageIcon("image_file.png"), JLabel.CENTER);
            l.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            l.setFont(l.getFont().deriveFont(20f));
            contentPane.add(l);
        }	
	}
}
