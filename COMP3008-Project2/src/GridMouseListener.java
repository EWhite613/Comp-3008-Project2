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
	    		flag.lblImage.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
	    		flag.selected = true;
	    		selected = true;
	    	}else{
	    		flag.lblImage.setBorder(null);
	    		selected = false;
	    		flag.selected = false;
	    	}
	    }

}

