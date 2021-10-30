package Arbol;

import java.lang.*;

class resultado {

    Node root;

    /*Clase que asigna los valores de los nodos*/
    public static class Node {
        String data;
        Node left, right;

        Node(String d) {
            data = d;
            left = null;
            right = null;
        }
    }

    /*Convierte el string en int y verifica si hay numeros negativos, ademas evalua si hay un numero negativo */
    private static int toInt(String s) {
        int num = 0;

        if (s.charAt(0) != '-')
            for (int i = 0; i < s.length(); i++)
                num = num * 10 + ((int) s.charAt(i) - 48);

            /*Si hay un numero negativo entonces evalua primero el valor positivo, agrega el negativo e invierte la expresión */
        else
            for (int i = 1; i < s.length(); i++) {
                num = num * 10 + ((int) (s.charAt(i)) - 48);
                num = num * -1;
            }
        return num;
    }

    /*Evalúa la expresion */
    public static int evalTree(Node root) {

        // Empty tree
        if (root == null) /*Verifica que la raiz no sea nulo */
            return 0;

        //*Verifica que los hijos izquierdo y derecho no sean nulos */
        if (root.left == null && root.right == null)
            return toInt(root.data);

        /*Evalua el subsarbol izquierdo */
        int leftEval = evalTree(root.left);

        /*Evalua el subarbol izquierdo */
        int rightEval = evalTree(root.right);


        if (root.data.equals("+")) /*Si es una suma entonces suma los 2 hijos */
            return leftEval + rightEval;

        if (root.data.equals("-")) /*Si es una resta entonces suma los 2 hijos */
            return leftEval - rightEval;

        if (root.data.equals("*")) /*Si es una multilicacion entonces suma los 2 hijos */
            return leftEval * rightEval;

        if (root.data.equals("/")) /*Si es una division entonces suma los 2 hijos */
            return leftEval / rightEval;

        if (root.data.equals("%")) /*Si es una division entera entonces suma los 2 hijos */
            return leftEval % rightEval;

        return leftEval / rightEval;
    }
}