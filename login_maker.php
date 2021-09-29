<?php
$conn=mysqli_connect("sql6.freesqldatabase.com","sql6419860","Q9YIJ2crXS");
mysqli_select_db($conn,"sql6419860");

$uname=$_GET['t1'];
$pwd=$_GET['t2'];

$qry="select * from login where username='$uname' and password='$pwd' and user='Worker'";

$raw=mysqli_query($conn,$qry);

$count=mysqli_num_rows($raw);

if($count>0)
 echo "found";
else
 echo "not found";
?>