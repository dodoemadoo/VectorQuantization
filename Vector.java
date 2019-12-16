
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Vector extends javax.swing.JFrame {

    public Vector() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Enter num of level ");

        jLabel2.setText("Enter width of block ");

        jLabel3.setText("Enter hieght of block ");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jButton1.setText("Compress");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Decompress");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(50, 50, 50)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                                        .addComponent(jTextField2)
                                                        .addComponent(jTextField3)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(65, 65, 65)
                                                .addComponent(jButton1)
                                                .addGap(75, 75, 75)
                                                .addComponent(jButton2)))
                                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addContainerGap(99, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Decompress ();

    }//GEN-LAST:event_jButton2ActionPerformed


    public static int[][] readImage(String filePath)
    {
        int width=0;
        int height=0;
        File file=new File(filePath);
        BufferedImage image=null;
        try
        {
            image=ImageIO.read(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        width=image.getWidth();
        height=image.getHeight();
        int[][] pixels=new int[height][width];

        for(int x=0;x<width;x++)
        {
            for(int y=0;y<height;y++)
            {
                int rgb=image.getRGB(x, y);
                int alpha=(rgb >> 24) & 0xff;
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = (rgb >> 0) & 0xff;

                pixels[y][x]=r;
            }
        }

        return pixels;
    }

    public static void writeImage(int[][] pixels,String outputFilePath,int width,int height)
    {
        File fileout=new File(outputFilePath);
        BufferedImage image2=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB );

        for(int x=0;x<width ;x++)
        {
            for(int y=0;y<height;y++)
            {
                image2.setRGB(x,y,(pixels[y][x]<<16)|(pixels[y][x]<<8)|(pixels[y][x]));
            }
        }
        try
        {
            ImageIO.write(image2, "jpg", fileout);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    class vector
    {
        int width ;
        int height ;
        double [][] data ;

        public vector () {}
        public vector(int width, int height) {
            this.width = width;
            this.height = height;
            this.data = new double [height][width];
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public double[][] getData() {
            return data;
        }

        public void setData(double[][] data) {
            this.data = data;
        }


    }

    class split_element
    {
        vector value ;
        ArrayList<vector> assoicated = new ArrayList<>();

        public split_element() {}

        public split_element(vector value ,ArrayList<vector> assoicated ) {
            this.value = value;
            this.assoicated = assoicated ;
        }

        public vector getValue() {
            return value;
        }

        public void setValue(vector value) {
            this.value = value;
        }

        public ArrayList<vector> getAssoicated() {
            return assoicated;
        }

        public void setAssoicated(ArrayList<vector> assoicated) {
            this.assoicated = assoicated;
        }


    }

    void ShowVector ( vector v)
    {
        for (int i=0 ; i<v.height ; i++ )
        {
            for (int j=0 ; j<v.width ; j++)
            {
                System.out.print(v.data[i][j] + "  ");
            }
            System.out.println();
        }

        System.out.println("---------------------------");
    }

    ArrayList <vector> createVectors (int [][] originalImage , vector [][] vectors,
                                      int numOfRows , int numOfCols , int blockWidth , int blockHeight)
    {

        ArrayList<vector> picVectors = new ArrayList<>();

        vector tempVector ;

        for (int i=0 ; i<originalImage.length ; i+=blockHeight)
        {
            for (int j=0 ; j<originalImage[0].length ; j+=blockWidth)
            {
                int x = i ; int z = j ;

                tempVector = new vector ( blockWidth , blockHeight );

                for (int n=0 ; n<blockHeight ; n++)
                {
                    for (int m=0 ; m<blockWidth ; m++)
                    {
                        tempVector.data[n][m]= originalImage[x][z++];
                    }

                    x++;   z=j;
                }

                picVectors.add(tempVector);

            }
        }
        int index =0 ;

        for (int i=0 ; i<numOfRows ; i++) // filling the new matrix that consists of vectors onli
        {
            for (int j=0 ; j<numOfCols ; j++)
            {
                vectors[i][j] = picVectors.get(index++);
            }
        }

        return picVectors ;
    }

    int indxOF_min_distance (ArrayList <Double> distance_difference )
    {
        double min_diff = distance_difference.get(0); // assume first element is the min
        int indx = 0 ;

        for (int i=1 ; i<distance_difference.size() ; i++)
        {
            if ( distance_difference.get(i) < min_diff)
            {
                min_diff = distance_difference.get(i);
                indx = i ;
            }

        }

        return indx ;
    }

    ArrayList<vector> associate ( ArrayList<vector> split , ArrayList <vector> data  ) // associate ang return avg
    {

        ArrayList <split_element> Split = new ArrayList<>();
        ArrayList <vector> Averages = new ArrayList<> ();// to store the average of nearest vectors of a specific split vector

        int width = data.get(0).width;
        int height = data.get(0).height ;

        for (int i = 0; i < split.size(); i++)  // inilialization
        {
            split_element initial = new split_element() ;
            initial.setValue(split.get(i));
            Split.add(initial);
        }

        for (int i=0 ; i<data.size() ; i++) // find nearest vectors
        {
            vector cur = data.get(i);
            ArrayList <Double> distance_difference = new ArrayList<> ();        //storing differences for euclidean distance


            for (int j=0 ; j<split.size() ;j++)     //calculating distance between every original vector and split vector
            {
                double total_diff = 0 ;

                for (int w=0 ; w<width ; w++)
                {
                    for (int h=0 ; h<height ; h++)
                    {
                        double value = cur.data[w][h]-split.get(j).data[w][h];
                        double distanc_diff =  Math.pow( value , 2);
                        total_diff +=distanc_diff ;
                    }
                }

                distance_difference.add(total_diff);

            }

            int indx = indxOF_min_distance (distance_difference);

            ArrayList <vector> cur_associated = Split.get(indx).getAssoicated();

            cur_associated.add(cur);

            split_element New = new split_element(Split.get(indx).getValue() , cur_associated);

            Split.set(indx , New );

        }

//        for (int i=0 ; i<Split.size() ; i++) // calculate average for the associated values
//        {
//            int arraysize = Split.get(i).getAssoicated().size();
//            System.out.println("************* split value ****************");
//
//            ShowVector(Split.get(i).value);
//
//            System.out.println("************* Association ****************");
//            for (int j = 0; j < arraysize; j++)
//            {
//                  ShowVector(Split.get(i).assoicated.get(j));
//            }
//
//        }

        for (int i=0 ; i<Split.size() ; i++) // calculate average for the associated values
        {
            int size = Split.get(i).getAssoicated().size();     //number of nearest vectors of a specific split vector
            vector avg = new vector(width , height);

            for (int w = 0; w < width; w++)
            {
                for (int h = 0; h < height; h++)
                {
                    double total = 0 ;

                    for (int j = 0; j < size; j++)
                    {
                        total+= Split.get(i).getAssoicated().get(j).data[w][h];
                    }

                    avg.data[w][h]= total/size;
                }

            }

            Averages.add(avg);

        }

        return Averages ;
    }

    ArrayList<vector> Split (ArrayList <vector> Averages ,  ArrayList <vector> originalVector , int levels ) // split original averages
    {
        int width = Averages.get(0).width ;
        int height = Averages.get(0).height ;

        for (int i=0 ; i<Averages.size() ; i++)
        {
            if (Averages.size()<levels)
            {

                ArrayList <vector> split = new ArrayList<>();

                for (int j=0 ; j<Averages.size() ; j++)
                {
                    vector left = new vector( width , height);
                    vector right = new vector( width , height);

                    for (int w=0 ; w<width ; w++)
                    {
                        for (int h=0 ; h<height ; h++)
                        {
                            int num = (int)Averages.get(j).data[w][h] ;

                            left.data[w][h]= num;
                            right.data[w][h]= num+1;

                        }

                    }

                    split.add(left);
                    split.add(right);
                }

                Averages.clear();
                Averages = associate( split , originalVector);

                i=0 ;

            }
            else {
                break;}

        }

        return Averages ;
    }

    ArrayList<vector> modify ( ArrayList<vector> prev_Averages , ArrayList<vector> new_Averages , ArrayList<vector> data  )
    {
        while (true)
        {
            int width = new_Averages.get(0).width;
            int height = new_Averages.get(0).height;
            int totaldiff = 0 ;
            int avgdiff = 0 ;

            for (int i=0 ; i<new_Averages.size() ; i++)
            {
                double DiffOf2vec =0 ;

                for (int w=0 ; w<width ; w++)
                {
                    for (int h=0 ; h<height ; h++)
                    {
                        DiffOf2vec += Math.abs(prev_Averages.get(i).data[w][h] - new_Averages.get(i).data[w][h]) ;
                    }
                }

                totaldiff+=DiffOf2vec;
            }

            avgdiff = totaldiff / prev_Averages.size() ;

            if (avgdiff < 0.0001 )
            {
                break;
            }

            else
            {
                prev_Averages = new_Averages ;
                new_Averages = associate( new_Averages , data);
            }

        }

        return new_Averages ;

    }

    void QuantizeImage(int numoflevels , ArrayList <vector> data , int widthOfBlock , int heightOfBlock , vector [][] vectors , int numOfRows , int numOfCols  )
    {
        vector first_avg = new vector( widthOfBlock , heightOfBlock );

        ArrayList<vector> Averages = new ArrayList<>();         //ArrayList to store first average and all later ones.

        for (int w = 0; w < widthOfBlock; w++)
        {
            for (int h = 0; h < heightOfBlock; h++)
            {
                double total = 0 ;

                for (int i = 0; i < data.size(); i++)
                {
                    total += data.get(i).data[w][h];

                }

                first_avg.data[w][h] = total/data.size();       //average of all vectors

            }

        }

        Averages.add(first_avg);

        Averages = Split (Averages , data , numoflevels );

        ArrayList<vector> prev_Averages = Averages ;
        ArrayList<vector> new_Averages = associate( Averages , data);       //finding nearest vectors

        new_Averages = modify(prev_Averages, new_Averages, data);


        ArrayList<vector> codeBook = new ArrayList<>();

        for (int i=0 ; i<new_Averages.size() ; i++)
        {
            codeBook.add(new_Averages.get(i));
        }


        int indx =0 ;

        for (int i=0 ; i<widthOfBlock ; i++) // filling the new matrix that consists of vectors onli
        {
            for (int j=0 ; j<numOfCols ; j++)
            {
                vectors[i][j] = data.get(indx++);
            }
        }

        compress (codeBook , vectors );

    }

    void compress ( ArrayList<vector> codeBook , vector [][] vectors )      //similar to associate
    {
        int Rows = vectors.length ;
        int Cols = vectors[0].length ;
        int [][] comp_image = new int [Rows][Cols];

        for (int i=0 ; i<Rows ; i++)
        {
            for (int j=0 ; j<Cols ; j++)
            {
                vector cur = vectors[i][j];
                ArrayList <Double> distance_difference = new ArrayList<> ();

                for (int k=0 ; k<codeBook.size() ;k++)
                {
                    double total_diff = 0 ;

                    for (int w=0 ; w<codeBook.get(0).width ; w++)
                    {
                        for (int h = 0; h < codeBook.get(0).height; h++)
                        {
                            double value = cur.data[w][h] - codeBook.get(k).data[w][h];
                            double distanc_diff = Math.pow(value, 2);
                            total_diff += distanc_diff;
                        }
                    }

                    distance_difference.add(total_diff);
                }

                int indx = indxOF_min_distance (distance_difference);
                comp_image[i][j]= indx ;

            }
        }

//        System.out.println("____________________ Comp Img _______________________");
//
//        for (int i=0 ; i<Rows ; i++)
//        {
//            for (int j=0 ; j<Cols ; j++)
//            {
//                System.out.print(comp_image[i][j] + "  ");
//            }
//
//            System.out.println();
//        }

        Save_CodeBook_CompImg ( codeBook , comp_image);

    }

    Scanner sc;

    public void open_file(String FileName) {
        try {
            sc = new Scanner(new File(FileName));
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    public void close_file() {
        sc.close();
    }

    Formatter out; //34an yktb el tag be format el string

    public void openfile(String pass) {
        try {
            out = new Formatter(pass);
        } catch (Exception e) {
        }

    }

    public void closefile() {
        out.close();
    }

    void write(String code) {

        out.format("%s", code);
        out.format("%n");
        out.flush(); // 34an yktb 3al file

    }


    void Decompress ()
    {

        ArrayList<vector> codeBook = new ArrayList <vector>();
        int [][] comp_image = new int [1][1] ;
        comp_image = Reconstruct( codeBook , comp_image);
        int [][] Decomp_image = new int [originalImage.length][originalImage[0].length];

        for (int i=0 ; i<comp_image.length ; i++)
        {
            for (int j=0 ; j<comp_image[0].length ; j++)
            {
                vector cur = new vector();
                cur = codeBook.get(comp_image[i][j]);

                int cornerx = i*cur.height;
                int cornery = j*cur.width ;


                for (int h=0 ; h<cur.height ; h++)
                {

                    for (int k=0 ; k<cur.width ; k++)
                    {
                        Decomp_image[cornerx+h][cornery+k] = (int) cur.data[h][k];
                    }
                }

            }
        }


        System.out.println("################################################&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println(Decomp_image);
        writeImage(Decomp_image, "Decompress.jpg", Decomp_image[0].length, Decomp_image.length);


       /* int widthOFCompImg = comp_image[0].length  ;
        int heigthOFCompImg = comp_image.length;

        ArrayList<vector> ReconstructedData = new ArrayList <vector>(); // by4eel kol indx mokabel leeh anhy vector mn l code book
        for (int i=0 ; i<comp_image.length ; i++)
        {
            for (int j=0 ; j<comp_image[0].length ;j++)
            {
                vector data = new vector () ;
                data = codeBook.get(comp_image[i][j]);
                ReconstructedData.add(data);
            }
        }

        int widthOfBlock = ReconstructedData.get(0).width;
        int heigthOfBlock = ReconstructedData.get(0).height;

        // note that we will construct the original decomp image using width bt3ha = widthOfvector eli fel code table * width of comp img eli 2reto mn el file and hiegth also the same

        int widthOfDecompImg = widthOFCompImg*widthOfBlock;
        int heightOfDecompImg = heigthOFCompImg*heigthOfBlock;
        int [][] Decomp_image = new int [heightOfDecompImg][widthOfDecompImg];


        ArrayList<Integer> values = new ArrayList<>();

        int row = 0 ;
        //int col = 0 ;
        int indx = 0 ;

        while (indx<ReconstructedData.size())
        {
            vector cur = ReconstructedData.get(indx);

            for (int col = 0 ; col<cur.width ; col++)
            {
                values.add((int) cur.data[row][col]);
            }

            indx++ ;

            if (indx%widthOfDecompImg == 0)
            {
                indx = indx-widthOfDecompImg;
                row++;

            }

        } */






//        for (int i = 0; i < ReconstructedData.size(); i+=widthOFCompImg)
//        {
//
//            for (int j=0 ; j<widthOFCompImg ; j++)
//            {
//              vector cur = ReconstructedData.get(i+j);
//
//                for (int h = 0; h < heigthOfBlock; h++)
//                {
//                    for (int w = 0; w < widthOfBlock; w++)
//                    {
//                        values.add((int) cur.data[h][w]);
//                    }
//
//                }
//
//
//
//            }
//
//        }


        // b3ml el matrix bt3t el sora
        /*for (int w=0 ; w<widthOfDecompImg ; w++)
        {
            for (int h=0 ; h<heightOfDecompImg ; h++)
            {
               for (int i=0 ; i<ReconstructedData.size() ; i++)
               {
                   vector cur = ReconstructedData.get(i);

               }

            }
        }*/



//        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//
//        for (int i=0 ; i<ReconstructedData.size() ; i++)
//        {
//            ShowVector(ReconstructedData.get(i));
//        }




    }

    void Save_CodeBook_CompImg ( ArrayList<vector> codeBook , int [][] comp_image )
    {
        openfile("CompressFile.txt");
        String codeBookSize = "" + codeBook.size();
        String WidthOfBlock = "" + codeBook.get(0).width;
        String heightOfBlock = "" + codeBook.get(0).height;

        write(codeBookSize);
        write(WidthOfBlock);
        write(heightOfBlock);

        for (int i=0 ; i<codeBook.size() ; i++)
        {
            for (int w=0 ; w<codeBook.get(i).width ; w++)
            {
                String row = "";

                for (int h=0 ; h<codeBook.get(i).height ; h++)
                {
                    row += codeBook.get(i).data[w][h] + " ";        //writing elements of each codebook vector to file.
                }

                write(row);
            }

        }

        // write("------------------------------------------------------------");

        String com_image_height = "" + comp_image.length ;
        write(com_image_height);
        String com_image_width = "" + comp_image[0].length ;
        write(com_image_width);

        //writing codebook codes to file

        for (int i=0 ; i<comp_image.length ; i++)
        {
            String row = "";

            for (int j=0 ; j<comp_image[0].length ; j++)
            {
                row+= comp_image[i][j] +" ";
            }

            write(row);
        }

        closefile();
    }


    int [][] Reconstruct( ArrayList<vector> codeBook , int [][] comp_image)
    {
        open_file("CompressFile.txt");
        // System.out.println(sc.nextLine());
        int codeBookSize = Integer.parseInt(sc.nextLine());
        int WidthOfBlock = Integer.parseInt(sc.nextLine());
        int heightOfBlock = Integer.parseInt(sc.nextLine());

        for (int i=0 ; i<codeBookSize ; i++)
        {
            vector cur = new vector(WidthOfBlock , heightOfBlock);

            for (int w=0 ; w<WidthOfBlock ; w++)
            {
                String row = sc.nextLine();
                String [] elements = row.split(" ");

                for (int h=0 ; h<heightOfBlock ; h++)
                {
                    //System.out.println("elements = " +elements[h]);
                    cur.data[w][h]= Double.parseDouble(elements[h]);
                    // System.out.println("Data = " +  cur.data[w][h]);
                }

            }

            codeBook.add(cur);

        }  // done reading codeBook

        int com_image_height = Integer.parseInt(sc.nextLine());
        int com_image_width =  Integer.parseInt(sc.nextLine());
        comp_image = new int [com_image_height][com_image_width];

        for (int i=0 ; i<comp_image.length ; i++)
        {
            String line = sc.nextLine();
            String [] row = line.split(" ");

            for (int j=0 ; j<comp_image[0].length ; j++)
            {
                comp_image[i][j] = Integer.parseInt(row[j]);
            }

        }

//       for (int i=0 ; i<comp_image.length ; i++)
//        {
//            for (int j=0 ; j<comp_image[0].length  ; j++)
//            {
//                System.out.print(comp_image[i][j] + "  ");
//            }
//
//            System.out.println();
//        }

//        for (int i=0 ; i<codeBook.size() ; i++)
//        {
//            ShowVector(codeBook.get(i));
//
//        }


        close_file();

        return comp_image ;

    }

    public  int [][] originalImage ;


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_jButton1ActionPerformed

        int numOfLevels = Integer.parseInt(jTextField1.getText()) ;
        int widthOfBlock = Integer.parseInt(jTextField2.getText()) ;
        int heightOfBlock = Integer.parseInt(jTextField3.getText()) ;
        originalImage  = readImage("Original input.jpg");

//        int [][] originalImage = new int[6][6];
//        Scanner sc = new Scanner (System.in);
//        for (int i=0 ; i<6 ; i++)
//        {
//            for (int j=0 ; j<6 ; j++)
//            {
//                originalImage[i][j]= sc.nextInt();
//            }
//        }

        int numOfRows = originalImage.length /heightOfBlock ; // lel new matrix li mtkwna mn vectors
        int numOfCols = originalImage[0].length /heightOfBlock ;
        vector [][] vectors = new vector [numOfRows][numOfCols]; // 2D array consist of vectors
        //  Build_vectors (originalImage , vectors , numOfRows , numOfCols , widthOfBlock , heightOfBlock );
        ArrayList <vector> data = createVectors (originalImage , vectors , numOfRows , numOfCols , widthOfBlock , heightOfBlock );
        QuantizeImage(numOfLevels , data , widthOfBlock , heightOfBlock ,vectors , numOfRows , numOfCols  );


    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vector().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
