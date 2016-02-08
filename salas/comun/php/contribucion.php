<?php
 
  $sala="ciberchess";

  include 'ConexionBD.php';
  
  $login=str_replace("+","",$login);
  $login = strtoupper($login);
  $login = trim($login);
	 		 

  $sql = "SELECT count(*) as cuenta";
  $sql = $sql . " FROM claves";
  $sql = $sql . " WHERE login='$login'";
  $sql = $sql . " AND finVIP>now()";
  $result = mysql_query($sql, $link);

  if ( mysql_result($result, 0, "cuenta") == 0)
  {
    $result = mysql_query("UPDATE claves
                            SET  smss = smss + 1,
	                     pais = '$pais',
	                     finVIP = DATE_ADD(CURRENT_DATE,INTERVAL 31 DAY)
	 		 WHERE login='$login'", $link);
	 echo "no era vip";
  }
  else  
  {
    $result = mysql_query("UPDATE claves
	                 SET smss = smss + 1,
	                     pais = '$pais',
	                     finVIP = DATE_ADD(finVIP,INTERVAL 31 DAY)  
	 		 WHERE login='$login'", $link);  
         echo "era vip";	 		 
  }
	 		 
?>	 		 