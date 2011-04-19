<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danke für die Rückmeldung!</title>
    </head>
    <body>
        <?php
			include './Config.php';

			// Check if we have everything we need
			if(isset($_GET['id']) && isset($_GET['date']) && isset($_GET['status'])) {
				// Establish connection with the database.
				$db = mysql_connect($db_server, $db_username, $db_password, true) or die("Connection to database failed...you made a type, did you?");
				mysql_select_db($db_database, $db);

				$id = mysql_real_escape_string($_GET['id'], $db);
				$date = mysql_real_escape_string($_GET['date'], $db);
				$status = mysql_real_escape_string($_GET['status'], $db);

				mysql_query("UPDATE `mail_confirmations` SET `status` = '$status' WHERE `memberId` = '$id' AND `date` = '$date' LIMIT 1;", $db) or die (mysql_error());

				if($status == 1) { ?>
					Anmeldung best&auml;tigt.
				<?php } else { ?>
					Schade eigentlich...
				<?php }

				// Close the connection
				mysql_close($db);
			} else { ?>
				&quot;How do you know I'm mad?&quot; - &quot;You must be, or you wouldn't have come here.&quot;
			<?php }
        ?>
    </body>
</html>
