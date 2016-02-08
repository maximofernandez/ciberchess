<?php
  session_start();
  $login=$_SESSION["loginsesion"];
  $password=$_SESSION["passwordsesion"];
  $idioma=$_SESSION["idiomasesion"];  
?>


<HEAD>
  <TITLE>CiberChess - Ajedrez on line</TITLE>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
</HEAD>

<?php

srand((double)microtime()*1000000);

if ($ancho==0)
  $ancho=800;

if ($alto==0)
  $alto=600;

if(isset($e)) //non entrou por apertura de emergencia
{
  $ancho=$ancho-10;
  $alto=$alto-115;
}
else
{
  $ancho=$ancho-30;
  $alto=$alto-145;
}

include 'ConexionBD.php';


$login = strtoupper($login);
$password = strtoupper($password);
 
   
  /*********************************************************************************/

	$result = mysql_query("UPDATE claves
	                       SET t = now()
	 		       WHERE login='$login'", $link);


	$result2 = mysql_query("SELECT *
	                       FROM configuracion 
	                       WHERE concepto = 'servidor'", $link);

	$result3 = mysql_query("SELECT *
	                       FROM configuracion 
	                       WHERE concepto = 'puertoSalon'", $link);
	
	$result5 = mysql_query("SELECT *
	                       FROM partida
	                       WHERE (loginb = '$login'
	                         OR loginn = '$login')
                                AND ganador LIKE ''", $link);

	$result6 = mysql_query("SELECT *
	                       FROM claves 
	                       WHERE login = '$login'", $link);
        $elo = mysql_result($result6, 0, "ELO");
        if(is_null(mysql_result($result6, 0, "tempoDesexado")))
          $tempoDesexado="A|10";
        else 
          $tempoDesexado=mysql_result($result6, 0, "tempoDesexado");
        if(is_null(mysql_result($result6, 0, "incrementoDesexado")))
          $incrementoDesexado="1";
        else 
          $incrementoDesexado=mysql_result($result6, 0, "incrementoDesexado");
        if(is_null(mysql_result($result6, 0, "colorDesexado")))
          $colorDesexado="Bla";
        else 
          $colorDesexado=mysql_result($result6, 0, "colorDesexado");                    
        $tempoIncCol = $tempoDesexado."]".$incrementoDesexado."]".$colorDesexado;
 
        $result7 = mysql_query("SELECT count(*) as cuenta FROM fraseMarquesina", $link);
        $contadorFrases = mysql_result($result7, 0, "cuenta");


        $numeroFraseAleatoria=rand()%$contadorFrases+1;

	$result8 = mysql_query("SELECT frase
	                       FROM fraseMarquesina 
	                       WHERE numero = $numeroFraseAleatoria", $link);
	$fraseMarquesina = mysql_result($result8, 0, "frase");

	$result9 = mysql_query("SELECT *
	                        FROM color
                                ORDER BY codigo", $link);
                                
       	$result10 = mysql_query("DESCRIBE claves", $link);  
       	$existeContribucion=false;
       	
	if (($row = mysql_fetch_array($result10)))
	{  
	  do 
	  { 
            if(strcmp("smss",$row["Field"])==0)
            {
              $existeContribucion=true;
            }
	  }while (($row = mysql_fetch_array($result10)));
	}       	
	
	$finVIP="";
	$sql = "SELECT count(*) as cuenta";
	$sql = $sql . " FROM claves";
	$sql = $sql . " WHERE login='$login'";
	$sql = $sql . " AND password='$password'";
	$sql = $sql . " AND finVIP>now()";
	$result11 = mysql_query($sql, $link);
	
	if ( mysql_result($result11, 0, "cuenta") > 0)
	{
		$sql = "SELECT finVIP";
		$sql = $sql . " FROM claves";
		$sql = $sql . " WHERE login='$login'";
		$sql = $sql . " AND password='$password'";
		$result12 = mysql_query($sql, $link);	
		$finVIP=mysql_result($result12, 0, "finVIP") ;
	}
       	
 
	$sql = "SELECT palabra";
	$sql = $sql . " FROM palabraprohibida";
	$sql = $sql . " WHERE nivel=2";
	$result14 = mysql_query($sql, $link);    
	
	$sql = "SELECT palabra";
	$sql = $sql . " FROM palabraprohibida";
	$sql = $sql . " WHERE nivel=1";
	$result15 = mysql_query($sql, $link); 
	
	$sql = "SELECT valor";
	$sql = $sql . " FROM configuracion";
	$sql = $sql . " WHERE concepto='publicidade'";
	$result16 = mysql_query($sql, $link);
       	
       	$publicidade="no";
	if (($row = mysql_fetch_array($result16)))
	{  
	  $publicidade=$row["valor"];
	} 	
	
	$sql = "SELECT valor";
	$sql = $sql . " FROM configuracion";
	$sql = $sql . " WHERE concepto='webPublicidade'";
	$result17 = mysql_query($sql, $link);
       	
       	$webPublicidade="http://www.ciberchess.com";
	if (($row = mysql_fetch_array($result17)))
	{  
	  $webPublicidade=$row["valor"];
	} 	
       	       	                              
                             
        $invitado=(strcmp(str_replace("INVITADO","",$login),$login)!=0);	
	
	echo "<body bgcolor='#000000' link=\"#EBDAB8\" vlink=\"#EBDAB8\" alink=\"#EBDAB8\" border=0 topmargin=0 leftmargin=0 align='center' valign=center >\n" ;



        echo "<TABLE border='0' cellspacing='1px' cellpadding='0'  WIDTH='".($ancho-10)."' align='center'>\n";
 //          echo "<TR width='100%' height='1px'>";
 //            echo "<TD>";
 //             echo "</TD>";
 //          echo "</TR>";
           echo "<TR>\n";
              echo "<TD>\n";
	
		echo "<applet code=Salon.class archive=tablero.jar codebase=../java  MAYSCRIPT NAME='salon' ";
		echo " width=$ancho\n";
		echo " height=$alto\n";
		echo ">\n";
		
		if ($existeContribucion)
		{
		  echo "<param name=existeContribucion value=\"si\">\n";
		  echo "<param name=fraseMarquesinaContribucion value=\"\">\n";
		  echo "<param name=fraseChatPublicoContribucion value=\"Oferta 6x5, usuario VIP 6 meses por 5 Euros a través de PayPal. O bien 30 días por un SMS. Menú Usuario/VIP.\">\n";
		}
		else 
		{
		  echo "<param name=existeContribucion value=\"no\">\n";
		}
		
                $usaColorAleatorio=mysql_result($result6, 0, "colorAleatorio")==1;
                $colorAleatorio=rand() % 10;
		$contador=0;
		if (($row = mysql_fetch_array($result9)))
		{  
		  do 
		  { 
		        if($contador==$colorAleatorio)
		        {
		          $colorAleatorioFondoRojo=$row["FondoRojo"];
		          $colorAleatorioFondoVerde=$row["FondoVerde"];
		          $colorAleatorioFondoAzul=$row["FondoAzul"];
		          $colorAleatorioFrenteRojo=$row["FrenteRojo"];
		          $colorAleatorioFrenteVerde=$row["FrenteVerde"];
		          $colorAleatorioFrenteAzul=$row["FrenteAzul"];
		        }
			echo "<param name=ColorFondoRojo".$contador." value=".$row["FondoRojo"].">\n";
			echo "<param name=ColorFondoVerde".$contador." value=".$row["FondoVerde"].">\n";
			echo "<param name=ColorFondoAzul".$contador." value=".$row["FondoAzul"].">\n";
			echo "<param name=ColorFrenteRojo".$contador." value=".$row["FrenteRojo"].">\n";
			echo "<param name=ColorFrenteVerde".$contador." value=".$row["FrenteVerde"].">\n";
			echo "<param name=ColorFrenteAzul".$contador." value=".$row["FrenteAzul"].">\n";
			$contador++;
		  }while (($row = mysql_fetch_array($result9)));
		}		

		if($usaColorAleatorio)
		  echo "<param name=ColorAleatorio value='1'>\n";
		else
		  echo "<param name=ColorAleatorio value='0'>\n";

                if(mysql_result($result6, 0, "colorRojoFondo")==null || 
                   $invitado ||
                   $usaColorAleatorio)
                  echo "<param name=colorSalon value='".$colorAleatorioFondoRojo.",".$colorAleatorioFondoVerde.",".$colorAleatorioFondoAzul."'>\n";
                else
                {
                  echo "<param name=colorSalon value='";
                  echo mysql_result($result6, 0, "colorRojoFondo");
                  echo ",";
                  echo mysql_result($result6, 0, "colorVerdeFondo");
                  echo ",";
                  echo mysql_result($result6, 0, "colorAzulFondo");                
                  echo "'>\n";
                }
                if(mysql_result($result6, 0, "colorRojoFrente")==null || 
                   $invitado ||
                   $usaColorAleatorio)
                  echo "<param name=colorObxetos value='".$colorAleatorioFrenteRojo.",".$colorAleatorioFrenteVerde.",".$colorAleatorioFrenteAzul."'>\n";
                else
                {                
                  echo "<param name=colorObxetos value='";
                  echo mysql_result($result6, 0, "colorRojoFrente");
                  echo ",";
                  echo mysql_result($result6, 0, "colorVerdeFrente");
                  echo ",";
                  echo mysql_result($result6, 0, "colorAzulFrente");                
                  echo "'>\n";                
                  echo "<param name=colorFondo value='0,0,0'>\n";
                }

                echo "<param name='traxectoriaLogo' value='../../ciberchess/imagenes/LogoJava.gif'>\n";
                echo "<param name='traxectoriaLogoV' value='../../ciberchess/imagenes/LogoHtmlV.jpg'>\n";                
		echo "<param name='sala' value='".$sala."'>\n";   
		echo "<param name='idioma' value='".$idioma."'>\n";             
		echo "<param name='login' value='".$login."'>\n";
		echo "<param name='clave' value='".$password."'>\n";
		echo "<param name='elo' value='".$elo."'>\n";
		echo "<param name='tempoIncCol' value='".$tempoIncCol."'>\n";
		echo "<param name='finVIP' value='".$finVIP."'>\n";
		echo "<param name='hora' value='".date("H")."'>\n";
		echo "<param name='minuto' value='".date("i")."'>\n";
		echo "<param name='segundo' value='".date("s")."'>\n";				
                $contador = 1;
		if (($row = mysql_fetch_array($result5)))
		{		  
		  do 
		  { 
			echo "<param name=m".$contador." value=".$row["partida"].">\n";
			echo "<param name=tb".$contador." value=".$row["tb"].">\n";
			echo "<param name=tn".$contador." value=".$row["tn"].">\n";
			echo "<param name=ritmo".$contador." value=".$row["ritmo"].">\n";
			echo "<param name=incrementos".$contador." value=".$row["incrementos"].">\n";
			echo "<param name=b".$contador." value=".$row["loginb"].">\n";
			echo "<param name=n".$contador." value=".$row["loginn"].">\n";
			echo "<param name='t".$contador."' value='".$row["t"]."'>\n";
			if(strcmp($row["loginb"],$login)==0)
				echo "	<param name=l".$contador." value=b>\n";
			else
				echo "	<param name=l".$contador." value=n>\n";
			$contador++;
		  }while (($row = mysql_fetch_array($result5)) && $contador<=5);
		}
		
		do 
		{ 
			echo "<param name=m".$contador." value=>\n";
			echo "<param name=b".$contador." value=>\n";
			echo "<param name=n".$contador." value=>\n";
			echo "	<param name=l".$contador." value=>\n";
			$contador++;
		} while ($contador<=5);
		
		
		
		echo "<param name=i value=si";
		echo ">\n";
		
		echo "<param name=s value=";
		echo mysql_result($result2, 0, "valor");
		echo ">\n";
		echo "<param name=ps value=";
		echo mysql_result($result3, 0, "valor");
		echo ">\n";
		echo "<param name=fraseMarquesina value=\"";
                echo $fraseMarquesina;
		echo "\">\n";


		  echo "<param name=columnCount value=2>\n";
		  echo "<param name=header value='Login, ELO'>\n";
		  echo "<param name=selectionMode value=single>\n";
		  echo "<param name=columnWidth value='130, 50'>\n";
		
		
	        echo "<param name=columnStretchOn value='true'>\n";
		echo "<param name=columnAlignment value='left, left, left'>\n";
		echo "<param name=columnType value='text, number, number'>\n";
		echo "<param name=filterWildCard value=''>\n";
		echo "<param name=urltarget value='test'>\n";
		echo "<param name=urlColor value='blue'>\n";
		
                if(mysql_result($result6, 0, "colorRojoFondo")==null  || 
                   $invitado ||
                   $usaColorAleatorio)
                  echo "<param name=foreground value='".($colorAleatorioFondoRojo-50).",".($colorAleatorioFondoVerde-50).",".($colorAleatorioFondoAzul-50)."'>\n";
                else
                {		
                  echo "<param name=foreground value='";
                  echo mysql_result($result6, 0, "colorRojoFondo")-50;
                  echo ",";
                  echo mysql_result($result6, 0, "colorVerdeFondo")-50;
                  echo ",";
                  echo mysql_result($result6, 0, "colorAzulFondo")-50; 
                  echo "'>\n";                
                }
                if(mysql_result($result6, 0, "colorRojoFrente")==null || 
                   $invitado ||
                   $usaColorAleatorio)
                  echo "<param name=background value='".$colorAleatorioFrenteRojo.",".$colorAleatorioFrenteVerde.",".$colorAleatorioFrenteAzul."'>\n";
                else
                {		                
                  echo "<param name=background value='";
                  echo mysql_result($result6, 0, "colorRojoFrente");
                  echo ",";
                  echo mysql_result($result6, 0, "colorVerdeFrente");
                  echo ",";
                  echo mysql_result($result6, 0, "colorAzulFrente");                
                  echo "'>\n";  
                }
                if(mysql_result($result6, 0, "colorRojoFrente")==null || 
                   $invitado ||
                   $usaColorAleatorio)
                  echo "<param name=rowBackground value='".($colorAleatorioFrenteRojo+30).",".($colorAleatorioFrenteVerde+40).",".($colorAleatorioFrenteAzul+40)."'>\n";
                else
                {		                
                  echo "<param name=rowBackground value='";
                  echo mysql_result($result6, 0, "colorRojoFrente")+30;
                  echo ",";
                  echo mysql_result($result6, 0, "colorVerdeFrente")+40;
                  echo ",";
                  echo mysql_result($result6, 0, "colorAzulFrente")+40;                
                  echo "'>\n";  
                } 		
                if(mysql_result($result6, 0, "colorRojoFondo")==null ||
                   $invitado ||
                   $usaColorAleatorio)
                  echo "<param name=rowForeground value='".$colorAleatorioFondoRojo.",".$colorAleatorioFondoVerde.",".$colorAleatorioFondoAzul."'>\n";
                else
                {		
                  echo "<param name=rowForeground value='";
                  echo mysql_result($result6, 0, "colorRojoFondo")-50;
                  echo ",";
                  echo mysql_result($result6, 0, "colorVerdeFondo")-50;
                  echo ",";
                  echo mysql_result($result6, 0, "colorAzulFondo")-50; 
                  echo "'>\n";                
                }
                echo "<param name=gridon value='true'>\n";
		echo "<param name=gridcolor value='205, 186, 158'>\n";
		
                $contador=1;
		if (($row = mysql_fetch_array($result14)))
		{  
		  do 
		  { 
			echo "<param name='ppg".$contador."' value='".$row["palabra"]."'>\n";
			$contador++;
		  }while (($row = mysql_fetch_array($result14)));
		}
		
                $contador=1;
		if (($row = mysql_fetch_array($result15)))
		{  
		  do 
		  { 
			echo "<param name='ppl".$contador."' value='".$row["palabra"]."'>\n";
			$contador++;
		  }while (($row = mysql_fetch_array($result15)));
		}		
		
		echo "<param name='publicidade' value='".$publicidade."'>\n";
		
		echo "<param name='webPublicidade' value='".$webPublicidade."'>\n";
				
		echo "	alt=Tu navegador no soporta applets java\n";
		echo "</applet>\n";
		
		echo "</font>";

             echo "</TD>";
           echo "</TR>";

           echo "<TR>";
              echo "<TD>";
			
             echo "</TD>";
           echo "</TR>";
          echo "</TABLE>";
			

?>
</body>
</html>
