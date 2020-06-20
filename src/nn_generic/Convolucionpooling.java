/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nn_generic;

/**
 *
 * @author gerson7
 */


import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public final class Convolucionpooling extends Component {

    public static void main(String[] foo) throws IOException {
        BufferedImage img = null;
       // img = ImageIO.read(new File("C:\\Users\\gerson7\\Documents\\leerjpg\\src\\leerjpg\\tanque.jpg")); // eventually C:\\ImageTest\\pic2.jpg
          //   mostrar(img);

        //   marchThroughImage(img);
        //   pastoEscalagrise(img);
       //   mostrar(img);
        //  escalar(img);
        double[][] m1 = {{0, 0, 0, 0, 0, 0},
            {0, 0, 0.6, 0.6, 0, 0}, 
            {0, 0.6, 0, 0, 0.6, 0},
        {0, 0.6, 0.6, 0.6, 0.6, 0},
        {0, 0.6, 0, 0, 0.6, 0},
        {0, 0, 0, 0, 0, 0}
        };
        double[][] m2 = {{1, 0, -1}, {2, 0, -2}, {1, 0, -1}};
        double[][] test={{0.0,0.0},{0.0 , 0.6}};
        double[][]m3=new double[m1.length - m2.length + 1][m1.length - m2.length + 1];;
       double[][]m4=new double[m3.length/2][m3.length/2];;

       m3=(convolucion(m1, m2));
       prinmatriz(m3);
        System.out.println("");
       prinmatriz(matrixrelu(m3));
       m4=pooling(matrixrelu(m3));
       
    //    System.out.printf("maximo : %f",mamixmomatriz(test)); 
      System.out.println("-------");
      prinmatriz(m4);
        System.out.println("-------");
// img =image;
    }
  
    public static void prinmatriz(double[][] m) {
        for (int x = 0; x < m.length; x++) {
            for (int y = 0; y < m[x].length; y++) {
                    System.out.printf("%11.1f",m[x][y]);
            }
            System.out.printf("\n");
        }
    }
    
    public static void mostrar(BufferedImage img) {
        JLabel label = new JLabel(new ImageIcon(img));
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(label);
        f.pack();
        f.setLocation(200, 200);
        f.setVisible(true);

    }
    public static double[][] matrixrelu(double [][] m) {
        //  double  n[][] = new double[ n.length][n.length];
           for (int x = 0; x < m.length; x++) {
            for (int y = 0; y < m[x].length; y++) {
                   m[x][y]=relu(m[x][y]);
            }
            
            System.out.printf("\n");
        }
     return m;
    }
    public static double relu(double n){
        if(n>0){
            return n;
        }else{
            return 0;
        } 
    }
    public static float[][] escalar(BufferedImage img) {
        float matriz[][] = new float[img.getHeight()][img.getWidth()];

        for (int i = 0; i < img.getHeight(); i++) {

            for (int j = 0; j < img.getWidth(); j++) {
                float mediapixel, colorSRGB;

                Color c = new Color(img.getRGB(j, i));
                float pi = c.getRed();
                // System.out.println("hello fuckin world");
                //  System.out.println(c.getRed());
                //  System.out.printf("%.2f " ,(pi/255));
                // colorSRGB=(pi<<16) | (pi<<8) | pi;
                matriz[i][j] = (pi / 255);
            }

            System.out.println(" ");
        }
        return matriz;

    }

    public static double[][] convolucion(double[][] m, double[][] n) {
        //double mcon[][] = null;
        double[][] mcon = new double[m.length - n.length + 1][m.length - n.length + 1];

        for (int x = 0; x < m.length - n.length + 1; x++) {
            for (int y = 0; y < m.length - n.length + 1; y++) {

                double[][] aux = new double[n.length][n.length];
                for (int k = x, a = 0; k < n.length + x; k++, a++) {

                    for (int l = y, b = 0; l < n.length + y; l++, b++) {
                      //  System.out.printf("%11.1f", m[k][l]);
                        aux[a][b] = m[k][l];
                     System.out.printf("%11.1f", aux[a][b]);

                    }
                    System.out.printf("\n");

                }
                mcon[x][y] = multiplicación(aux, n);
                System.out.println("------");

            }
        }

        return mcon;
    }
    public static double[][] pooling(double[][] m){
          double[][] aux = new double[m.length/2][m.length/2];
           double[][] aux2 = new double[m.length/2][m.length/2];
          
         for (int i = 0,z=0; i < m.length; i=i+ (m.length/2),z++) {
             for (int j = 0,q=0; j <m.length ; j=j+ (m.length/2),q++) {
                 //  int au=0;
                 for (int k = i,a=0; k <i+(m.length/2) ; k++,a++) {
                     for (int l = j,b = 0; l<j+(m.length/2); l++,b++) {
                       aux[a][b]=m[k][l];
                        // System.out.println("este valor :"+m.length/2);
                      //   System.out.println("k:"+k);
                        // System.out.println("l:"+l);
                      //   v[au]=m[k][l];
                        
                        System.out.printf("%11.1f", aux[a][b]);
                     }
                      System.out.printf("\n");
                 }
                 //aca
                aux2[z][q] =mamixmomatriz(aux);
                    System.out.println("max"+mamixmomatriz(aux));
                    System.out.println("z:"+z);
                    System.out.println("q:"+q);

                  System.out.printf("\n");
             //   System.out.printf("%11.1f", aux2[z][q]);
             }
        }
       return  aux2;
          
    }
    public static double multiplicación(double[][] A, double[][] B) {
        // columnas de la matriz A
        int n = A[0].length;
        // filas de la matriz A
        int m = A.length;
        // filas de la matriz B
        int n2 = B.length;
        // columnas de la matriz B
        int o = B[0].length;
        // nueva matriz 
            double a = 0;
        // se comprueba si las matrices se pueden multiplicar
   
        
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j <  B.length; j++) {
                    //aqui se multiplica la matriz
                    a = a + (A[i][j] * B[i][j]);
                     // System.out.println("prea:"+a);
                   
                }
             //   System.out.println("a:"+a);

            }

        
            
        /**
         * si no se cumple la condición se retorna una matriz vacía
         */
        return a;
    }
    public static double  mamixmomatriz(double[][] m){
         double []v=new double[m.length*2];
         int cont=0;
        for (int i = 0; i <  m.length; i++) {
              //   System.out.println("cont:"+cont);

            for (int j = 0; j <  m[i].length; j++) {
               v[cont]=m[i][j];
                cont++;
          //      System.out.println("cont:"+cont);
            }
        }
      double n=   maximovector(v);
      return n;
    }
 public static double  maximovector(double []v){
      double max=v[0];
     for (int i = 1; i < v.length; i++) {
         if (v[i]>max) {
            max=v[i];
         }
     }
     return max;
 }
    
    public static void pastoEscalagrise(BufferedImage img) {

        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                //Almacenamos el color del píxel
                Color coloraux;
                coloraux = new Color(img.getRGB(x, y));
                int mediapixel, colorSRGB;
                //Almacenamos el color del píxel

                //calculamos la mediade los  tres canales(rojo,ver,azul)
                mediapixel = (int) ((coloraux.getRed() + coloraux.getGreen() + coloraux.getBlue()) / 3);
                //cambiamo a formato sRGB
                //  System.out.println("mediapixel:" +mediapixel);
                colorSRGB = (mediapixel << 16) | (mediapixel << 8) | mediapixel;
                // System.out.println("colorSRGB:" +colorSRGB);

                img.setRGB(x, y, colorSRGB);

                //     System.out.println(": "+colorSRGB);  
            }
        }

    }

    public static void printPixelARGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);

    }

    private static void marchThroughImage(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        System.out.println("width, height: " + w + ", " + h);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.println("x,y: " + j + ", " + i);
                int pixel = image.getRGB(j, i);
                printPixelARGB(pixel);
                System.out.println("");
            }
        }

    }

}

