<?php



include 'ConexionBD.php'; 

$fechasql="$ano-$mes-$dia";

//mirar se hai partidas activas deste torneo
$SQL = "SELECT count(*) as cuenta";
$SQL = $SQL . " FROM torneopartida";
$SQL=$SQL." WHERE fecha='$fechasql'";
$SQL=$SQL." AND hora=$torneohora";
$result = mysql_query($SQL, $link);

if (mysql_result($result, 0, "cuenta")>0)
{
  echo "MAXIMO"; //non terminaron todas, indicase non devolvendo nada
  return;
}
else
{
  //marcar o torneo como "finalizado"
  $sql = "UPDATE torneo";
  $sql = $sql . " SET estado=2";
  $sql=$sql." WHERE fecha='$fechasql'";
  $sql=$sql." AND hora=$torneohora";
  $result = mysql_query($sql, $link);
  
  $sql = "SELECT login";
  $sql = $sql." FROM torneoapuntado ";
  $sql = $sql." WHERE puntuacion=(SELECT MAX(puntuacion) FROM torneoapuntado WHERE fecha='$fechasql' AND hora='$torneohora')";
  $sql = $sql." AND fecha='$fechasql'";
  $sql = $sql." AND hora='$torneohora'";
  $result = mysql_query($sql,$link);
  //echo $sql;

  $contador=0;
  $cadeaGanadores="";
  while($row = mysql_fetch_array($result))
  {
      $cadeaGanadores = $cadeaGanadores.$row["login"]."|";
      $contador++;
  }  
  
  if($contador>1)
    $cadeaGanadores = "|ganadores|".$cadeaGanadores;
  else
    $cadeaGanadores = "|ganador|".$cadeaGanadores;

  echo "MAXIMO".$torneohora."#Termina|el|torneo|de|las|".$torneohora.":00".$cadeaGanadores."]";
}

?>
