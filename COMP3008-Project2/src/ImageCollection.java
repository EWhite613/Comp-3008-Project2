import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class ImageCollection {
	public ArrayList<ImageIcon> array;
	
	public ImageCollection(){
		array = new ArrayList<ImageIcon>();
		generateImages();
	}
	
	public void generateImages(){
		File folder = new File("images");
		File[] listOfFiles = folder.listFiles();
		
		for( File f : listOfFiles){
			System.out.println("Adding Image: " + f.getAbsolutePath());
			array.add(new ImageIcon(f.getAbsolutePath()));
		}
	}
}
