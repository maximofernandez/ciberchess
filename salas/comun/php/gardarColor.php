
<?php

  include 'ConexionBD.php';

  $sql="UPDATE claves";
  $sql=$sql." SET t = now(),";
  $sql=$sql." colorRojoFondo = $colRojoFondo ,";
  $sql=$sql." colorVerdeFondo = $colVerdeFondo ,";
  $sql=$sql." colorAzulFondo = $colAzulFondo ,";
  $sql=$sql." colorRojoFrente = $colRojoFrente ,";
  $sql=$sql." colorVerdeFrente = $colVerdeFrente ,";
  $sql=$sql." colorAzulFrente = $colAzulFrente, ";
  $sql=$sql." colorAleatorio = $colAleatorio ";
  $sql=$sql." WHERE login='$login'";
  
  $result = mysql_query($sql, $link);

  echo "MAXIMO";

?>
