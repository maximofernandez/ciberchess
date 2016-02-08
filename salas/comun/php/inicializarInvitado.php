<?php

  include 'ConexionBD.php';

  //seguridad
  if (strcmp(str_replace("INVITADO","",$login),$login)!=0 ||
      strcmp(str_replace("GUEST","",$login),$login)!=0)
  {
    $sql = "UPDATE claves";
    $sql = $sql . " SET ELO=1500";
    $sql = $sql . " WHERE login='$login'";
    $result = mysql_query($sql, $link);
  }
  echo "MAXIMO";

?>
