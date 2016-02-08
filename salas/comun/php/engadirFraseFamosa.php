<?php
include 'ConexionBD.php';

$result7 = mysql_query("SELECT count(*) as cuenta FROM fraseMarquesina", $link);
$contadorFrases = mysql_result($result7, 0, "cuenta");
$contadorFrases++;
$frase=str_replace("_"," ",$frase);
$result7 = mysql_query("insert into fraseMarquesina(numero,frase) VALUES($contadorFrases,'$frase')", $link);

