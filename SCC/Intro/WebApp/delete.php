<!DOCTYPE html>
<?php
session_start();
include("glob.html");
?>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Delete Entry</title>
    <link href="sccstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<?php
if(!isset($_SESSION['user']) || $_SESSION['user']!=1){
    header("location: index.php");
}
?>
<div class="main">
<form accept-charset="utf-8" action="" method="post" id="deleteC">
    Διαγραφή Καρκίνου:<br/>
    <select name='CANCER'>
    <?php
    include 'connection.php';
    getCancers();
    ?>
    </select>
    <input type="submit" value="Διαγραφή" name="deleteCancer"/>
</form>
    <br />
<form accept-charset="utf-8" action="" method="post" id="deleteC">
    Διαγραφή Εξέτασης / Τρόπου Πρόληψης:<br/>
    <select name='EXAMINATION'>
        <?php
        include 'connection.php';
        getExams();
        ?>
    </select>
    <input type="submit" value="Διαγραφή" name="deleteExam"/>
</form>
</div>
<?php
if(isset($_POST['CANCER']))
{
    $con = mysql_connect("localhost","stopcancercyprus","zx&w&tin");
    if(!$con)
    {
        echo '<script type="text/javascript">alert("Πρόβλημα σύνδεσης με τη βάση δεδομένων. Παρακαλώ δοκιμάστε ξανά αργότερα.");window.location.href="main.php";</script>';
    }
    else
    {
        $split = explode(".",$_POST['CANCER'],2);
        mysql_select_db("stopcancercyprus", $con);
        mysql_set_charset('utf8', $con);
        $select_query = "UPDATE CANCER SET Deleted=1 WHERE ID_cancer=".$split[0];
        $res = mysql_query($select_query, $con);
        if($res)
        {
            mysql_close($con);
            unset($_POST['CANCER']);
            echo '<script type="text/javascript">alert("Επιτυχής διαγραφή!");window.location.href="main.php";</script>';
        }
        else
        {
            mysql_close($con);
            unset($_POST['CANCER']);
            echo '<script type="text/javascript">alert("Πρόβλημα σύνδεσης με τη βάση δεδομένων. Παρακαλώ δοκιμάστε ξανά αργότερα.");window.location.href="main.php";</script>';
        }
    }
}
if(isset($_POST['EXAMINATION']))
{
    $con = mysql_connect("localhost","stopcancercyprus","zx&w&tin");
    if(!$con)
    {
        echo '<script type="text/javascript">alert("Πρόβλημα σύνδεσης με τη βάση δεδομένων. Παρακαλώ δοκιμάστε ξανά αργότερα.");window.location.href="main.php";</script>';
    }
    else
    {
        $split = explode(".",$_POST['EXAMINATION'],2);
        mysql_select_db("stopcancercyprus", $con);
        mysql_set_charset('utf8', $con);
        $select_query = "UPDATE EXAMINATION SET Deleted=1 WHERE ID_exam=".$split[0];
        $res = mysql_query($select_query, $con);
        if($res)
        {
            mysql_close($con);
            unset($_POST['EXAMINATION']);
            echo '<script type="text/javascript">alert("Επιτυχής διαγραφή!");window.location.href="main.php";</script>';
        }
        else
        {
            mysql_close($con);
            unset($_POST['EXAMINATION']);
            echo '<script type="text/javascript">alert("Πρόβλημα σύνδεσης με τη βάση δεδομένων. Παρακαλώ δοκιμάστε ξανά αργότερα.");window.location.href="main.php";</script>';
        }
    }
}
?>
</body>
</html>