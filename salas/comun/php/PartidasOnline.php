
<?php

	$login = strtoupper($login);

	include 'ConexionBD.php';
	
	$SQL="SELECT *";
	$SQL=$SQL." FROM partida";
	$SQL=$SQL." ORDER BY t DESC";
	$result = mysql_query($SQL, $link);

        echo "MAXIMO";
	
	if (($row = mysql_fetch_array($result)))
	{
	  do 
	  { 
                $movementos=intval(strlen($row["partida"])/8);
		echo $row["t"]."#".$row["loginb"]."#".$row["loginn"]."#".$row["ritmo"]."#".$movementos."#".$row["ganador"]."#";
	  }while (($row = mysql_fetch_array($result)));
	}
	
?>
