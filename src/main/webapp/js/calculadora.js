
window.onload = function(){ //Acciones tras cargar la página
    pantalla=document.getElementById("textoPantalla"); //elemento pantalla de salida
    document.onkeydown = teclado; //función teclado disponible
}
x="0"; //número en pantalla
xi=1; //iniciar número en pantalla: 1=si; 0=no;
coma=0; //estado coma decimal 0=no, 1=si;
ni=0; //número oculto o en espera.
op="no"; //operación en curso; "no" =  sin operación.

//mostrar número en pantalla según se va escribiendo:
function numero(num) { //recoge el número pulsado en el argumento.
    if (x == "0" && xi == 1) {	// inicializar un número,
        pantalla.innerHTML = num; //mostrar en pantalla
        x = num; //guardar número
    } else { //continuar escribiendo un número
        pantalla.innerHTML += num;
        x += num;
    }
    xi = 0 //el número está iniciado y podemos ampliarlo.
}


function igualar() {
    /*Aqui mandariamos los datos al servidor*/
}

function borradoParcial() {
    cifras = x.length; //hayar número de caracteres en pantalla
    br = x.substr(cifras - 1, cifras) //describir último caracter
    x = x.substr(0, cifras - 1) //quitar el ultimo caracter
    if (x == "") {
        x = "0";
        xi = 1;
    } //si ya no quedan caracteres, pondremos el 0
    pantalla.innerHTML = x; //mostrar resultado en pantalla
}

function borradoTotal() {
    pantalla.innerHTML = 0; //poner pantalla a 0
    x = "0"; //reiniciar número en pantalla
    xi = 1;
    coma = 0; //reiniciar estado coma decimal
    ni = 0 //indicador de número oculto a 0;
    op = "no" //borrar operación en curso.
}

function teclado(elEvento) {
    evento = elEvento || window.event;
    k = evento.keyCode; //número de código de la tecla.
    //teclas númericas del teclado alfamunérico
    if (k > 47 && k < 58) {
        p = k - 48; //buscar número a mostrar.
        p = String(p) //convertir a cadena para poder añádir en pantalla.
        numero(p); //enviar para mostrar en pantalla
    }
    //Teclas del teclado númerico. Seguimos el mismo procedimiento que en el anterior.
    if (k > 95 && k < 106) {
        p = k - 96;
        p = String(p);
        numero(p);
    }
    if (k == 32 || k == 13) {
        igualar()
    } //Tecla igual: intro o barra espaciadora
    if (k == 46) {
        borradoTotal()
    } //Tecla borrado total: "supr"
    if (k == 36) {
        borradoParcial()
    } //Tecla borrado parcial: tecla de inicio.
}
