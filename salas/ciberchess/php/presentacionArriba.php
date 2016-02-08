<html>
  <head>
  </head>
  <body text='ffffff' bgcolor='111111' align='center'>  
  <center>
   <table cellpadding='0' cellspacing='0' width='485'>
   <tr>
   <td valign='Top' align='center'>   
     <IMG SRC='../imagenes/LogoHtml2.jpg' border='0'>
   </td>   
   </tr>    
   <tr>
   <td valign='Middle' align='center'>

     <font size='3' color="#EBDAB8" face='Verdana, Arial, Helvetica, sans-serif'>
     
<?php     
     
         include '../../comun/php/ConexionBD.php';
     
	 $result6 = mysql_query("SELECT * FROM claves WHERE login = '$login'", $link);
	 $elo = mysql_result($result6, 0, "ELO");

	 echo "<p align='left'>";
	 echo "Hola ".$login.", tu ELO al inicio de sesi&oacute;n es ". $elo." puntos";
	 
	 $finVIP="";
	 $sql = "SELECT count(*) as cuenta";
	 $sql = $sql . " FROM claves";
	 $sql = $sql . " WHERE login='$login'";
	 $sql = $sql . " AND finVIP>now()";
	 $result11 = mysql_query($sql, $link);	
	 if ( mysql_result($result11, 0, "cuenta") > 0)
         {
		$sql = "SELECT finVIP";
		$sql = $sql . " FROM claves";
		$sql = $sql . " WHERE login='$login'";
		$result12 = mysql_query($sql, $link);	
		$finVIP=mysql_result($result12, 0, "finVIP") ;
		echo ", eres usuario VIP hasta el ".$finVIP;
	 }
	 
	 echo ".";
	 
	 echo "</p>";
	 	  
	 echo "</font>";
	 echo "<BR>";	 
	 	 
	 echo "<font size='2' color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif'>";
	 
         echo "<TABLE border='0' cellPadding='0' cellSpacing='0'>";
	  echo "<TR>\n";
	    echo "<TD bgColor='#111111' align='center'>\n";         
              echo "<IMG SRC='./graficoELODias.php?sala=$sala&login=$login' border='0'>";
              echo "<BR>\n";
	      echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='1'>\n";              
              echo "ELO x d&iacute;as";	 
              echo "</FONT>\n";
  	    echo "</TD>\n";
	    echo "<TD bgColor='#111111' align='center'>\n";         
              echo "<IMG SRC='./graficoELOPartidas.php?sala=$sala&login=$login' border='0'>";	 
              echo "<BR>\n";
              echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='1'>\n";
              echo "ELO x partidas"; 
              echo "</FONT>\n";             
  	    echo "</TD>\n";  	    
	  echo "</TR>\n";            
         echo "</TABLE>";              
             
	 
	$SQL="SELECT fecha,hora,ritmo,incrementos";
	$SQL=$SQL." FROM torneo t";
	$SQL=$SQL." WHERE estado=2";
	$SQL=$SQL." AND 0<(SELECT COUNT(*) from torneoapuntado WHERE fecha=t.fecha AND hora=t.hora)";
	$SQL=$SQL." ORDER BY fecha DESC,hora DESC";
	$SQL=$SQL." LIMIT 60"; 
	$result = mysql_query($SQL, $link);	 
	
	echo "<BR>\n";
	
        echo "<TABLE border='0' cellPadding='0' cellSpacing='0' width='475'>";
        $trInicio=true;
	while($row = mysql_fetch_array($result))
	{
	  if($trInicio)
	  {
	    echo "<TR>\n";
	    $trFin=false;
	  }  
	  else
	  {
	    $trFin=true;
	  }
	    echo "<TD bgColor='#111111'>\n";
	     echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='2'>\n";
	     
	       $cadeaTorneo=$row["hora"].":00";
	         
	         
         	$sql = "SELECT *";
		$sql = $sql." FROM torneoapuntado";
		$sql = $sql." WHERE fecha='".$row["fecha"]."'";
		$sql = $sql." AND hora=".$row["hora"];
		$sql = $sql." AND puntuacion=(SELECT MAX(puntuacion) FROM torneoapuntado WHERE fecha='".$row["fecha"]."' AND hora=".$row["hora"].")";
		$sql = $sql." ORDER BY login DESC";
		$result2 = mysql_query($sql,$link);
			   
		$contador=0;
		$cadeaGanadores="";
		while($row2 = mysql_fetch_array($result2))
		{
		    $cadeaGanadores = $cadeaGanadores.$row2["login"]." ";
		    $contador++;
		}  
		
		if($contador>1)
		  $cadeaGanadores = " ganadores ".$cadeaGanadores;
		else
		  $cadeaGanadores = " ganador ".$cadeaGanadores;	
		
		if($contador>0)  
		  echo $cadeaTorneo." ".$cadeaGanadores;  		   
			   
	     echo "</FONT>\n";
	   echo "</TD>\n";
	  if($trFin)
	  {
	    echo "</TR>\n";
	    $trInicio=true;
	  }
	  else 
	    $trInicio=false;
    
	
	}	 
	echo "</TABLE>";
	 
	 
?>	 


   	 
       </font>
   </td>   
   </tr>    
   </table>  
   </center>
  </body>
</html>
