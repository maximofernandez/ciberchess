<?php
  session_start();
  $login=$_SESSION["loginsesion"];
  $password=$_SESSION["passwordsesion"];  
  $sala=$_SESSION["salasesion"];
  $idioma=$_SESSION["idiomasesion"];
  $torneofecha=$_SESSION["torneofechasesion"];
  $torneohora=$_SESSION["torneohorasesion"];
  $torneoritmo=$_SESSION["torneoritmosesion"];
  $torneoincrementos=$_SESSION["torneoincrementossesion"];
  
  include 'ConexionBD.php';
?>


<HTML>
<HEAD>
</HEAD>

<body bgcolor='#000000'>

<font size='3' face='Verdana, Arial, Helvetica, sans-serif' color='#EBDAB8'>
<A href='<?php echo "./TorneosPropostos.php?sala=$sala&login=$login&password=$password&idioma=$idioma"; ?> '>Volver</A>
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
                <FORM action='TorneosAlta.php' method='post' >
		<TABLE BORDER=0 WIDTH='70%' background="../imagenes/fondo.jpg" >
                <TR>
  		  <TD ALIGN='center' VALIGN=top>
                   <font size='4' face='Verdana, Arial, Helvetica, sans-serif' color='#EBDAB8'>
                    <B>Torneo <?php echo $torneofecha. " ".$torneohora.":00 ".$torneoritmo." mins. ".$torneoincrementos; ?></B>
                    <br>
                    <br>
                   </font>          
                  </TD>
                </TR>		
                <TR>
  		  <TD ALIGN='center' VALIGN=top>
                   <font size='2' face='Verdana, Arial, Helvetica, sans-serif' color='#EBDAB8'>
                    <B>
                     
		 <?php
		 
		   $sql = "SELECT COUNT(*) as cuenta";
		   $sql = $sql." FROM torneoapuntado";
		   $sql = $sql." WHERE login='$login'";
		   $sql = $sql." AND fecha='$torneofecha'";
		   $sql = $sql." AND hora='$torneohora'";
		   $result = mysql_query($sql,$link);
		   
		   $sql = "SELECT COUNT(*) as cuenta";
		   $sql = $sql." FROM claves";
		   $sql = $sql." WHERE login='$login'";
		   $sql = $sql." AND password='$password'";
		   $result2 = mysql_query($sql,$link);		   
		   
		   if (mysql_result($result, 0, "cuenta") > 0) 
		   {
		      echo "Usted ya estaba apuntado a este torneo\n" ;
		   }
		   else if (mysql_result($result2, 0, "cuenta") == 0) 
		   {
		      echo "No ha facilitado el password correcto para darse de alta en el torneo\n" ; //Evita altas con chamada directa
		   }		   
		   else 
		   {
		
		      $sql = "INSERT INTO torneoapuntado (fecha,hora,login,estado,instante,puntuacion,salida,descanso)";
		      $sql = $sql."VALUES('$torneofecha',$torneohora,'$login',0,now(),0,0,0)";
		      $result = mysql_query($sql,$link);
		      
		      if($result)
	                echo "Se ha apuntado correctamente al torneo, a la hora en punto aparecerá la primera partida en su pantalla, esté atento, suerte.\n" ;
	              else
	                echo "No se ha podido apuntar, se ha producido un error al añadirle a la base de datos.";
		     
		   }
		 ?>
                          
                    </B>
                    <br>
                    <br>
                   </font>          
       
                  </TD>
                </TR>                
		</TABLE>
		</FORM>
	      </td>
	    </TR>

	    <TR>
              <TD>
              </TD>
	    </TR>


	    <TR ALIGN='center' VALIGN='top'>
              <TD>          
		<TABLE BORDER=0 WIDTH='70%' background="../imagenes/fondo.jpg" >
                <TR>
  		  <TD ALIGN='CENTER' VALIGN='top'>
                   <font size='3' face='Verdana, Arial, Helvetica, sans-serif' color='#EBDAB8'>
                    <B>Lista de jugadores apuntados</B>
                   </font>          
                  </TD>
                </TR>
                <TR>
                  <TD ALIGN='center'>   
                    <?php include "TorneosApuntadosBody.php"; ?>
                  </TD>
		</TR>


		</TABLE>
	      </td>
	    </TR>


	  </TABLE>
</BODY>
</HTML>
