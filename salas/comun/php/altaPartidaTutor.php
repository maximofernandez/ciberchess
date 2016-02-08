<?php

  include 'ConexionBD.php';

  $sql = "SELECT now() as t";
  $result = mysql_query($sql, $link);

  $t = mysql_result($result, 0, "t"); 
  
  $sql="INSERT";
  $sql=$sql." INTO partidaTutor(partida,chat,t)";
  $sql=$sql." VALUES('','','$t')";
  $result = mysql_query($sql,$link);

  echo "MAXIMO".$t."#";

?>
