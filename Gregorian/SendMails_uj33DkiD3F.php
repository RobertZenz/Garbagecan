<!--
	If you ever wonder why the file has this odd name,
	it's because I'm too lazy to write a login/password
	protection system.

	On second thought, put this into a folder which
	is protected by access restrictions.
-->

<?php
include './Config.php';

// Establish connection with the database.
$db = mysql_connect($db_server, $db_username, $db_password, true) or die("Connection to database failed...you made a type, did you?");
mysql_select_db($db_database, $db);
?>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Super Awesome E-Mail Tool</title>
    </head>
    <body>

	<?php
		// Check if we should send mails or
		// if we'll just show everything.
		if (isset($_POST['text'])) {
			// send

			$date = mysql_real_escape_string($_POST['date'], $db);
			$text = $_POST['text'];

			// Okay, I admit it, this is some odd voodoo...
			$maxId = mysql_query("SELECT MAX(`id`) AS 'id' FROM `mail_members`;", $db) or die (mysql_error());
			$maxId = mysql_fetch_array($maxId);
			$maxId = $maxId['id'];

			$counter = 0;

			// We'll check every possible Id if we should sent an e-mail to it.
			for ($i = 1; $i <= $maxId; $i++) {
				$guestId = $_POST['guest_' . $i];

				if(isset($guestId) && $guestId) {
					// Allocate a record for the user.
					// Users which don't receive an e-mail can't be invited...because they suck...that's why...
					mysql_query("INSERT INTO `mail_confirmations` (`date`, `memberId`) VALUES ('$date', '$i');") or die (mysql_error());

					// More voodoo...
					$recipient = mysql_query("SELECT `email` FROM `mail_members` WHERE `id` = '$i' LIMIT 1;", $db) or die (mysql_error());
					$recipient = mysql_fetch_array($recipient);
					$recipient = $recipient['email'];

					// Prepare the link...
					$link = "Confirmation.php?id=$i&date=$date&status=";
					
					// Even more voodoo...nah, just kidding, this is just nested...
					mail($recipient, $mail_subject, str_replace("{yes}", $link . "1", str_replace("{no}", $link . "2", $text)), $mail_headers);

					$counter++;
				}
			}

			?><h1><?php echo $counter; ?> E-Mail(s) verschickt.</h1><?php
		} else {
			// Show the controls!

			?>
				<form name="sendMail" action="SendMails_nxH85Fa7L3.php" method="POST">
				<h2>Liste</h2>
			<?

			$result = mysql_query("SELECT `id`, `name`, `email` FROM `mail_members` ORDER BY `name` ASC;", $db) or die (mysql_error());
			while($row = mysql_fetch_array($result)) { ?>
				<label for="guest_<?php echo $row['id']; ?>"><input type="checkbox" name="guest_<?php echo $row['id']; ?>"><?php echo $row['name']; ?> (<?php echo $row['email']; ?>)</label><br>
			<?php }
			
			?>
				<h2>Datum (Format: yyyy-mm-dd)</h2>
				<input type="text" name="date" value="1970-01-01">
				
				<h2>Text ({yes}/{no} als Platzhalter)</h2>
				<textarea name="text" cols="80" rows="25">Hier sollte eigentlich der E-Mail-Text sein...aber egal, wer braucht sowas schon?

Bitte hier klicken wenn du dich Anmelden willst: http://www.deinedomaine.at/{yes}

Bitte hier klicken wenn du nicht dabei sein kannst: http://www.deinedomaine.at/{no}

{yes}/{no} with später durch sowas ähnliches wie Confirmation.php?id=0&date=2011-01-01&status=0 ersetzt.</textarea>
				<input type="submit" value="Senden">
			<?php
			?>
				</form>
			<?php
		}
	?>
    </body>
</html>


<?php

// Ruin our new shiny connection
mysql_close($db);

?>