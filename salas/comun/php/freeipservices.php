<?php

class freeipservices_class
{
	var $error="";
	var $timeout=0;
	var $http_user_agent="";

	Function GetIpLocation($ip,&$location)
	{
		$location=array();
		if(!strcmp($ip,"127.0.0.1"))
		{
			$this->error="$ip is not a valid public Internet address!";
			return(0);
		}
		$domain="freeipservices.com";
		if(!($connection=($this->timeout ? @fsockopen($domain,80,$errno,$error,$this->timeout) : @fsockopen($domain,80))))
		{
			switch($error=($this->timeout ? strval($errno) : "??"))
			{
				case "-3":
					$this->error="-3 socket could not be created";
					break;
				case "-4":
					$this->error="-4 dns lookup on hostname \"".$host_name."\" failed";
					break;
				case "-5":
					$this->error="-5 connection refused or timed out";
					break;
				case "-6":
					$this->error="-6 fdopen() call failed";
					break;
				case "-7":
					$this->error="-7 setvbuf() call failed";
					break;
				default:
					$this->error=$error." could not connect to the host \"".$domain."\"";
					break;
			}
			return(0);
		}
		if($this->timeout
		&& (function_exists($function="stream_set_timeout")
		|| function_exists($function="socket_set_timeout")
		|| function_exists($function="set_socket_timeout")))
			$function($connection,$this->timeout);
		$uri="/ip2geo?url=".UrlEncode($ip);
		if(!fputs($connection,"GET $uri HTTP/1.0\r\n"
			."Host: $domain\r\n"
			."User-Agent: ".$this->http_user_agent."\r\n"
			."\r\n"))
		{
			$this->error="could not retrieve the freeipservices site request response";
			fclose($connection);
			return(0);
		}
		
		$entrada="";
		while (!feof($connection)) 
		{
                   $entrada= $entrada . fgets($connection, 1024);
                }
                    		
		fclose($connection);
		
		$entrada = str_replace(" ","",$entrada);
		
		if($posicion1=strpos($entrada,"functiongetLat(){return'"))
		{
		   $posicion1+=24;
		   $posicion2=strpos($entrada,"';}",$posicion1);
		   $location['LAT']=substr($entrada,$posicion1,$posicion2-$posicion1);
		}
		else
		  return(0);
		  
		if($posicion1=strpos($entrada,"functiongetLng(){return'"))
		{
		   $posicion1+=24;
		   $posicion2=strpos($entrada,"';}",$posicion1);
		   $location['LONG']=substr($entrada,$posicion1,$posicion2-$posicion1);
		}
		else
		  return(0);	
		  
		if($posicion1=strpos($entrada,"functiongetCity(){return'"))
		{
		   $posicion1+=25;
		   $posicion2=strpos($entrada,"';}",$posicion1);
		   $location['CITY']=substr($entrada,$posicion1,$posicion2-$posicion1);
		}
		else
		  $location['CITY']="";	
		  
		if($posicion1=strpos($entrada,"functiongetState(){return'"))
		{
		   $posicion1+=26;
		   $posicion2=strpos($entrada,"';}",$posicion1);
		   $location['STATE']=substr($entrada,$posicion1,$posicion2-$posicion1);
		}
		else
		  $location['STATE']="";	
		  
		if($posicion1=strpos($entrada,"functiongetCountry(){return'"))
		{
		   $posicion1+=28;
		   $posicion2=strpos($entrada,"';}",$posicion1);
		   $location['COUNTRY']=substr($entrada,$posicion1,$posicion2-$posicion1);
		}
		else
		  $location['COUNTRY']="";		  		  	  	  
		
		return(1);
	}

};

?>