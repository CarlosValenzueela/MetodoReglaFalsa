/**
 * Paquete reglaFalsa.
 */
package reglafalsa;

/**
 * Importación del Scanner, que sirve para los valores de entrada
 */
import java.util.Scanner;

/*Nombre del archivo: asignacion03_00000207256
Nombre de alumno: Carlos Antonio Valenzuela Valdez
Matricula: 00000207256
Fecha de creación: 10/09/2020 */
/**
 * El programa de regla falsa o falsa posición nos ayuda a encontrar las raices
 * de una función ya establecida. Al ingresar datos de entrada iniciales y
 * finales, ademas de un error aproximado maximo que se usara para aproximarse a
 * la respuesta. Este nos ayuda a mejorar la velocidad al momento de querer
 * encontrar raices, ya que es muy rapido y facil hacerlo funcionar. Nos brinda
 * todos los datos con los que opera para dar el resultado así como las
 * iteraciones que necesito para dar con dicha respuesta.
 */
/*
Pasos a seguir en el programa.
1.- Escójanse dos valores iniciales
xi y xf de forma que tal que la
función cambie de signo en el
intervalo.
2.- Con los datos ingresados se trabajara en la proxima aproximacion
3.- Se comparara los resultados para obtener un resultado 
4.- Se calcula una nueva aproximación a la raíz.
5.- Decídase si la nueva aproximación es tan exacta como se
desea. Si es así los cálculos terminan, de otra manera
regrese al paso 3.
 */
/**
 * Clase donde se pediran los datos de entrada; los valores del valor inicial,
 * valor final y el error aproximado, esto para poder seguir con el algoritmo y
 * obtener la respuesta del mismo.
 *
 * @author CarlosValenzuela
 */
public class ReglaFalsa {

    public static void main(String[] args) {
        //Declaracion del método Scanner
        Scanner teclado = new Scanner(System.in);

        //Variables para los datos de entrada
        double valorInicial = 0.0, valorFinal = 0.0, errorAproxMax = 0.0;

        //Finalidad del programa.
        System.out.println("El programa de regla falsa o falsa posición nos ayuda a encontrar las raices\n"
                + "de una función ya establecida. Al ingresar datos de entrada iniciales y\n"
                + "finales, ademas de un error aproximado maximo que se usara para aproximarse a\n"
                + "la respuesta. Este nos ayuda a mejorar la velocidad al momento de querer\n"
                + "encontrar raices, ya que es muy rapido y facil hacerlo funcionar. Nos brinda\n"
                + "todos los datos con los que opera para dar el resultado así como las\n"
                + "iteraciones que necesito para dar con dicha respuesta.");

        System.out.println("\n F(x) = (0.8 - (0.3 * x)) / x");

        //Solicitud de datos
        System.out.println("\nSolicitud de los datos de entrada");
        System.out.print("Ingrese el valor inicial: ");
        valorInicial = teclado.nextDouble();

        System.out.print("Ingrese el valor final: ");
        valorFinal = teclado.nextDouble();

        System.out.print("Ingrese el error aproximado máximo: ");
        errorAproxMax = teclado.nextDouble();

        ReglaFalsa main = new ReglaFalsa();
        main.reglaFalsa(valorInicial, valorFinal, errorAproxMax);
    }

    /**
     * Método que hara el proceso de cambio de variables mediante el uso de otro
     * método para calcular la ordenada de Y, de esta forma se podra seguir con
     * el procedimiento con el programa y poder encontrar la raiz que satisface
     * al problema. Mostrara los valores obtenidos al terminar.
     *
     * @param valorInicial Valor X izquierdo
     * @param valorFinal Valor X derecho
     * @param errorAproMax Error aproximado
     */
    public void reglaFalsa(double valorInicial, double valorFinal, double errorAproMax) {
        //Declaración de variables
        errorAproMax = errorAproMax / 100;
        int iteraciones = 1;
        double funcionXi = 0.0, funcionXf = 0.0, funcionXr = 0.0, xR = 0.0, xRNueva = 0.0, errorAprox = 0.0, multiplicacionValores = 0.0;
        double aux = 0.0, aux2 = 0.0;
        boolean continuar = true;

        System.out.println("\n\n \t\t\t\t\t Tabla de valores ");
        System.out.print("\n\nIteración     Xi\t   xF\t      F(Xi)     F(Xf)\t\txR\t  F(Xr)\t      F(Xi)*F(Xr)      ea");
        //Ciclo para saber iteraciones, así como para proseguir con los pasos del algoritmo
        while (continuar) {
            //Obtiene F(i) y F(f)
            funcionXi = f(valorInicial);
            funcionXf = f(valorFinal);

            //Aplicando la regla 
            xR = ((valorInicial * funcionXr) - (valorFinal * funcionXi)) / (funcionXf - funcionXi);
            //Obtener F de (xR)
            funcionXr = f(xR);

            //Obtener la multiplicacion de F(xI) * F(xR)
            multiplicacionValores = funcionXi * funcionXr;
            if (iteraciones == 1) {
                imprimirValores2(iteraciones, valorInicial, valorFinal, funcionXi, funcionXf, xR, funcionXr, multiplicacionValores);
                iteraciones++;

            }
            if (multiplicacionValores == 0) {
                imprimirValores(iteraciones, valorInicial, valorFinal, funcionXi, funcionXf, xR, funcionXr, multiplicacionValores, errorAprox);

                break;
            }
            if (multiplicacionValores < 0) {
                //Variables para guardar dato a cambiar
                aux = xR;
                aux2 = valorInicial;
            }

            if (multiplicacionValores > 0) {
                //Variables para guardar dato a cambiar
                aux = valorFinal;
                aux2 = xR;

            }

            xRNueva = ((aux2 * funcionXr) - (aux * funcionXi)) / (funcionXf - funcionXi);
            if (xRNueva == 0) {
                imprimirValores(iteraciones, valorInicial, valorFinal, funcionXi, funcionXf, xR, funcionXr, multiplicacionValores, errorAprox);

                break;

            }

            errorAprox = (Math.abs(xRNueva - xR) / xRNueva);

            //Si el error aproximado es menor al error aproximado maximo, se finaliza el programa y se despliega la tabla.
            if (Math.abs(errorAprox) < errorAproMax) {
                imprimirValores(iteraciones, valorInicial, valorFinal, funcionXi, funcionXf, xR, funcionXr, multiplicacionValores, errorAprox);

                break;
            }
            imprimirValores(iteraciones, valorInicial, valorFinal, funcionXi, funcionXf, xR, funcionXr, multiplicacionValores, errorAprox);

            //Dar el valor que cambio a las variables.
            valorFinal = aux;
            valorInicial = aux2;
            iteraciones++;

        }

        System.out.println("\n\nResultados obtenidos: ");
        System.out.println("Número de iteraciones requeridas para encontrar raíz: " + iteraciones);
        System.out.printf("Valor de la raíz: %.6f \n", xR);
        System.out.printf("Valor de la función para la raíz: %.6f \n", funcionXr);

    }

    /**
     * Método que regresa la ordenada de Y recibiendo X como parametro para
     * sustituir los valores en la función. .
     *
     * @param x Valor al que se le sacara la ordenada
     * @return Valor de la función
     */
    public double f(double x) {
        double funcion = 0.0f;

        funcion = (0.8 - (0.3 * x)) / x;

        return funcion;

    }

    /**
     * Método para imprimir la tabla de valores
     *
     * @param iteraciones Número de iteraciones necesarias para dar con el
     * resultado
     * @param vI Valor inicial
     * @param vF Valor final
     * @param xR xR
     * @param fXf Funcion de valor final
     * @param fXi Funcion de valor inicial
     * @param fXr Funcion de xR
     * @param mult Multiplicacion de las funciones xI y xR
     * @param ea Error aproximado.
     */
    public void imprimirValores(int iteraciones, double vI, double vF, double fXi, double fXf, double xR, double fXr, double mult, double ea) {
        System.out.printf("\n%d     \t    %5.6f    %8.6f    %8.6f    %8.6f    %10.6f   %8.6f   %10.6f    %8.6f", iteraciones, vI, vF,
                fXi, fXf, xR, fXr, mult, ea);

    }

    /**
     * Método para imprimir la tabla de valores
     *
     * @param iteraciones Número de iteraciones necesarias para dar con el
     * resultado
     * @param vI Valor inicial
     * @param vF Valor final
     * @param xR xR
     * @param fXf Funcion de valor final
     * @param fXi Funcion de valor inicial
     * @param fXr Funcion de xR
     * @param mult Multiplicacion de las funciones xI y xR
     */
    public void imprimirValores2(int iteraciones, double vI, double vF, double fXi, double fXf, double xR, double fXr, double mult) {
        System.out.printf("\n%d     \t    %5.6f    %8.6f    %8.6f    %8.6f    %10.6f   %8.6f   %10.6f    ", iteraciones, vI, vF,
                fXi, fXf, xR, fXr, mult);

    }

}
