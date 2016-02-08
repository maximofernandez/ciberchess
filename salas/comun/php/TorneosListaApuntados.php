<?php

include 'ConexionBD.php';

$fechasql="$ano-$mes-$dia";

//mirar se hai partidas activas deste torneo, nese caso non chamaremos a creación da ronda no seguinte paso e non eliminaremos desconectados
$SQL = "SELECT count(*) as cuenta";
$SQL = $SQL . " FROM torneopartida";
$SQL=$SQL." WHERE fecha='$fechasql'";
$SQL=$SQL." AND hora=$torneohora";
$result = mysql_query($SQL, $link);

if (mysql_result($result, 0, "cuenta")>0)
{
  echo "MAXIMONO#";  //ainda hay partidas
}
else
{
  $sql = "SELECT *";
  $sql = $sql." FROM torneoapuntado";
  $sql = $sql." WHERE fecha='$fechasql'";
  $sql = $sql." AND hora=$torneohora";
  $sql = $sql." AND estado=0";
  $result99 = mysql_query($sql,$link);

  echo "MAXIMOSI#".$torneohora."#".$ano."#".$mes."#".$dia."#";

  while($row = mysql_fetch_array($result99))
  {
       echo $row["login"]."#";
  }
}



?>