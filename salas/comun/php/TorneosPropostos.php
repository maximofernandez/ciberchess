<?php

session_start();
$_SESSION["loginsesion"]=$login;
$_SESSION["passwordsesion"]=$password;
$_SESSION["salasesion"]=$sala;
$_SESSION["idiomasesion"]=$idioma;
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
          echo "Next tournaments";
        else
          echo "Próximos torneos";
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
  
  
$result = mysql_query("SELECT fecha,hora,ritmo,incrementos FROM torneo WHERE estado=0 AND (fecha>now() OR fecha=CURRENT_DATE AND hora>". date("H") .") ORDER BY fecha,hora", $link);


while($row = mysql_fetch_array($result))
{
  echo "<TR>\n";
    echo "<TD>\n";
     echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='4'>\n";
       if ($row["incrementos"]==0)
         echo "<A href=\"./TorneosFormularioAlta.php?torneofecha=" . $row["fecha"] . "&torneohora=" . $row["hora"] . "&torneoritmo=" . $row["ritmo"] . "&torneoincrementos=sin%20inc.\">";
       else
         echo "<A href=\"./TorneosFormularioAlta.php?torneofecha=" . $row["fecha"] . "&torneohora=" . $row["hora"] . "&torneoritmo=" . $row["ritmo"] . "&torneoincrementos=con%20inc.\">";  
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


?>
</TABLE>
</p>
</BODY>
</HTML>
