<?php

$login = strtoupper($login);
$password = strtoupper($password);

?>

<html>
<head>
<TITLE>CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - </TITLE>

<link rel="shortcut icon" href="../imagenes/ciberchess.ico" />

  <SCRIPT LANGUAGE="JavaScript">
    function AbrirVentana(juego,nombreJuego,ancho,alto) {
	var direccion;
        var login="<?php echo $login; ?>";
        var password="<?php echo $password; ?>";
        var sala="<?php echo $sala; ?>";        
        var nomeVenta;
      
        <?php
          if (strcmp(strtoupper($login),"ADMINISTRADOR")==0)
            $nomeVenta=$login.$sala;
          else
            //$nomeVenta=$sala;
            $nomeVenta=$sala.rand();
        ?>          
        nomeVenta="<?php echo($nomeVenta);?>";

	direccion=juego+"?p=CiberChessCiberChessCiberChessCiberChessCiberChessCiberChess&login="+login+"&password="+password+"&sala="+sala+"&traxectoriaLogo=../../"+sala+"/imagenes/LogoJava.gif"+"&traxectoriaLogoV=../../"+sala+"/imagenes/LogoHtmlV.jpg"+"&ancho="+ancho+"&alto="+alto+"&a="+Math.round(Math.random()*10000000);
      MiVentana=open(direccion,
                nombreJuego + nomeVenta,
                "toolbar=no,directories=no,menubar=no,status=no,resizable=yes,left=0,top=0,width="+(ancho-10)+",height="+(alto-29));
         }
  </SCRIPT>

<STYLE type=text/css>A {
	TEXT-DECORATION: none
}
</STYLE>
<STYLE>A:visited {
	COLOR: #EBDAB8
}
</STYLE>
<STYLE>A:link {
	COLOR: #EBDAB8
}
</STYLE>
<STYLE>A:hover {
	COLOR: #F0F0F0
}
</STYLE>

</head>


<?php

include '../../comun/php/ConexionBD.php';

$ip = getenv("REMOTE_ADDR");

$sql = "UPDATE claves";
$sql = $sql . " SET ip='$ip'";
$sql = $sql . " WHERE login='$login'";
$result = mysql_query($sql, $link);


$sql = "SELECT count(*) as cuenta";
$sql = $sql . " FROM claves";
$sql = $sql . " WHERE login='$login'";
$sql = $sql . " AND password='$password'";
$result = mysql_query($sql, $link);

if ( mysql_result($result, 0, "cuenta") == 0) 
{
	echo "<body>";
          echo "<p align='center'>&nbsp;</p>";
          echo "<p align='center'><span lang='es'><font size='7' color='#000080'>Login o password no válido</font></span></p>";
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else {

$sql = "SELECT count(*) as cuenta";
$sql = $sql . " FROM xogo";
$result = mysql_query($sql, $link);

$numJuegos=mysql_result($result, 0, "cuenta");

$sql = "SELECT resolucion";
$sql = $sql . " FROM claves";
$sql = $sql . " WHERE login='$login'";
$result = mysql_query($sql, $link);

$resolucion=mysql_result($result, 0, "resolucion");
if (strlen($resolucion)==0)
  $resolucion=0;
  
$anchoPantalla = $ancho;
$altoPantalla = $alto;  

if ($resolucion==800)
{
  $ancho=800;
  $alto=600;
}
if ($resolucion==1024)
{
  $ancho=1024;
  $alto=768;
}
if ($resolucion==1152)
{
  $ancho=1152;
  $alto=864;
}
if ($resolucion==1280)
{
  $ancho=1280;
  $alto=1024;
}
        
if ($ancho>$anchoPantalla || $alto>$altoPantalla )
{
  $ancho=$anchoPantalla;
  $alto=$altoPantalla;        
}        
  

$sql = "SELECT valor";
$sql = $sql . " FROM configuracion";
$sql = $sql . " WHERE concepto='ciberchess5432'";
$result = mysql_query($sql, $link);
$conectados5432=mysql_result($result, 0, "valor");

$sql = "SELECT valor";
$sql = $sql . " FROM configuracion";
$sql = $sql . " WHERE concepto='ciberchess1433'";
$result = mysql_query($sql, $link);
$conectados1433=mysql_result($result, 0, "valor");

$conectados = $conectados5432 + $conectados1433;

$sql = "SELECT count(*) as cuenta";
$sql = $sql . " FROM claves";
$sql = $sql . " WHERE login='$login'";
$sql = $sql . " AND password='$password'";
$sql = $sql . " AND finVIP>=now()";
$result = mysql_query($sql, $link);

if ( mysql_result($result, 0, "cuenta") > 0)
  $conectados = 0; 

echo "<body text='#EBDAB8' bgcolor='#000000'>\n";
echo "<font face='Verdana,Arial'>\n";

echo "<table border='0' cellpadding='0' cellspacing='0' style='border-collapse: collapse' bordercolor='#111111' width='100%' id='AutoNumber1' height='100%'>\n";
echo "  <tr height='15%'>\n";
echo "    <td width='40%' align='center'>\n";
echo "      <IMG SRC='../imagenes/LogoHtml.jpg'>";
echo "    </td>\n";
echo "    <td width='60%'>\n";
 
echo "<a href=\"http://www.lugonet.com\" target=\"_blank\"><IMG SRC=\"../imagenes/bannerup.gif\" Border=0 alt=\"Lugonet\" align=center></a>";

echo "      <br>";
echo "    </td>\n";
echo "  </tr>\n";


if($conectados < 150)
{

  echo "<SCRIPT LANGUAGE=\"JavaScript\">\n";  
  echo "  var javawsInstalled = 0;\n";
  echo "  var javaws142Installed=0;\n";
  echo "  var javaws150Installed=0;\n";
  echo "  isIE = \"false\";\n";
  echo "  if (navigator.mimeTypes && navigator.mimeTypes.length) { \n";
  echo "    x = navigator.mimeTypes['application/x-java-jnlp-file'];\n";
  echo "    if (x) {\n";
  echo "      javawsInstalled = 1;\n";
  echo "      javaws142Installed=1;\n";
  echo "      javaws150Installed=1;\n";
  echo "    }\n";
  echo "  }\n";
  echo "  else {\n";
  echo "    isIE = \"true\";\n";
  echo "  }\n";
  echo "</SCRIPT>\n";
  
  echo "<SCRIPT LANGUAGE=\"VBScript\">\n";
  echo "on error resume next\n";
  echo "If isIE = \"true\" Then\n";
  echo "  If Not(IsObject(CreateObject(\"JavaWebStart.isInstalled\"))) Then\n";
  echo "     javawsInstalled = 0\n";
  echo "  Else\n";
  echo "     javawsInstalled = 1\n";
  echo "  End If\n";
  echo "  If Not(IsObject(CreateObject(\"JavaWebStart.isInstalled.1.4.2.0\"))) Then\n";
  echo "     javaws142Installed = 0\n";
  echo "  Else\n";
  echo "     javaws142Installed = 1\n";
  echo "  End If \n";
  echo "  If Not(IsObject(CreateObject(\"JavaWebStart.isInstalled.1.5.0.0\"))) Then\n";
  echo "     javaws150Installed = 0\n";
  echo "  Else\n";
  echo "     javaws150Installed = 1\n";
  echo "  End If  \n";
  echo "End If\n";
  echo "</SCRIPT>\n";

  echo "  <tr height='35%'>\n";
  echo "    <td width='40%' align='center'>\n";
  
  echo "<SCRIPT LANGUAGE=\"JavaScript\">\n";
  echo "if (javawsInstalled || (navigator.userAgent.indexOf(\"Gecko\") !=-1)) {\n";
  echo "    document.write(\"<a HREF='../../comun/java/salon.jnlp?login=" . $login . "&password=" . $password . "&sala=" . $sala . "&ancho=" . $ancho . "&alto=" . $alto . "' >\"); \n";
  echo "    document.write(\"<img border='0' src='../../comun/imagenes/AJEDREZ.jpg' ></a>\"); \n";  
  echo "} else {\n";
  echo "    document.write(\"<a HREF='#'  onClick='AbrirVentana('../../comun/php/salon.php','ajedrez'," . $resolucion . ")'>\"); \n";
  echo "    document.write(\"<img border='0' src='../../comun/imagenes/AJEDREZ.jpg' ></a>\"); \n";  
  echo "}\n";
  echo "</SCRIPT>\n";
  
  echo "    </td>\n";
  echo "    <td width='60%'>\n"; 
  echo "      <font size='6'>";   

  echo "<SCRIPT LANGUAGE=\"JavaScript\">\n";
  echo "if (javawsInstalled || (navigator.userAgent.indexOf(\"Gecko\") !=-1)) {\n";
  echo "    document.write(\"<a HREF='../../comun/java/salon.jnlp?login=" . $login . "&password=" . $password . "&sala=" . $sala . "&ancho=" . $ancho . "&alto=" . $alto . "' >\"); \n";
  echo "    document.write(\"Ajedrez v2.0</a>\"); \n";  
  echo "    document.write (\"<font size='1'>\");"; 
  echo "    document.write(\"<br><a HREF='#'  onClick=AbrirVentana('../../comun/php/salon.php','ajedrez'," . $ancho . "," . $alto . ")>\"); \n";
  echo "    document.write(\"Apertura de emergencia</a>\"); \n";    
  echo "    document.write(\"</font>\");";  
  echo "} else {\n";
  echo "    document.write(\"<a HREF='#'  onClick=AbrirVentana('../../comun/php/salon.php','ajedrez'," . $resolucion . ")>\"); \n";
  echo "    document.write(\"Ajedrez v2.0</a>\"); \n";  
  echo "}\n";
  echo "</SCRIPT>\n";
  
  echo "      </font>";
  echo "    </td>\n";  
  echo "  </tr>\n";  

}
else
{
  echo "  <tr height='35%'>\n";
  echo "    <td width='40%' align='center'>\n";
  echo "    <img border='0' src='../../comun/imagenes/AJEDREZ.jpg' ></td>\n";  
  echo "    <td width='40%' align='left'>\n";
  echo "<a HREF='../../comun/php/donar.php'>";  
  echo "<font size='5'>\n";
  echo "<b>SALA SATURADA</b>\n";
  echo "</font>\n"; 
  echo "<font size='2'>\n";  
  echo "<br>\n"; 
  echo "<br>\n";
  echo "Intentalo más tarde o hazte usuario VIP\n";  
  echo "<br>\n";  
  echo "1 SMS ó 1 Euro por Paypal = 30 días usuario VIP\n";
  echo "<br>\n";  
  echo "Por solo 1 Euro 30 días usuario VIP, pincha aquí\n";  
  echo "</a>";
  echo "</font>\n";

  echo " </td>\n";
  echo "  </tr>\n";  
}

echo "  <tr height='25%'>\n";
echo "    <td width='40%' align='center'>\n";
echo "    <a HREF='#'  onClick=\"AbrirVentana('../../juegos/tetris/php/salon.php','tetris',800,600)\">\n";
echo "    <img border='0' src='../../../../tetris/imagenes/tetris.jpg' ></a></td>\n";
echo "    <td width='60%'>\n";
echo "<font size='5'>";
echo "    <a HREF='#'  onClick=\"AbrirVentana('../../juegos/tetris/php/salon.php','tetris',800,600)\">\n";
echo "    Tetris</a></font></td>\n";
echo "  </tr>\n";

/*
echo "  <tr height='25%'>\n";
echo "    <td width='40%' align='center'>\n";
echo "    <a HREF='#'  onClick=\"AbrirVentana('../../juegos/bobble/php/salon.php','bobble')\">\n";
echo "    <img border='0' src='../../juegos/bobble/imagenes/bobble.jpg' ></a></td>\n";
echo "    <td width='60%'>\n";
echo "<font size='5'>";
echo "    <a HREF='#'  onClick=\"AbrirVentana('../../juegos/bobble/php/salon.php','bobble')\">\n";
echo "    Puzzle Bobble</a></font></td>\n";
echo "  </tr>\n";
*/

echo "</table>\n";
echo "</font>\n";
echo "</body>\n";

}
?>
</html>
