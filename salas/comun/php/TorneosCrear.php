<?php

srand((double)microtime()*1000000);

include 'ConexionBD.php';

for($i=1;$i<9;$i++)
{
  $alea=rand()%4;
  
  switch ($alea) 
  {
    case 0:
      $ritmo=1;
      break;
    case 1:
      $ritmo=3;
      break;
    case 2:
      $ritmo=5;
      break;
    case 3:
      $ritmo=10;
      break;      
  }  

  $sql="INSERT";
  $sql=$sql." INTO torneo(fecha, hora, estado, ritmo, incrementos)";
  $sql=$sql." VALUES(ADDTIME(now(),'$i:0:0'), HOUR(ADDTIME(now(),'$i:0:0')),0,$ritmo,".(rand()%2).")";
  $result = mysql_query($sql,$link);
  
  //echo $sql."<br>";
}


echo "MAXIMO";