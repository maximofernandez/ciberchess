<?php

include 'ConexionBD.php';

$sql = "SELECT count(*) as cuenta";
$sql = $sql . " FROM partida";
$sql = $sql . " WHERE loginb='$blancas'";
$sql = $sql . " AND loginn='$negras'";
$result = mysql_query($sql, $link);

echo "MAXIMO";

if (mysql_result($result, 0, "cuenta") > 0) 
{
  $sql = "SELECT t";
  $sql = $sql . " FROM partida";
  $sql = $sql . " WHERE loginb='$blancas'";
  $sql = $sql . " AND loginn='$negras'";
  $result2 = mysql_query($sql, $link);  
  
  echo "MAXIMO".$blancas."#".$negras."#".$tempo."#".$incrementos."#".mysql_result($result2, 0, "t")."#";
 }


?>

