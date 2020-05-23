package sandbox.readingFromTxtFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadingFromTxtFile {
	
	public static Map<String, String> readXPaths(){
		Map<String, String> xPaths = new HashMap<String, String>();
		File myObj = new File("xPaths.txt");
		Scanner myReader;
		try {
			myReader=new Scanner(myObj);
			while(myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String values[] = data.split("'");
				System.out.println(values[0]+" "+values[1]);
				xPaths.put(values[0], values[1]);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("error");
			e.printStackTrace();
		}
		return xPaths;
	}

}
