
<?php

  include 'ConexionBD.php';

  $sql = "SELECT *";
  $sql = $sql . " FROM claves";
  $sql = $sql . " WHERE login = '$login'";

    $result = mysql_query($sql, $link);


      echo "MAXIMO";

      echo mysql_result($result, 0, "colorRojoFondo");
      echo "#";
      echo mysql_result($result, 0, "colorVerdeFondo");
      echo "#";
      echo mysql_result($result, 0, "colorAzulFondo");
      echo "#";
      echo mysql_result($result, 0, "colorRojoFrente");
      echo "#";
      echo mysql_result($result, 0, "colorVerdeFrente");
      echo "#";
      echo mysql_result($result, 0, "colorAzulFrente");
      echo "#";
      echo mysql_result($result, 0, "colorAleatorio");
      echo "#";      

?>
