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
			<br>
			<br>
			<br>			
	  		<IMG SRC='../imagenes/tablero.gif' width="150" height="150">  <br>
	</TD>
	<td align=center valign=top>
	  <FORM action=altaAjena.php method=post >
	  <TABLE BORDER=0 WIDTH='100%' ALIGN=center >
	    <TR>
		<TD ALIGN=center VALIGN=top >
		  <BR><img src='../../<?php echo $sala; ?>/imagenes/LogoHtml.jpg'> &nbsp;<br>
			<br>
		</TD>
          </TR>
	    <TR ALIGN=center VALIGN=top>
            <TD>
		  <TABLE BORDER=0 WIDTH='100%' background="../imagenes/fondo.jpg" >
                <TR>
  		      <TD ALIGN=LEFT VALIGN=top>
                   <font size=2 face=Verdana, Arial, Helvetica, sans-serif color='#EBDAB8'>
                    <B>REGISTRARSE&nbsp;&nbsp;</B>
                   </font>          
			  <A href="ayuda_registro.php?sala=<?php echo $sala;?>">
                    <FONT color=#8fbaa5 face=ARIAL size=2>
                    Informaci&oacute;n registro
                    </font>
                    </A>
			  <B>&nbsp;&nbsp;</B>
                    <BR>
                  </TD>
                </TR>
                <TR>
                  <TD>
                    <INPUT name='sala' type='hidden' value='<?php echo $sala;?>'>
	            <TABLE BORDER=0 WIDTH='100%'>
                      <TR>                        
                        <TD>  
                          <FONT color=#8fbaa5 face=ARIAL size=2>
			          Login
                           </FONT>
                        </TD>
                        <TD>
                          <input name=login size=10>
                        </TD>
                      </TR>
                      <TR>
                        <TD>
                          <FONT color=#8fbaa5 face=ARIAL size=2>
				    Clave
                          </FONT>
                        </TD>
                        <TD>
                          <input name=password size=10 type=password>
                        </TD>
                      </TR>
                      <TR>
                        <TD>				    
                          <FONT color=#8fbaa5 face=ARIAL size=2>
  			          Repite Clave 
                          </FONT>
                        </TD>
                        <TD>
				  <INPUT name=repassword size=10 type=password>
                        </TD>
                      </TR>
                      <TR>
                        <TD>
                          <FONT color=#8fbaa5 face=ARIAL size=2>
				    e-mail
                          </FONT>
                        </TD>
                        <TD>
                          <INPUT name=email size=32>
                        </TD>
                      </TR>
                      <TR>
				</TD>
                        <TD>
			      <TD>
                          <BR>
				  <INPUT type=submit value=Enviar>
                        </TD>
                      </TR>
                    </TABLE>
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