<?php

session_start();
$_SESSION["loginsesion"]=$login;
$_SESSION["passwordsesion"]=$password;
$_SESSION["salasesion"]=$sala;
$_SESSION["idiomasesion"]=$idioma;
$_SESSION["orixesesion"]="TorneosTerminados.php";
?>

<html>

<head>
<TITLE>CiberChess - Jugar Ajedrez Online</TITLE>
</head>

<body align='center' bgcolor='black' background='../imagenes/fondo.jpg'>


<p align="center">

<TABLE border='0'>
  <TR>
    <TD>
      <FONT size='1'>
      </FONT> 
      
      <img src='../../<?php echo $sala; ?>/imagenes/LogoHtml.jpg'>

      <FONT size='1'>
      <BR>
      <br>
      </FONT> 
    </TD>
  </TR>

  <TR>
    <TD>
    <FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='4'>
      <B>
      <?php
        if($idioma=="english")
          echo "Finish tournaments";
        else
          echo "Torneos terminados";
      ?>
      </B>
      </FONT>
      <FONT size='1'>
      <BR>
      <br>
      </FONT>     
    </TD>
  </TR>



<?php

include 'ConexionBD.php';
  
if(!isset($posicion))
  $posicion=0;  
  
$result7 = mysql_query("SELECT count(*) as cuenta FROM torneo WHERE estado=2", $link);
$totalClaves = mysql_result($result7, 0, "cuenta");  

if ($posicion<0)
  $posicion=0;
  
if ($posicion>$totalClaves)
  $posicion=$totalClaves-24;  

$SQL="SELECT fecha,hora,ritmo,incrementos";
$SQL=$SQL." FROM torneo";
$SQL=$SQL." WHERE estado=2";
$SQL=$SQL." ORDER BY fecha DESC,hora DESC";
$SQL=$SQL." LIMIT $posicion,24"; 
$result = mysql_query($SQL, $link);

while($row = mysql_fetch_array($result))
{
  echo "<TR>\n";
    echo "<TD bgColor='#111111'>\n";
     echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='4'>\n";
       echo "<A href=\"./TorneosClasificacion.php?torneofecha=" . $row["fecha"] . "&torneohora=" . $row["hora"] . "&torneoritmo=" . $row["ritmo"] . "&torneoincrementos=".$row["incrementos"]."\">";
       echo $row["fecha"]." ".$row["hora"].":00"." ".$row["ritmo"]." mins.";
       if ($row["incrementos"]==0)
         echo " sin inc.";
       else
         echo " con inc.";
       echo "</A>";
     echo "</FONT>\n";
   echo "</TD>\n";
  echo "</TR>\n";

}

echo "</TABLE>\n";

echo "<br>";

   echo "<TABLE border='0' cellPadding='0' cellSpacing='0' width='250'>\n";
   echo "<TR>\n";

    echo "<TD  width='41' align='left'>\n";
    if ($posicion > 1)
    {
	echo "<A HREF='TorneosTerminados.php?posicion=";
	echo $posicion-24;
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
	echo "<A HREF='TorneosTerminados.php?posicion=";
	echo $posicion-24*5;
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
	echo "<A HREF='TorneosTerminados.php?posicion=";
	echo $posicion-24*50;
	echo "&sala=" . $sala;
	echo "'>\n";
	echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='1'>\n";
	  echo "<B>&lt;&lt;-50</B>\n";
	echo "</FONT>\n";
	echo "</A>\n";
    }
    echo "</TD>\n";        

    echo "<TD width='41' align='right'>\n";
    if ($posicion < $totalClaves - 25)
    {
	echo "<A HREF='TorneosTerminados.php?posicion=";
	echo $posicion+24*50;
	echo "&sala=" . $sala;
	echo "'>\n";
	echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='1'>\n";
	  echo "<B>+50&gt;&gt;</B>\n";
	echo "</FONT>\n";
	echo "</A>\n";
    }
   echo "</TD>\n";
   
    echo "<TD width='41' align='right'>\n";
    if ($posicion < $totalClaves - 25)
    {
	echo "<A HREF='TorneosTerminados.php?posicion=";
	echo $posicion+24*5;
	echo "&sala=" . $sala;
	echo "'>\n";
	echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='1'>\n";
	  echo "<B>+5&gt;&gt;</B>\n";
	echo "</FONT>\n";
	echo "</A>\n";
    }
   echo "</TD>\n";
   
    echo "<TD width='41' align='right'>\n";
    if ($posicion < $totalClaves - 25)
    {
	echo "<A HREF='TorneosTerminados.php?posicion=";
	echo $posicion+24;
	echo "&sala=" . $sala;
	echo "'>\n";
	echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='1'>\n";
	  echo "<B>&gt;&gt;</B>\n";
	echo "</FONT>\n";
	echo "</A>\n";
    }
   echo "</TD>\n";      


  echo "</TR>\n";
  echo "</TABLE>\n";


?>
<br>
<br>
</p>
</BODY>
</HTML>
