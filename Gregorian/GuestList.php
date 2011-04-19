<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <?php
			include './Config.php';

			// Establish connection with the database.
			$db = mysql_connect($db_server, $db_username, $db_password, true) or die("Connection to database failed...you made a type, did you?");
			mysql_select_db($db_database, $db);

			// Get the list
			$lastDate = '1970-01-01';
			$result = mysql_query("SELECT m.`name` AS 'name', c.`status` AS 'status', c.`date` AS 'date' FROM `mail_confirmations` AS c LEFT JOIN `mail_members` AS m ON c.`memberId` = m.`id` ORDER BY c.`date` DESC, m.`name` ASC;");
			while($row = mysql_fetch_array($result)) {
				if($lastDate != $row['date']) {
					$lastDate = $row['date'];
					echo "<h2>$lastDate</h2>";
				}

				switch ($row['status']) {
					case 0:
						echo "<font color=\"#000066\">";
						break;

					case 1:
						echo "<font color=\"#006600\">";
						break;

					case 2:
						echo "<font color=\"#660000\">";
						break;
				}
				echo $row['name'] . "</font><br>";
			}
			
			// Close connection
			mysql_close($db);
		?>

    </body>
</html>
