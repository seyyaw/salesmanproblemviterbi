import java.util.StringTokenizer;
public class t {
	public static void main(String[] argv){
		double a[][]={{0.41,0.24,0.35},{0.27,0.39,0.34},{0.33,0.44,0.23}};//transition probability
		double b[][]={{0.36,0.15,0.29,0.20},{0.23,0.21,0.13,0.33},{0.21,0.27,0.38,0.14}};//emision probability
		double pi[]={1.0,0.0,0.0};
		//int length=31;
		
		//
		String o1="TFTTWWFWFDDDDDTWTDWFDWWFDFDTTD";
		StringTokenizer st=new StringTokenizer(o1," ");
		int len=st.countTokens();
		double VT[][]=new double[3][len];//most probable path
		int BP[][]=new int[3][len];//back pointer
		BP[0][0]=BP[1][0]=BP[2][0]=0;//Initial Backpointer
		int BPF=0;//final backpointer to the best final prob value
		//Intial Probabilities best paths
		VT[0][0]=b[0][0]*pi[0];
		VT[1][0]=b[1][0]*pi[1];
		VT[2][0]=b[2][0]*pi[2];
		//System.out.println(VT[0][0]+" "+VT[1][0]+" "+VT[2][0]);
		boolean flag=true; int i=0;
	for(i=0;i<len;i++){ 
		String ob=st.nextToken();
		if(flag){
			flag=false;
		}else{
			
			//System.out.println(ob);
			if(ob.equals("T")){
				VT[0][i]=max(VT[0][i-1]*a[0][0],VT[1][i-1]*a[1][0],VT[2][i-1]*a[2][0])*b[0][0];
				BP[0][i]=BPt(VT[0][i-1]*a[0][0],VT[1][i-1]*a[1][0],VT[2][i-1]*a[2][0]);
				VT[1][i]=max(VT[0][i-1]*a[0][1],VT[1][i-1]*a[1][1],VT[2][i-1]*a[2][1])*b[1][0];
				BP[1][i]=BPt(VT[0][i-1]*a[0][1],VT[1][i-1]*a[1][1],VT[2][i-1]*a[2][1]);
				VT[2][i]=max(VT[0][i-1]*a[0][2],VT[1][i-1]*a[1][2],VT[2][i-1]*a[2][2])*b[2][0];
				BP[2][i]=BPt(VT[0][i-1]*a[0][2],VT[1][i-1]*a[1][2],VT[2][i-1]*a[2][2]);				
			}
			else if(ob.equals("F")){
				VT[0][i]=max(VT[0][i-1]*a[0][0],VT[1][i-1]*a[1][0],VT[2][i-1]*a[2][0])*b[0][1];
				BP[0][i]=BPt(VT[0][i-1]*a[0][0],VT[1][i-1]*a[1][0],VT[2][i-1]*a[2][0]);
				VT[1][i]=max(VT[0][i-1]*a[0][1],VT[1][i-1]*a[1][1],VT[2][i-1]*a[2][1])*b[1][1];
				BP[1][i]=BPt(VT[0][i-1]*a[0][1],VT[1][i-1]*a[1][1],VT[2][i-1]*a[2][1]);
				VT[2][i]=max(VT[0][i-1]*a[0][2],VT[1][i-1]*a[1][2],VT[2][i-1]*a[2][2])*b[2][1];
				BP[2][i]=BPt(VT[0][i-1]*a[0][2],VT[1][i-1]*a[1][2],VT[2][i-1]*a[2][2]);						
			}
			else if(ob.equals("W")){
				VT[0][i]=max(VT[0][i-1]*a[0][0],VT[1][i-1]*a[1][0],VT[2][i-1]*a[2][0])*b[0][2];
				BP[0][i]=BPt(VT[0][i-1]*a[0][0],VT[1][i-1]*a[1][0],VT[2][i-1]*a[2][0]);
				VT[1][i]=max(VT[0][i-1]*a[0][1],VT[1][i-1]*a[1][1],VT[2][i-1]*a[2][1])*b[1][2];
				BP[1][i]=BPt(VT[0][i-1]*a[0][1],VT[1][i-1]*a[1][1],VT[2][i-1]*a[2][1]);
				VT[2][i]=max(VT[0][i-1]*a[0][2],VT[1][i-1]*a[1][2],VT[2][i-1]*a[2][2])*b[2][2];
				BP[2][i]=BPt(VT[0][i-1]*a[0][2],VT[1][i-1]*a[1][2],VT[2][i-1]*a[2][2]);						
			}
			else if(ob.equals("D")){
				VT[0][i]=max(VT[0][i-1]*a[0][0],VT[1][i-1]*a[1][0],VT[2][i-1]*a[2][0])*b[0][3];
				BP[0][i]=BPt(VT[0][i-1]*a[0][0],VT[1][i-1]*a[1][0],VT[2][i-1]*a[2][0]);
				VT[1][i]=max(VT[0][i-1]*a[0][1],VT[1][i-1]*a[1][1],VT[2][i-1]*a[2][1])*b[1][3];
				BP[1][i]=BPt(VT[0][i-1]*a[0][1],VT[1][i-1]*a[1][1],VT[2][i-1]*a[2][1]);
				VT[2][i]=max(VT[0][i-1]*a[0][2],VT[1][i-1]*a[1][2],VT[2][i-1]*a[2][2])*b[2][3];
				BP[2][i]=BPt(VT[0][i-1]*a[0][2],VT[1][i-1]*a[1][2],VT[2][i-1]*a[2][2]);						
			}
		}
	}
	int bestp=0;//best path for the last state
	if(max(VT[0][len-1],VT[1][len-1],VT[2][len-1])==VT[1][len-1])
		bestp=1;
	else if (max(VT[0][len-1],VT[1][len-1],VT[2][len-1])==VT[2][len-1])
		bestp=2;
	System.out.println("VT1 "+VT[0][len-1]+" VT2 "+VT[1][len-1]+" VT3 "+VT[2][len-1]+" best State "+bestp);
	/*if(bestp==0)
		System.out.println("A");
	else if(bestp==1)System.out.println("B");
	else System.out.println("C");*/
	for (i=len-1;i>0;i--){//System.out.println("Kutir "+ i);
		bestp=BP[bestp][i];//backtracking
		System.out.println("VT1 "+VT[0][i-1]+" VT2 "+VT[1][i-1]+" VT3 "+VT[2][i-1]+" best State "+bestp);
		/*if(bestp==0)System.out.println("A");
		else if(bestp==1)System.out.println("B");
		else System.out.println("C");*/
	}
	}
	public static double max(double a, double b, double c){
		if(a>=b && a>=c)
			return a;
		else if(a>=b&&b>=c)
			return b;
		else if(b>=c)
			return b;
		else
			return c;
	}	
	public static int BPt(double a, double b, double c){//point to the state of the previous VT
		if(a>=b && a>=c)
			return 0;
		else if(a>=b&&b>=c)
			return 1;
		else if(b>=c)
			return 1;
		else
			return 2;
	}	
}
