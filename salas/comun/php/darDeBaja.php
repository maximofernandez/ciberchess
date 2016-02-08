<?php

include 'ConexionBD.php';

$sql = "SELECT *";
$sql = $sql." FROM claves";
$sql = $sql." WHERE login='$login'";
$result = mysql_query($sql,$link);

?>

<HTML>
<HEAD>
<TITLE>CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess</TITLE>
</HEAD>
<body bgcolor='#00000'>

  <TABLE BORDER=0 WIDTH='100%'>
    <TR>
	<TD ALIGN=center VALIGN=top>

			<br>
			<br>
			<br>
			<br>
	  		<IMG SRC='../imagenes/tablero.gif' width="150" height="150">  <br>
	</TD>

	<td align=center valign=top>
	  <TABLE BORDER=0 ALIGN=center >
	    <TR>
		<TD ALIGN=center VALIGN=top>
		  <BR><IMG SRC='../../<?php echo $sala; ?>/imagenes/LogoHtml.jpg' &nbsp;<br>
			<br>
		</TD>
            </TR>

	    <TR ALIGN=center VALIGN=top>
              <TD>
                <FORM action='confirmarBajaAjena.php' method='post' >
		<TABLE BORDER=0 background="../imagenes/fondo.jpg" >
                <TR>
  		  <TD ALIGN=CENTER VALIGN=top>
                   <font size=2 face=Verdana, Arial, Helvetica, sans-serif color='#EBDAB8'>
                    <B>DAR DE BAJA&nbsp;&nbsp;</B>
                     </font>       
                    <BR>
                  </TD>
                </TR>
                <TR>
                  <TD ALIGN=left>   
                                <br>
                                <BR>
                                <font size=2 face=Verdana, Arial, Helvetica, sans-serif color='#EBDAB8'>
				Introduce jugador <input name=login size=10 type=text value=''>
                                <BR>
				<input name=sala size=10 type=hidden value=
                                    <?php echo $sala ?>
                                >         
                                </font>                       
                    <BR>
                    <BR>
                    <P align='center'>
				  <INPUT type=submit value="Dar de Baja">
		    </P>
                    <BR>
                  </TD> 
		</TR>
		</TABLE>
		</FORM>
	      </td>
	    </TR>


	  </TABLE>
      </td>
	<TD ALIGN=center VALIGN=top>

			<br>
			<br>
			<br>
			<br>
	  		<IMG SRC='../imagenes/tablero.gif' width="150" height="150">  <br>
	</TD>      
    </TR>
  </TABLE>
</BODY>
</HTML>
