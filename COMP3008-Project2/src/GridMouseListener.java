import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;


public class GridMouseListener extends MouseAdapter {
	
	 private final int index;
	 private final JLabel label;
	 private boolean selected;

	    public GridMouseListener(int index, JLabel label) {
	        this.index = index;
	        this.label = label;
	        this.selected = false;
	    }

	    @Override
	    public void mouseClicked(MouseEvent e){
	    	if (selected == false){
	    		label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.BLUE));
	    		selected = true;
	    	}else{
	    		label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	    		selected = false;
	    	}
	    }
	    
	    @Override
	    public void mouseEntered(MouseEvent e) {
	        System.out.println("Mouse entered for Label  " + index);
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
	        System.out.println("Mouse exited for Label " + index);
	    }

}
