package nn_generic;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class ImageProcess  extends Component {

  public static void main(String[] foo) {
    new ImageProcess();
  }

  public void printPixelARGB(int pixel) {
    int alpha = (pixel >> 24) & 0xff;
    int red = (pixel >> 16) & 0xff;
    int green = (pixel >> 8) & 0xff;
    int blue = (pixel) & 0xff;
    System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
  }
  
  private void maxPooling(BufferedImage image, String image_name) throws IOException {
            
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      System.out.print("Ingresa dim maxPooling: ");
      int poolDim = Integer.parseInt(reader.readLine());
      System.out.println(poolDim);
      
      int width_img = image.getWidth();
      int height_img = image.getHeight();
      System.out.println("new width, new height: " + width_img + ", " +height_img);
      int out_width = (int)Math.ceil((float)width_img/poolDim);
      int out_height = (int)Math.ceil((float)height_img/poolDim);
      
      BufferedImage image_out = new BufferedImage(out_width, out_height, image.getType());
      
      int outX = 0, outY = 0 ;
      
      for(int x=0; x<width_img ; x += poolDim){
        for(int y=0 ;y<height_img ; y += poolDim){

                int maxr = -1, maxg = -1, maxb = -1;

                for(int maxPoolX=x; maxPoolX< Math.min(x+poolDim,width_img); maxPoolX++){
                    for(int maxPoolY=y; maxPoolY<Math.min(y+poolDim,height_img); maxPoolY++)
                    {
                            int imageX = maxPoolX;
                            int imageY = maxPoolY;

                            int rgb = image.getRGB(imageX,imageY);
                            int r = (rgb >> 16) & 0xff; 
                            int g = (rgb >> 8) & 0xff;
                            int b = (rgb & 0xff);
                            
                            if( r > maxr) maxr = r;
                            if( g > maxg) maxg = g;
                            if( b > maxb) maxb = b;

                    }
                }
 
                int rr = (int) maxr;
                int gg = (int) maxg;
                int bb = (int) maxb;
 
                int pixel = (rr << 16) + (gg << 8) + bb;
                image_out.setRGB(outX,outY,pixel);
                
                outY++;
        }
        outY = 0;
        outX++;
      }
      
      System.out.print("Nombre de archivo modificado: ");
      String outputfname = reader.readLine();
      File myNewJPegFile = new File(System.getProperty("user.dir")+"\\src\\nn_generic\\"+outputfname+".jpg");
      ImageIO.write(image_out, "jpg", myNewJPegFile);
  }
  
  private void applyKernel(BufferedImage image, String image_name) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    double kernel[][] = {{0 , -1, 0},
                         {-1, 5, -1},
                         {0, -1, 0}}; 
    /*
    double kernel[][] = {{1 / 9, 1 / 9, 1 / 9},
                         {1 / 9, 1 / 9, 1 / 9},
                         {1 / 9, 1 / 9, 1 / 9}};*/
    
    int width_img = image.getWidth();
    int height_img = image.getHeight();
    BufferedImage image_out = image;
    System.out.println("kernel col, kernel row: " + kernel[0].length + ", " + kernel.length);
    System.out.println("Width:, Heigth: " + width_img + ", " + height_img);
    
    int kernel_col = kernel[0].length;
    int kernel_row = kernel.length;
    int offset_x = kernel_col/2;
    int offset_y = kernel_row/2;
    
    for(int x=offset_x; x<width_img - offset_x ;x++)
        for(int y=offset_y ;y<height_img - offset_y ;y++)
        {
                float red=0f,green=0f,blue=0f;
                for(int kr=0; kr<kernel_row; kr++)
                    for(int kc=0; kc<kernel_col; kc++)
                    {
                            int imageX = x + kr - offset_x; 
                            int imageY = y + kc - offset_y;

                            int rgb = image_out.getRGB(imageX,imageY);
                            int r = (rgb >> 16) & 0xff; 
                            int g = (rgb >> 8) & 0xff;
                            int b = (rgb & 0xff);

                            red += (r*kernel[kr][kc]);
                            green += (g*kernel[kr][kc]);
                            blue += (b*kernel[kr][kc]);
                    }


                
                int rr = Math.min(Math.max((int)(red*1),0),255);
                int gg = Math.min(Math.max((int)(green*1),0),255);
                int bb = Math.min(Math.max((int)(blue*1),0),255);
                /*
                int rr = (int) red;
                int gg = (int) green;
                int bb = (int) blue;*/
 
                int pixel = (rr << 16) + (gg << 8) + bb;
                image_out.setRGB(x,y,pixel);
        }
    
    System.out.print("Nombre de archivo modificado: ");
    String outputfname = reader.readLine();
    File myNewJPegFile = new File(System.getProperty("user.dir")+"\\src\\nn_generic\\"+outputfname+".jpg");
    ImageIO.write(image_out, "jpg", myNewJPegFile);
    
    
  }
  
  private void convertToGray(BufferedImage image, String image_name) throws IOException {
    
    int width_img = image.getWidth();
    int height_img = image.getHeight();
    BufferedImage image_out = image;
    System.out.println("width, height: " + width_img + ", " +height_img);
    
    for (int x = 0; x < image_out.getWidth(); ++x)
        for (int y = 0; y < image_out.getHeight(); ++y)
        {
            int rgb = image_out.getRGB(x, y);
            int alpha = (rgb >> 24) & 0Xff;
            int red = (rgb >> 16) & 0xff;
            int green = (rgb >> 8) & 0xff;
            int blue = (rgb & 0xff);
            
            // normalize and gamma correct
            float rr = (float) Math.pow(red   / 255.0 , 2.2);
            float gg = (float) Math.pow(green / 255.0 , 2.2);
            float bb = (float) Math.pow(blue  / 255.0 , 2.2);
            
            float ylinear = (float) (0.2126*rr + 0.7152*gg + 0.0722*bb);
            
            // doc: https://entropymine.com/imageworsener/grayscale/
            int grayPixel = (int) (255.0 * Math.pow(ylinear, 1.0 / 2.2));
            int gray = (alpha << 24) + (grayPixel << 16) + (grayPixel << 8) + grayPixel;
            image_out.setRGB(x, y, gray);
        }

    File myNewJPegFile = new File(System.getProperty("user.dir")+"\\src\\nn_generic\\"+image_name+"-gray.jpg");
    ImageIO.write(image_out, "jpg", myNewJPegFile);
    
  }
  
  private void marchThroughImage(BufferedImage image, String image_name) {
    
    int width_img = image.getWidth();
    int height_img = image.getHeight();
    System.out.println("width, height: " + width_img + ", " +height_img);

    for (int i = 0; i < height_img; i++) {
      for (int j = 0; j < width_img; j++) {
        System.out.println("x,y: " + j + ", " + i);
        int pixel = image.getRGB(j, i);
        printPixelARGB(pixel);
        System.out.println("");
      }
    }
    
  }

  public ImageProcess() {
    try {
      // get the BufferedImage, using the ImageIO class
      String image_name = "imagen3.jpg";
      
      BufferedImage image = ImageIO.read(this.getClass().getResource(image_name));
      image_name = image_name.substring(0,image_name.length()-4);
    //  marchThroughImage(image,image_name);
    //  convertToGray(image, image_name);
     //   marchThroughImage(image, image_name);
    //    applyKernel(image, image_name);
    maxPooling(image, image_name);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

}