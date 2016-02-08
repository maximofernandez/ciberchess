
<?php


/*******GARDAR DATOS GEOGRAFICOS***********/

include 'geobytes.php';
include 'hostipinfo.php';
include 'ip2country.php';


$ipLat="";
$ipLonx="";
$ipCidade="";
$ipEstado="";
$ipPais="";


$geobytes = new geobytes_class;

if($geobytes->GetIpLocation($ip,$location3))
{
  $ipLat=$location3['LAT'];
  $ipLonx=$location3['LONG'];
  $ipCidade=$location3["CITY"];
  $ipEstado=$location3["STATE"];
  $ipPais=$location3["COUNTRY"];
}

echo "geobytes<br>";
echo $ipLat."<br>";
echo $ipLonx."<br>";
echo $ipCidade."<br>";
echo $ipEstado."<br>";
echo $ipPais."<br>";


$hostipinfo = new hostipinfo_class;

if($hostipinfo->GetIpLocation($ip,$location))
{
  $ipLat=$location['LAT'];
  $ipLonx=$location['LONG'];
  $ipCidade=$location["CITY"];
  $ipEstado=$location["STATE"];
  $ipPais=$location["COUNTRY"];
}

echo "hostipinfo<br>";
echo $ipLat."<br>";
echo $ipLonx."<br>";
echo $ipCidade."<br>";
echo $ipEstado."<br>";
echo $ipPais."<br>";

//if(strlen($ipPais)==0)
{
  $ip2country = new ip2country_class;
  if($ip2country->GetIpLocation($ip,$location2))
    $ipPais=$location2["COUNTRY"];
    
echo "ip2country<br>";
echo $ipLat."<br>";
echo $ipLonx."<br>";
echo $ipCidade."<br>";
echo $ipEstado."<br>";
echo $ipPais."<br>";    
    
    
}

?>