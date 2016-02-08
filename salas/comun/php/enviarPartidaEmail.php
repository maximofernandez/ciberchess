<?php

require("class.phpmailer.php");

$mail = new PHPMailer();

$mail->IsSendmail();
$mail->From     = "list@mydomain.com";
$mail->FromName = "List manager";
//$mail->Host     = "smtp1.site.com;smtp2.site.com";
//$mail->Mailer   = "smtp";

/*
@MYSQL_CONNECT("localhost","root","password");
@mysql_select_db("my_company");
$query  = "SELECT full_name, email, photo FROM employee WHERE id=$id";
$result = @MYSQL_QUERY($query);

while ($row = mysql_fetch_array ($result))
{
*/
    // HTML body
 //   $body  = "Hello <font size=\"4\">" . $row["full_name"] . "</font>, <p>";
  //  $body .= "<i>Your</i> personal photograph to this message.<p>";
  //  $body .= "Sincerely, <br>";
  //  $body .= "PHPMailer List manager";

    // Plain text body (for mail clients that cannot read HTML)
  //  $text_body  = "Hello " . $row["full_name"] . ", \n\n";
  //  $text_body .= "Your personal photograph to this message.\n\n";
  //  $text_body .= "Sincerely, \n";
 //   $text_body .= "PHPMailer List manager";

    $mail->Body    = "textooo";
    $mail->AltBody = "textooo";
    $mail->AddAddress("maximofernandez@terra.es", "maximofernandez@terra.es");
    $mail->AddStringAttachment("adjuntooooooo", "YourPhoto.jpg");

    if(!$mail->Send())
        echo "There has been a mail error sending to " . $row["email"] . "<br>";

    // Clear all addresses and attachments for next loop
    $mail->ClearAddresses();
    $mail->ClearAttachments();
//}

?>