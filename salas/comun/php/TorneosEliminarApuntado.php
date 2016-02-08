<?php

include 'ConexionBD.php';

$fechasql="$ano-$mes-$dia";

//obter cal e a última ronda deste torneo se e que hai algunha para saber o número da seguinte
$sql = "SELECT count(*) as cuenta";
$sql = $sql . " FROM torneohistoria";
$sql=$sql." WHERE fecha='$fechasql'";
$sql=$sql." AND hora=$torneohora";
$result = mysql_query($sql, $link);
$numPartidasXogadas = mysql_result($result, 0, "cuenta");

$ronda=1;

if($numPartidasXogadas>0)
{
  $sql = "SELECT max(ronda) as rondamasalta";
  $sql = $sql . " FROM torneohistoria";
  $sql=$sql." WHERE fecha='$fechasql'";
  $sql=$sql." AND hora=$torneohora";
  $result = mysql_query($sql, $link);
  $ronda = mysql_result($result, 0, "rondamasalta")+1;  
}

$sql = "UPDATE torneoapuntado";
$sql = $sql." SET estado=$ronda";
$sql = $sql." WHERE fecha='$fechasql'";
$sql = $sql." AND hora=$torneohora";
$sql = $sql." AND login='$login'";
$result99 = mysql_query($sql,$link);

echo "MAXIMO";

?>