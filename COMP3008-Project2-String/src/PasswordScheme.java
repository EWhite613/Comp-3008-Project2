import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PasswordScheme extends JFrame {

	private JPanel contentPane;
	private String userName;
	private String domain;
	private String password;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordScheme frame = new PasswordScheme("usr","dom");
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
	public PasswordScheme(String usr, String dom) {
		userName = usr;
		domain   = dom;
		password = getUserPassword();
		this.setTitle("User: " + userName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
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
	
	public String getUserPassword(){
		
		try{
    		//HARD CODED DATABASE NAME:
    		Connection database = DriverManager.getConnection("jdbc:sqlite:Project2.data");
    	       //create a statement object which will be used to relay a
    	       //sql query to the database
    		PreparedStatement prep = database.prepareStatement(
		            "Select Password From UserAccounts where Username=? and Domain=?;");
    		
    		prep.setString(1, userName);
    		prep.setString(2, domain);
    		ResultSet rs = prep.executeQuery();
    		
    		rs.next();
    		String pass = rs.getString("Password");
    		
    		return pass;
    		
    		}catch(SQLException ex){
    			ex.printStackTrace();
    			return null;
    		}
		
	}
	
	private void Submit(){
		if (password.equals(textField.getText())){
			JOptionPane.showMessageDialog(this, "Password Successfully Entered!");
			this.dispose();
		}else{
			JOptionPane.showMessageDialog(this, "Password was Incorrect");
		}
	}

}
