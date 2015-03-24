import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Class to create log information to the database. Which is later exported as a .csv
 */
public class Logger {
	/**
	 * Log the given event for a user
	 * @param user: user the information is to logged for
	 * @param mode: The mode in which the user is in
	 * @param Event: The event that is invoked by the user
	 */
	public static void LogEvent(String user, String mode, String Event){
			
			try{
	    		//HARD CODED DATABASE NAME:
	    		Connection database = DriverManager.getConnection("jdbc:sqlite:Project2.data");
	    	    //Insert in the database the Log information given   
	    		PreparedStatement prep = database.prepareStatement(
			            "Insert into Logs (Username, Mode, Event, Timestamp) values (?, ?, ?, ?);");
	    		
	    		prep.setString(1, user);
	    		prep.setString(2, mode);
	    		prep.setString(3, Event);
	    		// Add a time stamp that is the current time this function was executed
	    		prep.setString(4, (new Timestamp(System.currentTimeMillis())).toString());
	    		
	    		prep.execute();
	    		
	    		database.close();
	
	    		
	    		}catch(SQLException ex){
	    			ex.printStackTrace();
	    			
	    		}
			
		}

}
