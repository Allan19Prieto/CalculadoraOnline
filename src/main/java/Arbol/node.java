package Arbol;

import java.util.Stack;

/**
 * Instancia el valor de los nodos
 * */
class Node {

    String value;
    Node left, right;

    Node(String item) {
        value = item;
        left = right = null;
    }
}
/**
 * Clase para crear el arbol de expresion binaria
 * */
class ExpressionTree {
    private resultado eval;
    /**
     * Clase para crear el arbol de expresion binaria
     * @param element
     * @return
     */
    boolean isOperator(String element) {
        char c = element.charAt(0);
        if (c == '+' || c == '-'
                || c == '*' || c == '/'
                || c == '%') {
            return true;
        }
        return false;
    }

    /**
     * Vielve la expresion infijo
     * @param t
     */
    void inorder(Node t){
        if (t != null){
            inorder(t.left);
            System.out.print(t.value + "");
            inorder(t.right);

        }
    }
    /**
     * Construye el arbol con la expresion postfija
     * */
    Node constructTree(String[] postfix) {
        eval = new resultado();
        Stack<Node> st = new Stack<Node>();
        Node t, t1, t2;

        /**
         * Evalua toda la expresion y asigna los valoras a la pila o a la salida depende de si es operador o operando
         * */
        for (int i = 0; i < postfix.length; i++) {

            /**
             * Verifica si es un operando y lo envia a la salidad
             * */
            if (!isOperator(postfix[i])) {
                t = new Node(postfix[i]);
                st.push(t);
            } else
            {
                //Verifica si es un operador y lo envia a la pila
                t = new Node(postfix[i]);

                /**
                 * Guarda 2 nodos en la pila
                 * */
                t1 = st.pop();      //Elimina el que esta mas arriba
                t2 = st.pop();

                /**
                 * Crea los hijos(operandos)
                 * */
                t.right = t1;
                t.left = t2;

                /**
                 * Agrega a la pila
                 * */
                st.push(t);
            }
        }

        /**
         * Raiz Padre
         * */
        t = st.peek();
        st.pop();

        /**
         * Devuelve el arbol
         * */
        System.out.println(eval.evalTree(t));

        return t;
    }

    /**
     * Main de la clase
     * @param args
     */
    public static void main(String args[]) {

        ExpressionTree et = new ExpressionTree();
        /**
         * Ejemplo:
         * 2+(15*10)-6
         * Expresion Infija: (2+(15*10)-6
         * Expresion Postfija: 2 15 10 * + 6 -
         * String[] prueba = {"2", "15", "10", "*", "+", "6", "-"}; tiene que pasarlo asi <-
         * ArrayList<String> prueba = ["2", "15", "10", "*", "+", "6", "-"];
         * System.out.println("infix expression is");
         */
    }
}