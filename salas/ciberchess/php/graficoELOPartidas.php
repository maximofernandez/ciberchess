<?php
require('Chart.class.php');

//objeto Chart
$chart = new Chart();
//creamos una tabla con
//valores 
$array = Array();
//numero de dias ou partidas de seguimento
$num=45;

$invitado=(strcmp(str_replace("INVITADO","",$login),$login)!=0);

if($invitado)
  for($i=0;$i<$num;$i++)
    $array[]=1500;
else
{
  include '../../comun/php/ConexionBD.php';

  $SQL="SELECT t,ELO";
  $SQL=$SQL." FROM modificaelo m";
  $SQL=$SQL." WHERE login='$login'";
  $SQL=$SQL." ORDER BY t DESC";
  $SQL=$SQL." LIMIT 60"; 
  $result = mysql_query($SQL, $link);

  while($row = mysql_fetch_array($result))
    $array[] = $row["ELO"];
}

$ultimoValor=1500;
$i=count($array);
if($i>0)
  $ultimoValor=$array[$i-1];
for(;$i<$num;$i++)
  $array[]=$ultimoValor;
for($i=0;$i<count($array);$i++)
  $array[$i]=max(0,$array[$i]-1000);

//colocamos los valores y
//generamos la imagen
$chart->draw(array_reverse($array));
?>