<html>
  <head>
  </head>
  <body text='ffffff' bgcolor='111111' align='center'>
    <p align='center'>

<?php

      srand((double)microtime()*1000000);

      include '../../comun/php/ConexionBD.php';

      $result4 = mysql_query("SELECT *
	                       FROM configuracion 
	                       WHERE concepto = 'numImaxesArte'", $link);    

      $contadorImaxesArte=mysql_result($result4, 0, "valor");

      $numeroImaxeArte=rand()%$contadorImaxesArte+1;
      
      echo "<IMG SRC='../../comun/imagenes/spanish/arte" . $numeroImaxeArte . ".gif'>"

?>

     
    </p>
  </body>
</html>