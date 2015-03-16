import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import java.util.Arrays;


public class RegisterPassword extends JFrame {

	private JPanel contentPane;
	private JPanel GridPanel;
	private String user;
	private JButton btnGeneratePassword;
	public JLabel labels[];
	private int password[];

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPassword frame = new RegisterPassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public RegisterPassword(String username) {
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
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Submit(username, password);
			}
		});
		btnSubmit.setBounds(226, 227, 89, 23);
		contentPane.add(btnSubmit);
		
		btnGeneratePassword = new JButton("Generate Password");
		btnGeneratePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneratePassword();
			}
		});
		btnGeneratePassword.setBounds(55, 227, 136, 23);
		contentPane.add(btnGeneratePassword);
		user = username;
		labels = new JLabel[80];
		this.setTitle("User: " + user);
		GenerateTable();
	}
	
	public void GenerateTable(){
		for (int i = 0; i < 80; i++) {
            JLabel l = new JLabel("" + i, JLabel.CENTER);
            //JLabel l = new JLabel(new ImageIcon("image_file.png"), JLabel.CENTER);
            l.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            l.setFont(l.getFont().deriveFont(20f));
            labels[i] = l;
            GridPanel.add(l);
        }	
	}
	
	public void GeneratePassword(){
		password = new int[6];
		Random randomGenerator = new Random();
	    for (int idx = 0; idx < 6; ++idx){
	      int randomInt = randomGenerator.nextInt(80);
	      password[idx] = randomInt;
	    }
	    System.out.println(Arrays.toString(password));
	    
	    showPassword();
	}
	
	public void showPassword(){
		for(JLabel l : labels){
			l.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		}
		for (int place : password){
			labels[place].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.BLUE));
		}
	}
	
	public void Submit(String user, int[] password){
		if (password != null){
			try{
	    		//HARD CODED DATABASE NAME:
	    		Connection database = DriverManager.getConnection("jdbc:sqlite:Project2");
	    	       //create a statement object which will be used to relay a
	    	       //sql query to the database
	    		PreparedStatement prep = database.prepareStatement(
			            "Insert or replace into UserAccounts (Username, Password) values (?, ?);");
	    		
	    		prep.setString(1, user);
	    		prep.setString(2, Arrays.toString(password));
	    		prep.execute();
	    		
	    		}catch(SQLException ex){
	    			ex.printStackTrace();
	    		}
		}else{
			JOptionPane.showMessageDialog(this,"You must first generate a password");
		}
	}

}
