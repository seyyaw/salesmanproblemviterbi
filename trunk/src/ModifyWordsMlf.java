import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


public class ModifyWordsMlf {

	/**
	 * this function removes txt from mlf file.
	 * @throws IOException 
	 */
	public static void RemoveTxt(String FileName) throws IOException{
		String mlffile=FileUtils.readFileToString(new File(FileName));
		mlffile=mlffile.replace(".txt","");
		FileUtils.writeStringToFile(new File ("/home/abuyusra/SP/words2.mlf"), mlffile);
	}
	public static void main(String [] argv) throws IOException{
		RemoveTxt("/home/abuyusra/SP/words.mlf");
	}
}
