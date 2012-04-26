
public class maxthree {

	public static  String max(double a, double b, double c){
		if (a>b && a>c)
			return "A";
		else if(a>b&&b>c)
			return "B";
		else if(b>c)
			return "B";
		else
			return "C";
	    }
	public static void main(String argv[]){
		System.out.println(max(1,3,2));
	}
}
