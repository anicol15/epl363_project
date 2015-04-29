
<?php
session_start();
$con = mysql_connect("localhost","stopcancercyprus","zx&w&tin");
if(!$con)
{
    echo '<script type="text/javascript">alert("Πρόβλημα σύνδεσης με τη βάση δεδομένων. Παρακαλώ δοκιμάστε ξανά αργότερα.");window.location.href="main.php";</script>';
}
else
{
    mysql_select_db("stopcancercyprus", $con);
    mysql_set_charset('utf8', $con);
    $select_query = "SELECT wpassword FROM WPASS WHERE STRCMP(wuser,'".$_POST['user']."')=0";
    $res = mysql_query($select_query, $con);
    if($res)
    {
        $row = mysql_fetch_row($res);
        if (strcmp($row[0],$_POST['pass'])==0)
        {
            $_SESSION['user']=1;
            mysql_close($con);
            header("location: main.php");
        }
        else
        {
            $_SESSION['user']=0;
            mysql_close($con);
            header("location: index.php");
        }
    }
    else
    {
        $_SESSION['user']=0;
        header("location: index.php");
    }

}

