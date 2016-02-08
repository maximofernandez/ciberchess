
<html>

<head>
<TITLE>CiberChess - Jugar Ajedrez Online</TITLE>
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
       Envía un correo a un amigo para que venga a jugar

       <br>
    </TD>
  </TR>
  <TR>
    <TD>
      Dirección origen:
    </TD>
    <TD>
      <input name='idioma' type='hidden' value='spanish'>
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
      Dirección destino:
    </TD>
    <TD>
      <input type="text" name="destino" size="32">
    </TD>
  </TR>
  <TR>
    <TD>
      Asunto:
    </TD>
    <TD>
      <input type="text" name="asunto" value="Ajedrez online" size="32">
    </TD>
  </TR>
  <TR>
    <TD>
      Contenido:
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
      <input type="submit" value="Enviar" name="B1">
    </TD>
  </TR>   
</TABLE>

</form>

</body>

</html>
