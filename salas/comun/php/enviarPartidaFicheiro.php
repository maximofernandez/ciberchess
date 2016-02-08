<?php


  function crear_semilla()
  {
    list($usec, $sec) = explode(' ', microtime());
    return (float) $sec + ((float) $usec * 100000);
  }

  srand(crear_semilla());
  $nombre_temp = "CIB" . rand();
  srand(crear_semilla());
  $nombre_temp = $nombre_temp . rand();
  srand(crear_semilla());
  $nombre_temp = $nombre_temp . rand();

  mkdir("./tmp/$nombre_temp");
  
  include 'ConexionBD.php';

  $gestor = fopen("./tmp/$nombre_temp/partida.cib", "w");

  $t1 = $f1." ".$h1;
  $SQL="SELECT partida,loginb,loginn,ritmo,ganador,tempos,t";
  $SQL=$SQL." FROM historia";
  $SQL=$SQL." WHERE t='$t1'";
  $SQL=$SQL." AND loginb='$lb1'";
  $SQL=$SQL." AND loginn='$ln1'";
  $result = mysql_query($SQL, $link);
	
  if (($row = mysql_fetch_array($result)))
  {
    fwrite($gestor, $row["partida"]."#".$row["loginb"]."#".$row["loginn"]."#".$l1."#".$row["ritmo"]."#".$row["ganador"]."#".$row["tempos"]."#".$row["t"]."#");
  }

  fclose($gestor);
  
  $i=2;
  while(strlen(${"f".$i})>0)
  {
    $gestor = fopen("./tmp/$nombre_temp/partida".$i.".cib", "w");

    $t = ${"f".$i}." ".${"h".$i};
    $SQL="SELECT partida,loginb,loginn,ritmo,ganador,tempos,t";
    $SQL=$SQL." FROM historia";
    $SQL=$SQL." WHERE t='$t'";
    $SQL=$SQL." AND loginb='${"lb".$i}'";
    $SQL=$SQL." AND loginn='${"ln".$i}'";
    $result = mysql_query($SQL, $link);
	
    if (($row = mysql_fetch_array($result)))
    {
      fwrite($gestor, $row["partida"]."#".$row["loginb"]."#".$row["loginn"]."#".${"l".$i}."#".$row["ritmo"]."#".$row["ganador"]."#".$row["tempos"]."#".$row["t"]."#");
    }

    fclose($gestor);    
    
    $i++;
  }
  
  if(strlen($f2)==0)
    $nome_jar = $lb1 . "_".$ln1 . "_".$f1 . "_".str_replace(":","-",$h1).".jar";
  else
    $nome_jar = $f1."_".str_replace(":","-",$h1).".jar";
  
  $nome_jar = str_replace("Ñ","N",$nome_jar);
  $nome_jar = str_replace("ñ","N",$nome_jar);
  
  copy("./partida.jar","./tmp/".$nombre_temp."/".$nome_jar);
  
  chdir("."."/tmp/".$nombre_temp."/");
  system("jar -uf ".$nome_jar." partida.cib");
  $i=2;
  while(strlen(${"f".$i})>0)
  {
    system("jar -uf ".$nome_jar." partida".$i.".cib");
    $i++;
  }
  chdir("../../");

  unlink("./tmp/$nombre_temp/partida.cib");
  
  $i=2;
  while(strlen(${"f".$i})>0)
  {
    unlink("./tmp/$nombre_temp/partida".$i.".cib");
    $i++;
  }

  echo "MAXIMO";
  echo "../php/tmp/".$nombre_temp."/".$nome_jar;

?>
