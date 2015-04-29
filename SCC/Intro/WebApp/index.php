<!DOCTYPE html>
<?php
session_start();
?>
<html>

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<title>Login</title>
<link rel="stylesheet" type="text/css" href="sccstyle.css"/>
</head>

<body>
<?php
if(isset($_SESSION['user'])){
    if($_SESSION['user']==1){
        session_unset();
    }
    else if($_SESSION['user']==0){
        echo "<script type='text/javascript'>alert('Έχετε εισάγει λανθασμένα στοιχεία. Παρακαλώ ξαναδοκιμάστε.');</script>";
    }
}
?>

<img alt="background image" src="images/bground.jpg" id="full-screen-background-image"/>
<div id="wrapper">
<form action="userAuthNew.php" method="post">
	<p>Εισάγετε τα στοιχεία σας για να συνεχίσετε:</p>
	<p>Username:  <input name="user" type="text" /></p>
	<p>Password:  <input name="pass" type="password" /></p>
	<input name="submitLogin" type="submit" value="Υποβολή" /></form>
</div>
</body>

</html>
