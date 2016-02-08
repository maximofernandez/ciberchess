<?php

include 'ConexionBD.php';

$sql = "SELECT ELO";
$sql = $sql." FROM claves";
$sql = $sql." WHERE login = '$login'";
$result = mysql_query($sql, $link);

$miELO = mysql_result($result, 0, "ELO");

$sql = "SELECT ELO";
$sql = $sql." FROM claves";
$sql = $sql." WHERE login = '$loginRival'";
$result = mysql_query($sql, $link);

$suELO = mysql_result($result, 0, "ELO");

if($suELO > $miELO)
  $diferencia = $suELO - $miELO;
else
  $diferencia = $miELO - $suELO;

if($diferencia <= 3) $probabilidade = 50;
else if($diferencia <= 10) $probabilidade = 51;
else if($diferencia <= 17) $probabilidade = 52;
else if($diferencia <= 25) $probabilidade = 53;
else if($diferencia <= 32) $probabilidade = 54;
else if($diferencia <= 39) $probabilidade = 55;
else if($diferencia <= 46) $probabilidade = 56;
else if($diferencia <= 53) $probabilidade = 57;
else if($diferencia <= 61) $probabilidade = 58;
else if($diferencia <= 68) $probabilidade = 59;
else if($diferencia <= 76) $probabilidade = 60;
else if($diferencia <= 83) $probabilidade = 61;
else if($diferencia <= 91) $probabilidade = 62;
else if($diferencia <= 98) $probabilidade = 63;
else if($diferencia <= 106) $probabilidade = 64;
else if($diferencia <= 113) $probabilidade = 65;
else if($diferencia <= 121) $probabilidade = 66;
else if($diferencia <= 129) $probabilidade = 67;
else if($diferencia <= 137) $probabilidade = 68;
else if($diferencia <= 145) $probabilidade = 69;
else if($diferencia <= 153) $probabilidade = 70;
else if($diferencia <= 162) $probabilidade = 71;
else if($diferencia <= 170) $probabilidade = 72;
else if($diferencia <= 179) $probabilidade = 73;
else if($diferencia <= 188) $probabilidade = 74;
else if($diferencia <= 197) $probabilidade = 75;
else if($diferencia <= 206) $probabilidade = 76;
else if($diferencia <= 215) $probabilidade = 77;
else if($diferencia <= 225) $probabilidade = 78;
else if($diferencia <= 235) $probabilidade = 79;
else if($diferencia <= 245) $probabilidade = 80;
else if($diferencia <= 256) $probabilidade = 81;
else if($diferencia <= 267) $probabilidade = 82;
else if($diferencia <= 278) $probabilidade = 83;
else if($diferencia <= 290) $probabilidade = 84;
else if($diferencia <= 302) $probabilidade = 85;
else if($diferencia <= 315) $probabilidade = 86;
else if($diferencia <= 328) $probabilidade = 87;
else if($diferencia <= 344) $probabilidade = 88;
else if($diferencia <= 357) $probabilidade = 89;
else if($diferencia <= 374) $probabilidade = 90;
else if($diferencia <= 391) $probabilidade = 91;
else if($diferencia <= 411) $probabilidade = 92;
else if($diferencia <= 432) $probabilidade = 93;
else if($diferencia <= 456) $probabilidade = 94;
else if($diferencia <= 484) $probabilidade = 95;
else if($diferencia <= 517) $probabilidade = 96;
else if($diferencia <= 559) $probabilidade = 96;
else if($diferencia <= 619) $probabilidade = 96;
else if($diferencia <= 735) $probabilidade = 96;
else $probabilidade = 96;

if(strlen($ganador)>0) //hai un ganador
{
	if($miELO > $suELO)
	{
	  
	  if (strcmp($ganador,$login)==0) 
            $modificoPuntos = round((1-$probabilidade/100.0) * 25);
	  else
            $modificoPuntos = - round($probabilidade/100.0 * 25); 
	}
	else
	{
	  if (strcmp($ganador,$login)==0) 
            $modificoPuntos = round($probabilidade/100.0 * 25);
	  else
            $modificoPuntos = - round((1-$probabilidade/100.0) * 25);
	}
	if (strcmp($ganador,$login)==0)
	{
	  $miResultado="g";
	  $suResultado="p"; 
        }
	else
	{
	  $miResultado="p";
	  $suResultado="g"; 
        }	
}
else  //tablas
{
	if($miELO > $suELO)
            $modificoPuntos = - round(($probabilidade/100.0-0.5) * 25); 
	else
            $modificoPuntos = round(($probabilidade/100.0-0.5) * 25);
            
	$miResultado="t";
	$suResultado="t";            
}

if($modificar == 1) //termina a partida e modificase o ELO
{

  //comprobación por se xa se modificou o ELO por esta partida, esto podería pasar cando o sistema esté moi cargado
  $sql = "SELECT count(*) as cuenta";
  $sql = $sql . " FROM modificaelo";
  $sql = $sql . " WHERE login='$login'";
  $sql = $sql . " AND t='".$fecha." ".$hora."'";
  $result = mysql_query($sql, $link);
  
  //comprobación por se a partida non está na táboa "partida", esto podería pasar cando o sistema esté moi cargado e non se cree correctamente a partida
  $sql = "SELECT count(*) as cuenta";
  $sql = $sql . " FROM partida";
  $sql = $sql . " WHERE (loginb='$login' AND loginn='$loginRival' OR loginn='$login' AND loginb='$loginRival') ";
  $sql = $sql . " AND t='".$fecha." ".$hora."'";
  $resultadoContaPartida = mysql_query($sql, $link);  

  if ( mysql_result($result, 0, "cuenta") > 0 || mysql_result($resultadoContaPartida, 0, "cuenta")==0)
  {
    echo "MAXIMO";
    return;
  }
  else
  {

    $miELO = $miELO + $modificoPuntos;
    $suELO = $suELO - $modificoPuntos;

    $sql = "UPDATE claves";
    $sql = $sql." SET ELO=$miELO";
    $sql = $sql." WHERE login='$login'";
    $result = mysql_query($sql, $link);
  
    $sql="INSERT";
    $sql=$sql." INTO modificaelo(login,t,resultado,ELO)";
    if(strlen($fecha)==0)
      $sql=$sql." VALUES('$login',now(),'$miResultado',$miELO)";
    else
      $sql=$sql." VALUES('$login','".$fecha." ".$hora."','$miResultado',$miELO)";
    $result = mysql_query($sql,$link);  

    $sql = "UPDATE claves";
    $sql = $sql." SET ELO=$suELO";
    $sql = $sql." WHERE login='$loginRival'";
    $result = mysql_query($sql, $link);
  
    $sql="INSERT";
    $sql=$sql." INTO modificaelo(login,t,resultado,ELO)";
    if(strlen($fecha)==0)
      $sql=$sql." VALUES('$loginRival',now(),'$suResultado',$suELO)";
    else  
      $sql=$sql." VALUES('$loginRival','".$fecha." ".$hora."','$suResultado',$suELO)";
    $result = mysql_query($sql,$link);  
  }  

}

echo "MAXIMO";

echo $modificoPuntos;

?>
