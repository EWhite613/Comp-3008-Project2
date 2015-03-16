import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;


public class GridMouseListener extends MouseAdapter {
	
	 private final int index;
	 private final SelectableLabel label;
	 private boolean selected;

	    public GridMouseListener(int index, SelectableLabel label) {
	        this.index = index;
	        this.label = label;
	        this.selected = false;
	    }

	    @Override
	    public void mouseClicked(MouseEvent e){
	    	if (selected == false){
	    		label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.BLUE));
	    		label.selected = true;
	    		selected = true;
	    	}else{
	    		label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	    		selected = false;
	    		label.selected = false;
	    	}
	    }

}
