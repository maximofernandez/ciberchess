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

Para acceder a la ventana de "Ver partidas" hay que seleccionar en el menú "Ver/Partidas".
<br>
<br>
Se pueden ver las partidas que se están jugando en este momento y también se pueden
ver todas las partidas que se han jugado en los últimos meses y que todavía están 
almacenadas en la base de datos.
<br>
<br>
Para ver las partidas que se están jugando se pulsa el botón "On line", para ver las partidas
ya terminadas de un determinado jugador se escribe su nombre en el campo de texto y se pulsa el
botón "Histórico". Si se pulsa el botón "Histórico" con el campo de texto en blanco se 
mostrarán las partidas ya terminadas de todos los jugadores. En cualquier caso se
mostrará una lista similar a la siguiente:

<p align="center">
  <IMG SRC="../../imagenes/verPartidas.jpg">
</p>

<br>
Haciendo clic en la última fila que pone "Más datos" se añaden a la lista las siguientes 20
partidas, se cargan de 20 en 20 porque a veces el listado puede ser de en torno a medio
millón de partidas, con lo que se saturaría la conexión, por mucho ancho de banda que se 
tenga.
<br>
<br>
Para ver una partida se selecciona en la lista y luego se pulsa el botón "Ver Blancas"
para ver el tablero desde el lado de las blancas o "Ver Negras" para ver el tablero
desde el lado de las negras. Si se pulsa "<A href="./ayuda_descargar_partidas.php<?php echo "?sala=".$sala; ?>">Descargar Partida</A>"
 se abrirá un navegador que 
preguntará donde guardar el archivo con la partida en el ordenador local.

<br>
<br>
<br>
<br>
</p>


</TD>
</TABLE>

</body>

</html>
