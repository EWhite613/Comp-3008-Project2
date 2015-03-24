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

/**
 * Password Scheme to test individual passwords
 */
public class PasswordScheme extends JFrame {

	private JPanel contentPane;
	private JPanel GridPanel;
	public Flag flags[];
	public SelectableLabel labels[];
	private String user;
	private String Domain;
	private int[] password;
	public ImageCollection images;
	
		
	

	/**
	 * Create the frame.
	 * @param username: user logged in
	 * @param dom: Domain for which user has chosen to their test password
	 */
	public PasswordScheme(String username, String dom) {
		System.out.println("Domain is " + dom);
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
		Domain = dom;
		this.setTitle("User: " + user);
		flags = new Flag[80];
		images = new ImageCollection();
		GenerateTable();
		password = getUserPassword(user);
		System.out.println(Arrays.toString(password));
		
	}
	
	/**
	 * Populate grid with Flags
	 */
	public void GenerateTable(){
		for (int i = 0; i < 80; i++) {
			//Get flag from ImageCollectionClass
			Flag f = images.array.get(i);
			f.setBounds(0, 0, 90, 90);
			//Add the Action Listner GridMouseListener to the indiviual Flag
            f.addMouseListener(new GridMouseListener(i, f));
            flags[i] = f;
            //Add Flag to cell
            GridPanel.add(f);
        }	
	}
	
	/**
	 * Get password of User given the domain this PasswordScheme was created with
	 * @param user: User to get password for
	 * @return integer array of user password. Each element links to specific grid cell( 1 - 80)
	 */
	public int[] getUserPassword(String user){
		
		try{
    		//HARD CODED DATABASE NAME:
    		Connection database = DriverManager.getConnection("jdbc:sqlite:Project2.data");
    	    //Sql Query get Password of User's given Domain  
    		PreparedStatement prep = database.prepareStatement(
		            "Select Password From UserAccounts where Username=? and Domain=?;");
    		
    		prep.setString(1, user);
    		prep.setString(2, Domain);
    		ResultSet rs = prep.executeQuery();
    		
    		rs.next();
    		String pass = rs.getString("Password");
    		//Convert Text to int[]
    		return StringtoIntArray(pass);
    		
    		}catch(SQLException ex){
    			ex.printStackTrace();
    			return null;
    		}
		
	}
	
	/**
	 * Convert String of integer array to integer array
	 * @param arr: integer array as type String
	 * @return integer array that matches the passed in parameter
	 */
	public int[] StringtoIntArray(String arr){
		//Replace [,], and spaces with ""(Format goes to 3,2,5). Then split string on ",".
		String[] items = arr.replaceAll("\\[", "").replaceAll("\\]", "").replace(" ", "").split(",");
		
		//Initialize integer array to be of the same length of the split string
		int[] results = new int[items.length];
		
		//Convert each string to integer
		for (int i = 0; i < items.length; i++) {
		    try {
		        results[i] = Integer.parseInt(items[i]);
		    } catch (NumberFormatException nfe) {};
		}
		return results;
	}
	
	/**
	 * Check if user provided password matches the one in the database
	 * @return boolean that exclaims if given password matches the one in database or not
	 */
	public boolean checkPassword(){
		int[] result = new int[6];
		int count = 0;
		//Get user inputed password and store in result
		for(int i=0;i<80;i++){
			Flag l = flags[i];
			if (l.selected == true){
				//selected
				if (count > 5){
					//if password is more then size 6 then it is wrong since each password is only 6 elements
					return false;
				}else{
					//store selected item and increase count
					result[count] = i;
					count++;
				}
				
			}
		}
		//Sort both password since equals returns true if order is same and elements are the same
		Arrays.sort(password);
		Arrays.sort(result);
		
		System.out.println("Password: " + Arrays.toString(password) + " Result: " + Arrays.toString(result));
		//Return true if passwords are equal else false
		return Arrays.equals(password,result);
	}
	
	/**
	 * Submit Button Listener: User has submitted a password that they wish to check if it is correct
	 */
	private void Submit(){
		boolean result = checkPassword();
		if (result == true){
			JOptionPane.showMessageDialog(this, "Password Successfully Entered!");
			//close Frame
			this.dispose();
		}else{
			JOptionPane.showMessageDialog(this, "Password was Incorrect");
			//Reset Grid
			for(Flag f : flags){
				f.selected = false;
				f.lblImage.setBorder(null);
			}
		}
	}
}
