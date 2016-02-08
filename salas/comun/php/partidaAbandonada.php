<?php

include 'ConexionBD.php';

/*PASAR PARTIDA ABANDONADA AO HISTORICO***********************/   
    
    //como non sei cal xogador ten blancas e cal negras intento das duas maneiras 
    
    $sql="SELECT COUNT(*) as cuenta";
    $sql=$sql." FROM torneopartida";
    $sql=$sql." WHERE loginb = '$login'";
    $sql=$sql." AND loginn = '$loginRival'";
    $result = mysql_query($sql, $link);    
    
    if(mysql_result($result, 0, "cuenta")>0) //comprobar se a partida pertenece a un torneo
    {
      $sql="INSERT INTO torneohistoria(fecha,hora,ronda,loginb,loginn,t)";
      $sql=$sql." SELECT fecha,hora,ronda,loginb,loginn,t";
      $sql=$sql." FROM torneopartida";
      $sql=$sql." WHERE loginb = '$login'";
      $sql=$sql." AND loginn = '$loginRival'";
      $result = mysql_query($sql, $link);
    
      $sql="DELETE";
      $sql=$sql." FROM torneopartida";
      $sql=$sql." WHERE loginb = '$login'";
      $sql=$sql." AND loginn = '$loginRival'";
      $result = mysql_query($sql, $link);  
    }   

    $sql="INSERT INTO historia(t,loginb,loginn,partida,tempos,tb,tn,ritmo,ganador)";
    $sql=$sql." SELECT t,loginb,loginn,partida,tempos,tb,tn,ritmo,'ABANDONADA'";
    $sql=$sql." FROM partida";
    $sql=$sql." WHERE loginb='$login' ";
    $sql=$sql." AND loginn='$loginRival' ";
    $result = mysql_query($sql, $link);
    $sql="DELETE";
    $sql=$sql." FROM partida";
    $sql=$sql." WHERE loginb='$login' ";
    $sql=$sql." AND loginn='$loginRival' ";
    $result = mysql_query($sql, $link); 



    $sql="SELECT COUNT(*) as cuenta";
    $sql=$sql." FROM torneopartida";
    $sql=$sql." WHERE loginb = '$loginRival'";
    $sql=$sql." AND loginn = '$login'";
    $result = mysql_query($sql, $link);    
    
    if(mysql_result($result, 0, "cuenta")>0) //comprobar se a partida pertenece a un torneo
    {
      $sql="INSERT INTO torneohistoria(fecha,hora,ronda,loginb,loginn,t)";
      $sql=$sql." SELECT fecha,hora,ronda,loginb,loginn,t";
      $sql=$sql." FROM torneopartida";
      $sql=$sql." WHERE loginb = '$loginRival'";
      $sql=$sql." AND loginn = '$login'";
      $result = mysql_query($sql, $link);
    
      $sql="DELETE";
      $sql=$sql." FROM torneopartida";
      $sql=$sql." WHERE loginb = '$loginRival'";
      $sql=$sql." AND loginn = '$login'";
      $result = mysql_query($sql, $link);  
    }   
    
    $sql="INSERT INTO historia(t,loginb,loginn,partida,tempos,tb,tn,ritmo,ganador)";
    $sql=$sql." SELECT t,loginb,loginn,partida,tempos,tb,tn,ritmo,'ABANDONADA'";
    $sql=$sql." FROM partida";
    $sql=$sql." WHERE loginb='$loginRival' ";
    $sql=$sql." AND loginn='$login' ";
    $result = mysql_query($sql, $link);
    $sql="DELETE";
    $sql=$sql." FROM partida";
    $sql=$sql." WHERE loginb='$loginRival' ";
    $sql=$sql." AND loginn='$login' ";
    $result = mysql_query($sql, $link);    
  
    echo "MAXIMO";  
?>