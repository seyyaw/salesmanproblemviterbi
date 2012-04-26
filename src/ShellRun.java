import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ShellRun {

	public static void main(String argv[]) throws IOException, InterruptedException{
		String cmd = "praat /home/abuyusra/"+"getmeanpitch.praat";
		Runtime run = Runtime.getRuntime();
		Process pr = run.exec(cmd);
		pr.waitFor();
		BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line = "";
		while ((line=buf.readLine())!=null) {
			System.out.println(line);
		}

	}
}
