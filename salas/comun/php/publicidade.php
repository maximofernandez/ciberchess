<?php

include 'ConexionBD.php';

  $sql="INSERT";
  $sql=$sql." INTO publicidade(login,t)";
  $sql=$sql." VALUES('$login',now())";
  $result = mysql_query($sql,$link);  

echo "MAXIMO";

?>
