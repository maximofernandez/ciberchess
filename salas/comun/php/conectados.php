
<?php


	include 'ConexionBD.php';
	
        $sql = "UPDATE configuracion";
        $sql = $sql . " SET valor = '$conectados'";
        $sql = $sql . " WHERE concepto = 'ciberchess".$portoTCP."'";
        $result = mysql_query($sql, $link);
        
        $sql = "UPDATE configuracion";
        $sql = $sql . " SET valor = '$conectadosNoVIP'";
        $sql = $sql . " WHERE concepto = 'ciberchessNoVIP".$portoTCP."'";
        $result = mysql_query($sql, $link);        

        echo "MAXIMO";
	
	
?>
