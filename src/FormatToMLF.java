import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;


public class FormatToMLF {
	
	/**
	 * this function opens the timit.txt file and remove the timing range from every senetences
	 * so that proper MLF will be created.
	 * @throws IOException 
	 */
public static void RemoveNumRanges(String FileName) throws IOException{
	String file=FileUtils.readFileToString(new File(FileName));
	StringTokenizer st=new StringTokenizer(file,"\n");
	StringBuilder sb=new StringBuilder();
	while(st.hasMoreElements()){
		String path=st.nextToken().toString();
		StringTokenizer stpath=new StringTokenizer(path,"/");
		while(stpath.hasMoreElements()){
			stpath.nextToken();stpath.nextToken();stpath.nextToken();
			sb.append("*/"+stpath.nextToken().toLowerCase()+" ");//We need only file name.
		}
		String Sent=st.nextToken();
		System.out.println(Sent);
		sb.append(Sent.substring(8,Sent.length()).toLowerCase()+"\n");
		}
FileUtils.writeStringToFile(new File("/home/abuyusra/SP/timit2.txt"), sb.toString());	
	}
public static void main(String[] argv) throws IOException{
	RemoveNumRanges("/home/abuyusra/SP/timit.txt");
}
}
