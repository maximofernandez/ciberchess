<html>

<head>
<TITLE>Ciberchess - Play chess online</TITLE>

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
    Playing games | 
  </font>


<A href="./ayuda_ver_partidas.php<?php echo "?sala=".$sala; ?>">
  <FONT face="Verdana,Arial" size=4>
    See games</font></A>

  <FONT face="Verdana,Arial" size=4>
    |
  </font>

<A href="./ayuda_descargar_partidas.php<?php echo "?sala=".$sala; ?>">
  <FONT face="Verdana,Arial" size=4>
    Download games</font></A>


</p>     


<FONT face="Verdana,Arial" size=3>
<br>
<p align="left"><b>CREATE GAMES: </b>you can play multiple simultaneous with different opponents, each game will create a new tab. To create a game select an opponent from the list on the left (below) and then clicking on it. 
<br>
<br>
At the time of challenge may be marked if we want to increase time. If select game with increases, this occurs at every move by the system Brónstein (not earn time but recovers). It is intended not to lose time for a game clearly won. To ensure that the games were not eternicen and have a maximum limit of time, the increase only occurs through movement 40, from there the game is finished by the system guillotine. So we have what is called a pattern of increase varies. 
<br>
If the opponet accepts the challenge a tab appears with a new game, if he does not accept a message indicates it. 
<br>
<br>
By clicking on a opponent of the list appears as a window where the following conditions are laid down the challenge. 
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
      Chess game (minutes)
    </TD>
    <TD>
      Increase (seconds)
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

<p align="left"><b>CHESSBOARD:</b> to move drag (click with the mouse button and holding tight move to the destination where raves) from the square origin to the destination, even when it is occupied by a opponent piece that we are eat. When a pawn reaches to crown, the pieces are shown consecutively queen, rook, bishop and knight until clicking one with the mouse to select it. Once we have done our movement and it appears to opponent we can not rectify. So think carefully before moving. This is also the case in any chess tournament. </p>

<p align="left">The buttons  |&lt; &lt; &gt; &gt;| are, as you will have guessed, to be able to track the game allowing go back to start, go to end, a movement back or a movement forward. </p>

<p align="left"><b>PUBLIC CHAT: </b>You can send messages to all players who currently are connected. To tell you what you want or ask or get in line with them to play. To send messages key in the text box blank and press ENTER already been sent to all.</p>


<p align="left"><b>PRIVATE CHAT: </b>To send messages key in the text box blank and press ENTER already been sent to opponent.</p>

<p align="left">
To improve the language used in the chat, from version 2.03 a record with the IP address, date, user and the sentence.
</p>

<p align="left">
It is intended that each be responsible for what they say in the chat with this information you can know that someone is behind every comment, as though using IP address variable in each connection, Internet providers are also obliged to keep records that IPs assigned to their customers and that time intervals.
</p>

<p align="left">
The file with this record is available to any judge who demands. 
</p>


<br>
<br>
<br>

</font>


</TD>
</TABLE>

</body>

</html>
