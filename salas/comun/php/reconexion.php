<?php

include 'ConexionBD.php';

$sql = "UPDATE claves";
$sql = $sql . " SET";
$sql = $sql . " reconexions=reconexions + 1";
$sql = $sql . " WHERE login='$login'";
$result = mysql_query($sql, $link);

echo "MAXIMO";

?>