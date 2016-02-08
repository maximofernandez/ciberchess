
<?php

  include 'ConexionBD.php';

  $result = mysql_query("UPDATE claves
	                 SET t = now()
	 		 WHERE login='$login'", $link);

  $sql = "SELECT *";
  $sql = $sql . " FROM partida";
  $sql = $sql . " WHERE loginb = '$b'";
  $sql = $sql . " AND loginn = '$n'";
  $result = mysql_query($sql, $link);

  $actual_partida = mysql_result($result, 0, "partida");
  $actual_tempos = mysql_result($result, 0, "tempos");
  $tb = mysql_result($result, 0, "tb");
  $tn = mysql_result($result, 0, "tn");
  $ritmo = mysql_result($result, 0, "ritmo");
  if(strlen($actual_partida) < $psp+strlen($m))
  {
    $nueva_partida = substr($actual_partida,0,$psp).$m;
    if(strcmp($login,$b)==0)
      $tb = $tb - $segundos;
    else
      $tn = $tn - $segundos;

    $SQL="UPDATE partida"; 
    $SQL=$SQL." SET partida = '$nueva_partida',";
    $SQL=$SQL."tb = $tb,";
    $SQL=$SQL."tn = $tn,";
    if(strcmp($login,$b)==0)
      $SQL=$SQL."tmb=now()";
    else
      $SQL=$SQL."tmn=now()";
    $SQL=$SQL."WHERE loginb = '$b'";
    $SQL=$SQL."AND loginn = '$n'";  
    $result = mysql_query($SQL, $link);
  }

  if(strcmp($login,$b)==0)
  {
    $novo_tempo = substr("0000".$tb,strlen("0000".$tb)-4);
    $tempoanteriorcompletar=substr("0000".$tn,strlen("0000".$tn)-4);
  }  
  else
  {
    $novo_tempo = substr("0000".$tn,strlen("0000".$tn)-4);
    $tempoanteriorcompletar=substr("0000".$tb,strlen("0000".$tb)-4);
  }  

  if(strlen($m)==8)
    $posicionservidortempo=$psp/2;
  else if (strlen($m)==16)
    $posicionservidortempo=($psp+8)/2;

  if($posicionservidortempo>strlen($actual_tempos)) //completar
    $nueva_tempos = $actual_tempos.$tempoanteriorcompletar.$novo_tempo; //usase $tempoanteriorcompletar para mellor aproximación se se perde un mensaxe
  else if ($posicionservidortempo<strlen($actual_tempos)) //insertar
    $nueva_tempos = substr($actual_tempos,0,$posicionservidortempo).$novo_tempo.substr($actual_tempos,$posicionservidortempo+4);
  else 
    $nueva_tempos = $actual_tempos.$novo_tempo;
  
 
  $SQL="UPDATE partida"; 
  $SQL=$SQL." SET tempos = '$nueva_tempos'";
  $SQL=$SQL."WHERE loginb = '$b'";
  $SQL=$SQL."AND loginn = '$n'";  
  $result = mysql_query($SQL, $link);  

  echo "MAXIMO";

?>
