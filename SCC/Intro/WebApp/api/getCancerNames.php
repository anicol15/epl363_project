<?php
$link=mysql_connect("localhost", "stopcancercyprus", "zx&w&tin");
mysql_set_charset('utf8',$link);
mysql_select_db("stopcancercyprus");
$sql = mysql_query("select cancer_name from CANCER"); //something will be done with this to not ask for all data
while ($row = mysql_fetch_assoc($sql))
    $output[] = $row;
print(json_encode($output)); // this will print the output in json
mysql_close();