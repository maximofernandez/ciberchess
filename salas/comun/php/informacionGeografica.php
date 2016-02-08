<?php

include 'ConexionBD.php';


$result = mysql_query("SELECT *
                       FROM claves 
                       WHERE login = '$login'", $link);
$latitude = mysql_result($result, 0, "ipLat");
$lonxitude = mysql_result($result, 0, "ipLonx");
$pais = mysql_result($result, 0, "ipPais");
$estado = mysql_result($result, 0, "ipEstado");
$cidade = mysql_result($result, 0, "ipCidade");
?>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>www.Ciberchess.com - Información geográfica del usuario <?php echo $login; ?></title>
    <script src="http://maps.google.com/maps?file=api&amp;v=2&key=ABQIAAAAQ0hbSp7XPtCdAma4cCPdjxQ7jObJbF5BhcTk1NBCTw5tqYovehTDM5HxyDuUfo0Pe2oDBIuWYKMtFQ"
            type="text/javascript"></script>
    <script type="text/javascript">
    
    function initialize() 
    {
      var map,marker;
      if (GBrowserIsCompatible()) 
      {
        map = new GMap2(document.getElementById("map_canvas"));
        map.setCenter(new GLatLng(<?php echo $latitude; ?>, <?php echo $lonxitude; ?>), 5);
        map.addControl(new GLargeMapControl());
        map.addControl(new GMapTypeControl());
        
        marker = new GMarker(new GLatLng(<?php echo $latitude; ?>, <?php echo $lonxitude; ?>));
        GEvent.addListener(marker, "click", function() { marker.openInfoWindowHtml('<?php echo $login; ?>'); } ); 
        map.addOverlay(marker);        
      }
    }

    </script>
  </head>

  <body text='#EBDAB8' border=0 topmargin=0 leftmargin=0 bgcolor='#000000' onload="initialize()" onunload="GUnload()">
  <table  cellpadding='0' cellspacing='0' border=0 topmargin=0 leftmargin=0 align='center' valign='top' width='700'>
  <tr align='center' bgcolor='#000000'>
  <td align='center'>
    <?php echo "      <IMG SRC='../../".$sala."/imagenes/LogoHtml.jpg'>"; ?>
  </td>
  </tr>   
  <tr  bgcolor='#000000'>
  <td >
    <?php
      if($idioma=="english")
        echo "The geographical point is not very accurate for the position of the user " . $login . ", because what really drew is a gateway to the Internet provider that is connected to that user, in any case the country is almost 100% correct, in a city percentage fairly minor. If someone finds its own information erroneous, you can fix it in http://www.hostip.info/ which is where the data are taken.";
      else
        echo "El punto geográfico no es muy preciso para señalar la posición del usuario " . $login . ", porque lo que señala realmente es la salida a Internet del proveedor al que está conectado dicho usuario, de todas formas el país es correcto casi al 100%, la ciudad en un porcentaje bastante menor. Si alguien encuentra su propia información errónea, puede corregirlo en http://www.hostip.info/ que es de donde se toman los datos.";
    ?>  
    <br>
    <?php
      if(isset($pais))
        echo "País: ".$pais;
      if(isset($estado))
        echo " Estado: ".$estado;
      if(isset($ciudad))
        echo " Cidade: ".$cidade;    
    ?>    
  </td>
  </tr>  
  <tr >
  <td >
    <?php
      if(!isset($latitude))
        if($idioma=="english")
          echo "<br><br>Sorry, but we do not have geographical information about ".$login.".";
        else
          echo "<br><br>Lo siento, pero no disponemos de información geográfica sobre ".$login.".";
      else  
      {
        echo "<TABLE BORDER='1' cellpadding='0' cellspacing='0'>";
        echo "<TR>";
        echo "<TD>";
        echo "<div id=\"map_canvas\" style=\"width: 700px; height: 500px\"></div>";
        echo "</TD>";
        echo "</TR>";
        echo "</TABLE>";
      }
    ?> 
  </td>
  </tr>
  </table>
  </body>
</html>
