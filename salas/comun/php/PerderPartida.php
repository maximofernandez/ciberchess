
<?php

  include 'ConexionBD.php';

  $result = mysql_query("UPDATE partida
                         SET ganador = '$loginRival'
                       WHERE loginb = '$b'
                         AND loginn = '$n'", $link);
                              
  $sql="INSERT INTO historia(t,loginb,loginn,partida,tempos,tb,tn,ritmo,ganador)";
  $sql=$sql." SELECT t,loginb,loginn,partida,tempos,tb,tn,ritmo,ganador";
  $sql=$sql." FROM partida";
  $sql=$sql." WHERE loginb = '$b'";
  $sql=$sql." AND loginn = '$n'";
  $result = mysql_query($sql, $link);
    
  $sql="DELETE";
  $sql=$sql." FROM partida";
  $sql=$sql." WHERE loginb = '$b'";
  $sql=$sql." AND loginn = '$n'";
  $result = mysql_query($sql, $link);     
  
  $sql="SELECT COUNT(*) as cuenta";
  $sql=$sql." FROM torneopartida";
  $sql=$sql." WHERE loginb = '$b'";
  $sql=$sql." AND loginn = '$n'";
  $result = mysql_query($sql, $link);             
  
  if(mysql_result($result, 0, "cuenta")>0) //comprobar se a partida pertenece a un torneo
  {
  
    $sql="SELECT fecha,hora";
    $sql=$sql." FROM torneopartida";
    $sql=$sql." WHERE loginb = '$b'";
    $sql=$sql." AND loginn = '$n'";
    $result99 = mysql_query($sql, $link);  
  
    $sql = "UPDATE torneoapuntado";
    $sql = $sql . " SET puntuacion=puntuacion+1";
    $sql=$sql." WHERE login='$loginRival'";
    $sql=$sql." AND fecha='".mysql_result($result99, 0, "fecha")."'";
    $sql=$sql." AND hora=".mysql_result($result99, 0, "hora");
    $result = mysql_query($sql, $link);
  
    $sql="INSERT INTO torneohistoria(fecha,hora,ronda,loginb,loginn,t)";
    $sql=$sql." SELECT fecha,hora,ronda,loginb,loginn,t";
    $sql=$sql." FROM torneopartida";
    $sql=$sql." WHERE loginb = '$b'";
    $sql=$sql." AND loginn = '$n'";
    $result = mysql_query($sql, $link);
    
    $sql="DELETE";
    $sql=$sql." FROM torneopartida";
    $sql=$sql." WHERE loginb = '$b'";
    $sql=$sql." AND loginn = '$n'";
    $result = mysql_query($sql, $link);  
  }  

  echo "MAXIMO";
?>
