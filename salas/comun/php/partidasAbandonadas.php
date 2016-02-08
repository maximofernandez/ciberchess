<?php

$sala='ciberchess';

include 'ConexionBD.php';

/*BUSCAR PARTIDAS ABANDONADAS PARA PASALAS O HISTORICO***********************/   




    $sql="SELECT *";
    $sql=$sql." FROM torneopartida tp";
    $sql=$sql." WHERE 0=(SELECT COUNT(*) FROM partida WHERE loginb=tp.loginb AND loginn=tp.loginn)";
    $result99 = mysql_query($sql, $link);  


    while($row = mysql_fetch_array($result99))
    {
       
      $sql="INSERT INTO torneohistoria(fecha,hora,ronda,loginb,loginn,t)";
      $sql=$sql." SELECT fecha,hora,ronda,loginb,loginn,t";
      $sql=$sql." FROM torneopartida";
      $sql=$sql." WHERE loginb='".$row["loginb"]."'";
      $sql=$sql." AND loginn='".$row["loginn"]."'";
      $result = mysql_query($sql, $link);       
    
      $sql="DELETE";
      $sql=$sql." FROM torneopartida";
      $sql=$sql." WHERE loginb='".$row["loginb"]."'";
      $sql=$sql." AND loginn='".$row["loginn"]."'";
      $result = mysql_query($sql, $link);
              
    } 








    $sql="SELECT *";
    $sql=$sql." FROM partida";
    $sql=$sql." WHERE TIMESTAMPDIFF(SECOND,t,now()) > (2*ritmo*60+incrementos*40)*2 ";
    $sql=$sql." OR TIMESTAMPDIFF(SECOND,tmb,now()) > ritmo*60*2 ";
    $sql=$sql." OR TIMESTAMPDIFF(SECOND,tmn,now()) > ritmo*60*2 ";
    //$sql=$sql." OR (TIMESTAMPDIFF(SECOND,t,now()) > 30 AND partida='' )";
    $result99 = mysql_query($sql, $link);  


    while($row = mysql_fetch_array($result99))
    {
       
      $sql="INSERT INTO torneohistoria(fecha,hora,ronda,loginb,loginn,t)";
      $sql=$sql." SELECT fecha,hora,ronda,loginb,loginn,t";
      $sql=$sql." FROM torneopartida";
      $sql=$sql." WHERE loginb='".$row["loginb"]."'";
      $sql=$sql." AND loginn='".$row["loginn"]."'";
      $result = mysql_query($sql, $link);       
    
      $sql="DELETE";
      $sql=$sql." FROM torneopartida";
      $sql=$sql." WHERE loginb='".$row["loginb"]."'";
      $sql=$sql." AND loginn='".$row["loginn"]."'";
      $result = mysql_query($sql, $link);
      
      $sql="INSERT INTO historia(t,loginb,loginn,partida,tempos,tb,tn,ritmo,ganador)";
      $sql=$sql." SELECT t,loginb,loginn,partida,tempos,tb,tn,ritmo,'ABANDONADA' as ganador";
      $sql=$sql." FROM partida";
      $sql=$sql." WHERE loginb='".$row["loginb"]."'";
      $sql=$sql." AND loginn='".$row["loginn"]."'";
      $result = mysql_query($sql, $link);
    
      $sql="DELETE";
      $sql=$sql." FROM partida";
      $sql=$sql." WHERE loginb='".$row["loginb"]."'";
      $sql=$sql." AND loginn='".$row["loginn"]."'";
      $result = mysql_query($sql, $link);        
    } 
    
    

    $sql="UPDATE torneo t";
    $sql=$sql." SET estado=2";
    $sql=$sql." WHERE estado=1";
    $sql=$sql." AND 0=(SELECT COUNT(*) FROM torneopartida WHERE fecha=t.fecha AND hora=t.hora)";
    $sql=$sql." AND TIMESTAMPDIFF(SECOND,TIMESTAMPADD(HOUR,hora,fecha),now()) > 5*  (2*ritmo*60+incrementos*40)*1.2  +5*30";
    $result = mysql_query($sql, $link);
  
echo "MAXIMO";  
   
?>