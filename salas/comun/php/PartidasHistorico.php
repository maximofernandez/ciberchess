
<?php

        $xogadorHistorico = strtoupper($xogadorHistorico);

	include 'ConexionBD.php';
	
	$SQL="SELECT *";
	$SQL=$SQL." FROM historia";
	$SQL=$SQL." WHERE 1=1";
        if (strlen($xogadorHistorico)>0)
        {
          $SQL=$SQL." AND (loginb LIKE '$xogadorHistorico'";
	  $SQL=$SQL." OR loginn LIKE '$xogadorHistorico')";
        }
	$SQL=$SQL." ORDER BY t DESC";
	$SQL=$SQL." LIMIT $posicion,20";
	$result = mysql_query($SQL, $link);

        echo "MAXIMO";	
	
	if (($row = mysql_fetch_array($result))){
	  $contador = 0;
	  do { 
                $movementos=intval(strlen($row["partida"])/8);
		echo $row["t"]."#".$row["loginb"]."#".$row["loginn"]."#".$row["ritmo"]."#".$movementos."#".$row["ganador"]."#";
			
		$contador++;
	  }while (($row = mysql_fetch_array($result)) && $contador<20);
	}
	
?>
