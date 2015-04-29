<!DOCTYPE html>
<?php
session_start();
include("glob.html");
?>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert - Cancer</title>
    <link href="sccstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<?php
if(!isset($_SESSION['user']) || $_SESSION['user']!=1){
    header("location: index.php");
}
?>
<div class="main">
    <form accept-charset="utf-8" action="" method="post" id="insertC">
        <p>Όνομα Καρκίνου:  <input name="cName" type="text" value="<?php newtext('cName')?>"/></p>
        <p>Περιγραφή: </p>
        <p><textarea cols="40" rows="5" name="cDescr" wrap="virtual"><?php newtext('cDescr')?></textarea></p>
        <input type="button" onclick="clearForm()" value="Καθαρισμός">
        <input name="submitCancer" type="submit" value="Αποθήκευση"  />
    </form>
</div>
<script>
    function clearForm(){
        document.getElementById("insertC").reset();
    }
</script>
<?php
if(isset($_POST['cName']) && isset($_POST['cDescr']) && !isset($_SESSION['cUpdate']))
{
    if($_POST['cName']!="" && $_POST['cDescr']!=""){
        $con = mysql_connect("localhost","stopcancercyprus","zx&w&tin");
        if(!$con)
        {
            echo '<script type="text/javascript">alert("Πρόβλημα σύνδεσης με τη βάση δεδομένων. Παρακαλώ δοκιμάστε ξανά αργότερα.");window.location.href="main.php";</script>';
        }
        else
        {
            mysql_select_db("stopcancercyprus", $con);
            mysql_set_charset('utf8', $con);
            $select_query = "INSERT INTO CANCER(cancer_name,cancer_description,deleted) VALUES ('".$_POST['cName']."','".$_POST['cDescr']."',0)";
            $res = mysql_query($select_query, $con);
            if($res)
            {
                echo '<script type="text/javascript">alert("Τα στοιχεία υποβλήθηκαν επιτυχώς!");window.location.href="main.php";</script>';
                mysql_close($con);
            }
            else
            {
                echo '<script type="text/javascript">alert("Πρόβλημα εισαγωγής. Παρακαλώ δοκιμάστε ξανά αργότερα.");window.location.href="main.php";</script>';
                mysql_close($con);
            }
        }
    }
    else
        echo '<script type="text/javascript">alert("Πρέπει να συμπληρώσετε όλα τα πεδία για να προχωρήσετε!");</script>';
}
elseif(isset($_POST['cName']) && isset($_POST['cDescr']) && isset($_SESSION['cUpdate']) && ($_SESSION['cUpdate']==1))
{
    unset($_SESSION['cUpdate']);
    if($_POST['cName']!="" && $_POST['cDescr']!=""){
        $con = mysql_connect("localhost","stopcancercyprus","zx&w&tin");
        if(!$con)
        {
            echo '<script type="text/javascript">alert("Πρόβλημα σύνδεσης με τη βάση δεδομένων. Παρακαλώ δοκιμάστε ξανά αργότερα.");window.location.href="main.php";</script>';
        }
        else
        {
            mysql_select_db("stopcancercyprus", $con);
            mysql_set_charset('utf8', $con);
            $select_query = "UPDATE CANCER SET cancer_name='".$_POST['cName']."', cancer_description='".$_POST['cDescr']."' WHERE ID_cancer=".$_SESSION['cID'];
            $res = mysql_query($select_query, $con);
            if($res)
            {
                echo '<script type="text/javascript">alert("Τα στοιχεία ενημερώθηκαν επιτυχώς!");window.location.href="main.php";</script>';
                mysql_close($con);
            }
            else
            {
                echo '<script type="text/javascript">alert("Πρόβλημα ενημέρωσης. Παρακαλώ δοκιμάστε ξανά αργότερα.");window.location.href="main.php";</script>';
                mysql_close($con);
            }
        }
    }
    else
        echo '<script type="text/javascript">alert("Πρέπει να συμπληρώσετε όλα τα πεδία για να προχωρήσετε!");</script>';
}

function newtext($name)
{
    if(isset($_SESSION['cUpdate']) && ($_SESSION['cUpdate']==1))
        echo $_SESSION[$name];
    else
        echo "";
}


?>
</body>

</html>