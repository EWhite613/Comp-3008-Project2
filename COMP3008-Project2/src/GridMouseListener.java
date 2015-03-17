import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;


public class GridMouseListener extends MouseAdapter {
	
	 private final int index;
	 private final Flag flag;
	 private boolean selected;

	    public GridMouseListener(int index, Flag f) {
	        this.index = index;
	        this.flag = f;
	        this.selected = false;
	    }

	    @Override
	    public void mouseClicked(MouseEvent e){
	    	if (selected == false){
	    		flag.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.BLUE));
	    		flag.selected = true;
	    		selected = true;
	    	}else{
	    		flag.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	    		selected = false;
	    		flag.selected = false;
	    	}
	    }

}
