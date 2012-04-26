import java.util.*;
import java.io.*;

/**
* Recursive file listing under a specified directory.
*  
* @author Seid M.
*/
public final class GetMeanPitch {

   public static void main(String... aArgs) throws IOException, InterruptedException {
    File startingDirectory= new File("/home/abuyusra/Desktop/test");
    List<File> files = GetMeanPitch.getFileListing(startingDirectory);
    String line = "";int m = 0,f = 0,mf = 0;//categorizing to malel, female and both based on pitch value
    //gives directory name to praat, where the java runtime opens the praat to execute
    for(File file : files ){
	//praat system call, the praat script, and its argument directory
    	String cmd = "praat /home/abuyusra/"+"getmeanpitch.praat "+file;
		Runtime run = Runtime.getRuntime();
		Process pr = run.exec(cmd);
		pr.waitFor();
		BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		
		while ((line=buf.readLine())!=null) {
			double val=Double.parseDouble(line);
			if(val>=200)f++;
			else if(val<=150)m++;
			else mf++;
		}
    }
    System.out.println("Female := "+f+" Male := "+m+" Both := "+mf);
  }
  static public List<File> getFileListing(File aStartingDir) 
  throws FileNotFoundException {
    List<File> result = getFileListingNoSort(aStartingDir);
    Collections.sort(result);
    return result;
  }
  static private List<File> getFileListingNoSort(
    File aStartingDir
  ) throws FileNotFoundException {
    List<File> result = new ArrayList<File>();
    File[] filesAndDirs = aStartingDir.listFiles();
    List<File> filesDirs = Arrays.asList(filesAndDirs);
    for(File file : filesDirs) {
    	if(!file.isFile())//we need only the directory
      result.add(file); 
      if ( ! file.isFile() ) {
        List<File> deeperList = getFileListingNoSort(file);
        result.addAll(deeperList);
      }
    }
    return result;
  }

}
