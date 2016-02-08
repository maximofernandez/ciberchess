<?php
  session_start();
  $login=$_SESSION["loginsesion"];
  $password=$_SESSION["passwordsesion"];  
  $sala=$_SESSION["salasesion"];
  $idioma=$_SESSION["idiomasesion"];
  $orixe=$_SESSION["orixesesion"];
  
  include 'ConexionBD.php';
?>


<HTML>
<HEAD>
</HEAD>

<body bgcolor='#000000'>
  <font size='3' face='Verdana, Arial, Helvetica, sans-serif' color='#EBDAB8'>
  <A href='
    <?php 
      echo "./$orixe?sala=$sala&login=$login&password=$password&idioma=$idioma"; $a++; 
    ?> 
  '>Volver</A>
  </font>
  <font size='3' face='Verdana, Arial, Helvetica, sans-serif' color='#EBDAB8'>
  <A href='<?php echo "./TorneosClasificacion.php?sala=$sala&login=$login&password=$password&idioma=$idioma&torneofecha=$torneofecha&torneohora=$torneohora&torneoritmo=$torneoritmo&torneoincrementos=$torneoincrementos&a=$a"; ?>'>Actualizar</A>
  </font>  
  
	  <TABLE BORDER='0' WIDTH='100%' ALIGN='center' >
	    <TR>
		<TD ALIGN='center' VALIGN='top'>
		  <IMG SRC='../../<?php echo $sala; ?>/imagenes/LogoHtml.jpg'
		  <br>
		</TD>
            </TR>
	    <TR ALIGN=center VALIGN=top>
              <TD>
		<TABLE BORDER=0 WIDTH='70%' background="../imagenes/fondo.jpg" >
                <TR>
  		  <TD ALIGN='center' VALIGN=top>
                   <font size='4' face='Verdana, Arial, Helvetica, sans-serif' color='#EBDAB8'>
                    <B>Torneo 
                    <?php
                      if($torneoincrementos==0) 
                        echo $torneofecha. " ".$torneohora.":00 ".$torneoritmo." mins. sin inc."; 
                      else
                        echo $torneofecha. " ".$torneohora.":00 ".$torneoritmo." mins. con inc."; 
                    ?>
                    </B>
                    <br>
                    <br>
                   </font>          
                  </TD>
                </TR>		
                <TR>
  		  <TD ALIGN='center' VALIGN=top>
                                        
                     
			 <?php
			 
	
			   $sql = "SELECT *";
			   $sql = $sql." FROM torneoapuntado";
			   $sql = $sql." WHERE fecha='$torneofecha'";
			   $sql = $sql." AND hora='$torneohora'";
			   $sql = $sql." ORDER BY puntuacion DESC";
			   $result = mysql_query($sql,$link);
			   
                           echo "<font size='3' face='Verdana, Arial, Helvetica, sans-serif' color='#EBDAB8'>";
                           echo "<B>Clasificaci&oacute;n</B>";
                           echo "<br>";
			   echo "</font>";
			   echo "<br>";
	
	
	                   echo "<TABLE border='0' cellPadding='3' cellSpacing='0'>";
	
	
			   $contador = 0;	
			   while($row = mysql_fetch_array($result))
			   {
			     if($contador % 2 == 0)
			     {
			      echo "<TR>\n";
			        echo "<TD bgColor='#222222'>\n";
			         echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='4'>\n";
			           echo "<B>".$row["login"]."</B>";
			         echo "</FONT>\n";
			       echo "</TD>\n";
			       echo "<TD bgColor='#222222' align='left'>\n";
			         echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='4'>\n";
			           echo "<B>".$row["puntuacion"]."</B>";
			         echo "</FONT>\n";
			       echo "</TD>\n";
			       echo "<TD bgColor='#222222 align='left'>\n";
			         echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='4'>\n";
			           if($row["estado"]>0)
			             echo " abandona en la ronda ".$row["estado"];
			         echo "</FONT>\n";
			       echo "</TD>\n";			       
			      echo "</TR>\n";
			     }
			     else
			     {
			      echo "<TR >\n";
			        echo "<TD bgColor='#111111'>\n";
			         echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='4'>\n";
			           echo "<B>".$row["login"]."</B>";
			         echo "</FONT>\n";
			       echo "</TD>\n";
			       echo "<TD bgColor='#111111' align='left'>\n";
			         echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='4'>\n";
			           echo "<B>".$row["puntuacion"]."</B>";
			         echo "</FONT>\n";
			       echo "</TD>\n";
			       echo "<TD bgColor='#111111' align='left' >\n";
			         echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='4'>\n";
			           if($row["estado"]>0)
			             echo " abandona en la ronda ".$row["estado"];
			         echo "</FONT>\n";
			       echo "</TD>\n";			       
			      echo "</TR>\n";
			     }
			
			     $contador ++;
			   }
	
	
	
	                   echo "</TABLE>\n";
	
	
			   
			   $sql = "SELECT tp.ronda,p.loginb,p.loginn,p.t";
			   $sql = $sql." FROM torneopartida tp join partida p on tp.loginb=p.loginb AND tp.loginn=p.loginn";
			   $sql = $sql." WHERE tp.fecha='$torneofecha'";
			   $sql = $sql." AND tp.hora='$torneohora'";
			   $sql = $sql." ORDER BY tp.ronda,p.loginb,p.loginn";
			   $result = mysql_query($sql,$link);
			
	  		   echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='3'>\n";
			
			   $cabeceira=1;
			   while($row = mysql_fetch_array($result))
			   {
			       if($cabeceira==1)
			       {
			         $cabeceira=0;
			         echo "<br>";
			         echo "<br>";
			         echo "<b>Partidas en juego</b>";
			         echo "<br>";
			         echo "<br>";       
			         echo "<TABLE border='0' cellPadding='3' cellSpacing='0'>";
			       }
			       echo "<TR >\n";
			         echo "<TD bgColor='#222222'>\n";	
			         echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='4'>\n";			         		       
			           echo "Ronda ".$row["ronda"];
			         echo "</TD>\n";
			         echo "<TD bgColor='#222222 align='left'>\n";
			         echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='4'>\n";
			           echo "<A href='CHAMADALOCALVERPARTIDAS".$row["t"]."#".$row["loginb"]."#".$row["loginn"]."##b#'>".$row["loginb"]."</A>";
			         echo "</TD>\n";
			         echo "<TD bgColor='#222222 align='left'>\n";
			         echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='4'>\n";			           
			           echo "<A href='CHAMADALOCALVERPARTIDAS".$row["t"]."#".$row["loginb"]."#".$row["loginn"]."##n#'>".$row["loginn"]."</A>";
			         echo "</FONT>\n";
			         echo "</TD>\n";			       
			       echo "</TR>\n";
			   }
			   if($cabeceira==0)
			     echo "</TABLE>\n";	   
			   echo "</FONT>\n";	
			   
			   
			   $sql = "SELECT th.ronda,h.loginb,h.loginn,h.t,h.ganador,h.t";
			   $sql = $sql." FROM torneohistoria th join historia h on th.loginb=h.loginb AND th.loginn=h.loginn AND th.t=h.t";
			   $sql = $sql." WHERE th.fecha='$torneofecha'";
			   $sql = $sql." AND th.hora='$torneohora'";
			   $sql = $sql." ORDER BY th.ronda,h.loginb,h.loginn";
			   $result = mysql_query($sql,$link);
			
	  		   echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='3'>\n";
			
			   $cabeceira=1;
			   while($row = mysql_fetch_array($result))
			   {
			       if($cabeceira==1)
			       {
			         $cabeceira=0;
			         echo "<br>";
			         echo "<br>";
			         echo "<b>Partidas terminadas</b>";
			         echo "<br>";
			         echo "<br>";       
			         echo "<TABLE border='0' cellPadding='3' cellSpacing='0'>";			         
			       }
			       echo "<TR >\n";
			         echo "<TD bgColor='#222222'>\n";	
			         echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='4'>\n";			         		       
			           echo "Ronda ".$row["ronda"];
			         echo "</TD>\n";
			         echo "<TD bgColor='#222222 align='left'>\n";
			         echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='4'>\n";
			           echo "<A href='CHAMADALOCALVERPARTIDAS".$row["t"]."#".$row["loginb"]."#".$row["loginn"]."#".$row["ganador"]."#b#'>".$row["loginb"]."</A>";
			         echo "</TD>\n";
			         echo "<TD bgColor='#222222 align='left'>\n";
			         echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='4'>\n";			           
			           echo "<A href='CHAMADALOCALVERPARTIDAS".$row["t"]."#".$row["loginb"]."#".$row["loginn"]."#".$row["ganador"]."#n#'>".$row["loginn"]."</A>";
			         echo "</FONT>\n";
			         echo "</TD>\n";			       

			         echo "<TD bgColor='#222222 align='left'>\n";
			         echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='4'>\n";			           
			         if($row["ganador"]=="TABLAS")
			           echo " ".$row["ganador"]."</A>";
			         else
			           echo " ganador ".$row["ganador"]."</A>";
			         echo "</FONT>\n";
			         echo "</TD>\n";			         
			       echo "</TR>\n";
			       echo "<BR>";
			   }
			   if($cabeceira==0)
			     echo "</TABLE>\n";			   
			   echo "</FONT>\n";			   	   
	
			 ?>
                          
                    <br>
                    <br>          
       
                  </TD>
                </TR>                
		</TABLE>
	      </td>
	    </TR>

	    <TR>
              <TD>
		  <font size='3' face='Verdana, Arial, Helvetica, sans-serif' color='#EBDAB8'>
		  <A href='<?php echo "./$orixe?sala=$sala&login=$login&password=$password&idioma=$idioma"; ?> '>Volver</A>
		  </font>
		  <font size='3' face='Verdana, Arial, Helvetica, sans-serif' color='#EBDAB8'>
		  <A href='<?php echo "./TorneosClasificacion.php?sala=$sala&login=$login&password=$password&idioma=$idioma&torneofecha=$torneofecha&torneohora=$torneohora&torneoritmo=$torneoritmo&torneoincrementos=$torneoincrementos&a=$a"; ?>'>Actualizar</A>
		  </font>              
              </TD>
	    </TR>

	  </TABLE>
</BODY>
</HTML>
