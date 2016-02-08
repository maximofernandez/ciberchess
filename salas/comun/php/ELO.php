<html>

<head>
<TITLE>CiberChess - Jugar Ajedrez Online</TITLE>
</head>

<body align='center' bgcolor='black' lang=ES >

      <FONT size='1'>
      <BR>
      </FONT> 
      
      <img src='../../<?php echo $sala; ?>/imagenes/LogoHtml.jpg'>

      <FONT size='1'>
      <BR>
      </FONT> 

<TABLE border='0' cellPadding='0' cellSpacing='0' width='250'>
  <TR>
    <TD bgColor='black' height='19' width='200'><FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' 
      size='4'>
      <B>
      <?php
        if($idioma=="english")
          echo "Username";
        else
          echo "Usuario";
      ?>
      </B></FONT>
      <FONT size='1'>
      <BR>
      </FONT>     
    </TD>
    <TD bgColor='black' height='19' width='50' align=right><FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' 
      size='4'><B>ELO</B></FONT>
      <FONT size='1'>
      <BR>
      </FONT>      
    </TD>
  </TR>



<?php

include 'ConexionBD.php';

if (!isset($posicion))
  $posicion=0;
  
  
$result7 = mysql_query("SELECT count(*) as cuenta FROM claves", $link);
$totalClaves = mysql_result($result7, 0, "cuenta");  

if ($posicion > $totalClaves)
  $posicion = $totalClaves - 15;
  
if ($posicion<0)
  $posicion=0;


$SQL="SELECT *";
$SQL=$SQL." FROM claves";
$SQL=$SQL." WHERE login!='ADMINISTRADOR'";
$SQL=$SQL." ORDER BY elo DESC,login ASC";
$SQL=$SQL." LIMIT $posicion,15"; 
$result = mysql_query($SQL, $link);

$contador = 0;	
while($row = mysql_fetch_array($result))
{
 if($contador % 2 == 0)
 {
  echo "<TR>\n";
    echo "<TD bgColor='#222222' width='200'>\n";
     echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='3'>\n";
       echo $row["login"];
     echo "</FONT>\n";
   echo "</TD>\n";
   echo "<TD bgColor='#222222' width='50' align='right'>\n";
     echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='3'>\n";
       echo $row["ELO"];
     echo "</FONT>\n";
   echo "</TD>\n";
  echo "</TR>\n";
 }
 else
 {
  echo "<TR>\n";
    echo "<TD bgColor='#111111' width='200'>\n";
     echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='3'>\n";
       echo $row["login"];
     echo "</FONT>\n";
   echo "</TD>\n";
   echo "<TD bgColor='#111111' width='50' align='right'>\n";
     echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='3'>\n";
       echo $row["ELO"];
     echo "</FONT>\n";
   echo "</TD>\n";
  echo "</TR>\n";
 }

 $contador ++;
}

//echo $totalClaves;

echo "</TABLE>\n";


   echo "<TABLE border='0' cellPadding='0' cellSpacing='0' width='250'>\n";
   echo "<TR>\n";

    echo "<TD  width='41' align='left'>\n";
    if ($posicion > 1)
    {
	echo "<A HREF='ELO.php?posicion=";
	echo $posicion-15;
	echo "&sala=" . $sala;
	echo "'>\n";
	echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='1'>\n";
	  echo "<B>&lt;&lt;</B>\n";
	echo "</FONT>\n";
	echo "</A>\n";
    }
    echo "</TD>\n";
    
    echo "<TD  width='41' align='left'>\n";
    if ($posicion > 1)
    {
	echo "<A HREF='ELO.php?posicion=";
	echo $posicion-15*5;
	echo "&sala=" . $sala;
	echo "'>\n";
	echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='1'>\n";
	  echo "<B>&lt;&lt;-5</B>\n";
	echo "</FONT>\n";
	echo "</A>\n";
    }
    echo "</TD>\n";
    
    echo "<TD  width='41' align='left'>\n";
    if ($posicion > 1)
    {
	echo "<A HREF='ELO.php?posicion=";
	echo $posicion-15*50;
	echo "&sala=" . $sala;
	echo "'>\n";
	echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='1'>\n";
	  echo "<B>&lt;&lt;-50</B>\n";
	echo "</FONT>\n";
	echo "</A>\n";
    }
    echo "</TD>\n";        

    echo "<TD width='41' align='right'>\n";
    if ($posicion < $totalClaves - 16)
    {
	echo "<A HREF='ELO.php?posicion=";
	echo $posicion+15*50;
	echo "&sala=" . $sala;
	echo "'>\n";
	echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='1'>\n";
	  echo "<B>+50&gt;&gt;</B>\n";
	echo "</FONT>\n";
	echo "</A>\n";
    }
   echo "</TD>\n";
   
    echo "<TD width='41' align='right'>\n";
    if ($posicion < $totalClaves - 16)
    {
	echo "<A HREF='ELO.php?posicion=";
	echo $posicion+15*5;
	echo "&sala=" . $sala;
	echo "'>\n";
	echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='1'>\n";
	  echo "<B>+5&gt;&gt;</B>\n";
	echo "</FONT>\n";
	echo "</A>\n";
    }
   echo "</TD>\n";
   
    echo "<TD width='41' align='right'>\n";
    if ($posicion < $totalClaves - 16)
    {
	echo "<A HREF='ELO.php?posicion=";
	echo $posicion+15;
	echo "&sala=" . $sala;
	echo "'>\n";
	echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='1'>\n";
	  echo "<B>&gt;&gt;</B>\n";
	echo "</FONT>\n";
	echo "</A>\n";
    }
   echo "</TD>\n";      

?>
  </TR>
</TABLE>
</BODY>
</HTML>
