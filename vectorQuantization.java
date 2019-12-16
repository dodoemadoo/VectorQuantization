import java.io.*;
import java.util.*;

class vector
{
    int width ;
    int height ;
    double [][] data ;

    public vector () {}
    public vector(int width, int height) 
    {
        this.width = width;
        this.height = height;
        this.data = new double [height][width];
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width) 
    {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public double[][] getData() 
    {
        return data;
    }

    public void setData(double[][] data)
    {
        this.data = data;
    }
}

class split_element
{
    vector value ;
    ArrayList<vector> assoicated = new ArrayList<>();

    public split_element() {}

    public split_element(vector value ,ArrayList<vector> assoicated )
    {
        this.value = value;
        this.assoicated = assoicated ;
    }

    public vector getValue()
    {
        return value;
    }

    public void setValue(vector value) 
    {
        this.value = value;
    }

    public ArrayList<vector> getAssoicated()
    {
        return assoicated;
    }

    public void setAssoicated(ArrayList<vector> assoicated) 
    {
        this.assoicated = assoicated;
    }
}


public class vectorQuantization 
{
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

    ArrayList <vector> createVectors (int [][] originalImage , vector [][] vectors, int numOfRows , int numOfCols , int blockWidth , int blockHeight)
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

        for (int i=0 ; i<numOfRows ; i++) 
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
        double min_diff = distance_difference.get(0); 
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

    ArrayList<vector> associate ( ArrayList<vector> split , ArrayList <vector> data  ) 
    {
        ArrayList <split_element> Split = new ArrayList<>();
        ArrayList <vector> Averages = new ArrayList<> ();// to store the average of nearest vectors of a specific split vector
        int width = data.get(0).width;
        int height = data.get(0).height ;
        for (int i = 0; i < split.size(); i++)  
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
        Save_CodeBook_CompImg ( codeBook , comp_image);
    }

    Scanner sc;

    public void open_file(String FileName) {
        try
        {
            sc = new Scanner(new File(FileName));
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
    }

    public void close_file() 
    {
        sc.close();
    }

    Formatter out; //34an yktb el tag be format el string

    public void openfile(String pass) 
    {
        try 
        {
            out = new Formatter(pass);
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
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
        Image.writeImage(Decomp_image, "Decompress.jpg", Decomp_image[0].length, Decomp_image.length);


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
                    cur.data[w][h]= Double.parseDouble(elements[h]);
                }

            }

            codeBook.add(cur);

        } 

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


}
