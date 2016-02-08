<html>

<head>
<TITLE>CiberChess - Jugar ajedrez en línea</TITLE>

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
    |
  </font>

<A href="./ayuda_ver_partidas.php<?php echo "?sala=".$sala; ?>">
  <FONT face="Verdana,Arial" size=4>
    Ver partidas</font></A>
    
  <FONT face="Verdana,Arial" size=4>
    | Descargar partidas 
  </font>

</p>

<FONT face="Verdana,Arial" size=3>
<br>
<p align="left">


Se pueden descargar varias partidas en un solo archivo .jar. Hasta un máximo de 100 partidas por archivo.
Menú "Ver/Partidas" pulsar el botón "Historico" y luego seleccinando varias filas de la lista a la vez usando 
el ratón y las teclas "CONTROL" ó "SHIFT". Una vez seleccionadas las partidas deseadas se pulsa el botón "Descargar Partidas".
El objetivo es descargarnos nuestras partidas a 
una carpeta en nuestro ordenador y poder verlas de nuevo tan solo con hacer
doble clic sobre el archivo. Las partidas se muestran en una ventana como la
siguiente:
<br>

<p align="center">
  <IMG SRC="../../imagenes/visorPartidas.jpg">
</p>

<br>
El archivo de esta partida se llama INVITADO48_INVITADO63_2007-08-07_18-36-06.jar,
con el nombre del archivo ya sabemos cuando se jugó la partida y entre que rivales.
<br>
<br>
El archivo que se descarga es un .jar, un ejecutable de java que incluye
la partida y el programa para verla, realmente es un archivo
comprimido, por eso suele estar asociado con los programas de
descompresión. ESTE ES EL PROBLEMA, que en algunos equipos al hacer doble
clic sobre el archivo aparece el winrar descomprimiendo el archivo y no el
programa que muestra la partida.
<br>
<br>
Lo que hay que hacer es decirle al sistema que lo abra la máquina virtual
java y no el winrar o cualquier otro programa de descomprimir con el que 
este asociada esta extensión.
<br>
<br>
Pulsando con el botón derecho del raton sobre el .jar que se descarga y
clic en "Abrir con/Elegir programa" se asocia la extensión .jar con el
programa javaw, que si no aparece directamente en la lista suele estar en
una carpeta similar a la siguiente:
<br>
<br>
C:\Archivos de programa\Java\j2re1.4.2_03\bin
<br>
<br>
<br>

Una vez se ha asociado correctamente la extensión .jar con el programa javaw para 
ver las partidas es suficiente con hacer doble clic sobre el archivo .jar.
<br>
<br>
<br>
En sistemas Linux con el explorador de archivos se pulsa sobre el archivo .jar con
el botón derecho del ratón "Open With..." y se teclea "java -jar " con esto ya se
debería abrir la ventana que muestra las partidas.
<br>
<br>
<br>
<br>
<br>
</p>


</TD>
</TABLE>

</body>

</html>
