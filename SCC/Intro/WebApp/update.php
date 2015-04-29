<!DOCTYPE html>
<?php
session_start();
include("glob.html");
?>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update Entry</title>
    <link href="sccstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<?php
if(!isset($_SESSION['user']) || $_SESSION['user']!=1){
    header("location: index.php");
}
?>
<div class="main">
    <form accept-charset="utf-8" action="" method="post" id="updateC">
        Ενημέρωση Καρκίνου:<br/>
        <select id="updateCancer" name="selectC">
            <?php
            include 'connection.php';
            getCancers();
            ?>
        </select>
        <input type="submit" value="Ενημέρωση" name="updateCancer"/>
    </form>
    <br />
    <form accept-charset="utf-8" action="" method="post" id="updateE">
        Ενημέρωση Εξέτασης / Τρόπου Πρόληψης:<br/>
        <select id="updateExam" name="selectE">
            <?php
            include 'connection.php';
            getExams();
            ?>
        </select>
        <input type="submit" value="Ενημέρωση" name="updateExam"/>
    </form>
</div>
<?php
if(isset($_POST['updateCancer']))
{
    $con = mysql_connect("localhost","stopcancercyprus","zx&w&tin");
    if(!$con)
    {
        echo '<script type="text/javascript">alert("Πρόβλημα σύνδεσης με τη βάση δεδομένων. Παρακαλώ δοκιμάστε ξανά αργότερα.");window.location.href="main.php";</script>';
    }
    else
    {
        mysql_select_db("stopcancercyprus", $con);
        mysql_set_charset('utf8', $con);
        $select_query = "SELECT * FROM CANCER WHERE ID_cancer=".$_POST['selectC']." AND Deleted=0";
        $res = mysql_query($select_query, $con);
        if($res)
        {
            $row = mysql_fetch_row($res);
            $_SESSION['cUpdate']=1;
            $_SESSION['cID']=$row[0];
            $_SESSION['cName']=$row[1];
            $_SESSION['cDescr']=$row[2];
            mysql_close($con);
            header("location:insertCancer.php");

        }
        else
        {
            mysql_close($con);
            unset($_POST['updateCancer']);
            echo '<script type="text/javascript">alert("Πρόβλημα σύνδεσης με τη βάση δεδομένων. Παρακαλώ δοκιμάστε ξανά αργότερα.");window.location.href="main.php";</script>';
        }
    }
}

if(isset($_POST['updateExam']))
{
    $con = mysql_connect("localhost","stopcancercyprus","zx&w&tin");
    if(!$con)
    {
        echo '<script type="text/javascript">alert("Πρόβλημα σύνδεσης με τη βάση δεδομένων. Παρακαλώ δοκιμάστε ξανά αργότερα.");window.location.href="main.php";</script>';
    }
    else
    {
        mysql_select_db("stopcancercyprus", $con);
        mysql_set_charset('utf8', $con);
        $select_query = "SELECT * FROM EXAMINATION WHERE ID_exam=".$_POST['selectE']." AND Deleted=0";
        $res = mysql_query($select_query, $con);
        if($res)
        {
            $row = mysql_fetch_row($res);
            $_SESSION['eUpdate']=1;
            $_SESSION['eID']=$row[0];
            $_SESSION['eName']=$row[1];
            $_SESSION['eDescr']=$row[2];
            $_SESSION['eFreq']=$row[3];
            $_SESSION['eGender']=$row[10];
            $_SESSION['eSmoker']=$row[7];
            $_SESSION['eAlcohol']=$row[9];
            $_SESSION['eHistory']=$row[8];
            $_SESSION['eExam']=$row[12];
            $_SESSION['eSex']=$row[4];
            $agedb=explode("-",$row[5]);
            if($agedb[0]==0)
                $_SESSION['eAgeFrom']="";
            else
                $_SESSION['eAgeFrom']=$agedb[0];
            if($agedb[1]==200)
                $_SESSION['eAgeTo']="";
            else
                $_SESSION['eAgeTo']=$agedb[1];
            $dmsdb=explode("-",$row[6]);
            if($dmsdb[0]==0)
                $_SESSION['eDMSFrom']="";
            else
                $_SESSION['eDMSFrom']=$dmsdb[0];
            if($dmsdb[1]==100)
                $_SESSION['eDMSTo']="";
            else
                $_SESSION['eDMSTo']=$dmsdb[1];
            mysql_close($con);
            unset($_POST['updateExam']);
            header("location:insertExam.php");
        }
        else
        {
            mysql_close($con);
            unset($_POST['updateExam']);
            echo '<script type="text/javascript">alert("Πρόβλημα σύνδεσης με τη βάση δεδομένων. Παρακαλώ δοκιμάστε ξανά αργότερα.");window.location.href="main.php";</script>';
        }
    }
}
?>
</body>
</html>