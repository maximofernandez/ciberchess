
<?php

  include 'ConexionBD.php';

  $sql = "SELECT *";
  $sql = $sql . " FROM partidaTutor";
  $sql = $sql . " WHERE t = '$t'";
  $result = mysql_query($sql, $link);

  $actual_partida = mysql_result($result, 0, "partida");
  if(strlen($actual_partida) < $psp+strlen($m))
    $nueva_partida = substr($actual_partida,0,$psp).$m;
  else
    $nueva_partida = $actual_partida;

  $SQL="UPDATE partidaTutor"; 
  $SQL=$SQL." SET partida = '$nueva_partida'";
  $SQL=$SQL."WHERE t = '$t'";
  $result = mysql_query($SQL, $link);

  echo "MAXIMO";

?>
