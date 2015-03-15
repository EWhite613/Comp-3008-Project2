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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PasswordScheme extends JFrame {

	private JPanel contentPane;
	private JPanel GridPanel;
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
		contentPane.setLayout(null);
		
		GridPanel = new JPanel();
		GridPanel.setBounds(10, 11, 414, 212);
		contentPane.add(GridPanel);
		GridPanel.setLayout(new GridLayout(10, 8, 3, 3));
		
		JLabel lblResult = new JLabel("Result: ");
		lblResult.setBounds(270, 234, 46, 14);
		contentPane.add(lblResult);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnSubmit.setBounds(74, 230, 89, 23);
		contentPane.add(btnSubmit);
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
            l.addMouseListener(new GridMouseListener(i, l));
            GridPanel.add(l);
        }	
	}
}
