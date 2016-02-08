
<?php



	include 'ConexionBD.php';

	$timestamp = $fecha." ".$hora;
	$SQL="SELECT partida,tempos,ritmo";
	$SQL=$SQL." FROM historia";
	$SQL=$SQL." WHERE t='$timestamp'";
	$SQL=$SQL." AND loginb='$loginb'";
 	$SQL=$SQL." AND loginn='$loginn'";
	$result = mysql_query($SQL, $link);

	
	if (($row = mysql_fetch_array($result))){
		echo "MAXIMO" . $row["partida"] . "#" . $row["tempos"] . "#" . $row["ritmo"];
	}
	
?>

