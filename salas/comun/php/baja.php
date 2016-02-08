<HTML>
<HEAD>
<TITLE>CiberChess - Jugar Ajedrez Online</TITLE>

</HEAD>

<?php
include 'ConexionBD.php';

$result7 = mysql_query("SELECT count(*) as cuenta FROM partida WHERE loginb='$login' OR loginn='$login'", $link);
$contadorPartidas = mysql_result($result7, 0, "cuenta");

$sql = "SELECT password";
$sql = $sql . " FROM claves";
$sql = $sql . " WHERE login='$login'";
$result = mysql_query($sql, $link);


if (strcmp(mysql_result($result, 0, "password"),$clave)==0 && $contadorPartidas==0)
{
  if (strcmp(str_replace("INVITADO","",$login),$login)!=0) {
	echo "<body>";
          echo "<p align=\"center\">&nbsp;</p>";
          if($idioma=="english")
            echo "<p align=\"center\"><span lang=\"es\"><font size=\"7\" color=\"#000080\">Signoff not allowed.</font></span></p>";
          else
            echo "<p align=\"center\"><span lang=\"es\"><font size=\"7\" color=\"#000080\">Baja no permitida.</font></span></p>";
	echo "</body>";
  }
  else
  {

            $sql = "INSERT INTO bajas(t,login,password,email,ELO,conectado,quiereCorreo)";
            $sql = $sql." SELECT now() AS t,";
            $sql = $sql." login,password,email,ELO,conectado,quiereCorreo";
            $sql = $sql." FROM claves";
            $sql = $sql." WHERE login = '$login'";
      $result = mysql_query($sql, $link);
            $sql = "DELETE FROM claves";
            $sql = $sql." WHERE login = '$login'";
      $result = mysql_query($sql, $link);
	echo "<body>";
          echo "<p align=\"center\">&nbsp;</p>";
          if($idioma=="english")
            echo "<p align=\"center\"><span lang=\"es\"><font size=\"7\" color=\"#000080\">Signoff complete.</font></span></p>";
          else
            echo "<p align=\"center\"><span lang=\"es\"><font size=\"7\" color=\"#000080\">Dado de baja.</font></span></p>";
	echo "</body>";
  }
}
else
{
	echo "<body>";
          echo "<p align=\"center\">&nbsp;</p>";
          if($idioma=="english")
            echo "<p align=\"center\"><span lang=\"es\"><font size=\"7\" color=\"#000080\">Signoff cancel.</font></span></p>";
          else
            echo "<p align=\"center\"><span lang=\"es\"><font size=\"7\" color=\"#000080\">Baja cancelada.</font></span></p>";
	echo "</body>";

}

echo "</html>";
?>
