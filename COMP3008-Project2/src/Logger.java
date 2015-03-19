import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class Logger {
	
public static void LogEvent(String user, String mode, String Event){
		
		try{
    		//HARD CODED DATABASE NAME:
    		Connection database = DriverManager.getConnection("jdbc:sqlite:Project2.data");
    	       //create a statement object which will be used to relay a
    	       //sql query to the database
    		PreparedStatement prep = database.prepareStatement(
		            "Insert into Logs (Username, Mode, Event, Timestamp) values (?, ?, ?, ?);");
    		
    		prep.setString(1, user);
    		prep.setString(2, mode);
    		prep.setString(3, Event);
    		prep.setString(4, (new Timestamp(System.currentTimeMillis())).toString());
    		
    		prep.execute();
    		
    		database.close();
    		
    		}catch(SQLException ex){
    			ex.printStackTrace();
    			
    		}
		
	}

}
