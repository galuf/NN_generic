package nn_generic;

public class Rn01 {
  
  public static double sigmoidea(double x){
    return 1/(1+Math.exp(-x));
  }

  public static void mostrar(double[][] m){
    for(int i=0;i<m.length;i++){
      for(int j = 0; j<m[0].length;j++){
        System.out.print(m[i][j]+" ");
      }
      System.out.println();
    }
  }

  public static double[][] matrizSigmoidea(double[][] m){
    double[][] m2 = new double[m.length][m[0].length];
    for(int i=0;i<m.length;i++){
      for(int j = 0; j<m[0].length;j++){
        m2[i][j] = sigmoidea(m[i][j]);
      }
    }
    return m2;
  }

  public static double[][] onesColumn(double[][] m){
    //aumenta uno al inicio de todas las columnas
    double[][] one = new double[m.length+1][m[0].length];
    for(int i=0;i<one.length;i++){
      for(int j = 0; j<one[0].length;j++){
        if(i == 0) one[i][j] = 1;
        else{
          one[i][j] = m[i-1][j];
        }
      }
    }
    return one;
  }

  public static double[][] onesRow(double[][] m){
    //aumenta uno al inicio de todas las filas
    double[][] one = new double[m.length][m[0].length+1];
    for(int i=0;i<one.length;i++){
      for(int j = 0; j<one[0].length;j++){
        if(j == 0) one[i][j] = 1;
        else{
          one[i][j] = m[i][j-1];
        }
      }
    }
    return one;
  }

  public static double[][] dot(double[][] a, double[][] b){
    int fil_a = a.length;
    int col_a = a[0].length;
		
    int fil_b = b.length;
    int col_b = b[0].length;
    
    if (col_a != fil_b)
      throw new RuntimeException("No se pueden multiplicar las matrices");

    double[][] multiplicacion = new double[fil_a][col_b]; // c

    for (int i = 0; i < fil_a; i++) {
      for (int j = 0; j < col_b; j++) {
        for (int k = 0; k < col_a; k++) {
          multiplicacion[i][j] += a[i][k] * b[k][j];
        }
      }
    }
    return multiplicacion;
  }

  public static double[][] transpuesta(double[][] m){
    double[][] mT = new double[m[0].length][m.length];
    for (int x=0; x < m.length; x++) {
      for (int y=0; y < m[x].length; y++) {
        mT[y][x] = m[x][y];
      }
    }
    return mT;
  }
  
  public static void example_varargs(int ...a){
     System.out.println("We have: "+a.length+" elements.");
  }
}