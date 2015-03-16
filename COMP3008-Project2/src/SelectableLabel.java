import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SelectableLabel extends JLabel {
	public boolean selected;
	
	public SelectableLabel(){
		selected=false;
	}
	
	public SelectableLabel(String s, int i){
		super(s, i);
		selected=false;
	}
	
	public SelectableLabel(ImageIcon s, int i){
		super(s, i);
		selected=false;
	}
}
