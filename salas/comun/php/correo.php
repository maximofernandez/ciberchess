<?php

error_reporting(E_ALL); 


if (strlen($origen)==0)
  $origen="admin@ciberchess.com";
if (strlen($destino)>0)
{
mail($destino, $asunto,$contenido,"From:".$origen);
echo "<p align=\"center\">&nbsp;</p>";
if($idioma=="english")
  echo "<p align=\"center\"><span lang=\"es\"><font size=\"7\" color=\"#000080\">Mail sent.</font></span></p>";
else
  echo "<p align=\"center\"><span lang=\"es\"><font size=\"7\" color=\"#000080\">Correo Enviado.</font></span></p>";
}
else
{
echo "<p align=\"center\">&nbsp;</p>";
if($idioma=="english")
  echo "<p align=\"center\"><span lang=\"es\"><font size=\"7\" color=\"#000080\">Without address</font></span></p>";
else
  echo "<p align=\"center\"><span lang=\"es\"><font size=\"7\" color=\"#000080\">Sin Destino.</font></span></p>";
}
?>
