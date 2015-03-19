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

import javax.swing.JTextField;


public class RegisterPassword extends JFrame {

	private JPanel contentPane;
	private JPanel GridPanel;
	private String user;
	private JButton btnGeneratePassword;
	public JLabel labels[];
	private int password[];
	private JTextField txtDomain;
	public Flag flags[];
	public ImageCollection images;
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1215, 844);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		flags = new Flag[80];
		images = new ImageCollection();
		
		GridPanel = new JPanel();
		GridPanel.setBounds(10, 11, 1159, 661);
		contentPane.add(GridPanel);
		GridPanel.setLayout(new GridLayout(10, 8, 3, 3));
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Submit(username, password, txtDomain.getText());
			}
		});
		btnSubmit.setBounds(757, 683, 89, 23);
		contentPane.add(btnSubmit);
		
		btnGeneratePassword = new JButton("Generate Password");
		btnGeneratePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneratePassword();
			}
		});
		btnGeneratePassword.setBounds(369, 683, 136, 23);
		contentPane.add(btnGeneratePassword);
		
		txtDomain = new JTextField();
		txtDomain.setText("Facebook");
		txtDomain.setBounds(612, 684, 86, 20);
		contentPane.add(txtDomain);
		txtDomain.setColumns(10);
		
		JLabel lblDomain = new JLabel("Domain:");
		lblDomain.setBounds(556, 687, 46, 14);
		contentPane.add(lblDomain);
		user = username;
		labels = new JLabel[80];
		this.setTitle("User: " + user);
		GenerateTable();
	}
	
	public void GenerateTable(){
		for (int i = 0; i < 80; i++) {
			//SelectableLabel l = new SelectableLabel("" + i, SelectableLabel.CENTER);
            //SelectableLabel l = new SelectableLabel(images.array.get(i), SelectableLabel.CENTER);
            //l.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            //l.setFont(l.getFont().deriveFont(20f));
			Flag f = images.array.get(i);
			f.setBounds(0, 0, 90, 90);
            //f.addMouseListener(new GridMouseListener(i, f));
            flags[i] = f;
            GridPanel.add(f);
        }	
	}
	
	public void GeneratePassword(){
		password = new int[6];
		Random randomGenerator = new Random();
		boolean con = true;
		int randomInt = 0;
	    for (int idx = 0; idx < 6; ++idx){
	      while (con == true){
	    	 randomInt = randomGenerator.nextInt(80);
	    	 if (contains(password, randomInt) == false){
	    		 break;
	    	 }
	      }
	      password[idx] = randomInt;
	    }
	    System.out.println(Arrays.toString(password));
	    
	    showPassword();
	}
	
	public void showPassword(){
		for(Flag l : flags){
			l.lblImage.setBorder(null);
		}
		for (int place : password){
			flags[place].lblImage.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
		}
	}
	
	public void Submit(String user, int[] password, String domain){
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
	    		prep.setString(3, Arrays.toString(password));
	    		prep.execute();
	    		
	    		}catch(SQLException ex){
	    			ex.printStackTrace();
	    		}
			this.dispose();
		}else{
			JOptionPane.showMessageDialog(this,"You must first generate a password and Your Domain for the Password must not be empty");
		}
	}
	
	public boolean contains(int[] arr, int i){
		for (int e : arr){
			if(e == i){
				return true;
			}
		}
		return false;
	}
}
