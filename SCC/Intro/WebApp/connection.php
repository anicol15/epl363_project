<?php
function getCancers()
{
	$con = mysql_connect("localhost","stopcancercyprus","zx&w&tin");
	if (!$con)
		echo "".mysql_error();
	else
    {
        mysql_select_db("stopcancercyprus", $con);
        mysql_set_charset('utf8', $con);
        $select_query = "SELECT ID_cancer,cancer_name FROM CANCER WHERE Deleted=0";
        $select_query_run = mysql_query($select_query, $con);
       // echo "<select name='CANCER'>";
        $row = mysql_fetch_array($select_query_run);
        while ($row)
        {
            echo "<option value='".$row{'ID_cancer'}."'> ".$row{'cancer_name'}." </option>";
            $row = mysql_fetch_array($select_query_run);
        }
      //  echo "</select>";
    }
    mysql_close($con);
}

function getExams()
{
    $con = mysql_connect("localhost","stopcancercyprus","zx&w&tin");
    if (!$con)
        echo "".mysql_error();
    else
    {
        mysql_select_db("stopcancercyprus", $con);
        mysql_set_charset('utf8', $con);
        $select_query = "SELECT ID_exam,exam_name,exam_agerange FROM EXAMINATION WHERE Deleted=0";
        $select_query_run = mysql_query($select_query, $con);
     //   echo "<select name='EXAMINATION'>";
        $row = mysql_fetch_array($select_query_run);
        while ($row)
        {
            echo "<option value='".$row{'ID_exam'}."'> ".$row{'exam_name'}." [".$row{'exam_agerange'}."] </option>";
            $row = mysql_fetch_array($select_query_run);
        }
     //   echo "</select>";
    }
    mysql_close($con);
}