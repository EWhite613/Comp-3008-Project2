import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;
import java.awt.Font;


public class RegisterPassword extends JFrame {

	private JPanel contentPane;
	private  String userName;
	private JTextField txtDomain;
	private String password;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPassword frame = new RegisterPassword("nnnnewww");
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
	public RegisterPassword(String user) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		userName = user;
		password = "";
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		pwdGen();
		JLabel lblPwd = new JLabel(password);
		lblPwd.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwd.setBounds(100, 58, 223, 43);
		panel.add(lblPwd);

		JButton btnGeneratePassword = new JButton("Generate Password");
		btnGeneratePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pwdGen();
				lblPwd.setText(password);
			}
		});
		btnGeneratePassword.setBounds(133, 118, 162, 29);
		panel.add(btnGeneratePassword);

		txtDomain = new JTextField();
		txtDomain.setText("Facebook");
		txtDomain.setBounds(189, 185, 134, 28);
		panel.add(txtDomain);
		txtDomain.setColumns(10);

		JLabel lblDomain = new JLabel("Domain:");
		lblDomain.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDomain.setBounds(93, 191, 94, 16);
		panel.add(lblDomain);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Submit(userName, password, txtDomain.getText());
			}
		});
		btnSubmit.setBounds(159, 225, 117, 29);
		panel.add(btnSubmit);

		this.setTitle("User: " + userName);
	}

	//generate a random 6-digit lower-case password
	public void pwdGen(){
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		password = sb.toString();
	}

	//submit button handler
	public void Submit(String user, String password, String domain){
		if (password != null){
			try{
	    		//HARD CODED DATABASE NAME:
	    		Connection database = DriverManager.getConnection("jdbc:sqlite:Project2.data");
	    	       //create a statement object which will be used to relay a
	    	       //sql query to the database
	    		PreparedStatement prep = database.prepareStatement(
			            "Insert or replace into UserAccounts (Username, Domain, Password) values (?, ? , ?);");

	    		prep.setString(1, user);
	    		prep.setString(2, domain);
	    		prep.setString(3, password);
	    		prep.execute();

	    		}catch(SQLException ex){
	    			ex.printStackTrace();
	    		}
			this.dispose();
		}else{
			JOptionPane.showMessageDialog(this,"You must first generate a password and Your Domain for the Password must not be empty");
		}
	}
}
