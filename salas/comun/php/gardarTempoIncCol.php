
<?php

  include 'ConexionBD.php';

  $sql="UPDATE claves";
  $sql=$sql." SET t = now(),";
  $sql=$sql." tempoDesexado = '$tempoDesexado',";
  $sql=$sql." incrementoDesexado = '$incrementoDesexado',";
  $sql=$sql." colorDesexado = '$colorDesexado'";
  $sql=$sql." WHERE login='$login'";
  
  $result = mysql_query($sql, $link);

  echo "MAXIMO";

?>
