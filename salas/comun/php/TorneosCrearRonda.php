<?php



class Xogador 
{
     var $login;
     var $puntuacion;
     var $salida;
     var $descanso;

     function Xogador($l,$p,$bn,$d)
    {
        $this->login=$l;
        $this->puntuacion=$p;
        $this->salida=$bn;
        $this->descanso=$d;
    }
  
}



include 'ConexionBD.php';


function emparellar($grups,&$pars)
{
  $n=0;

  for($g=0;$g<count($grups);$g++)
    for($i=0;$i<count($grups[$g])/2;$i++)
    {
      $pars[$n][0]=$grups[$g][$i];
      $pars[$n][1]=$grups[$g][$i+count($grups[$g])/2];
      $n++;
    }  
}


function validarParellas($pars)
{
  //TODO: como é pouco probable que se repitan de momento non o comprobo de momento
  return 1;
}


  
$fechasql="$ano-$mes-$dia";

//mirar se hai partidas activas deste torneo, nese caso abortamos a creación da ronda porque non acabou a anterior
/*******************************************************************
ESTA COMPROBACIÓN AHORA XA SE FAI NO PASO ANTERIOR DO SERVIDOR DE COMUNICACIÓNS
$SQL = "SELECT count(*) as cuenta";
$SQL = $SQL . " FROM torneopartida";
$SQL=$SQL." WHERE fecha='$fechasql'";
$SQL=$SQL." AND hora=$torneohora";
$result = mysql_query($SQL, $link);

if (mysql_result($result, 0, "cuenta")>0)
{
  echo "MAXIMO".$torneohora."#0#"; //non sei a ronda así que devolvo cero
  return;
}
**********************************************************************/

//obter cal e a última ronda deste torneo se e que hai algunha para saber o número da seguinte
$sql = "SELECT count(*) as cuenta";
$sql = $sql . " FROM torneohistoria";
$sql=$sql." WHERE fecha='$fechasql'";
$sql=$sql." AND hora=$torneohora";
$result = mysql_query($sql, $link);
$numPartidasXogadas = mysql_result($result, 0, "cuenta");

$ronda=1;

if($numPartidasXogadas>0)
{
  $sql = "SELECT max(ronda) as rondamasalta";
  $sql = $sql . " FROM torneohistoria";
  $sql=$sql." WHERE fecha='$fechasql'";
  $sql=$sql." AND hora=$torneohora";
  $result = mysql_query($sql, $link);
  $ronda = mysql_result($result, 0, "rondamasalta")+1;  
}


// Eliminamos os xogadores que non moveron na partida da ronda anterior
if($ronda>1)
{
  $sql = "UPDATE torneoapuntado t";
  $sql = $sql." SET estado=$ronda";
  $sql = $sql." WHERE fecha='$fechasql'";
  $sql = $sql." AND hora=$torneohora";
  $sql = $sql." AND EXISTS (SELECT * FROM torneohistoria th JOIN historia h ON th.loginb=h.loginb AND th.loginn=h.loginn AND th.t=h.t WHERE th.fecha=t.fecha AND th.hora=t.hora AND th.loginb=t.login AND th.ronda=".($ronda-1)." AND length(h.partida)=0 AND NOT h.ganador=t.login)";
  $result = mysql_query($sql,$link);
  
  //echo $sql;
  
  $sql = "UPDATE torneoapuntado t";
  $sql = $sql." SET estado=$ronda";
  $sql = $sql." WHERE fecha='$fechasql'";
  $sql = $sql." AND hora=$torneohora";
  $sql = $sql." AND EXISTS (SELECT * FROM torneohistoria th JOIN historia h ON th.loginb=h.loginb AND th.loginn=h.loginn AND th.t=h.t WHERE th.fecha=t.fecha AND th.hora=t.hora AND th.loginn=t.login AND th.ronda=".($ronda-1)." AND length(h.partida)=8 AND NOT h.ganador=t.login)";
  $result = mysql_query($sql,$link);
}

//obter a lista de xogadores de torneoapuntados
$SQL="SELECT t.puntuacion,t.salida,t.descanso,t.instante,c.login,c.ELO";
$SQL=$SQL." FROM torneoapuntado t join claves c on t.login=c.login";
$SQL=$SQL." WHERE t.fecha='$fechasql'";
$SQL=$SQL." AND t.hora=$torneohora";
$SQL=$SQL." AND t.estado=0";
$SQL=$SQL." ORDER BY puntuacion DESC,descanso DESC,salida DESC,ELO DESC,instante ASC";
$result = mysql_query($SQL, $link);

$g=-1;
$puntuacionGrupo=-1.0;

//echo $SQL;
$aindaHaiApuntados=0;
while (($row = mysql_fetch_array($result)))
{
  $aindaHaiApuntados++;
  if ($puntuacionGrupo!=$row["puntuacion"])
  {
    $puntuacionGrupo=$row["puntuacion"];
    $g++;
    $i=0;
  }
  $grupos[$g][$i]=new Xogador($row["login"],$row["puntuacion"],$row["salida"],$row["descanso"]);
  $i++;
}

if($aindaHaiApuntados<=1)
{
  echo "MAXIMO".$torneohora."#5#"; //non hai xogadores para seguir, devolvo ronda 5 para indicar que terminou o torneo
  return;
}

//echo "<br>Grupos antes de facelos pares";
//for($g=0;$g<count($grupos);$g++)
//  for($i=0;$i<count($grupos[$g]);$i++)
//    echo "<br>grupo ".$g.": ".$grupos[$g][$i]->login." ".$grupos[$g][$i]->puntuacion." ".$grupos[$g][$i]->descanso;

//facer as modificacións necesarias para deixar grupos pares
//para cada grupo impar engadirlle o primeiro do seguinte grupo(excepto para o último grupo)
for($g=0;$g<count($grupos)-1;$g++)
  if(count($grupos[$g]) % 2) //impar
  {
    $xogadorSube=array_shift($grupos[$g+1]);
    if(count($grupos[$g+1])==0) //se o grupo o que se lle quita o xogador queda a cero eliminase o grupo
      array_splice($grupos,$g+1,1);
    array_push($grupos[$g],$xogadorSube);
  }

$loginDescansa="";
//se o último grupo é impar e o último xogador non descansou márcase para descanso e elimínase do grupo
if(count($grupos[count($grupos)-1]) % 2) //impar
{
  if($grupos[count($grupos)-1][count($grupos[count($grupos)-1])-1]->descanso > 0) //último xogador xa descansou, buscar hacia atrás un que non descansara e intercambialos
  {
    $posx = -1;
    $posy = -1;
    for($g=count($grupos)-1;$g>=0 && $posx<0;$g--)
      for($i=count($grupos[$g])-1;$i>=0 && $posx<0;$i--)
        if($grupos[$g][$i]->descanso==0)
        {
          $posx=$g;
          $posy=$i;
        }
    if($posx>=0)
    {
      $templogin = $grupos[$posx][$posy]->login;
      $temppuntuacion = $grupos[$posx][$posy]->puntuacion;
      $tempsalida = $grupos[$posx][$posy]->salida;
      $tempdescanso = $grupos[$posx][$posy]->descanso;
      $grupos[$posx][$posy]->login = $grupos[count($grupos)-1][count($grupos[count($grupos)-1])-1]->login;
      $grupos[$posx][$posy]->puntuacion = $grupos[count($grupos)-1][count($grupos[count($grupos)-1])-1]->puntuacion;
      $grupos[$posx][$posy]->salida = $grupos[count($grupos)-1][count($grupos[count($grupos)-1])-1]->salida;
      $grupos[$posx][$posy]->descanso = $grupos[count($grupos)-1][count($grupos[count($grupos)-1])-1]->descanso;     
      $grupos[count($grupos)-1][count($grupos[count($grupos)-1])-1]->login = $templogin;
      $grupos[count($grupos)-1][count($grupos[count($grupos)-1])-1]->puntuacion = $temppuntuacion;
      $grupos[count($grupos)-1][count($grupos[count($grupos)-1])-1]->salida = $tempsalida;
      $grupos[count($grupos)-1][count($grupos[count($grupos)-1])-1]->descanso = $tempdescanso;           
    }       
  }
  //xa sabemos que o último non descansou, marcámolo con descanso e eliminarémolo do grupo
  $grupos[count($grupos)-1][count($grupos[count($grupos)-1])-1]->descanso=1;
  $loginDescansa=$grupos[count($grupos)-1][count($grupos[count($grupos)-1])-1]->login;
  array_pop($grupos[count($grupos)-1]);
  if(count($grupos[count($grupos)-1])==0) //se o último grupo o que se lle quita o xogador queda a cero eliminase o grupo
    array_splice($grupos,count($grupos)-1,1);  
}

//echo "<br>Grupos despois de facelos pares";
//for($g=0;$g<count($grupos);$g++)
//  for($i=0;$i<count($grupos[$g]);$i++)
//    echo "<br>grupo ".$g.": ".$grupos[$g][$i]->login." ".$grupos[$g][$i]->puntuacion;


//se hai partidas xa xogadas neste torneo telas en conta como datos para crear as novas
//non se poden enfrentar duas veces os mesmos, equilibrar partidas con blancas e negras, ...
$contador = 0; //se en 5 veces sigue habendo parellas repetidas deixo así
do
{
  emparellar($grupos,$parellas);
  $contador++;
} while (!validarParellas($parellas) && $contador<5);


//Se é a primeira ronda marcar o torneo como "en xogo"
if($ronda==1)
{
  $sql = "UPDATE torneo";
  $sql = $sql . " SET estado=1";
  $sql=$sql." WHERE fecha='$fechasql'";
  $sql=$sql." AND hora=$torneohora";
  $result = mysql_query($sql, $link);
}


//crear un instante común para a creación das partidas e consultar datos do torneo
$sql = "SELECT ritmo,incrementos,now() as t";
$sql = $sql . " FROM torneo";
$sql = $sql." WHERE fecha='$fechasql'";
$sql = $sql." AND hora=$torneohora";
$result = mysql_query($sql, $link);
$instanteCreacion = mysql_result($result, 0, "t");
$ritmo = mysql_result($result, 0, "ritmo"); 
$incrementos = mysql_result($result, 0, "incrementos");  

//echo $sql;

//con todos esos datos e aplicando o sistema suizo fixar as partidas da ronda e
//insertar as partidas en partida e a relación en torneo partida
$cadeaRetorno="MAXIMO".$torneohora."#".$ronda."#".$ritmo."#".$incrementos."#";
$temposegundos=$ritmo*60;
for($n=0;$n<count($parellas);$n++)
{
  $sql="INSERT";
  $sql=$sql." INTO partida(loginb,loginn,partida,tempos,tb,tn,ritmo,tmb,tmn,ganador,t,incrementos)";
  if($parellas[$n][0]->salida<=$parellas[$n][1]->salida)
  {
    $sql=$sql." VALUES('".$parellas[$n][0]->login."','".$parellas[$n][1]->login."','','',$temposegundos,$temposegundos,$ritmo,'$instanteCreacion','$instanteCreacion','','$instanteCreacion',$incrementos)";
    $cadeaRetorno=$cadeaRetorno.$parellas[$n][0]->login."#".$parellas[$n][1]->login."#";
  }
  else
  {
    $sql=$sql." VALUES('".$parellas[$n][1]->login."','".$parellas[$n][0]->login."','','',$temposegundos,$temposegundos,$ritmo,'$instanteCreacion','$instanteCreacion','','$instanteCreacion',$incrementos)";
    $cadeaRetorno=$cadeaRetorno.$parellas[$n][1]->login."#".$parellas[$n][0]->login."#";
  }
  $result = mysql_query($sql,$link);  
  
  
  $sql="INSERT";
  $sql=$sql." INTO torneopartida(fecha,hora,ronda,loginb,loginn,t)";
  if($parellas[$n][0]->salida<=$parellas[$n][1]->salida)
    $sql=$sql." VALUES('$fechasql',$torneohora,$ronda,'".$parellas[$n][0]->login."','".$parellas[$n][1]->login."','$instanteCreacion')";
  else
    $sql=$sql." VALUES('$fechasql',$torneohora,$ronda,'".$parellas[$n][1]->login."','".$parellas[$n][0]->login."','$instanteCreacion')";
  $result = mysql_query($sql,$link);    
}
//finalmente devolver unha cadea coa lista de partidas para que o servidorcomunicacionsalon
//envie os mensaxes correspondentes os xogadores para que abran as partidas (logo podería haber
//mensaxes de que o rival non compareceu ou cousas así)


//echo "<br><br><br>Ronda: $ronda";
//echo "<br>Parellas:";
//for($n=0;$n<count($parellas);$n++)
//  echo "<br>".$parellas[$n][0]->login." ".$parellas[$n][1]->login;
//echo "<br><br>login descansa: ".$loginDescansa;

if(strlen($loginDescansa)>0)
{
  $cadeaRetorno=$cadeaRetorno.$loginDescansa."##";
  
  $sql = "UPDATE torneoapuntado";
  $sql = $sql . " SET puntuacion=puntuacion+1,descanso=1";
  $sql=$sql." WHERE login='$loginDescansa'";
  $sql = $sql." AND fecha='$fechasql'";
  $sql = $sql." AND hora=$torneohora";
  $result = mysql_query($sql, $link);  
}

echo $cadeaRetorno;

?>
