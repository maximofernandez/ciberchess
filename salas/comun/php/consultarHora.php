<?php
  echo "MAXIMO";
  
  $ahora=time();
  
  echo date("H#i#s#",$ahora);
  
  if (date("H",$ahora)==23 && date("i")>30) //interesa devolver fecha do dia seguinte para o torneo
    $ahora=$ahora+3600*24;
  echo date("Y#m#d#",$ahora);

?>