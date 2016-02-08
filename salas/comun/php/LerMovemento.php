
<?php

  include 'ConexionBD.php';

  $sql = "SELECT partida,tb,tn";
  $sql = $sql . " FROM partida";
  $sql = $sql . " WHERE loginb = '$b'";
  $sql = $sql . " AND loginn = '$n'";
  do
  {
    $result = mysql_query($sql, $link);

    if ($psp < strlen(mysql_result($result, 0, "partida")))
    {
      echo "MAXIMO";
      if (strcmp($login,$b)==0)
        echo mysql_result($result, 0, "tn");
      else
        echo mysql_result($result, 0, "tb");
      echo "#";
      echo substr(mysql_result($result, 0, "partida"),$psp);
      echo "#";
      echo $psp;
      echo "#";
      break;
    }
    else
    { 
      //sleep(3);
      echo "MAXIMO";
      break;
    }
  } while(true);

 


?>
