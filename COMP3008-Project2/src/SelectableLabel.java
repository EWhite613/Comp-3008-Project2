import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * @deprecated
 */
public class SelectableLabel extends JLabel {
	public boolean selected;
	/**
	 * @deprecated
	 */
	public SelectableLabel(){
		selected=false;
	}
	/**
	 * @deprecated
	 */
	public SelectableLabel(String s, int i){
		super(s, i);
		selected=false;
	}
	/**
	 * @deprecated
	 */
	public SelectableLabel(ImageIcon s, int i){
		super(s, i);
		selected=false;
	}
}
