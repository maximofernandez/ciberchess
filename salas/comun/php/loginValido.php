
<?php

include 'ConexionBD.php';


/*******GARDAR DATOS GEOGRAFICOS***********/

include 'geonames.php';
include 'geobytes.php';
include 'hostipinfo.php';
include 'ip2country.php';


/*******COMPROBAR CUENTA*********/
$sql = "SELECT count(*) as cuenta";
$sql = $sql . " FROM claves";
$sql = $sql . " WHERE login='$login'";
$sql = $sql . " AND password='$password'";
$result = mysql_query($sql, $link);

$sql = "SELECT count(*) as cuenta";
$sql = $sql . " FROM claves";
$sql = $sql . " WHERE login='$login'";
$sql = $sql . " AND (ip NOT LIKE ''";
$sql = $sql . " OR login LIKE 'INVITADO%')";
$result2 = mysql_query($sql, $link);

$sql = "SELECT ELO";
$sql = $sql . " FROM claves";
$sql = $sql . " WHERE login='$login'";
$sql = $sql . " AND password='$password'";
$result3 = mysql_query($sql, $link);

$sql = "SELECT count(*) as cuenta";
$sql = $sql . " FROM claves";
$sql = $sql . " WHERE login='$login'";
$sql = $sql . " AND password='$password'";
$sql = $sql . " AND finVIP>=now()";
$result4 = mysql_query($sql, $link);

$expulsarPorNoVIP = false;
if ( mysql_result($result4, 0, "cuenta") > 0)
  $vip = "VIPSI";
else
{
  $vip = "VIPNO";
  
  $sql = "SELECT valor";
  $sql = $sql . " FROM configuracion";
  $sql = $sql . " WHERE concepto='AforoNoVIP'";
  $result7 = mysql_query($sql, $link);
  
  $sql = "SELECT valor";
  $sql = $sql . " FROM configuracion";
  $sql = $sql . " WHERE concepto='ciberchessNoVIP5432'";
  $result8 = mysql_query($sql, $link);  
  
  $sql = "SELECT valor";
  $sql = $sql . " FROM configuracion";
  $sql = $sql . " WHERE concepto='ciberchessNoVIP1433'";
  $result9 = mysql_query($sql, $link);   
  
  if(intval(mysql_result($result7, 0, "valor")) < intval(mysql_result($result8, 0, "valor"))+intval(mysql_result($result9, 0, "valor")))
    $expulsarPorNoVIP = true;
}

echo "MAXIMO";

if (mysql_result($result, 0, "cuenta") == 0)
	echo "PIRATA";
else if (mysql_result($result2, 0, "cuenta") == 0)
	echo "PIRATA";
else if ($expulsarPorNoVIP)
	echo "PIRATA";	
else //Ahora só se comproba a posición xeográfica se non é PIRATA, é mais eficiente todo 
{
  $ipLat="";
  $ipLonx="";
  $ipCidade="";
  $ipEstado="";
  $ipPais="";



  $geonames = new geonames_class;

  if($geonames->GetIpLocation($ip,$location4)) 
  {
    $ipLat=trim($location4['LAT']);
    $ipLonx=trim($location4['LONG']);
    $ipCidade="";
    $ipEstado=trim($location4["STATE"]);
    $ipPais=trim($location4["COUNTRY"]);
  
  }


  if(strlen($ipPais)==0)  //se non conseguin datos xeográficos no anterior intento neste
  {

    $geobytes = new geobytes_class;

    if($geobytes->GetIpLocation($ip,$location3))
    {
      $ipLat=$location3['LAT'];
      $ipLonx=$location3['LONG'];
      $ipCidade=$location3["CITY"];
      $ipEstado=$location3["STATE"];
      $ipPais=$location3["COUNTRY"];
    }
  }


  if(strlen($ipPais)==0)  //se non conseguin datos xeográficos no anterior intento neste
  {

    $hostipinfo = new hostipinfo_class;


    if($hostipinfo->GetIpLocation($ip,$location))
    {
      $ipLat=$location['LAT'];
      $ipLonx=$location['LONG'];
      $ipCidade=$location["CITY"];
      $ipEstado=$location["STATE"];
      $ipPais=$location["COUNTRY"];
    }
  }


  if(strlen($ipPais)==0)  //se non conseguin datos xeográficos no anterior intento neste 
  {
    $ip2country = new ip2country_class;
    if($ip2country->GetIpLocation($ip,$location2))
      $ipPais=$location2["COUNTRY"];
  }

  $sql = "UPDATE claves";
  $sql = $sql . " set t=now(),";
  $sql = $sql . " ip='$ip',";
  if(strlen($ipLat)>0)
    $sql = $sql . " ipLat=".doubleval($ipLat).",";
  else
    $sql = $sql . " ipLat=null,";
  if(strlen($ipLonx)>0)
    $sql = $sql . " ipLonx=".doubleval($ipLonx).",";
  else
    $sql = $sql . " ipLonx=null,";
  if(strlen($ipCidade)>0)
    $sql = $sql . " ipCidade='$ipCidade',";
  else
    $sql = $sql . " ipCidade=null,";

  if(strlen($ipPais)>0)
    $sql = $sql . " ipPais='$ipPais',";
  //else
   //$sql = $sql . " ipPais=null,";  
  if(strlen($ipEstado)>0)
    $sql = $sql . " ipEstado='$ipEstado'";
  else
    $sql = $sql . " ipEstado=null";      
  $sql = $sql . " WHERE login='$login'";
  $sql = $sql . " AND password='$password'";
  $result = mysql_query($sql, $link);	
  
  //ler ipPais da base de datos porque se non o gardou sase o gardado en accesos anteriores
  $sql = "SELECT ipPais";
  $sql = $sql . " FROM claves";
  $sql = $sql . " WHERE login='$login'";
  $result70 = mysql_query($sql, $link);	
  $ipPais=mysql_result($result70, 0, "ipPais") ;  
  
  $numeroBandera=0;
  if(strlen($ipPais)>0)
  {
    $sql = "SELECT count(*) as cuenta";
    $sql = $sql . " FROM pais";
    $sql = $sql . " WHERE nombre='$ipPais'";
    $result61 = mysql_query($sql, $link);
    if ( mysql_result($result61, 0, "cuenta") > 0)
    {
	$sql = "SELECT numero";
	$sql = $sql . " FROM pais";
	$sql = $sql . " WHERE nombre='$ipPais'";
	$result62 = mysql_query($sql, $link);	
	$numeroBandera=mysql_result($result62, 0, "numero") ;
    }
  }   
  
  echo mysql_result($result3, 0, "ELO")."#".$numeroBandera."#".$vip;
}
?>