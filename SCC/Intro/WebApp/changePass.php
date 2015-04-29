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
    <form accept-charset="utf-8" action="" method="post" id="changeP">
        <table style="width:700"
        <tr>
        <td>Παλιός κωδικός: </td>
        <td><input name="oldP" type="password" /></td>
        </tr>
        <tr>
        <td>Νέος κωδικός:  </td>
        <td><input name="newP1" type="password" /></td>
        </tr>
        <tr>
        <td>Επιβεβαίωση νέου κωδικού: </td>
        <td><input name="newP2" type="password" /></td>
        </tr>
        </table>
        <br />
        <input type="button" onclick="clearForm()" value="Καθαρισμός">
        <input name="submitPass" type="submit" value="Αλλαγή Κωδικού"  /></form>
</div>
<script>
    function clearForm(){
        document.getElementById("changeP").reset();
    }
</script>
<?php
if(isset($_POST['oldP']) && isset($_POST['newP1']) && isset($_POST['newP2']))
{
    if($_POST['oldP']!="" && $_POST['newP1']!="" && $_POST['newP2']!="")
    {
        if($_POST['newP1']==$_POST['newP2']){
            $con = mysql_connect("localhost","stopcancercyprus","zx&w&tin");
            if(!$con)
            {
                echo '<script type="text/javascript">alert("Πρόβλημα σύνδεσης με τη βάση δεδομένων. Παρακαλώ δοκιμάστε ξανά αργότερα.");window.location.href="main.php";</script>';
            }
            else
            {
                mysql_select_db("stopcancercyprus", $con);
                mysql_set_charset('utf8', $con);
                $select_query = "SELECT wpassword FROM WPASS";
                $res = mysql_query($select_query, $con);
                if($res)
                {
                    $row = mysql_fetch_row($res);
                    if(strcmp($_POST['oldP'],$row[0])==0)
                    {
                        $query = "UPDATE WPASS SET wpassword='".$_POST['newP1']."' WHERE STRCMP(wuser,'admin')=0";
                        $res2 = mysql_query($query, $con);
                        if($res2)
                        {
                            mysql_close($con);
                            echo '<script type="text/javascript">alert("Ο κωδικός έχει αλλάξει με επιτυχία!");window.location.href="main.php";</script>';
                        }
                        else
                        {
                            mysql_close($con);
                            echo '<script type="text/javascript">alert("Πρόβλημα σύνδεσης με τη βάση δεδομένων. Παρακαλώ δοκιμάστε ξανά αργότερα.");window.location.href="main.php";</script>';
                        }
                    }
                    else
                    {
                        mysql_close($con);
                        echo '<script type="text/javascript">alert("Έχετε εισάγει λανθασμένο κωδικό πρόσβασης. Παρακαλώ ξαναδοκιμάστε.");</script>';
                    }
                }
                else
                {
                    echo '<script type="text/javascript">alert("Πρόβλημα σύνδεσης με τη βάση δεδομένων. Παρακαλώ δοκιμάστε ξανά αργότερα.");window.location.href="main.php";</script>';
                    mysql_close($con);
                }
            }
        }
        else
            echo '<script type="text/javascript">alert("Ο νέος κωδικός και ο επιβεβαιωμένος νέος κωδικός δεν ταιριάζουν! Παρακαλώ ξαναδοκιμάστε.");</script>';
        }
    else
        echo '<script type="text/javascript">alert("Πρέπει να συμπληρώσετε όλα τα πεδία για να προχωρήσετε!");</script>';
}
?>
</body>

</html>