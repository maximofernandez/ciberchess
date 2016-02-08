<?php

class Xogador 
{
     var $login;
     var $puntuacion;
     var $blancasnegras;

     function Xogador($l,$p,$bn)
    {
        $login=$l;
        $puntuacion=$p;
        $bancasnegras=$bn;
    }
  
}

$matriz[0][0]=new Xogador("maximo",4,1);
$matriz[0][1]=new Xogador("maximo",4,1);
$matriz[0][2]=new Xogador("maximo",4,1);
$matriz[1][0]=new Xogador("maximo",4,1);

array_shift($matriz[1]);

echo count($matriz)."\n\n<br><br>";
echo count($matriz[0])."\n\n<br><br>";
echo count($matriz[1])."\n\n<br><br>";

print_r ($matriz); echo "\n<br><br>";
print_r ($matriz[0]); echo "\n<br><br>";
print_r ($matriz[1]); echo "\n<br><br>";

?>