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
    |
  </font>

<A href="./ayuda_ver_partidas.php<?php echo "?sala=".$sala; ?>">
  <FONT face="Verdana,Arial" size=4>
    See games</font></A>
    
  <FONT face="Verdana,Arial" size=4>
    | Download games 
  </font>

</p>

<FONT face="Verdana,Arial" size=3>
<br>
<p align="left">


You can download several chess games in a single file .jar. Up to 100 games per file. Menu "View / Games" click on "History" and then selecting several rows of the list at the same time using the mouse and keys "control" or "Shift". Once selected desired games click "Download Games." The goal is download our chess games to a folder in our computer and be able to see them again just to make double click on the file. The items are displayed in a window as follows:
<br>

<p align="center">
  <IMG SRC="../../imagenes/visorPartidas.jpg">
</p>

<br>
The file is called INVITADO48_INVITADO63_2007-08-07_18-36-06.jar, with the file name already know where the game was played and among opponents. 
<br>
<br>
The file you download is a .jar, an executable java including the chess game and the program to see, really is a compressed file, so often associated with the programs of decompression. THIS IS THE PROBLEM, which in some computers to double-click on the file appears winrar unpacking the file and the program is not showing the game.
<br>
<br>
What you need to do is tell the system it will open the Java virtual machine and not winrar or any other decompression program associated with this extension.
<br>
<br>
Pressing the right mouse button .jar that is downloaded and click "Open with / Choose Program" associated extension .jar with the program javaw, if that does not appear directly on the list is often similar to a folder:
<br>
<br>
C:\Program Files\Java\j2re1.4.2_03\bin
<br>
<br>
<br>

Once you have correctly associate the extension .jar with the program javaw to see the chess games is enough to double click on the file .jar. 
<br>
<br>
<br>
On Linux systems with the browser files click on the file .jar with the right mouse button "Open With ..." and then type "java -jar" and this should open the window showing the chess games.
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
