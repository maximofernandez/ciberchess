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
			<br>
			<br>
	  		<IMG SRC='../imagenes/tablero.gif' width="150" height="150">  <br>
	</TD>
	<td align=center valign=top>
	  <TABLE BORDER=0 WIDTH='100%' ALIGN=center >
	    <TR>
		<TD ALIGN=center VALIGN=top>
		  <BR><IMG SRC='../../<?php echo $sala; ?>/imagenes/LogoHtml.jpg' &nbsp;<br>
			<br>
		</TD>
            </TR>

	    <TR ALIGN=center VALIGN=top>
              <TD>
		<TABLE BORDER=0 WIDTH='100%' background="../imagenes/fondo.jpg" >
                <TR>
  		  <TD ALIGN=LEFT VALIGN=top>
                   <font size=2 face=Verdana, Arial, Helvetica, sans-serif color='#EBDAB8'>
                    <BR>
                    <B>CONFIRMAR BAJA&nbsp;&nbsp;</B>
                   </font>          
                    <BR>
                  </TD>
                </TR>
                <TR>
                  <TD ALIGN=center>
                    <FORM 'action=baja.php' method='post' >   
				<input name=login size=10 type=hidden value=
                                    <?php echo $login ?>
                                >
				<input name=clave size=10 type=hidden value=
                                    <?php echo mysql_result($result, 0, "password") ?>
                                >
				<input name=sala size=10 type=hidden value=
                                    <?php echo $sala ?>
                                >                                


                    <BR>
                    <BR>
                    
                      <INPUT type=submit value="Darlo de Baja">
                    
		    </FORM>

                    <BR>
				  
		    <FORM action='bajaCancelada.php' method='post' >
		      <input name=a type=hidden value='a'>
		      <INPUT name='cancelar' type='submit' value="Cancelar">
                    </FORM>   
                    <BR>
                  </TD>
		</TR>
		</TABLE>	
	      </td>
	    </TR>


	  </TABLE>
      </td>
	<TD ALIGN=center VALIGN=top>
			<br>
			<br>
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
