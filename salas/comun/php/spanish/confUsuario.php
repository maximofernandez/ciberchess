<?php

include '../ConexionBD.php';

$sql = "SELECT *";
$sql = $sql." FROM claves";
$sql = $sql." WHERE login='$login'";
$sql = $sql." AND password='$clave'";
$result = mysql_query($sql,$link);

?>

<HTML>
<HEAD>
<TITLE>CiberChess - Jugar Ajedrez Online</TITLE>
</HEAD>
<body bgcolor='#00000'>

	  <TABLE BORDER='0' WIDTH='100%' ALIGN='center' >
	    <TR>
		<TD ALIGN='center' VALIGN='top'>
		  <IMG SRC='../../../<?php echo $sala; ?>/imagenes/LogoHtml.jpg'
		  <br>
		</TD>
            </TR>
	    <TR ALIGN=center VALIGN=top>
              <TD>
                <FORM action='../modificacion.php' method='post' >
		<TABLE BORDER=0 WIDTH='70%' background="../../imagenes/fondo.jpg" >
                <TR>
  		      <TD ALIGN=LEFT VALIGN=top>
                   <font size=2 face='Verdana, Arial, Helvetica, sans-serif' color='#EBDAB8'>
                    <B>CONFIGURACIÓN USUARIO&nbsp;&nbsp;</B>
                   </font>          
                  </TD>
                </TR>
                <TR>
                  <TD>   
		    <TABLE BORDER=0 WIDTH='100%'>
                      <TR>                        
                        <TD>  
                          <FONT color='#EBDAB8' face='ARIAL' size='2'>
			          Login
                           </FONT>
                        </TD>
                        <TD>
                           <FONT color='#EBDAB8' face='ARIAL' size='2'>
				<input name='sala' size=10 type='hidden' value=
                                    <?php echo $sala ?>
                                >                           
				<input name='login' size=10 type='hidden' value=
                                    <?php echo $login ?>
                                >
				<?php echo $login ?>
                           </FONT>
				<input name=clave size=10 type=hidden value=
                                    <?php echo $clave ?>
                                >
                                <input name='idioma' type=hidden value='spanish'>
                        </TD>
                      </TR>
                      <TR>
                        <TD>
                          <FONT color='#EBDAB8' face='ARIAL' size='2'>
				    Password
                          </FONT>
                        </TD>
                        <TD>
                          <input name=password size=10 type=password value=
				<?php echo mysql_result($result, 0, "password") ?>
                          >
                        </TD>
                      </TR>
                      <TR>
                        <TD>				    
                          <FONT color=#EBDAB8 face=ARIAL size=2>
  			          Repite Password 
                          </FONT>
                        </TD>
                        <TD>
                          <input name=repassword size=10 type=password value=
				<?php echo mysql_result($result, 0, "password") ?>
                          >
                        </TD>
                      </TR>
                      <TR>
                        <TD>
                          <FONT color=#EBDAB8 face=ARIAL size=2>
				    e-mail
                          </FONT>
                        </TD>
                        <TD>
                          <INPUT name=email size=32 value=
				<?php echo mysql_result($result, 0, "email") ?>
                          > 
                        </TD>
                      </TR>
                      <TR>
                        <TD>
                          <FONT color=#EBDAB8 face=ARIAL size=2>
				    Enviarme e-mail
                          </FONT>
                        </TD>
                        <TD>
                          <input type="checkbox" name="quiereCorreo" value=1 
                               <?php if(mysql_result($result, 0, "quiereCorreo")==1)
                                  echo "CHECKED";
                               ?>
                          > 
                        </TD>
                      </TR>
                      <TR>
                        <TD>
                          <FONT color='#EBDAB8' face='ARIAL' size='2'>
				    Tamaño tablero
                          </FONT>
                        </TD>
                        <TD>
                          <select name="resolucion">
                             <option
                               <?php if(mysql_result($result, 0, "resolucion")==0)
                                  echo " selected";
                               ?>
                             value=0>Pantalla completa
                             <option
                               <?php if(mysql_result($result, 0, "resolucion")==800)
                                  echo " selected";
                               ?>
                             value=800>800x600
                             <option
                               <?php if(mysql_result($result, 0, "resolucion")==1024)
                                  echo " selected";
                               ?>
                             value=1024>1024x768
                             <option
                               <?php if(mysql_result($result, 0, "resolucion")==1152)
                                  echo " selected";
                               ?>
                             value=1152>1152x864
                             <option
                               <?php if(mysql_result($result, 0, "resolucion")==1280)
                                  echo " selected";
                               ?>
                             value=1280>1280x1024
                          </select> 
                        </TD>
                      </TR>
                      <TR>
			<TD>
                        </TD>
			<TD ALIGN='left'>
				  <INPUT type="submit" value="Guardar" name="B1">
                        </TD>
                      </TR>
                    </TABLE>
                   </TD>
		    </FORM>
		  </TR>
		</TABLE>
	      </td>
	    </TR>

	    <TR>
              <TD>
              </TD>
	    </TR>


	    <TR ALIGN=center VALIGN=top>
              <TD>
	      <FORM action='../confirmarBaja.php' method='post' >              
		<TABLE BORDER=0 WIDTH='70%' background="../../imagenes/fondo.jpg" >
                <TR>
  		  <TD ALIGN=LEFT VALIGN=top>
                   <font size=2 face=Verdana, Arial, Helvetica, sans-serif color='#EBDAB8'>
                    <B>DARSE DE BAJA&nbsp;&nbsp;</B>
                   </font>          
                  </TD>
                </TR>
                <TR>
                  <TD ALIGN=center>   
				<input name=login size=10 type=hidden value=
                                    <?php echo $login ?>
                                >
				<input name=clave size=10 type=hidden value=
                                    <?php echo $clave ?>
                                >
				<input name=sala size=10 type=hidden value=
                                    <?php echo $sala ?>
                                >                                
                                <input name='idioma' type=hidden value='spanish'>
                    <BR>
				  <INPUT type='submit' value="Darse de Baja" name=B2>

                  </TD>
		  </FORM>
		</TR>


		</TABLE>
	      </td>
	    </TR>


	  </TABLE>
</BODY>
</HTML>
