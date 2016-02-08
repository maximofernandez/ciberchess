<?php

include 'ConexionBD.php';

      $sql = "DELETE";
      $sql = $sql . " FROM historia";
      $sql = $sql . " WHERE now()-t>864000";
      $result = mysql_query($sql, $link);

?>
