import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class ImageCollection {
	public ArrayList<Flag> array;
	
	
	public ImageCollection(){
		array = new ArrayList<Flag>();
		generateImages();
	}
	
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
