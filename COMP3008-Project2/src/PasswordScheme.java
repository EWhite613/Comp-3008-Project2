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


public class PasswordScheme extends JFrame {

	private JPanel contentPane;
	private JPanel GridPanel;
	public SelectableLabel labels[];
	private String user;
	private int[] password;
	public ImageCollection images;
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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		GridPanel = new JPanel();
		GridPanel.setBounds(10, 11, 722, 416);
		GridPanel.setLayout(new GridLayout(10, 8, 3, 3));
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(311, 433, 89, 23);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Submit();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(GridPanel);
		contentPane.add(btnSubmit);
		user = username;
		this.setTitle("User: " + user);
		labels = new SelectableLabel[80];
		images = new ImageCollection();
		GenerateTable();
		password = getUserPassword(user);
		System.out.println(Arrays.toString(password));
		
	}
	
	public void GenerateTable(){
		for (int i = 0; i < 80; i++) {
			SelectableLabel l = new SelectableLabel("" + i, SelectableLabel.CENTER);
            //SelectableLabel l = new SelectableLabel(images.array.get(i), SelectableLabel.CENTER);
            l.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            l.setFont(l.getFont().deriveFont(20f));
            l.addMouseListener(new GridMouseListener(i, l));
            labels[i] = l;
            GridPanel.add(l);
        }	
	}
	
	public int[] getUserPassword(String user){
		
		try{
    		//HARD CODED DATABASE NAME:
    		Connection database = DriverManager.getConnection("jdbc:sqlite:Project2");
    	       //create a statement object which will be used to relay a
    	       //sql query to the database
    		PreparedStatement prep = database.prepareStatement(
		            "Select Password From UserAccounts where Username=?;");
    		
    		prep.setString(1, user);
    		ResultSet rs = prep.executeQuery();
    		
    		rs.next();
    		String pass = rs.getString("Password");
    		
    		return StringtoIntArray(pass);
    		
    		}catch(SQLException ex){
    			ex.printStackTrace();
    			return null;
    		}
		
	}
	
	public int[] StringtoIntArray(String arr){
		String[] items = arr.replaceAll("\\[", "").replaceAll("\\]", "").replace(" ", "").split(",");

		int[] results = new int[items.length];

		for (int i = 0; i < items.length; i++) {
		    try {
		        results[i] = Integer.parseInt(items[i]);
		    } catch (NumberFormatException nfe) {};
		}
		return results;
	}
	
	public boolean checkPassword(){
		int[] result = new int[6];
		int count = 0;
		for(int i=0;i<80;i++){
			SelectableLabel l = labels[i];
			if (l.selected == true){
				//selected
				if (count > 6){
					return false;
				}else{
					result[count] = i;
					count++;
				}
				
			}
		}
		Arrays.sort(password);
		Arrays.sort(result);
		System.out.println("Password: " + Arrays.toString(password) + " Result: " + Arrays.toString(result));
		return Arrays.equals(password,result);
	}
	
	private void Submit(){
		boolean result = checkPassword();
		if (result == true){
			JOptionPane.showMessageDialog(this, "Password Successfully Entered!");
		}else{
			JOptionPane.showMessageDialog(this, "Password was Incorrect");
		}
	}
}
