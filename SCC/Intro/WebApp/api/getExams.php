<?php
$con=mysql_connect("localhost", "stopcancercyprus", "zx&w&tin");
mysql_select_db("stopcancercyprus", $con);
mysql_set_charset('utf8', $con);
$select_query = "SELECT * FROM EXAM WHERE udate > ".$_GET['udate'];
$res = mysql_query($select_query, $con);
if($res)
{
    while ($row = mysql_fetch_assoc($res))
        $output[] = $row;
    print(json_encode($output));
}
else
{
    print("No data to fetch");
}
mysql_close($con);
