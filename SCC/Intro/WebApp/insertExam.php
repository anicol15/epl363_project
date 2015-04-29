<!DOCTYPE html>
<?php
session_start();
include("glob.html");
?>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert - Exam</title>
    <link href="sccstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<?php
if(!isset($_SESSION['user']) || $_SESSION['user']!=1){
    header("location: index.php");
}
?>

<div class="main">
    <form action="" method="post" id="insertE" enctype="multipart/form-data">
        <p>Όνομα Εξέτασης:  <input name="eName" type="text" value="<?php newtext('eName')?>" /></p>
        <p>Περιγραφή: <br />
        <textarea cols="40" rows="5" name="eDescr" wrap="virtual"><?php newtext('eDescr')?></textarea></p>
        <p>Συχνότητα διεκπεραίωσης (σε μήνες): <input name="eFreq" type="text"  value="<?php newtext('eFreq')?>"/></p>
        <p>Αφορά:<br />
        <table>
            <tr>
            <td>Φύλο:</td>
            <td>
            <select name='eGender'>
                <option value='2' <?php selectCombo('eGender','2') ?>> Και τα δυο </option>
                <option value='0' <?php selectCombo('eGender','0') ?>> Άνδρες </option>
                <option value='1' <?php selectCombo('eGender','1') ?>> Γυναίκες </option>
            </select>
            </td>
            </tr>
            <tr>
            <td>Κάπνισμα:</td>
            <td>
            <select name='eSmoker'>
                <option value='3' <?php selectCombo('eSmoker','3') ?>> Και τα τρια </option>
                <option value='0' <?php selectCombo('eSmoker','0') ?>> Καπνιστές </option>
                <option value='1' <?php selectCombo('eSmoker','1') ?>> Πρώην Καπνιστές </option>
                <option value='2' <?php selectCombo('eSmoker','2') ?>> Μη Καπνιστές </option>
            </select>
            </td>
            </tr>
            <tr>
            <td>Κατανάλωση αλκοόλ:</td>
            <td>
            <select name='eAlcohol'>
                <option value='2' <?php selectCombo('eAlcohol','2') ?>> Και τα δυο </option>
                <option value='0' <?php selectCombo('eAlcohol','0') ?>> Αλκοολικούς </option>
                <option value='1' <?php selectCombo('eAlcohol','1') ?>> Μη Αλκοολικούς </option>
            </select>
            </td>
            </tr>
            <tr>
            <td>Οικογενειακό Ιστορικό:</td>
            <td>
            <select name='eHistory'>
                <option value='2' <?php selectCombo('eHistory','2') ?>> Και τα δυο </option>
                <option value='0' <?php selectCombo('eHistory','0') ?>> Με Οικογενειακό Ιστορικό </option>
                <option value='1' <?php selectCombo('eHistory','1') ?>> Χωρίς Οικογενειακό Ιστορικό </option>
            </select>
            </td>
            </tr>
            <tr>
            <td>Σεξουαλική Κατάσταση:</td>
            <td>
            <select name='eSex'>
                <option value='2' <?php selectCombo('eSex','2') ?>> Και τα δυο </option>
                <option value='0' <?php selectCombo('eSex','0') ?>> Σεξουαλικά Ενεργούς </option>
                <option value='1' <?php selectCombo('eSex','1') ?>> Μη Σεξουαλικά Ενεργούς </option>
            </select>
            </td>
            </tr>
            <tr>
            <td>Εξέταση / Τρόπος Πρόληψης:</td>
            <td>
            <select name='eExam'>
                <option value='1' <?php selectCombo('eExam','1') ?>> Εξέταση </option>
                <option value='0' <?php selectCombo('eExam','0') ?>> Τρόπος Πρόληψης </option>
            </select>
            </td>
            </tr>
        </table>
            <p><table style="width:700px">
            <tr>
                <td>Ηλικίες:</td>
                <td>Από:</td>
                <td><input name="eAgeFrom" type="text" value="<?php newtext('eAgeFrom')?>"/></td>
                <td>Μέχρι:</td>
                <td><input name="eAgeTo" type="text" value="<?php newtext('eAgeTo')?>"/></td>
            </tr>
            <tr>
                <td>Δείκτης μάζας σώματος:</td>
                <td>Από:</td>
                <td><input name="eDMSFrom" type="text" value="<?php newtext('eDMSFrom')?>"/></td>
                <td>Μέχρι:</td>
                <td><input name="eDMSTo" type="text" value="<?php newtext('eDMSTo')?>"/></td>
            </tr>
        </table></p>
        <input type="button" onclick="clearForm()" value="Καθαρισμός">
        <input name="submitExam" type="submit" value="Αποθήκευση" /></form>
</div>
<script type="text/javascript">
    function clearForm(){
        document.getElementById("insertE").reset();
    }
</script>
<?php
//Insert
if(isset($_POST['eName']) && isset($_POST['eDescr']) && isset($_POST['eFreq']) && isset($_POST['eAgeFrom']) && isset($_POST['eAgeTo']) && isset($_POST['eDMSFrom']) && isset($_POST['eDMSTo']) && !isset($_SESSION['eUpdate']))
{
    if(($_POST['eName']!="") && ($_POST['eDescr']!="") && ($_POST['eFreq']!=""))
    {
        //Set default values for age & dms range
        if($_POST['eAgeFrom']=="")  $_POST['eAgeFrom']=0;
        if($_POST['eAgeTo']=="")    $_POST['eAgeTo']=200;
        if($_POST['eDMSFrom']=="")  $_POST['eDMSFrom']=0;
        if($_POST['eDMSTo']=="")    $_POST['eDMSTo']=100;

        //check rest of values, must be numeric
        if(!is_numeric($_POST['eFreq']) || !is_numeric($_POST['eAgeFrom']) || !is_numeric($_POST['eAgeTo']) || !is_numeric($_POST['eDMSFrom']) || !is_numeric($_POST['eDMSTo']) || ($_POST['eFreq']<0) || ($_POST['eAgeFrom']<0) || ($_POST['eAgeTo']<0) || ($_POST['eDMSFrom']<0) || ($_POST['eDMSTo']<0))

            echo '<script type="text/javascript">alert("Μη επιτρεπτή τιμή. Πρέπει να εισάγετε αριθμό μεγαλύτερο από 0.");</script>';

        else
        {
            //connect to the sql database
            $con = mysql_connect("localhost","stopcancercyprus","zx&w&tin");
            if(!$con)
            {
                echo '<script type="text/javascript">alert("Πρόβλημα σύνδεσης με τη βάση δεδομένων. Παρακαλώ δοκιμάστε ξανά αργότερα.");window.location.href="main.php";</script>';
            }
            else
            {
                //set values to agree with app, age & dms range
                $age = $_POST['eAgeFrom']."-".$_POST['eAgeTo'];
                $dms = $_POST['eDMSFrom']."-".$_POST['eDMSTo'];

                mysql_select_db("stopcancercyprus", $con);
                mysql_set_charset('utf8', $con);
                $select_query = "INSERT INTO EXAMINATION(exam_name,exam_description,exam_frequency,exam_sex,exam_agerange,exam_dms,exam_smoker,exam_familyhistory,exam_alcohol,exam_gender,is_exam) VALUES ('".$_POST['eName']."','".$_POST['eDescr']."',".$_POST['eFreq'].",".$_POST['eSex'].",'".$age."','".$dms."',".$_POST['eSmoker'].",".$_POST['eHistory'].",".$_POST['eAlcohol'].",".$_POST['eGender'].",".$_POST['eExam'].")";
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
    }
    else
        echo '<script type="text/javascript">alert("Πρέπει να συμπληρώσετε όλα τα πεδία για να προχωρήσετε!");</script>';
}
//Update
elseif (isset($_POST['eName']) && isset($_POST['eDescr']) && isset($_POST['eFreq']) && isset($_POST['eAgeFrom']) && isset($_POST['eAgeTo']) && isset($_POST['eDMSFrom']) && isset($_POST['eDMSTo']) && isset($_SESSION['eUpdate']) && ($_SESSION['eUpdate']==1))
{
    unset($_SESSION['eUpdate']);
    if(($_POST['eName']!="") && ($_POST['eDescr']!="") && ($_POST['eFreq']!=""))
    {
        //Set default values for age & dms range
        if($_POST['eAgeFrom']=="")  $_POST['eAgeFrom']=0;
        if($_POST['eAgeTo']=="")    $_POST['eAgeTo']=200;
        if($_POST['eDMSFrom']=="")  $_POST['eDMSFrom']=0;
        if($_POST['eDMSTo']=="")    $_POST['eDMSTo']=100;

        //check rest of values, must be numeric
        if(!is_numeric($_POST['eFreq']) || !is_numeric($_POST['eAgeFrom']) || !is_numeric($_POST['eAgeTo']) || !is_numeric($_POST['eDMSFrom']) || !is_numeric($_POST['eDMSTo']) || ($_POST['eFreq']<0) || ($_POST['eAgeFrom']<0) || ($_POST['eAgeTo']<0) || ($_POST['eDMSFrom']<0) || ($_POST['eDMSTo']<0))

            echo '<script type="text/javascript">alert("Μη επιτρεπτή τιμή. Πρέπει να εισάγετε αριθμό μεγαλύτερο από 0.");</script>';

        else
        {
            $con = mysql_connect("localhost","stopcancercyprus","zx&w&tin");
            if(!$con)
            {
                echo '<script type="text/javascript">alert("Πρόβλημα σύνδεσης με τη βάση δεδομένων. Παρακαλώ δοκιμάστε ξανά αργότερα.");window.location.href="main.php";</script>';
            }
            else
            {

                $age = $_POST['eAgeFrom']."-".$_POST['eAgeTo'];
                $dms = $_POST['eDMSFrom']."-".$_POST['eDMSTo'];

                mysql_select_db("stopcancercyprus", $con);
                mysql_set_charset('utf8', $con);
                $select_query = "UPDATE EXAMINATION SET exam_name='".$_POST['eName']."', exam_description='".$_POST['eDescr']."', exam_frequency=".$_POST['eFreq'].", exam_sex=".$_POST['eSex'].", exam_agerange='".$age."', exam_dms='".$dms."', exam_smoker=".$_POST['eSmoker'].",exam_familyhistory=".$_POST['eHistory'].", exam_alcohol=".$_POST['eAlcohol'].", exam_gender=".$_POST['eGender'].",is_exam=".$_POST['eExam']." WHERE ID_exam=".$_SESSION['eID'];
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
    }
    else
        echo '<script type="text/javascript">alert("Πρέπει να συμπληρώσετε όλα τα πεδία για να προχωρήσετε!");</script>';
}


function newtext($name)
{
    if(isset($_SESSION['eUpdate']) && ($_SESSION['eUpdate']==1))
        echo $_SESSION[$name];
    else
        echo "";
}

function selectCombo($name, $val)
{
    if(isset($_SESSION['eUpdate']) && ($_SESSION['eUpdate']==1))
    {
        if($val==$_SESSION[$name])
            echo 'selected';
        else
            echo '';
    }
    else
        echo '';
}
?>

</body>
</html>