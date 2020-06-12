package nn_generic;

public class Main {
    
    public static void main(String[] args) {

    double [][] input = {{1},{1}};
    double [][] output = {{1}};

    double [][] pesos1 = {{2,-2},{1,3}};
    double [][] pesos2 = {{3,-2}};
    Rn01.mostrar(Rn01.onesRow(pesos1));
    System.out.println();
    Rn01.mostrar(Rn01.onesColumn(input));
    System.out.println();
    Rn01.mostrar(Rn01.dot(Rn01.onesRow(pesos1),Rn01.onesColumn(input)));
    System.out.println();
    Rn01.mostrar(Rn01.matrizSigmoidea(Rn01.dot(Rn01.onesRow(pesos1),Rn01.onesColumn(input))));
    System.out.println();
    Rn01.mostrar(Rn01.onesColumn(Rn01.matrizSigmoidea(Rn01.dot(Rn01.onesRow(pesos1),Rn01.onesColumn(input)))));
    System.out.println();
    Rn01.mostrar(Rn01.dot(Rn01.onesRow(pesos2),Rn01.onesColumn(Rn01.matrizSigmoidea(Rn01.dot(Rn01.onesRow(pesos1),Rn01.onesColumn(input))))));
    System.out.println();
    Rn01.mostrar(Rn01.matrizSigmoidea(Rn01.dot(Rn01.onesRow(pesos2),Rn01.onesColumn(Rn01.matrizSigmoidea(Rn01.dot(Rn01.onesRow(pesos1),Rn01.onesColumn(input)))))));
    
    Rn01.example_varargs(1,3,4);
    Rn01.example_varargs(1,4,5,6,7);
    // mostrar(onesColumn(m2));
    // mostrar(onesRow(m1));
    // mostrar(transpuesta(m2));
    // mostrar(dot(m1,m2));
    // mostrar(matrizSigmoidea(dot(m1,m2)));
  }
    
}
