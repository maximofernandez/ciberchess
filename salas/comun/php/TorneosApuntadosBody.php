<?php

  

$sql = "SELECT *";
$sql = $sql." FROM torneoapuntado";
$sql = $sql." WHERE fecha='$torneofecha'";
$sql = $sql." AND hora='$torneohora'";
$result99 = mysql_query($sql,$link);

echo "<FONT color='#EBDAB8' face='Verdana, Arial, Helvetica, sans-serif' size='2'>\n";

while($row = mysql_fetch_array($result99))
{
       echo $row["login"];
       echo "<BR>";
}
echo "</FONT>\n";

?>