<?php

$sala='english';

include 'ConexionBD.php';

$sql = "INSERT";
$sql = $sql . " INTO claves (login,password,email,ELO,t,conectado,quiereCorreo,ip)";
$sql = $sql . " VALUES('GUEST','','',1500,now(),0,0,'')";
$result = mysql_query($sql, $link);

for($contador=1; $contador <= 100; $contador++) {
  
      $sql = "INSERT";
      $sql = $sql . " INTO claves (login,password,email,ELO,t,conectado,quiereCorreo,ip)";
      $sql = $sql . " VALUES('GUEST$contador','','',1500,now(),0,0,'')";
      $result = mysql_query($sql, $link);
	echo $contador."<br>";
}


?>
