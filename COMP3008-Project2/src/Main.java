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
				register();
			}
		});
		btnRegister.setBounds(109, 117, 89, 23);
		frmLogin.getContentPane().add(btnRegister);
	}
	
	public void login(){
		try{
    		//HARD CODED DATABASE NAME:
    		Connection database = DriverManager.getConnection("jdbc:sqlite:Project2");
    	       //create a statement object which will be used to relay a
    	       //sql query to the database
    		PreparedStatement prep = database.prepareStatement(
		            "Select Password From UserAccounts where Username=?;");
    		
    		prep.setString(1, txtUsername.getText());
    		ResultSet rs = prep.executeQuery();
    		
    		
    		if (!rs.next()){
    			JOptionPane.showMessageDialog(frmLogin, "You must first register a password for this user.");
    		}else{
    			PasswordScheme p = new PasswordScheme(txtUsername.getText());
    			p.setVisible(true);
    		}
    		}catch(SQLException ex){
    			ex.printStackTrace();
    			
    		}
		
		
	}
	public void register(){
		RegisterPassword r = new RegisterPassword(txtUsername.getText());
		r.setVisible(true);
	}
}
