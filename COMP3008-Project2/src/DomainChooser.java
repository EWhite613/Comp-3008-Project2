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
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DomainChooser extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> cbDomain;
	private String user;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public DomainChooser(String user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.user = user;
		
		cbDomain = new JComboBox<String>();
		cbDomain.setBounds(150, 104, 167, 20);
		contentPane.add(cbDomain);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PasswordScheme p = new PasswordScheme(user, cbDomain.getSelectedItem().toString());
				p.setVisible(true);
			}
		});
		btnSubmit.setBounds(190, 173, 89, 23);
		contentPane.add(btnSubmit);
		
		PopulateCombobox();
	}
	
	public void PopulateCombobox(){
		try{
    		//HARD CODED DATABASE NAME:
    		Connection database = DriverManager.getConnection("jdbc:sqlite:Project2.data");
    	       //create a statement object which will be used to relay a
    	       //sql query to the database
    		PreparedStatement prep = database.prepareStatement(
		            "Select Domain From UserAccounts where Username=?;");
    		
    		prep.setString(1, user);
    		ResultSet rs = prep.executeQuery();
    		
    		while( rs.next()){
    			String dom = rs.getString("Domain");
    			System.out.println(dom);
    			cbDomain.addItem(dom);
    		}
    		
    		}catch(SQLException ex){
    			ex.printStackTrace();
    		}
	}
}
