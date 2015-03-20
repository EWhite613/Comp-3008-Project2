import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class TestPasswordScheme extends JFrame {

	private JPanel contentPane;
	private String userName;
	private String domain;
	private String password;
	int currentDomain;
	private int Success;
	private int Failures;
	ArrayList<String> domains;
	ArrayList<String> passwords;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestPasswordScheme frame = new TestPasswordScheme();
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
	public TestPasswordScheme(String user) {
		currentDomain = 0;
		Success = 0;
		Failures = 0;
		userName = user;
		this.setTitle("User: " + userName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		getUserDomains(userName);
		setContentPane(contentPane);
		password = getUserPassword(user);
		this.setTitle("User: " + user + ", Domain: " + domains.get(currentDomain));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(189, 100, 134, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(91, 106, 96, 16);
		panel.add(lblPassword);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Submit();
			}
		});
		btnSubmit.setBounds(154, 160, 117, 29);
		panel.add(btnSubmit);
	}
	
	public void getUserDomains(String user){
		
		try{
    		//HARD CODED DATABASE NAME:
    		Connection database = DriverManager.getConnection("jdbc:sqlite:Project2.data");
    	       //create a statement object which will be used to relay a
    	       //sql query to the database
    		PreparedStatement prep = database.prepareStatement(
		            "Select Domain, Password From UserAccounts where Username=? order by rowid;");
    		
    		prep.setString(1, user);
    		
    		ResultSet rs = prep.executeQuery();
    		domains = new ArrayList<String>();
    		passwords = new ArrayList<String>();
    		
    		int count = 0;
    		
    		while(rs.next()){
    			if (count > 2){
    				break;
    			}else{
    				domains.add(rs.getString("Domain"));
    				passwords.add(rs.getString("Password"));
    				count++;
    			}
    		}
    	}catch(SQLException ex){
    			ex.printStackTrace();	
    	}
	}
	
	public String getUserPassword(String user){
		return passwords.get(currentDomain);
    }
	
	private void Submit(){
		if (password.equals(textField.getText())){
			if(currentDomain < 2){
				currentDomain++;
				JOptionPane.showMessageDialog(this, "Success. Moving on to next password for the domain: " + domains.get(currentDomain));
				password = getUserPassword(userName);
				this.setTitle("User: " + userName + ", Domain: " + domains.get(currentDomain));
			}else{
				JOptionPane.showMessageDialog(this, "All Passwords Successfully Entered!");
				this.dispose();
			}
			Success++;
		}else{
			JOptionPane.showMessageDialog(this, "Password was Incorrect");
			Failures++;
		}
	}

}
