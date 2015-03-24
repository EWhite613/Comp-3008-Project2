import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

/**
 * Action Listener added to the panel Flags
 * @author Eric
 *
 */
public class GridMouseListener extends MouseAdapter {
	
	 private final int index;
	 private final Flag flag;
	 private boolean selected;
	 	
	 	/**
	 	 * Initialize Listener
	 	 * @param index: index on grid of the flag
	 	 * @param f: The flag this action listener is attached to
	 	 */
	    public GridMouseListener(int index, Flag f) {
	        this.index = index;
	        this.flag = f;
	        this.selected = false;
	    }
	    
	    @Override
	    public void mouseClicked(MouseEvent e){
	    	//On Click
	    	
	    	//if the flag has not been previously selected. 
	    	if (selected == false){
	    		//Create a visible border around the flag and set the item attribute selected to true.
	    		flag.lblImage.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
	    		flag.selected = true;
	    		selected = true;
	    	}else{
	    		//Flag has been previously selected
	    		
	    		//Reset flag attributes to false and take away border
	    		flag.lblImage.setBorder(null);
	    		selected = false;
	    		flag.selected = false;
	    	}
	    }

}

