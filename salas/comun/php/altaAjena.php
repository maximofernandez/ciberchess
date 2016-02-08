<HTML>
<HEAD>

<TITLE>CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess</TITLE>

  <SCRIPT LANGUAGE="JavaScript">
    function AbrirVentana(login,password,sala) {
	var direccion;
	direccion="../salas/"+sala+"/php/indice.php?p=CiberChessCiberChessCiberChessCiberChessCiberChessCiberChess&login="+login+"&password="+password+"&sala="+sala+"&n=B&l=b&a="+Math.round(Math.random()*10000000);
	nombreVentana="CiberChess"+Math.round(Math.random()*10000000);
      MiVentana=open(direccion,
                nombreVentana,
                "toolbar=no,directories=no,menubar=no,status=no,resizable=yes,left=0,top=0,width=790,height=600");
         }
  </SCRIPT>
</HEAD>

<?php
$login = strtoupper($login);
$password = strtoupper($password);
$repassword = strtoupper($repassword);

if ($password <> $repassword) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
   	echo "Password y repetición de password no son iguales.\n" ;
        echo "</font></span></p>";
        echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strlen($login) == 0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
	echo "Introduzca login.\n";
        echo "</font></span></p>";
        echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strlen($login) > 10) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
	echo "El login no puede tener más de 10 caracteres.\n";
        echo "</font></span></p>";
        echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strlen($password) == 0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
	echo "Introduzca password.\n";
        echo "</font></span></p>";
        echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (ord($login[0])<65 || ord($login[0])>90) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
	echo "Login debe comenzar con una letra.\n";
        echo "</font></span></p>";
        echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace("#","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
	echo "No se permite el caracter # en el login.\n";
        echo "</font></span></p>";
        echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace("¬","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
	echo "No se permite el caracter ¬ en el login.\n";
        echo "</font></span></p>";
        echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace(" ","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
	echo "No se permiten espacios en el login.\n";
        echo "</font></span></p>";
        echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace("\\","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
	echo "No se permite el caracter \\ en el login.\n";
        echo "</font></span></p>";
        echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace("\"","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
	echo "No se permite el caracter \" en el login.\n";
        echo "</font></span></p>";
        echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace("INVITADO","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
	echo "Login no permitido.\n";
        echo "</font></span></p>";
        echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace("WEBMASTER","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
	echo "Login no permitido.\n";
        echo "</font></span></p>";
        echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace("SISTEMA","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
	echo "Login no permitido.\n";
        echo "</font></span></p>";
        echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace("MAQUINA","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
	echo "Login no permitido.\n";
        echo "</font></span></p>";
        echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp("TABLAS",$login)==0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
	echo "Login no permitido.\n";
        echo "</font></span></p>";
        echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else 
{
   include 'ConexionBD.php';
 
   $sql = "SELECT COUNT(*) as cuenta";
   $sql = $sql." FROM claves";
   $sql = $sql." WHERE login='$login'";
   $result = mysql_query($sql,$link);
   if (mysql_result($result, 0, "cuenta") > 0) {
      echo "<body>";
      echo "<p align=\"center\">&nbsp;</p>";
      echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
      echo "Login ya existe, elija otro\n" ;
      echo "</font></span></p>";
      echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
      echo "</body>";
   }
   else {


      $sql = "INSERT INTO claves (login,password,email,ELO,t,quiereCorreo)";
      $sql = $sql."VALUES('$login','$password','$email',1500,now(),1)";
      $result = mysql_query($sql,$link);
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
	echo "Jugador dado de alta.\n";
        echo "</font></span></p>";
        echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
	}
}

echo "</html>";
?>

