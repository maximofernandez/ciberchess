<html>

<head>
<TITLE>CiberChess - Jugar Ajedrez Online</TITLE>

</head>

<body bgcolor='black' text='#EBDAB8' lang='ES' >


<p align=center>

<?php
echo "<img src='../../../". $sala ."/imagenes/LogoHtml.jpg'>";
?>
        
</p>


<TABLE border=0 background='../../imagenes/fondo.jpg'>
<TR>
<TD> 
</TD>
<TD>

<p align=center>

<A href="./ayuda_tablero.php<?php echo "?sala=".$sala; ?>">
  <FONT face="Verdana,Arial" size=4>
    Jugar partidas</font></A>
    
  <FONT face="Verdana,Arial" size=4>
    | Ver partidas |
  </font>
  

<A href="./ayuda_descargar_partidas.php<?php echo "?sala=".$sala; ?>">
  <FONT face="Verdana,Arial" size=4>
    Descargar partidas</font></A>  

</p>

<FONT face="Verdana,Arial" size=3>
<br>
<p align="left">

Para acceder a la ventana de "Ver partidas" hay que seleccionar en el men� "Ver/Partidas".
<br>
<br>
Se pueden ver las partidas que se est�n jugando en este momento y tambi�n se pueden
ver todas las partidas que se han jugado en los �ltimos meses y que todav�a est�n 
almacenadas en la base de datos.
<br>
<br>
Para ver las partidas que se est�n jugando se pulsa el bot�n "On line", para ver las partidas
ya terminadas de un determinado jugador se escribe su nombre en el campo de texto y se pulsa el
bot�n "Hist�rico". Si se pulsa el bot�n "Hist�rico" con el campo de texto en blanco se 
mostrar�n las partidas ya terminadas de todos los jugadores. En cualquier caso se
mostrar� una lista similar a la siguiente:

<p align="center">
  <IMG SRC="../../imagenes/verPartidas.jpg">
</p>

<br>
Haciendo clic en la �ltima fila que pone "M�s datos" se a�aden a la lista las siguientes 20
partidas, se cargan de 20 en 20 porque a veces el listado puede ser de en torno a medio
mill�n de partidas, con lo que se saturar�a la conexi�n, por mucho ancho de banda que se 
tenga.
<br>
<br>
Para ver una partida se selecciona en la lista y luego se pulsa el bot�n "Ver Blancas"
para ver el tablero desde el lado de las blancas o "Ver Negras" para ver el tablero
desde el lado de las negras. Si se pulsa "<A href="./ayuda_descargar_partidas.php<?php echo "?sala=".$sala; ?>">Descargar Partida</A>"
 se abrir� un navegador que 
preguntar� donde guardar el archivo con la partida en el ordenador local.

<br>
<br>
<br>
<br>
</p>


</TD>
</TABLE>

</body>

</html>
