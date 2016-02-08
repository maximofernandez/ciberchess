<?php
$login = strtoupper($login);
$password = strtoupper($password);
$repassword = strtoupper($repassword);

session_start();
session_register('loginsesion');
$_SESSION["loginsesion"]=$login;
session_register('passwordsesion');
$_SESSION["passwordsesion"]=$password;
session_register('idiomasesion');
$_SESSION["idiomasesion"]=$idioma;
?>

<HTML>
<HEAD>
</HEAD>

<?php


if ($password <> $repassword) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
   	if ($idioma=='english')
   	  echo "Password and repeat password are not equal.\n" ;
   	else
   	  echo "Password y repetición de password no son iguales.\n" ;
        echo "</font></span></p>";
        if ($idioma=='english')
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Come back</FONT></A>";
        else
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strlen($login) == 0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
   	if ($idioma=='english')
   	  echo "Enter username\n" ;
   	else        
	  echo "Introduzca login.\n";
        echo "</font></span></p>";
        if ($idioma=='english')
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Come back</FONT></A>";
        else
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strlen($login) > 10) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
   	if ($idioma=='english')
   	  echo "The user can not have more than 10 characters.\n" ;
   	else        
	  echo "El login no puede tener más de 10 caracteres.\n";
        echo "</font></span></p>";
        if ($idioma=='english')
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Come back</FONT></A>";
        else
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strlen($password) == 0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
   	if ($idioma=='english')
   	  echo "Enter password\n" ;
   	else            
	  echo "Introduzca password.\n";
        echo "</font></span></p>";
        if ($idioma=='english')
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Come back</FONT></A>";
        else
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (ord($login[0])<65 || ord($login[0])>90) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
   	if ($idioma=='english')
   	  echo "Username must begin with a letter.\n" ;
   	else            
	  echo "Login debe comenzar con una letra.\n";
        echo "</font></span></p>";
        if ($idioma=='english')
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Come back</FONT></A>";
        else
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace("#","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
   	if ($idioma=='english')
   	  echo "The # character is not allowed in the username.\n" ;
   	else        
	  echo "No se permite el caracter # en el login.\n";
        echo "</font></span></p>";
        if ($idioma=='english')
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Come back</FONT></A>";
        else
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace("]","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
   	if ($idioma=='english')
   	  echo "The ] character is not allowed in the username.\n" ;
   	else         
	  echo "No se permite el caracter ] en el login.\n";
        echo "</font></span></p>";
        if ($idioma=='english')
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Come back</FONT></A>";
        else
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace(" ","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
   	if ($idioma=='english')
   	  echo "The space character is not allowed in the username.\n" ;
   	else         
	  echo "No se permiten espacios en el login.\n";
        echo "</font></span></p>";
        if ($idioma=='english')
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Come back</FONT></A>";
        else
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace("\\","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
   	if ($idioma=='english')
   	  echo "The \\ character is not allowed in the username.\n" ;
   	else         
	  echo "No se permite el caracter \\ en el login.\n";
        echo "</font></span></p>";
        if ($idioma=='english')
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Come back</FONT></A>";
        else
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace("\"","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
   	if ($idioma=='english')
   	  echo "The \" character is not allowed in the username.\n" ;
   	else         
	  echo "No se permite el caracter \" en el login.\n";
        echo "</font></span></p>";
        if ($idioma=='english')
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Come back</FONT></A>";
        else
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace("INVITADO","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
   	if ($idioma=='english')
   	  echo "Username not allowed\n" ;
   	else         
	  echo "Login no permitido.\n";
        echo "</font></span></p>";
        if ($idioma=='english')
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Come back</FONT></A>";
        else
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace("GUEST","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
   	if ($idioma=='english')
   	  echo "Username not allowed\n" ;
   	else         
	  echo "Login no permitido.\n";
        echo "</font></span></p>";
        if ($idioma=='english')
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Come back</FONT></A>";
        else
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace("WEBMASTER","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
   	if ($idioma=='english')
   	  echo "Username not allowed\n" ;
   	else         
	  echo "Login no permitido.\n";
        echo "</font></span></p>";
        if ($idioma=='english')
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Come back</FONT></A>";
        else
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace("SISTEMA","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
   	if ($idioma=='english')
   	  echo "Username not allowed\n" ;
   	else         
	  echo "Login no permitido.\n";
        echo "</font></span></p>";
        if ($idioma=='english')
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Come back</FONT></A>";
        else
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp(str_replace("MAQUINA","",$login),$login)!=0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
   	if ($idioma=='english')
   	  echo "Username not allowed\n" ;
   	else         
	  echo "Login no permitido.\n";
        echo "</font></span></p>";
        if ($idioma=='english')
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Come back</FONT></A>";
        else
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
	echo "</body>";
}
else if (strcmp("TABLAS",$login)==0) 
{
	echo "<body>";
        echo "<p align=\"center\">&nbsp;</p>";
        echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
   	if ($idioma=='english')
   	  echo "Username not allowed\n" ;
   	else         
	  echo "Login no permitido.\n";
        echo "</font></span></p>";
        if ($idioma=='english')
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Come back</FONT></A>";
        else
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
   if (mysql_result($result, 0, "cuenta") > 0) 
   {
      echo "<body>";
      echo "<p align=\"center\">&nbsp;</p>";
      echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">";
      echo "Login ya existe, elija otro\n" ;
      echo "</font></span></p>";
        if ($idioma=='english')
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Come back</FONT></A>";
        else
          echo "<A HREF=\"javascript:window.history.back()\"><FONT color=#000088 face=ARIAL size=2>Volver</FONT></A>";
      echo "</body>";
   }
   else {


      $sql = "INSERT INTO claves (login,password,email,ELO,t,quiereCorreo,finVIP)";
      $sql = $sql."VALUES('$login','$password','$email',1500,now(),1,ADDDATE(CURRENT_DATE,INTERVAL -1 DAY))";
      $result = mysql_query($sql,$link);
	echo "<body >\n";
     
	echo "<SCRIPT LANGUAGE=\"Javascript\" TYPE=\"text/javascript\">\n";
	
		echo "top.location= \"../../" . $sala ."/php/indice.php?sala=" . $sala . "&ancho=" . $ancho . "&alto=" . $alto ;
                echo "\"\n";
	echo "</SCRIPT>\n";
	echo "</body>\n";
	}
}

echo "</html>";
?>
