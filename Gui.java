import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*; 

public class Gui extends JFrame implements ActionListener{
	 private static JFrame frame1;
	 private static JLabel label1;
	 private static JLabel label2;
	 private static JLabel label3;
	 private static JLabel label4;
	 private static JButton button1;
	 private static JButton button2;
	 private static JButton button3;
	 private static JButton button4;
	 private static JTextField textbox1;
	 private static JTextField textbox2;
	 private static JTextField textbox3;
	 private static JTextField textbox4;
	 private static vectorQuantization v = new vectorQuantization();
	 Gui()
	 {
		 	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	 }
	 public static void main (String[] args) 
	 {     
		    // label to diaplay text 
		    label1 = new JLabel("Vector Height"); 
		    
		    // label to diaplay text 
		    label2 = new JLabel("Vector Width");
		    
		    // label to diaplay text 
		    label3 = new JLabel("Code Book size");
		    
		    // label to diaplay text 
		    label4 = new JLabel("The file path"); 
		 
	        // creates instance of JFrame 
	        frame1 = new JFrame("Vector Quantization"); 
	  
	        // creates instance of JButton 
	        button1 = new JButton("Compression"); 
	          
	        // "button2" appears on the button 
	        button2 = new JButton("Decompression");
	        
	        // "button3" appears on the button 
	        button3 = new JButton("Read from file");
	        
	        // "button4" appears on the button 
	        button4 = new JButton("Exit");

	        
	        // JTextField
	        textbox1= new JTextField(); 
	        
	        // JTextField
	        textbox2= new JTextField(); 
	        
	        // JTextField
	        textbox3= new JTextField(); 
	        
	        // JTextField
	        textbox4= new JTextField(); 
	        
	        // x axis, y axis, width, height 
	        button1.setBounds(50, 120, 120, 50); 
	        button2.setBounds(180, 120, 120, 50); 
	        button3.setBounds(330, 120, 120, 50);
	        button4.setBounds(480, 120, 120, 50);
	        
	        textbox1.setBounds(150, 20, 50, 30);
	        textbox2.setBounds(350, 20,50, 30);
	        textbox3.setBounds(550, 20, 50, 30);
	        textbox4.setBounds(150, 70,450, 30);
	        
	        label1.setBounds(50, 20, 200, 30);
	        label2.setBounds(250, 20, 200, 30);
	        label3.setBounds(450, 20, 200, 30);
	        label4.setBounds(50, 70, 200, 30);
	        
	        //adds button1 in Frame1 
	        frame1.add(button1); 
	          
	        //adds button2 in Frame1 
	        frame1.add(button2); 
	        
	        //adds button3 in Frame1 
	        frame1.add(button3); 
	        
	        //adds button4 in Frame1 
	        frame1.add(button4); 
	        	        
	        //adds textbox1 in Frame1
	        frame1.add(textbox1);
	      
	        //adds textbox in Frame1
	        frame1.add(textbox2);
	        
	        //adds textbox1 in Frame1
	        frame1.add(textbox3);
	        
	        //adds textbox in Frame1
	        frame1.add(textbox4);
	        
	        //adds label1 in Frame1
	        frame1.add(label1);
	     
	        //adds label in Frame1
	        frame1.add(label2);

	        //adds label1 in Frame1
	        frame1.add(label3);
	        
	        //adds label in Frame1
	        frame1.add(label4);
	        
	        //400 width and 500 height of frame1 
	        frame1.setSize(670, 250) ; 
	          
	        //uses no layout managers 
	        frame1.setLayout(null); 
	          
	        //makes the frame visible 
	        frame1.setVisible(true); 
	        Gui g = new Gui();
		 	button1.addActionListener(g);
		 	button2.addActionListener(g);
		 	button3.addActionListener(g);
		 	button4.addActionListener(g);
	    } 
	 	public void actionPerformed(ActionEvent e) 
	    { 
	        String s = e.getActionCommand(); 
	        if (s.equals("Compression")) 
	        {
	        	String path = textbox4.getText();
				int numOfLevels = Integer.parseInt(textbox3.getText());
				int widthOfBlock = Integer.parseInt(textbox2.getText());
				int heightOfBlock = Integer.parseInt(textbox1.getText());
				v.originalImage = Image.readImage(path);


				int numOfRows = v.originalImage.length / heightOfBlock; // lel new matrix li mtkwna mn vectors
				int numOfCols = v.originalImage[0].length / heightOfBlock;
				vector[][] vectors = new vector[numOfRows][numOfCols]; // 2D array consist of vectors
				//  Build_vectors (originalImage , vectors , numOfRows , numOfCols , widthOfBlock , heightOfBlock );
				ArrayList<vector> data = v.createVectors(v.originalImage, vectors, numOfRows, numOfCols, widthOfBlock, heightOfBlock);
				v.QuantizeImage(numOfLevels, data, widthOfBlock, heightOfBlock, vectors, numOfRows, numOfCols);
	        } 
	        else if (s.equals("Decompression")) 
	        { 
	        	String path =textbox4.getText();
	        	v.Decompress(path);
	        } 
	        else if (s.equals("Read from file")) 
	        { 
	        	 JFileChooser browser = new JFileChooser();
                 browser.showOpenDialog(null);
                 textbox4.setText(browser.getSelectedFile().toString());
	        }
	        else if (s.equals("Exit")) 
	        { 
	        	 System.exit(0); 
	        } 
	    } 
}
