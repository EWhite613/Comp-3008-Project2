import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Initial Login Frame that present the option of Registering a password, testing a password, and password testing framework that applies to the project guidelines
 */
public class Main {

	private JFrame frmLogin;
	private JTextField txtUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblUser = new JLabel("User: ");
		lblUser.setBounds(102, 71, 46, 14);
		frmLogin.getContentPane().add(lblUser);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(158, 68, 86, 20);
		frmLogin.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnSubmit = new JButton("Login");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		btnSubmit.setBounds(233, 117, 89, 23);
		frmLogin.getContentPane().add(btnSubmit);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( txtUsername.getText().isEmpty()){
					javax.swing.JOptionPane.showMessageDialog(null, "Please type in a user name!");
				}else{
					register();
				}
			}
		});
		btnRegister.setBounds(109, 117, 89, 23);
		frmLogin.getContentPane().add(btnRegister);
		
		JButton btnTestPasswordScheme = new JButton("Test Password Scheme");
		btnTestPasswordScheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				testPasswordScheme();
			}
		});
		btnTestPasswordScheme.setBounds(110, 169, 200, 50);
		frmLogin.getContentPane().add(btnTestPasswordScheme);
	}
	
	/**
	 * Test Password Scheme Button Listener: Launch the Frame that tests password according to project guidelines
	 */
	public void testPasswordScheme(){
		try{
    		//HARD CODED DATABASE NAME:
    		Connection database = DriverManager.getConnection("jdbc:sqlite:Project2.data");
    	    
    		//SQL query that gets the of password domains a user has
    		PreparedStatement prep = database.prepareStatement(
		            "Select count(Domain) From UserAccounts where Username=?;");
    		
    		prep.setString(1, txtUsername.getText());
    		ResultSet rs = prep.executeQuery();
    		
    		rs.next();
    		int counter =rs.getInt("count(Domain)");
    		//if the user has less than 3 passwords they cannot Test the password scheme
    		if (counter <3){
        		database.close();
    			JOptionPane.showMessageDialog(frmLogin, "You must first register a password for this user. And have three different domain passwords");
    		}else{
    				
    			database.close();
    			
    			//User has 3 or more password so we can launch the Test Password Scheme
    			TestPasswordScheme t = new TestPasswordScheme(txtUsername.getText());
    			t.setVisible(true);
    		}
    		
    		}catch(SQLException ex){
    			ex.printStackTrace();
    			
    		}
	}
	
	/**
	 * Login Button Listener: Bring user to Domain Chooser to test individual passwords
	 */
	public void login(){
		try{
    		//HARD CODED DATABASE NAME:
    		Connection database = DriverManager.getConnection("jdbc:sqlite:Project2.data");
    	    //SQL query that gets passwords for a user
    		PreparedStatement prep = database.prepareStatement(
		            "Select Password From UserAccounts where Username=?;");
    		
    		prep.setString(1, txtUsername.getText());
    		ResultSet rs = prep.executeQuery();
    		
    		//If a user has no password then they cannot proceed in login
    		if (!rs.next()){
        		database.close();
    			JOptionPane.showMessageDialog(frmLogin, "You must first register a password for this user.");
    		}else{
    			database.close();
        		
    			//Display another Frame that allows user to choose the domain of the password they wish to test
    			DomainChooser d = new DomainChooser(txtUsername.getText());
    			d.setVisible(true);
    			
    		}

    		}catch(SQLException ex){
    			ex.printStackTrace();
    			
    		}
		
		
	}
	/**
	 * Register button Click Listener: Create Password for given user on a domain of their choosing
	 */
	public void register(){
		//If the username is provided display the Register Password Frame for the user
		
		RegisterPassword r = new RegisterPassword(txtUsername.getText());
		r.setVisible(true);
		
	}
}
