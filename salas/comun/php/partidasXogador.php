
<?php

	$login = strtoupper($login);

	include 'ConexionBD.php';
	
	$SQL="SELECT *";
	$SQL=$SQL." FROM partida";
	$SQL=$SQL." WHERE loginb='$login'";
	$SQL=$SQL." OR loginn='$login'";	
	$result = mysql_query($SQL, $link);

        echo "MAXIMO";
      
        echo $login."#";
        echo $sala."#";
	
	if (($row = mysql_fetch_array($result)))
	{
	  do 
	  { 
	    if (strcmp($row["loginb"],$login)==0)
              echo $row["loginn"]."#";
            else
              echo $row["loginb"]."#";
			
	  }while (($row = mysql_fetch_array($result)));
	}
	
?>
