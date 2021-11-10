package Arbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static ExpressionTree expressionTree;

    /**
     * Clase principal
     * @param args
     */
    public static void main(String[] args) {
        /**
         * Instancia la clase que crea el arbol
         * */
        expressionTree = new ExpressionTree();

        /**
         * Entrada de datos
         * */

        String leer = "5*3/8+(95%5-10)";

        /**
         * Elimina espacios
         * */
        String expr = depurar(leer);
        String[] arrayInfix = expr.split(" ");


        Stack < String > E = new Stack < String > (); /*Pila entrada*/
        Stack < String > P = new Stack < String > (); /*Pila operadores*/
        Stack < String > S = new Stack < String > (); /*Pila Salida*/

        /**
         * Añade la array a la pila de entrada
         * */
        for (int i = arrayInfix.length - 1; i >= 0; i--) {
            E.push(arrayInfix[i]);
        }

        try {
            /**
             * Algoritmo para convertir la expresion a postfijo
             * */
            while (!E.isEmpty()) { /*Verifica si esta vacia*/
                switch (jerarquia(E.peek())){ /*Verifica cual es la jerarquia*/
                    case 1: /*Parentesis abierto*/
                        P.push(E.pop());
                        break;
                    case 3: /*Suma o resta*/
                    case 4: /*Multiplicacion, division y division entera*/
                        while(jerarquia(P.peek()) >= jerarquia(E.peek())) { /*Verifica si la jerarquia es mayor o menor*/
                            S.push(P.pop());
                        }
                        P.push(E.pop());
                        break;
                    case 2: /*Parentesis cerrado*/
                        while(!P.peek().equals("(")) {
                            S.push(P.pop());
                        }
                        P.pop();
                        E.pop();
                        break;
                    default:
                        S.push(E.pop());
                }
            }

            /**
             * Elimina todas las impurezas de la expresion
             * */
            String infix = expr.replace(" ", "");
            String postfix = S.toString().replaceAll("[\\]\\[,]", "");
            /*Muestra los resultados*/
            System.out.println("Expresion Infija: " + infix); /*Expresion infija*/
            System.out.println("Expresion Postfija: " + postfix); /*Expresion postfija*/
            String[] posfix_array = new String[S.size()]; /*Crea la nueva array*/
            // prueba
            int index = S.size() - 1;
            while (!S.isEmpty()){
                posfix_array[index] = S.pop(); /*Ingresa los datos al array*/
                index--;
            }
            /**
             * Llama a la clase que crea el arbol
             * */
            Node root = expressionTree.constructTree(posfix_array);
            expressionTree.inorder(root);

        }catch(Exception ex){
            System.out.println("Error en la expresión algebraica");
            System.err.println(ex);
        }
    }

    /**
     * Depura la expresion
     * @param s
     * @return
     */
    private static String depurar(String s) {
        s = s.replaceAll("\\s+", ""); /*Elimina los espacios en blanco*/
        s = "(" + s + ")";
        String simbols = "+-*/%()";
        String str = "";

        /**
         * Deja espacios entre operadores
         * */
        for (int i = 0; i < s.length(); i++) {
            if (simbols.contains("" + s.charAt(i))) {
                str += " " + s.charAt(i) + " ";
            }else str += s.charAt(i);
        }
        return str.replaceAll("\\s+", " ").trim();

    }
    /**
     * Jerarquia de los operadores
     * @param op
     * @return
     */
    private static int jerarquia(String op) {
        int prf = 99;
        if (op.equals("*") || op.equals("/") || op.equals("%")) prf = 4;
        if (op.equals("+") || op.equals("-")) prf = 3;
        if (op.equals(")")) prf = 2;
        if (op.equals("(")) prf = 1;
        return prf;
    }
}