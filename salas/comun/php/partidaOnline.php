
<?php

	include 'ConexionBD.php';

	$SQL="SELECT partida,tempos,ritmo";
	$SQL=$SQL." FROM partida";
	$SQL=$SQL." WHERE loginb='$loginb'";
 	$SQL=$SQL." AND loginn='$loginn'";
	$result = mysql_query($SQL, $link);

	
	if (($row = mysql_fetch_array($result))){
		echo "MAXIMO" . $row["partida"] . "#" . $row["tempos"] . "#" . $row["ritmo"];
	}
	
?>

