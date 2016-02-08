<?php

$sala='ciberchess';

include 'ConexionBD.php';

for($contador=1; $contador <= 100; $contador++) {
  
      $sql = "INSERT";
      $sql = $sql . " INTO claves (login,password,email,ELO,t,conectado,quiereCorreo,ip)";
      $sql = $sql . " VALUES('INVITADO$contador','','',1500,now(),0,0,'')";
      $result = mysql_query($sql, $link);
	echo $contador."<br>";
}


?>
