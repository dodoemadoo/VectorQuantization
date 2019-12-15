import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	public static int height;
    public static int width;
    public static int[][] readImage(String path) 
    {
    	BufferedImage img = null; 
        File f = null;
        int[][] imagePixels;
        try
        { 
            f = new File(path); 
            img = ImageIO.read(f); 
        } 
        catch(IOException e) 
        { 
            System.out.println(e); 
        }
        height = img.getHeight(); 
        width = img.getWidth(); 
        imagePixels= new int[height][width];
        for (int y = 0; y < height; y++) 
        { 
            for (int x = 0; x < width; x++) 
            { 
                int pixel = img.getRGB(x,y); 
                int alpha = (pixel>>24)&0xff; 
                int red = (pixel>>16)&0xff; 
                int green = (pixel>>8)&0xff; 
                int blue = pixel&0xff; 
                imagePixels[y][x] = red;
            }
        }
        return imagePixels;
    }
    
    public static void writeImage(int[][] imagePixels, int width, int height, String outPath) 
    {
        File f = null;
    	BufferedImage img = null; 
        try
        { 
            f = new File(outPath);
            img =new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB );
            for(int x=0;x<width ;x++)
            {
                for(int y=0;y<height;y++)
                {
                    img.setRGB(x,y,(imagePixels[y][x]<<16)|(imagePixels[y][x]<<8)|(imagePixels[y][x]));
                }
            }
            ImageIO.write(img, "jpg", f); 
        } 
        catch(IOException e) 
        { 
            System.out.println(e); 
        } 
    }
}
