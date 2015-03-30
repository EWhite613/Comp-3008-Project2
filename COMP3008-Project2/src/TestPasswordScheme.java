import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TestPasswordScheme extends JFrame {
	
	ArrayList<String> Domains;
	ArrayList<String> passwords;
	int currentDomain;
	private JPanel contentPane;
	private JPanel GridPanel;
	public Flag flags[];
	public SelectableLabel labels[];
	private String user;
	private String Domain;
	private int[] password;
	public ImageCollection images;
	private int Success;
	private int Failures;
	

	/**
	 * Create the frame.
	 * @param username: User testing the password scheme
	 */
	public TestPasswordScheme(String username) {
		
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1215, 844);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		GridPanel = new JPanel();
		GridPanel.setBounds(10, 11, 1159, 661);
		GridPanel.setLayout(new GridLayout(10, 8, 3, 3));
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(508, 677, 89, 23);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Submit();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(GridPanel);
		contentPane.add(btnSubmit);
		user = username;
		getUserDomains(user);
		this.Success = 0;
		this.Failures = 0;
		this.setTitle("User: " + user);
		flags = new Flag[80];
		images = new ImageCollection();
		GenerateTable();
		password = getUserPassword(user);
		
		//Log that the user has started the test password scheme process
		Logger.LogEvent(user, "Login", "Start");
		
		this.setTitle("User: " + user + ", Domain: " + Domains.get(currentDomain));
		System.out.println("[" + flags[password[0]].lblName.getText() + " , " + flags[password[1]].lblName.getText() + " , " + flags[password[2]].lblName.getText() + " , "+ flags[password[3]].lblName.getText() + " , " + flags[password[4]].lblName.getText() + " , " + flags[password[5]].lblName.getText() + " ] ");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	/**
	 * Get User Passwords for 3 domains
	 * @param user: User to get passwords from
	 */
	public void getUserDomains(String user){
		
		try{
    		//HARD CODED DATABASE NAME:
			Connection database = DriverManager.getConnection("jdbc:sqlite:Project2.data");
    		//SQL query, get user Domain passwords. Ordered by the time entered ( rowid is a column on every table in sqlite).
			PreparedStatement prep = database.prepareStatement(
		            "Select Domain, Password From UserAccounts where Username=? order by rowid;");
    		
    		prep.setString(1, user);
    		
    		ResultSet rs = prep.executeQuery();
    		//Initialize Arrays of Domains, and Passwords
    		Domains = new ArrayList<String>();
    		passwords = new ArrayList<String>();
    		
    		int count = 0;
    		
    		while(rs.next()){
    			if (count > 2){
    				break;
    			}else{
    				//Add Password and domain to respective arrays
    				// Passwords[i] is linked to Domains[i]
    				Domains.add(rs.getString("Domain"));
    				passwords.add(rs.getString("Password"));
    				count++;
    			}
    		}
    		
    		database.close();

    		
    		}catch(SQLException ex){
    			ex.printStackTrace();
    			
    		}finally{
    			
    		}
		
	}
	
	/**
	 * Populate Datagrid with Flags
	 */
	public void GenerateTable(){
		for (int i = 0; i < 80; i++) {
			//get image from ImagesCollection
			Flag f = images.array.get(i);
			f.setBounds(0, 0, 90, 90);
			//Add action listener GridMouseListener to individual flag
            f.addMouseListener(new GridMouseListener(i, f));
            flags[i] = f;
            //add to panel
            GridPanel.add(f);
        }	
	}
	
	/**
	 * Get users password for the current domain they are testing
	 * @param user: user to get password for
	 * @return integer array representing the users password for the current domain
	 */
	public int[] getUserPassword(String user){
    		return StringtoIntArray(passwords.get(currentDomain));		
	}
	/**
	 * Convert String to integer array
	 * @param arr: Integer array in String Format
	 * @return Integer array that matches given array
	 */
	public int[] StringtoIntArray(String arr){
		//Remove garbage charaters so form is in "3,2,5". Then split the string on comma.
		String[] items = arr.replaceAll("\\[", "").replaceAll("\\]", "").replace(" ", "").split(",");
		
		//Intialize integer array so it is the same length as the new string array
		int[] results = new int[items.length];
		
		//Convert string to integer and store in the integer array
		for (int i = 0; i < items.length; i++) {
		    try {
		        results[i] = Integer.parseInt(items[i]);
		    } catch (NumberFormatException nfe) {};
		}
		return results;
	}
	
	/**
	 * Check if the user submitted password matches the one in the database
	 * @return Return true if given password matches on in the database
	 */
	public boolean checkPassword(){
		int[] result = new int[6];
		int count = 0;
		
		//Get user submitted password and store in result
		for(int i=0;i<80;i++){
			Flag l = flags[i];
			if (l.selected == true){
				//selected
				if (count > 5){
					//If the submitted password has more than 6 elements return false since passwords are only 6 elements
					return false;
				}else{
					result[count] = i;
					count++;
				}
				
			}
		}
		//Sort Arrays since equals looks at order and elements
		Arrays.sort(password);
		Arrays.sort(result);
		
		System.out.println("Password: " + Arrays.toString(password) + " Result: " + Arrays.toString(result));
		return Arrays.equals(password,result);
	}
	
	/**
	 * Submit button Action Listener: User wants to check if submitted password is correct
	 */
	private void Submit(){
		boolean result = checkPassword();
		if (result == true){
			if(currentDomain < 2){
				//If there are more domain to test, move onto the next domain.
				currentDomain++;
				JOptionPane.showMessageDialog(this, "Success. Moving on to next password for the domain: " + Domains.get(currentDomain));
				password = getUserPassword(user);
				this.setTitle("User: " + user + ", Domain: " + Domains.get(currentDomain));
				//Reset clicked items
				for(Flag f : flags){
					f.selected = false;
					f.lblImage.setBorder(null);
				}
				//Log that the user success fully logged in
				Logger.LogEvent(user, "Login", "Success");
				//Log that the user is starting a new login
				Logger.LogEvent(user, "Login", "Start");
				System.out.println("New Password: [" + flags[password[0]].lblName.getText() + " , " + flags[password[1]].lblName.getText() + " , " + flags[password[2]].lblName.getText() + " , "+ flags[password[3]].lblName.getText() + " , " + flags[password[4]].lblName.getText() + " , " + flags[password[5]].lblName.getText() + " ] ");

			}else{
				//User finished the final domain
				JOptionPane.showMessageDialog(this, "All Passwords Successfully Entered!");
				//Log that the user successfully logged in
				Logger.LogEvent(user, "Login", "Success");
			}
			Success++;
		}else{
			//The user failed to submit a correct password
			JOptionPane.showMessageDialog(this, "Password was Incorrect");
			//Log that the user failed their login
			Logger.LogEvent(user, "Login", "Failure");
			//Logger.LogEvent(user, "Login", "Start");
			Failures++;
			//Reset clicked items
			for(Flag f : flags){
				f.selected = false;
				f.lblImage.setBorder(null);
			}
		}
	}
}
