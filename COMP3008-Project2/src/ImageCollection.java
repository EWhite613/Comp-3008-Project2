import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * Collection of Flags to populate grid with
 */
public class ImageCollection {
	public ArrayList<Flag> array;
	
	/**
	 * Constructor
	 */
	public ImageCollection(){
		array = new ArrayList<Flag>();
		generateImages();
	}
	/**
	 * Get all images from folder images, and store their image and name in an array.
	 */
	public void generateImages(){
		File folder = new File("images");
		File[] listOfFiles = folder.listFiles();
		
		for( File f : listOfFiles){
			System.out.println("Adding Image: " + f.getAbsolutePath());
			String country = f.getName();
			array.add(new Flag(new ImageIcon(f.getAbsolutePath()), country));
		}
	}
}
