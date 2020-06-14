package nn_generic;

public class Main {
    
    public static void main(String[] args) {
        
        double ingreso[][]={
                            {1,1,1,1,0,1,1,1,1,1,0,1,1,0,1},
                            {1,1,1,1,0,0,1,1,1,1,0,0,1,1,1},
                            {0,1,0,0,1,0,0,1,0,0,1,0,0,1,0},
                            {1,1,1,1,0,1,1,0,1,1,0,1,1,1,1}};
        double salida[][]={{0,0},{0,1},{1,0},{1,1}};
        double evaluar[][]={
                            {1,1,0,1,0,1,1,1,1,1,0,1,0,0,1}, //salida debe ser : {0,0}
                            {1,1,1,1,0,0,1,1,0,1,0,0,1,1,1}, //salida debe ser : {0,1}
                            {0,1,1,0,1,0,0,1,0,0,1,0,1,1,0}, //salida debe ser : {1,0}
                            {1,1,0,1,0,1,1,0,1,1,0,1,1,1,0}};//salida debe ser : {1,1} 
        //RnGen01 rn = new RnGen01(15,15,15,3,3,3,3,3,2); // (ingreso , capa oculta, salidas)
        RnGen01 rn = new RnGen01(15,15,3,2);
        rn.entrenamiento(ingreso, salida,1000);
        rn.prueba(evaluar);
    }   
}
