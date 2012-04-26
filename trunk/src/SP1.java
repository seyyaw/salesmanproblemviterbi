import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author Seid M.
 */
public class SP1 extends javax.swing.JFrame {

    /** Creates new form SP1 */
    public SP1() {
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
    	for (char c: ObsStates){
    		ItemSold.add(c);
    	}
    	Iterator ItemIterator=ItemSold.iterator();
    	boolean firstcity=true;
    	String Itm;
    	int i=0;
    	while(ItemIterator.hasNext()){ i++;
    		Itm=ItemIterator.next().toString().trim();
    		if(Itm.equals("")){continue;}//remove spaces from user input// Items not in T F D or W or not checked
    		if(firstcity){
    			Cities=Cities.append("A");
    			firstcity=false;
    			partialprb=1.0*0.41*0.36;
    			prevstate="A";
    		}    	
    		else if(Itm.equals("T")){
    			if(prevstate.equals("A")){//Being in A and then transition to A,B,C and prob of selling T there
    			Cities.append(max(partialprb*0.41*0.36,partialprb*0.24*0.23,partialprb*0.35*0.21));
    			partialprb=Math.max(partialprb*0.41*0.36, Math.max(partialprb*0.24*0.23, partialprb*0.35*0.21));
    			prevstate=max(partialprb*0.41*0.36,partialprb*0.24*0.23,partialprb*0.35*0.21);
	    		}
	    		else if(prevstate.equals("B")){
	    			Cities.append(max(partialprb*0.27*0.36,partialprb*0.39*0.23,partialprb*0.34*0.21));
	    			partialprb=Math.max(partialprb*0.27*0.36, Math.max(partialprb*0.39*0.23, partialprb*0.34*0.21));
	    			prevstate=max(partialprb*0.27*0.36,partialprb*0.39*0.23,partialprb*0.34*0.21);
	    		}
	    		else if(prevstate.equals("C")){
	    			Cities.append(max(partialprb*0.33*0.36,partialprb*0.44*0.23,partialprb*0.23*0.21));
	    			partialprb=Math.max(partialprb*0.33*0.36, Math.max(partialprb*0.44*0.23, partialprb*0.23*0.21));
	    			prevstate=max(partialprb*0.33*0.36,partialprb*0.44*0.23,partialprb*0.23*0.21);
	    		}
	    		}
    		//Item F
    		else if(Itm.equals("F")){
    			if(prevstate.equals("A")){
    			Cities.append(max(partialprb*0.41*0.15,partialprb*0.24*0.21,partialprb*0.35*0.27));
    			partialprb=Math.max(partialprb*0.41*0.15, Math.max(partialprb*0.24*0.21, partialprb*0.35*0.27));
	    			prevstate=max(partialprb*0.41*0.15,partialprb*0.24*0.21,partialprb*0.35*0.27);
	    		}
	    		else if(prevstate.equals("B")){
	    			Cities.append(max(partialprb*0.27*0.15,partialprb*0.39*0.21,partialprb*0.34*0.27));
	    			partialprb=Math.max(partialprb*0.27*0.15, Math.max(partialprb*0.39*0.21, partialprb*0.34*0.27));
	    			prevstate=max(partialprb*0.27*0.15,partialprb*0.39*0.21,partialprb*0.34*0.27);
	    		}
	    		else if(prevstate.equals("C")){
	    			Cities.append(max(partialprb*0.33*0.15,partialprb*0.44*0.21,partialprb*0.23*0.27));
	    			partialprb=Math.max(partialprb*0.33*0.15, Math.max(partialprb*0.44*0.21, partialprb*0.23*0.27));
	    			prevstate=max(partialprb*0.33*0.15,partialprb*0.44*0.21,partialprb*0.23*0.27);
	    		}
	    	}
    		//Item W
    		else if(Itm.equals("W")){
    			if(prevstate.equals("A")){
    			Cities.append(max(partialprb*0.41*0.29,partialprb*0.24*0.13,partialprb*0.35*0.38));
    			partialprb=Math.max(partialprb*0.41*0.29, Math.max(partialprb*0.24*0.13, partialprb*0.35*0.38));
    			prevstate=max(partialprb*0.41*0.29,partialprb*0.24*0.13,partialprb*0.35*0.38);
	    		}
	    		else if(prevstate.equals("B")){
	    			Cities.append(max(partialprb*0.27*0.29,partialprb*0.39*0.13,partialprb*0.34*0.38));
	    			partialprb=Math.max(partialprb*0.27*0.29, Math.max(partialprb*0.39*0.13, partialprb*0.34*0.38));
	    			prevstate=max(partialprb*0.27*0.29,partialprb*0.39*0.13,partialprb*0.34*0.38);
	    		}
	    		else if(prevstate.equals("C")){
	    			Cities.append(max(partialprb*0.33*0.29,partialprb*0.44*0.13,partialprb*0.23*0.38));
	    			partialprb=Math.max(partialprb*0.33*0.29, Math.max(partialprb*0.44*0.13, partialprb*0.23*0.38));
	    			prevstate=max(partialprb*0.33*0.29,partialprb*0.44*0.13,partialprb*0.23*0.38);
	    		}
    		}
    		//Item D
    		else if(Itm.equals("D")){
    			if(prevstate.equals("A")){
    			Cities.append(max(partialprb*0.41*0.2,partialprb*0.24*0.33,partialprb*0.35*0.14));
    			partialprb=Math.max(partialprb*0.41*0.2, Math.max(partialprb*0.24*0.33, partialprb*0.35*0.14));
    			prevstate=max(partialprb*0.41*0.2,partialprb*0.24*0.33,partialprb*0.35*0.14);
	    		}
	    		else if(prevstate.equals("B")){
	    			Cities.append(max(partialprb*0.27*0.2,partialprb*0.39*0.33,partialprb*0.34*0.14));
	    			partialprb=Math.max(partialprb*0.27*0.2, Math.max(partialprb*0.39*0.33, partialprb*0.34*0.14));
	    			prevstate=max(partialprb*0.27*0.2,partialprb*0.39*0.33,partialprb*0.34*0.14);
	    		}
	    		else if(prevstate.equals("C")){
	    			Cities.append(max(partialprb*0.33*0.2,partialprb*0.44*0.33,partialprb*0.23*0.14));
	    			partialprb=Math.max(partialprb*0.33*0.2, Math.max(partialprb*0.44*0.33, partialprb*0.23*0.14));
	    			prevstate=max(partialprb*0.33*0.2,partialprb*0.44*0.33,partialprb*0.23*0.14);
	    		}
    		}
    		System.out.println("Item: "+ Itm+" "+"Prev State : "+prevstate+"  All States:  "+Cities.toString());
    	}	
    	txthiddenstate.removeAll();
    	txthiddenstate.setText(Cities.toString());
    }
public  String max(double a, double b, double c){
	 if (a>b && a>c)
		return "A";
	else if(a>b&&b>c)
		return "B";
	else if(b>c)
		return "B";
	else
		return "C";
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SP1().setVisible(true);
            }
        });
    }

    private javax.swing.JButton btnComputeCity;
    private javax.swing.JLabel lblHiddenState;
    private javax.swing.JLabel lblItemsSold;
    private javax.swing.JTextField txtObservedSeq;
    private javax.swing.JTextField txthiddenstate;
}
