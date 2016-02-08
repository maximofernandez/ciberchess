<?php

  session_start();
  $login=$_SESSION["loginsesion"];
  $password=$_SESSION["passwordsesion"];
  $sala=$_SESSION["salasesion"];
  $idioma=$_SESSION["idiomasesion"]; 
  $_SESSION["torneofechasesion"]=$torneofecha;
  $_SESSION["torneohorasesion"]=$torneohora;  
  $_SESSION["torneoritmosesion"]=$torneoritmo;
  $_SESSION["torneoincrementossesion"]=$torneoincrementos;

  include 'ConexionBD.php';

?>

<HTML>
<HEAD>
<TITLE>CiberChess - Jugar Ajedrez Online</TITLE>
</HEAD>
<body bgcolor='#00000'>
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
                   <font size=2 face='Verdana, Arial, Helvetica, sans-serif' color='#EBDAB8'>
                    <INPUT type="submit" value="Apuntarse al torneo" name="B1">
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
