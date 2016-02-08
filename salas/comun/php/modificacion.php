<HTML>
<HEAD>
<TITLE>CiberChess - Jugar Ajedrez Online</TITLE>

</HEAD>

<?php

$password = strtoupper($password);
$repassword = strtoupper($repassword);


include 'ConexionBD.php';

$sql = "SELECT password";
$sql = $sql . " FROM claves";
$sql = $sql . " WHERE login='$login'";
$result = mysql_query($sql, $link);

if (strcmp(mysql_result($result, 0, "password"),$clave)!=0)
{
	echo "<body>";
          echo "<p align=\"center\">&nbsp;</p>";
          if($idioma=="english")
            echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">It only allows a change by session</font></span></p>";
          else
            echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">Solo se permite una modificación por sesión</font></span></p>";
	echo "</body>";
}
else if (strcmp(str_replace("=","",$password.$email.$resolucion),$password.$email.$resolucion)!=0) 
{
	echo "<body>";
          echo "<p align=\"center\">&nbsp;</p>";
          if($idioma=="english")
            echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">Change not allowed.</font></span></p>";
          else          
            echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">Modificación no permitida.</font></span></p>";
	echo "</body>";
}
else
{
   if (strcmp($password,$repassword)!=0)
   {
	echo "<body>";
          echo "<p align=\"center\">&nbsp;</p>";
          if($idioma=="english")
            echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">Password and repassword different.</font></span></p>";
          else          
            echo "<p align=\"center\"><span lang=\"es\"><font size=\"6\" color=\"#000080\">Password y repassword distintos.</font></span></p>";
	echo "</body>";

   }
   else
   {

      if (!isset($quiereCorreo))
        $quiereCorreo = 0;

        $sql = "UPDATE claves";
        $sql = $sql . " SET password = '$password',";
        $sql = $sql . " email = '$email',";
        $sql = $sql . " quiereCorreo = $quiereCorreo,";
        $sql = $sql . " resolucion = $resolucion";
        $sql = $sql . " WHERE login = '$login'";
        $result = mysql_query($sql, $link);
	echo "<body>";
          echo "<p align=\"center\">&nbsp;</p>";
          if($idioma=="english")
            echo "<p align=\"center\"><span lang=\"es\"><font size=\"7\" color=\"#000080\">Data stored.</font></span></p>";
          else          
            echo "<p align=\"center\"><span lang=\"es\"><font size=\"7\" color=\"#000080\">Datos guardados.</font></span></p>";
	echo "</body>";
   }
}

echo "</html>";
?>
