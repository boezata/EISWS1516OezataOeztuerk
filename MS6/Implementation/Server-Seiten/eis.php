<html>
	<head>
		<title>
			TH-Parkplatz-App
		</title>
	</head>
	<body>
		<h1 align="center">TH-Parkplatz-App</h1>
    <div align="center">
		  <?php
		
	  		mysql_connect("localhost", "eis", "Schinkenwurst");
    
  			mysql_select_db("eis");
			  mysql_query("SET NAMES 'utf8'");
				
		  	if($_GET["mode"] == "reg") {
					
	  			echo "<h2 align=\"center\">Registrierung abschlie&szlig;en</h2>";
				
  				mysql_connect("localhost", "eis", "Schinkenwurst");
    
				  mysql_select_db("eis");
			  	mysql_query("SET NAMES 'utf8'");
				
		  		$query = "SELECT COUNT(*) FROM `user` WHERE `email` = \"" . $_GET["mail"] . "\";";
				
	  			$res = mysql_query($query) or exit(mysql_error());
        
  				$dsatz = mysql_fetch_array($res);
          
				  $erg = mysql_result($res,0);
    
			  	if ($erg == 1){
					
		  			$query = "SELECT COUNT(*) FROM `user` WHERE `email` = \"" . $_GET["mail"] . "\" && `secCode` = \"" . $_GET["secCode"] . "\";";
				
	  				$res = mysql_query($query) or exit(mysql_error());
        
  					$dsatz = mysql_fetch_array($res);
          
					  $erg = mysql_result($res,0);
    
				  	if ($erg == 1){
						
			  			$query = "UPDATE `user` SET `mailChecked`=\"J\" WHERE `email` = \"" . $_GET["mail"] . "\";";
						
		  				mysql_query($query) or exit(mysql_error());
					
	  					echo "<p align=\"center\">Nutzer wurde aktiviert</p>";
						
  					} else {    
						  echo "<p align=\"center\"><b>FEHLER:</b> SecCode ist fehlerhaft.</p>";
					  }
					
				  } else {    
			  		echo "<p align=\"center\"><b>FEHLER:</b> Der Nutzer ist nicht vorhanden</p>";
		  		}
					
	  		}
      
        if($_GET["mode"] == "fpw1") {
      
          $query = "SELECT COUNT(*) FROM `user` WHERE `email` = \"" . $_GET["mail"] . "\" && `secCode` = \"" . $_GET["secCode"] . "\";";
				
			  	$res = mysql_query($query) or exit(mysql_error());
        
		  		$dsatz = mysql_fetch_array($res);
          
	  			$erg = mysql_result($res,0);
    
  				if ($erg == 1){
          
            echo "<p><form method=\"POST\" action=\"eis.php?mode=fpw2&mail=" . $_GET["mail"] . "&secCode=" . $_GET["secCode"] . "\"><table><tr><td>Neues Passwort:</td><td><input type=\"password\" name=\"newPw1\"></td></tr><tr><td>Neues Passwort wiederholen:</td><td><input type=\"password\" name=\"newPw2\"></td></tr><tr><td><input type=\"reset\"></td><td><input type=\"submit\"></td></tr></table></form></p>";
          
          } else {
            echo "<p align=\"center\"><b>FEHLER:</b> Der Nutzer ist nicht vorhanden</p>";
          }
      
        }
      
        if($_GET["mode"] == "fpw2") {
      
          $query = "SELECT COUNT(*) FROM `user` WHERE `email` = \"" . $_GET["mail"] . "\" && `secCode` = \"" . $_GET["secCode"] . "\";";
				
			  	$res = mysql_query($query) or exit(mysql_error());
        
		  		$dsatz = mysql_fetch_array($res);
          
	  			$erg = mysql_result($res,0);
    
  				if ($erg == 1){
        
            $query = "UPDATE `user` SET `password`= \"" . $_POST["newPw1"] . "\" WHERE `email` = \"" . $_GET["mail"] . "\";";
				
  			  	mysql_query($query) or exit(mysql_error());
          
            echo "<p>Passwort wurde erfolgreich ge&auml;ndert</p>";
          
          }
        
        }
		
  		?>
    </div>
	</body>
</html>
