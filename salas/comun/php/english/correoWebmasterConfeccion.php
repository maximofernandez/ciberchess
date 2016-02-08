<html>

<head>
<TITLE>CiberChess - Play Chess Online</TITLE>
</head>

<body bgcolor='black' text='#EBDAB8' lang=ES '>



<TABLE align=center border=0>
  <TR>
	<TD>
        <img src='../../../<?php echo $sala; ?>/imagenes/LogoHtml.jpg'>
	</TD>

   </TR>
</TABLE>


<form method="POST" action="../correo.php">
  

<TABLE align='center' border='0'>
  <TR>
    <TD>
    </TD>
    <TD>
       Send an e-mail to administrator 

       <br>
    </TD>
  </TR>
  <TR>
    <TD>
      Home Address: 
    </TD>
    <TD>
      <input name='idioma' type=hidden value='english'>
      <input type="text" name="origen" value="

<?php

        include '../ConexionBD.php';

        $sql = "SELECT email";
        $sql = $sql." FROM claves";
        $sql = $sql." WHERE login = '$login'";
  	  $result = mysql_query($sql, $link);
	  $miMail = mysql_result($result, 0, "email");

        echo $miMail;
?>

" size="32">
    </TD>
  </TR>
  <TR>
    <TD>
      Destination Address:
    </TD>
    <TD>
      <font color="#EBDAB8">ADMINISTRATOR </font>
      
      <?php
        echo "<input type=\"hidden\" name=\"destino\" value=\"";
        include '../ConexionBD.php';    

	$result2 = mysql_query("SELECT *
	                       FROM configuracion 
	                       WHERE concepto = 'correoAdministrador'", $link);
	                       
	echo mysql_result($result2, 0, "valor");     
	echo "\" size=\"53\">";                            
      ?>
      
    </TD>
  </TR>
  <TR>
    <TD>
      Subject:
    </TD>
    <TD>
      <input type="text" name="asunto" value="" size="32">
    </TD>
  </TR>
  <TR>
    <TD>
      Content:
    </TD>
    <TD>
      <textarea rows="7" name="contenido" cols="31"></textarea>
    </TD>
  </TR>  
  <TR>
    <TD>
    </TD>
    <TD align='center'>
      <br>
      <input type="submit" value="Send" name="B1">
    </TD>
  </TR>   
</TABLE>

</form>

</body>

</html>





















<html>

<head>
<TITLE>CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess - CiberChess</TITLE>
</head>

<body bgcolor=black lang=ES link=blue vlink=purple style='tab-interval:35.4pt'>

<div class=Section1>

<TABLE align=center border=0>
  <TR>
	<TD>
	</TD>
	<TD>
        <img src='../../<?php echo $sala; ?>/imagenes/LogoHtml.jpg'>
	</TD>

   </TR>
</TABLE>


<p align="center">

<font color="#EBDAB8">
Envía un correo al administrador
</font>

<form method="POST" action="correoHTML.php">
  

<TABLE align=center border=0>
  <TR>
    <TD>
      <font color="#EBDAB8">Dirección origen: </font>
    </TD>
    <TD>
      <input type="text" name="origen" value="

<?php

        include 'ConexionBD.php';

        $sql = "SELECT email";
        $sql = $sql . " FROM claves";
        $sql = $sql . " WHERE login = '$login'";

  	$result = mysql_query($sql, $link);
	$miMail = mysql_result($result, 0, "email");

        echo $miMail;
?>

" size="53">
    </TD>
  </TR>
  <TR>
    <TD>
      <font color="#EBDAB8">Dirección destino: </font>
    </TD>
    <TD>
      <font color="#EBDAB8">ADMINISTRADOR </font>
      
      <?php
        echo "<input type=\"hidden\" name=\"destino\" value=\"";
        include 'ConexionBD.php';    

	$result2 = mysql_query("SELECT *
	                       FROM configuracion 
	                       WHERE concepto = 'correoAdministrador'", $link);
	                       
	echo mysql_result($result2, 0, "valor");     
	echo "\" size=\"53\">";                            
      ?>
      
    </TD>
  </TR>
  <TR>
    <TD>
      <font color="#EBDAB8">Asunto: </font>
    </TD>
    <TD>
      <input type="text" name="asunto" size="53">
    </TD>
  </TR>
</TABLE>
<br>
<textarea rows="7" name="contenido" cols="54"></textarea>
<br>
<br>
<input type="submit" value="Enviar" name="B1"></p>

</form>
</p>


</div>

</body>

</html>
