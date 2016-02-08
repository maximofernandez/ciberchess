<?php
class Chart {
    var $image; //identicador de la imagen
    var $title = ''; //t�tulo del diagrama
    var $width = 400; //ancho de la imagen resultante
    var $height = 100; //altura de la imagen resultante
    var $margin_x = 10; //margen horizontal
    var $margin_y = 0; //margen vertical
    var $bar_w = 1.0;    //relaci�n del ancho de la columna a
                        //la distancia entre ellas
    var $bar_color = Array(235, 218, 184); //color de las columnas
    var $text_color = Array(235, 218, 184); //color del texto
    var $grid_color = Array(16, 16, 16); //color de la cuadr�cula

    var $_x_min; //valor m�nimo de x del espacio para el diagrama
    var $_x_max; //valor m�ximo de x del espacio para el diagrama
    var $_y_min; //valor m�nimo de y del espacio para el diagrama
    var $_y_max; //valor m�ximo de y del espacio para el diagrama
    var $_range_w; //ancho del espacio para el diagrama
    var $_range_h; //altura del espacio para el diagrama

    /**
     * M�todo draw � genera la imagen
     * como argumento recibe la tabla de valores
     */
    function draw($series) {
        $this->width = count($series)*8;
        if($this->width < 50)
          $this->width=50;
        if($this->width > 250)
          $this->width=250;          
        //generamos la imagen cuya altura y ancho son definidos
        $this->image = imagecreate($this->width, $this->height);
        //asignamos el color de fondo
        $this->translateColor(Array(16, 16, 16));
        //definimos el espacio para la imagen
        $this->calcRange($series);
        //dibujamos la cuadr�cula
        $this->drawGrid();
        //colocamos los valores consecutivos en el diagrama
        foreach($series as $x=>$y) {
            $this->setValue($x, $y);
        }
        //colocamos el t�tulo del diagrama
        imagestring($this->image, 5, $this->margin_x/2, $this->margin_y/2,
          $this->title, $this->translateColor($this->text_color));
        header("Content-type: image/png");
        imagepng($this->image);
        imagedestroy($this->image);
        exit;
    }

    /**
     * M�todo drawGrid � dibuja la cuadr�cula sobre la imagen
     * su par�metro puede ser la distancia entre las l�neas consecutivas
     */
    function drawGrid($size = 20) {
        $y = $size*round($this->_y_min/$size) - $size;
        while($y < ($this->_y_max + $size)) {
            //calculamos los valores consecutivos de y
            $y0 = $this->translateCoordY($y);
            //dibujamos las l�neas horizontales
            imageline($this->image, 0, $y0, $this->width, $y0,
              $this->translateColor($this->grid_color));
            $y += $size;
        }
    }

    /**
     * M�todo setValue � dibuja un rect�ngulo con base en los par�metros recibidos
     * los par�metros son los valores  x y y
     */
    function setValue($x, $y) {
        //definimos todas las coordenadas del rect�ngulo de ancho de bar_w
        $p = Array(
            ($x-$this->bar_w/2), $y,
            ($x+$this->bar_w/2), $y,
            ($x+$this->bar_w/2), 0,
            ($x-$this->bar_w/2), 0
        );
        //Las convertimos a coordenadas de la imagen
        $r = $this->translatePoly($p);
        //dibujamos el rect�ngulo
        $r[2]-=2; // retocado M�XIMO
        $r[4]-=2;
        imagefilledpolygon($this->image, $r, sizeof($r)/2,
          $this->translateColor($this->bar_color));
    }

    /**
     * M�todo calcRange � define el espacio para la gr�fica
     * su par�metro es la tabla de valores
     */
    function calcRange($series) {
        //comenzamos en el inicio de las coordenadas polares
        $this->_x_min = 0; $this->_y_min = 0;
        $this->_x_max = 1; $this->_y_max = 1;
        foreach($series as $x=>$y) {
            //cambiamos el rango dependiendo del valor
            if($x >= $this->_x_max) $this->_x_max = $x;
            if($x < $this->_x_min) $this->_x_min = $x;
            if($y >= $this->_y_max) $this->_y_max = $y;
            if($y < $this->_y_min) $this->_y_min = $y;
        }
        //definimos el ancho y la altura del diagrama
        $this->_range_w = $this->_x_max-$this->_x_min;
        $this->_range_h = $this->_y_max-$this->_y_min;
    }

    /**
     * M�todo translatePoly � proyecta los puntos del espacio del diagrama
     * al area de la imagen su par�metro es la tabla con los puntos x, y
     * consecutivos
     */
    function translatePoly($p) {
        $r = Array();
        for($i=0; $i<sizeof($p); $i+=2) {
            //convertimos los puntos consecutivamente
            $r[] = $this->translateCoordX($p[$i]);
            $r[] = $this->translateCoordY($p[$i+1]);
        }
        return $r;
    }

    /**
     * M�todos translateCoordX y translateCoordY � transforman las coordenadas
     * del diagrama su par�metro es el valor x y y correspondientemente
     * el m�todo devuelve el valor de la coordenada para el �rea de la imagen
     */
    function translateCoordX($x) {
        return round($this->margin_x+($this->width-2*$this->margin_x)*
          ($x-$this->_x_min)/$this->_range_w);
    }
    function translateCoordY($y) {
        return round($this->margin_y-($this->height-2*$this->margin_y)*
          ($y-$this->_y_max)/$this->_range_h);
    }

    /**
     * M�todo translateColor � permite obtener el identificador del color
     * su par�metro es la tabla de colores RGB
     * el m�todo devuelve el identificador del color
     */
    function translateColor($rgb = Array(255, 255, 255)) {
        return imagecolorallocate($this->image, $rgb[0], $rgb[1], $rgb[2]);
    }

}
?>