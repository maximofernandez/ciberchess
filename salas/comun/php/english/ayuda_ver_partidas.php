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


<TABLE border=0 background='../../imagenes/fondo.jpg'>
<TR>
<TD> 
</TD>
<TD>

<p align=center>

<A href="./ayuda_tablero.php<?php echo "?sala=".$sala; ?>">
  <FONT face="Verdana,Arial" size=4>
    Playing games</font></A>
    
  <FONT face="Verdana,Arial" size=4>
    | See games |
  </font>
  

<A href="./ayuda_descargar_partidas.php<?php echo "?sala=".$sala; ?>">
  <FONT face="Verdana,Arial" size=4>
    Download games</font></A>  

</p>

<FONT face="Verdana,Arial" size=3>
<br>
<p align="left">

To access the window "See chess games" must be selected in the menu "View / Games." 
<br>
<br>
You can see the chess games that are playing now (live) and you can also see all the games that have been played in recent months and who are still stored in the database.
<br>
<br>

To view the chess games that are playing now click the button "Online", to view the games already completed a certain player is written his name in the text field and click the button "History". If you press the button "History" with the text field blank are displayed games already completed all the players. In any case you will see a list like this:
<p align="center">
  <IMG SRC="../../imagenes/verPartidas.jpg">
</p>

<br>
Clicking on the last row that says "More data" add to the list the following 20 games, are charged to 20 in 20 because sometimes the list can be around a million chess games, which would saturate the connection, however much bandwidth to be taken.
<br>
<br>
To view a game is selected in the list and then click "White Side" to see the board from the side of the white or "Black Side" to see the board from the side of the blacks. When pressed "Download Game" opens a browser to ask where to save the file with the game on the local computer. 
 When pressed "<A href="./ayuda_descargar_partidas.php<?php echo "?sala=".$sala; ?>">Download Game</A>"
 opens a browser to ask where to save the file with the game on the local computer.

<br>
<br>
<br>
<br>
</p>


</TD>
</TABLE>

</body>

</html>
