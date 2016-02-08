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


<TABLE border=0  background='../../imagenes/fondo.jpg'>
<TR>
<TD> 
</TD>
<TD>


<p align=center>

  <FONT face="Verdana,Arial" size=4>
    Jugar partidas | 
  </font>


<A href="./ayuda_ver_partidas.php<?php echo "?sala=".$sala; ?>">
  <FONT face="Verdana,Arial" size=4>
    Ver partidas</font></A>

  <FONT face="Verdana,Arial" size=4>
    |
  </font>

<A href="./ayuda_descargar_partidas.php<?php echo "?sala=".$sala; ?>">
  <FONT face="Verdana,Arial" size=4>
    Descargar partidas</font></A>


</p>     


<FONT face="Verdana,Arial" size=3>
<br>
<p align="left"><b>CREAR PARTIDAS: </b>puedes jugar varias
partidas simultáneas con distintos rivales, cada partida se creará en una nueva
pestaña. Para crear una partida seleccionas un rival en la lista de la izquierda
(abajo) y a continuación haces clic sobre él. 
<br>
<br>
En el momento de retar se puede marcar si se quiere incremento de tiempo. 

En caso de seleccionar partida con incremento, este se produce en cada movimiento por el sistema Brónstein (no permite ganar tiempo pero si recuperarlo). 

Se pretende que no se pierda por tiempo una partida claramente ganada. 

Para garantizar que las partidas no se eternicen y tener un límite máximo de duración de partida, el incremento solo se produce hasta el movimiento 40, a partir de ahí la partida se termina por el sistema de guillotina. Por tanto tenemos lo que se denomina una modalidad de incremento variable.

<br>
Si el rival acepta el reto aparece una nueva pestaña con la partida, si no lo acepta lo indica un mensaje.
<br>
<br>
Al hacer clic sobre un rival de la lista aparece una ventana como la siguiente donde se establecen las condiciones del reto.
<br>

<p align="center">
  <IMG SRC="../../imagenes/retar.jpg">
</p>

</p>

<BR>

</font>

<TABLE border="0">
  <FONT face="Verdana,Arial" size=3>
  <TR>
    <TD>
      Partida (minutos)
    </TD>
    <TD>
      Incremento (segundos)
    </TD>
  </TR>
  <TR>
    <TD>
      1
    </TD>
    <TD>
      1
    </TD>
  </TR>
  <TR>
    <TD>
      2
    </TD>
    <TD>
      1
    </TD>
  </TR>
  <TR>
    <TD>
      3
    </TD>
    <TD>
      2
    </TD>
  </TR>
  <TR>
    <TD>
      5
    </TD>
    <TD>
      3
    </TD>
  </TR>
  <TR>
    <TD>
      10
    </TD>
    <TD>
      6
    </TD>
  </TR>
  <TR>
    <TD>
      20
    </TD>
    <TD>
      12
    </TD>
  </TR>
  <TR>
    <TD>
      40
    </TD>
    <TD>
      24
    </TD>
  </TR>
  <TR>
    <TD>
      60
    </TD>
    <TD>
      36
    </TD>
  </TR>
  <TR>
    <TD>
      120
    </TD>
    <TD>
      72
    </TD>
  </TR>
  </font>
</TABLE>

<FONT face="Verdana,Arial" size=3>

<p align="left"><b>TABLERO: </b>para mover se
arrastra(pinchar con el ratón y manteniendo el botón apretado moverlo hasta el
destino donde se suelta) la ficha origen desde la casilla origen hasta la casilla
destino, incluso cuando esta se encuentra ocupada por una ficha rival que nos
disponemos a comer. Cuando un peón llega a coronar, se muestran
consecutivamente las figuras reina, torre, alfil y caballo hasta que pulsemos
una con el ratón para seleccionarla.<span style='mso-spacerun:yes'>  </span>Una
vez hayamos hecho nuestro movimiento ya le aparece al rival y no podemos
rectificar. Por tanto pensarlo bien antes de mover. Esto también es así en
cualquier torneo de ajedrez, pieza tocada, pieza movida.</p>

<p align="left">Los botones |&lt; &lt; &gt; &gt;| son, como habréis imaginado,
para poder hacer un seguimiento de la partida permitiendo ir al principio de
todo, al final de todo, un movimiento atrás o un movimiento adelante.</p>

<p align="left"><b>CHAT PÚBLICO: </b>permite enviar mensajes
para que los vean todos los jugadores que en esos momentos estén conectados.
Para contarles lo que quieras o preguntarles o ponerte de acuerdo con ellos
para jugar. Para enviar mensajes se teclean en el cuadro de texto blanco del Chat
Público, al finalizar el mensaje se pulsa INTRO y ya se envía a todos.</p>


<p align="left"><b>CHAT PRIVADO: </b>para enviar mensajes al
rival se teclean en el cuadro de texto blanco del Chat Privado, al finalizar el
mensaje se pulsa INTRO y ya se envía.</p>

<p align="left">
Para mejorar el lenguaje usado en el chat, a partir de la versión 2.03 se guarda un registro
con la dirección IP, el momento, el usuario y la frase.
</p>

<p align="left">
Se pretende que cada uno se haga responsable de lo que dice en el chat, con esta información
se puede saber que persona está detrás de cada comentario, ya que aunque use dirección IP
variable en cada conexión, los proveedores de internet también están obligados a guardar
registros de que IPs asignan a sus clientes y en que intervalos de tiempo.
</p>

<p align="left">
El archivo con este registro está a disposición de cualquier juez que lo requiera. 
</p>


<br>
<br>
<br>

</font>


</TD>
</TABLE>

</body>

</html>
