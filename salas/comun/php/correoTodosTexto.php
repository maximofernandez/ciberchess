<?php

include 'ConexionBD.php';

$SQL="SELECT login,password,nombre,email";
$SQL=$SQL." FROM claves";
$SQL=$SQL." WHERE login>='".$login."'";
$SQL=$SQL." ORDER BY login";
$result = mysql_query($SQL, $link);

if (($row = mysql_fetch_array($result))){
  do { 
       mail($row["email"], "CiberChess - Ajedrez Online", "\nHola ".$row["nombre"]. " hay un montón de gente para jugar al ajedrez online, estás registrado con el login ".$row["login"]." y password ".$row["password"].".\nEs gratis.\nPuedes jugar simultaneas.\nChat privado en cada partida con tu rival.\nChat publico con todos los jugadores.\n\nhttp://www.CiberChess.com","From:netchess@ozu.es");
   echo ("<br> ".$row["nombre"]. "  login ".$row["login"]); 		
  }while ($row = mysql_fetch_array($result));
}

?>
 