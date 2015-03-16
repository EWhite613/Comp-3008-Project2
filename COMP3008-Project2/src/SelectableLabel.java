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
}
