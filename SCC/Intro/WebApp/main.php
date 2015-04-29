<!DOCTYPE html>
<?php
session_start();
include("glob.html");
?>
<html>

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<title>Main</title>
<link href="sccstyle.css" rel="stylesheet" type="text/css" />
</head>

<body>
<?php
if(!isset($_SESSION['user']) || $_SESSION['user']!=1){
	header("location: index.php");
}
?>
    <div class="main">
    <p>Καλωσορίσατε στην σελίδα διαχείρισης της εφαρμογής StopCancerCyprus!</p>
    <p>Χρησιμοποιήστε το μενού στα αριστερά σας για να διαχειριστείτε τη βάση δεδομένων της εφαρμογής.</p>
    </div>

</body>

</html>
