import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * 
 * Panel to represent individual flags. Each panel has the image of a flag and label that tells the country the flag represents
 *
 */
public class Flag extends JPanel {
	
	public JLabel lblImage;
	public JLabel lblName;
	public ImageIcon flag;
	public boolean selected;
	/**
	 * Create the panel.
	 * @param f: the image of the flag
	 * @param Country: The Country associated with the given flag
	 */
	public Flag(ImageIcon f, String Country) {
		setLayout(null);
		this.setMinimumSize( new Dimension(80,80));
		this.setMaximumSize(new Dimension(95, 95));
		this.setSize( new Dimension(80, 80));
		this.setPreferredSize(new Dimension(110, 87));
		
		flag = f;
		
		lblImage = new JLabel(f);
		lblImage.setBounds(0, 0, 80, 55);
		add(lblImage);
		//System.out.println("Image Size: " + lblImage.getSize().toString());
		
		Country = Country.replace(".png", "");
		Country = Country.replace("_", " ");
		lblName = new JLabel(Country);
		lblName.setBounds(0, 51, 124, 14);
		add(lblName);
		
		selected = false;

	}
}
