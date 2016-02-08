<?php

$sala="ciberchess";

include 'ConexionBD.php';

$SQL="SELECT login,password,email,ELO";
$SQL=$SQL." FROM claves";
$SQL=$SQL." WHERE quiereCorreo=1";
$SQL=$SQL." AND login='ANDRES'";
$SQL=$SQL." ORDER BY login";
$result = mysql_query($SQL, $link);

if (($row = mysql_fetch_array($result))){
  do { 
       mail($row["email"], "Nueva Versión - CiberChess.com - Ajedrez Online", 
"


This is a multi-part message in MIME format.

------=_NextPart_BD9867235AB40D2F7F94F5BF6996C1D3
Content-Type: text/plain; charset=\"iso-8859-1\"
Content-Transfer-Encoding: 7bit

www.CiberChess.com

------=_NextPart_BD9867235AB40D2F7F94F5BF6996C1D3
Content-Type: text/html; charset=\"us-ascii\"
Content-Transfer-Encoding: 7bit

<HTML>


<HEAD>


<TITLE>CiberChess</TITLE>


<META HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=iso-8859-1\">


</HEAD>


<body bgcolor=0 text=#ffffff>
<p align=center>

      	<applet codebase=http://www.ciberchess.com/comun/java/ code=Xadrez.class width=250 height=250>
       		<param name=entrar value=no>
	       	<param name=blanco value=no>
	       	<param name=retardo value=20>
	<img border=\"0\" src=\"http://www.ciberchess.com/comun/imagenes/tablero.gif\">
	      </applet>
</p>
<p align=left>
<font size=3 face=  Helvetica, sans-serif,Arial, color='#EBDAB8'>
<b>

Hola ".$row["login"].
"<br>
<br>
Hemos instalado una nueva versión 1.4:
<br>
-Permite ver partidas mientras se juegan
<br>
 en la pestaña Ver Partidas, botón OnLine
<br>
-Deshabilitado pegar en el Chat Público
<br>
-Mejoras en la lista de conectados
<br>
-Mejoras en la gestión de invitados
<BR>
<BR>
Te recuerso tu clave ".$row["password"]."
<br>
<BR>
Visitanos
<br><br>
Saludos
<br>
<br>
<FONT	color=#8fbaa5 face=ARIAL size=3>
<A HREF='www.ciberchess.com'>
www.CiberChess.com</A>
</FONT>
<br>

	<br>
	<img border=\"0\" src=\"http://www.ciberchess.com/ciberchess/imagenes/LogoHtml.jpg\">
<br>
<br>
</font>
<font size=1 face=Verdana, Arial, Helvetica, sans-serif color='#EBDAB8'>
Si quieres darte de baja o no volver a recibir correo, en el salón de juego botón de Configuración Usuario
</font>
</p>
</body>
</HTML>

------=_NextPart_BD9867235AB40D2F7F94F5BF6996C1D3--

"

,"From:maximofernandez@terra.es\nMIME-Version: 1.0
Content-Type: multipart/alternative;
	boundary=\"----=_NextPart_BD9867235AB40D2F7F94F5BF6996C1D3\"
X-Accept-Language: en");
   echo ("<br> ".$row["nombre"]. "  login ".$row["login"]); 		
  }while ($row = mysql_fetch_array($result));
}

?>
