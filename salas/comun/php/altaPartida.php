<?php

include 'ConexionBD.php';


$sql="SELECT *";
$sql=$sql." FROM claves";
$sql=$sql." WHERE login = '$blancas'";
$sql=$sql." OR login = '$negras'";
$result = mysql_query($sql,$link);

$row = mysql_fetch_array($result);
$ipblancas = $row["ip"];

if($row = mysql_fetch_array($result)) //se non existe o segundo xogador tampouco creamos a partida, esto é o que aproveitaban
{                                     //para darse de baixa, seguir xogando e fastidiar a puntuación a outros xogadores
  $ipnegras = $row["ip"];  


  if (strcmp($ipblancas,$ipnegras) != 0 ||
      strcmp(str_replace("INVITADO","",$blancas),$blancas)!=0 ||
      strcmp(str_replace("INVITADO","",$negras),$negras)!=0 ||
      strcmp(str_replace("GUEST","",$blancas),$blancas)!=0 ||
      strcmp(str_replace("GUEST","",$negras),$negras)!=0 ||    
      strcmp($blancas,"B")==0 ||
      strcmp($negras,"B")==0
   || 
      strcmp($blancas,"ANDRES")==0 ||
      strcmp($negras,"ANDRES")==0 ||
      strcmp($blancas,"MAXIMO")==0 ||
      strcmp($negras,"MAXIMO")==0)
  {

    $sql="DELETE";
    $sql=$sql." FROM partida";
    $sql=$sql." WHERE (loginb='$blancas'";
    $sql=$sql." AND loginn='$negras')";
    $sql=$sql." OR (loginn='$blancas'";
    $sql=$sql." AND loginb='$negras')";
    $result = mysql_query($sql, $link); 
    $sql="INSERT";
    $sql=$sql." INTO partida(loginb,loginn,partida,tempos,tb,tn,ritmo,tmb,tmn,ganador,t,incrementos)";
    $sql=$sql." VALUES('$blancas','$negras','','',60*$tempo,60*$tempo,$tempo,now(),now(),'',now(),$incrementos)";
    $result = mysql_query($sql,$link);
    $sql="UPDATE partida";
    $sql=$sql." SET tmn=tmb";
    $sql=$sql." WHERE loginb='blancas'";
    $sql=$sql." AND loginn='negras'";
    $result = mysql_query($sql, $link);
  }   
}

echo "MAXIMO".$blancas."#".$negras."#".$tempo."#".$incrementos."#";

?>
