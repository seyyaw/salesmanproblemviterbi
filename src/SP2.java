import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
/**
 *
 * @author Seid M.
 */
/**
 * This code is written to solve the following problem, 
 * as given in the course, speech processing, HLTI, University of Trento, 2010/11
 * 
 * 1. Given the sequences of Items soled by the person, we are to search most probable
 * cities the person has sold those items. Here, the idea is to find maximum
 * probabilities of cities where those items were sold. We have Cities A, B, and C. the
 * sequences of Items sold are T F T T W W F W F D D D D D T W T D W F D W W F D F D T T D.
 */
public class SP2 extends javax.swing.JFrame {
    public SP2() {
        initComponents();
    }
    private void initComponents() {
        lblItemsSold = new javax.swing.JLabel();
        txtObservedSeq = new javax.swing.JTextField();
        lblHiddenState = new javax.swing.JLabel();
        txthiddenstate = new javax.swing.JTextField();
        btnComputeCity = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Viterbi Algorithm for Sales Man Problem");

        lblItemsSold.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        lblItemsSold.setForeground(new java.awt.Color(243, 9, 240));
        lblItemsSold.setText("Enter Observed Sequences");

        txtObservedSeq.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        txtObservedSeq.setForeground(new java.awt.Color(18, 19, 240));

        lblHiddenState.setBackground(new java.awt.Color(153, 175, 190));
        lblHiddenState.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        lblHiddenState.setForeground(new java.awt.Color(239, 11, 215));
        lblHiddenState.setText("Cities Items Sold are:");

        txthiddenstate.setBackground(new java.awt.Color(184, 146, 109));
        txthiddenstate.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        txthiddenstate.setForeground(new java.awt.Color(250, 53, 15));

        btnComputeCity.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        btnComputeCity.setForeground(new java.awt.Color(22, 11, 245));
        btnComputeCity.setText("Compute City");
        btnComputeCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComputeCityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblItemsSold)
                            .addComponent(lblHiddenState))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txthiddenstate, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                            .addComponent(txtObservedSeq, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnComputeCity, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(228, 228, 228))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblItemsSold, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtObservedSeq, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnComputeCity)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHiddenState, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txthiddenstate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>
    private void btnComputeCityActionPerformed(java.awt.event.ActionEvent evt) {
    	char[] ObsStates=txtObservedSeq.getText().trim().toUpperCase().toCharArray(); 
    	double partialprb = 0;
    	String prevstate="";
    	ArrayList ItemSold=new ArrayList();
    	ArrayList Hiddenstate=new ArrayList();
    	StringBuilder Cities = new StringBuilder();
    	int len=0;
    	for (char c: ObsStates){
    		len++;
    		ItemSold.add(c);
    	}
    	System.out.println(len);
    	Iterator ItemIterator=ItemSold.iterator();
    	double a[][]={{0.41,0.24,0.35},{0.27,0.39,0.34},{0.33,0.44,0.23}};//transition probability
		double b[][]={{0.36,0.15,0.29,0.20},{0.23,0.21,0.13,0.33},{0.21,0.27,0.38,0.14}};//emision probability
		double pi[]={1.0,0.0,0.0};;
		double VT[][]=new double[3][len];//most probable path
		int BP[][]=new int[3][len];//back pointer
		BP[0][0]=BP[1][0]=BP[2][0]=0;//Initial Backpointer
		int BPF=0;//final backpointer to the best final prob value
		//Intial Probabilities best paths
		VT[0][0]=b[0][0]*pi[0];
		VT[1][0]=b[1][0]*pi[1];
		VT[2][0]=b[2][0]*pi[2];
    	boolean firstcity=true;
    	String Itm;
    	int i=0;
    	for(i=0;i<len;i++){  
    		String ob=ItemIterator.next().toString();
    		if(firstcity){
    			firstcity=false;
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
    	String[]Bestpaths=new String[len];
    int bestp=0;//best path for the last state
	if(max(VT[0][len-1],VT[1][len-1],VT[2][len-1])==VT[1][len-1])
		bestp=1;
	else if (max(VT[0][len-1],VT[1][len-1],VT[2][len-1])==VT[2][len-1])
		bestp=2;
	//System.out.println(VT[0][len-1]+" "+VT[1][len-1]+" "+VT[2][len-1]+" best State "+bestp);
	if(bestp==0)
		Bestpaths[len-1]="A";
	else if(bestp==1)Bestpaths[len-1]="B";
	else Bestpaths[len-1]="C";
	for (i=len-1;i>0;i--){
		bestp=BP[bestp][i];//backtracking
		if(bestp==0)Bestpaths[i-1]="A";
		else if(bestp==1)Bestpaths[i-1]="B";
		else Bestpaths[i-1]="C";
	}
	Bestpaths[0]="A";
	StringBuilder sb=new StringBuilder();
	for(i=0;i<len;i++){
		sb.append(Bestpaths[i].toString());
	}
		txthiddenstate.setText(sb.toString());
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
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SP2().setVisible(true);
            }
        });
    }
    private javax.swing.JButton btnComputeCity;
    private javax.swing.JLabel lblHiddenState;
    private javax.swing.JLabel lblItemsSold;
    private javax.swing.JTextField txtObservedSeq;
    private javax.swing.JTextField txthiddenstate;
    
}
