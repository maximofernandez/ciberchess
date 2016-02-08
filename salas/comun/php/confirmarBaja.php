<?php

include 'ConexionBD.php';

$sql = "SELECT *";
$sql = $sql." FROM claves";
$sql = $sql." WHERE login='$login'";
$result = mysql_query($sql,$link);


?>

<HTML>
<HEAD>
<TITLE>CiberChess - Jugar Ajedrez Online</TITLE>
</HEAD>
<body bgcolor='#00000'>


	  <TABLE BORDER=0 WIDTH='100%' ALIGN=center >
	    <TR>
		<TD ALIGN=center VALIGN=top>
		  <BR><IMG SRC='../../<?php echo $sala; ?>/imagenes/LogoHtml.jpg' &nbsp;<br>
			<br>
		</TD>
            </TR>

	    <TR ALIGN=center VALIGN=top>
              <TD>
	                      
		<TABLE BORDER=0 WIDTH='50%' background="../imagenes/fondo.jpg" >
                <TR>
  		  <TD ALIGN=LEFT VALIGN=top>
                   <font size=3 face=Verdana, Arial, Helvetica, sans-serif color='#EBDAB8'>
                    <BR>
                    <?php
                      if($idioma=="english")
                        echo "<B>CONFIRM SIGNOFF&nbsp;&nbsp;</B>";
                      else
                        echo "<B>CONFIRMAR BAJA&nbsp;&nbsp;</B>";
                    ?>
                   </font>          
                    <BR>
                  </TD>
                  <TD>
                  </TD>                                    
                </TR>
                <TR >
                  <TD ALIGN='center' valign='center'>   
                              <FORM action='baja.php' method='post' >
                                <?php
		                  echo "<input name='idioma' type=hidden value='$idioma'>";
		                ?>                              
				<input name=login size=10 type=hidden value=
                                    <?php echo $login ?>
                                >
				<input name=clave size=10 type=hidden value=
                                    <?php echo $clave ?>
                                >
				<input name=sala size=10 type=hidden value=
                                    <?php echo $sala ?>
                                >                                
                                <?php
                                  if($idioma=="english")
                                    echo "<INPUT name='baja' type=submit value=\"Signoff\">";
                                  else
                                    echo "<INPUT name='baja' type=submit value=\"Darse de Baja\">";
                                ?>
			      </FORM>

		              <br>

                  </TD>
                  <TD ALIGN='center' valign='center'>
                            <FORM action='bajaCancelada.php' method='post' >
                              <?php
		                echo "<input name='idioma' type=hidden value='$idioma'>";
		              ?>
				
                                <?php
                                  if($idioma=="english")
                                    echo "<INPUT name='cancelar' type=submit value=\"Cancel\">";
                                  else
                                    echo "<INPUT name='cancelar' type=submit value=\"Cancelar\">";
                                ?>				  
                            </FORM>   

				  <br>				  
              
                  </TD>                                    
		  
		</TR>


		</TABLE>
	      </td>
	    </TR>


	  </TABLE>

</BODY>
</HTML>
