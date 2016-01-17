<html>
  <head>
    <title>EIS Parkplatz-App</title>
  </head>
  <body>
    <?php
    
      mysql_connect("localhost", "eis", "Schinkenwurst");
    
			mysql_select_db("eis");
			mysql_query("SET NAMES 'utf8'");
				
			$query = "SELECT COUNT(*) FROM `user` WHERE `email` = \"" . $_POST["ben"] . "\"  && `password` = \"" . $_POST["pw"] . "\";";
      
      $res = mysql_query($query) or exit(mysql_error());
        
			$dsatz = mysql_fetch_array($res);
          
			$erg = mysql_result($res,0);
    
			if ($erg == 1){
      
        $pw = $_POST["pw"];
      
        if($_GET["mode"] == "changeUserGroup"){
        
          $query = "UPDATE `user` SET `usergroup`= \"" . $_POST["usergroup"] . "\" WHERE `email` = \"" . $_POST["ben"] . "\";";
              
          mysql_query($query) or exit(mysql_error());
              
         echo "Usergruppe erfolgreich ge&auml;ndert";
        
        }
					
        if($_GET["mode"] == "changePW"){
        
          if ($_POST["pw"] == $_POST["pwOld"]) {
          
            if ($_POST["pwNew1"] == $_POST["pwNew2"]) {
            
              $query = "UPDATE `user` SET `password`= \"" . $_POST["pwNew1"] . "\" WHERE `email` = \"" . $_POST["ben"] . "\";";
              
              mysql_query($query) or exit(mysql_error());
              
              $pw = $_POST["pwNew1"];
              
              echo "Passwort erfolgreich ge&auml;ndert";
            
            } else {
              echo "Die neuen Passw&ouml;rter stimmen nicht &uuml;berein";
            }
          
          } else {
            echo "Die alte Passwort ist nicht korreckt";
          }
        
        }
					
        echo "<p>Login erfolgreich</p>";
        echo "<h3>Usergruppe &auml;ndern</h3>";
        echo "<p><form action=\"intern.php?mode=changeUserGroup\" method=\"POST\"><input hidden=\"hidden\" name=\"ben\" value=\"" . $_POST["ben"] . "\"><input name=\"pw\" value=\"" . $pw . "\" hidden><select name=\"usergroup\"><option value=\"1\">Professor</option><option value=\"2\">Mitarbeiter</option><option value=\"3\">Student</option><option value=\"4\">Gast</option></select><input type=\"submit\"></form></p>";
        echo "<h3>Passwort &auml;ndern</h3>";
        echo "<p><form action=\"intern.php?mode=changePW\" method=\"POST\"><input hidden=\"hidden\" name=\"ben\" value=\"" . $_POST["ben"] . "\"><input name=\"pw\" value=\"" . $pw . "\" hidden><table><tr><td>Altes Passwort</td><td><input name=\"pwOld\" type=\"password\" ></td></tr><tr><td>Neues Passwort</td><td><input name=\"pwNew1\" type=\"password\" ></td></tr><tr><td>Neues Passwort wiederholen</td><td><input name=\"pwNew2\" type=\"password\" ></td></tr></table><input type=\"submit\"></form></p>";
          
      } else { 
      
        echo "<p>Login fehlgeschlagen <a href=\"login.html\">Erneut versuchen</a></p>";
        
      }
					
    
    ?>
  </body>
</html>