<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Calculadora_TEC</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/stylePantalla.css">
    <script type="text/javascript" src="js/calculadora.js"></script>
</head>
<body>
<div id="columna1">
    <center>
        <h2>Ingrese una expresión</h2>
        <br>
        <div class="calculadora">
            <form action="#" name="calculadora" id="calculadora">
                <p id="textoPantalla">0</p>
                <p>
                    <!-- Nos permite gererar espacios en el html -->
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" class="largo" value="<-- Borrar" onclick="borradoParcial()"/>
                    <input type="button" class="largo" value="C" onclick="borradoTotal()"/>
                </p>
                <p>
                    <input type="button" value="7" onclick="numero(7)"/>
                    <input type="button" value="8" onclick="numero('8')"/>
                    <input type="button" value="9" onclick="numero('9')"/>
                    <input type="button" class="operadores" value="+" onclick="numero('+')"/>
                    <input type="button" class="operadores" value="-" onclick="numero('-')"/>
                </p>
                <p>
                    <input type="button" value="4" onclick="numero('4')"/>
                    <input type="button" value="5" onclick="numero('5')"/>
                    <input type="button" value="6" onclick="numero('6')"/>
                    <input type="button" class="operadores" value="*" onclick="numero('*')"/>
                    <input type="button" class="operadores" value="/" onclick="numero('/')"/>
                </p>
                <p>
                    <input type="button" value="1" onclick="numero('1')"/>
                    <input type="button" value="2" onclick="numero('2')"/>
                    <input type="button" value="3" onclick="numero('3')"/>
                    <input type="button" class="operadores" value="%" onclick="numero('%')"/>
                    <input type="button" class="operadores" value="(" onclick="numero('(')"/>
                </p>
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" value="0" onclick="numero('0')"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" class="operadores" value=")" onclick="numero(')')"/>
                    <input type="button" class="operadores" value="=" onclick="igualar()"/>
                </p>
            </form>
        </div>
    </center>
</div>

<div id="columna2">
    Columna 2

</div>
</body>
</html>