import java.util.*;
import java.io.*;

import org.apache.commons.io.FileUtils;

/**
* Recursive file listing under a specified directory.
*  
* @author javapractices.com
* @author Alex Wong
* @author anonymous user
*/
public final class CreateMfcScp {

  /**
  * Demonstrate use.
  * 
  * @param aArgs - <tt>aArgs[0]</tt> is the full name of an existing 
  * directory that can be read.
 * @throws IOException 
 * @throws InterruptedException 
  */
  public static void main(String... aArgs) throws IOException, InterruptedException {
    File startingDirectory= new File("/home/abuyusra/SP/timit/test/");
    List<File> files = CreateMfcScp.getFileListing(startingDirectory);

    //print out all file names, in the the order of File.compareTo()
    StringBuilder scp=new StringBuilder();
    for(File file : files ){
    if(file.toString().endsWith("mfc")){	
    	scp.append("*/"+file.getName()+"\n");
    }
    FileUtils.writeStringToFile(new File("/media/HIRUT57/x/test.scp"), scp.toString());
    	
    }
    }
  
  /**
  * Recursively walk a directory tree and return a List of all
  * Files found; the List is sorted using File.compareTo().
  *
  * @param aStartingDir is a valid directory, which can be read.
  */
  static public List<File> getFileListing(
    File aStartingDir
  ) throws FileNotFoundException {
    List<File> result = getFileListingNoSort(aStartingDir);
    Collections.sort(result);
    return result;
  }

  // PRIVATE //
  static private List<File> getFileListingNoSort(
    File aStartingDir
  ) throws FileNotFoundException {
    List<File> result = new ArrayList<File>();
    File[] filesAndDirs = aStartingDir.listFiles();
    List<File> filesDirs = Arrays.asList(filesAndDirs);
    for(File file : filesDirs) {
    	if(file.isFile())
      result.add(file); //always add, even if directory
      if ( ! file.isFile() ) {
        //must be a directory
        //recursive call!
        List<File> deeperList = getFileListingNoSort(file);
        result.addAll(deeperList);
      }
    }
    return result;
  }

}
