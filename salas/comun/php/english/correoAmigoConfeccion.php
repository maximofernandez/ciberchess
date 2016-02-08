
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
       Send an email to a friend to come to play

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
      <input type="text" name="destino" size="32">
    </TD>
  </TR>
  <TR>
    <TD>
      Subject: 
    </TD>
    <TD>
      <input type="text" name="asunto" value="Chess online" size="32">
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
