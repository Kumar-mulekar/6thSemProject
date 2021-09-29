<?php
  $connection=mysqli_connect("sql6.freesqldatabase.com","sql6419860","Q9YIJ2crXS","sql6419860");
  $meter=$_POST['meter'];
  $reading=$_POST['reading'];
  $rdate=$_POST['rdate'];
 
  
  $qry="INSERT INTO reading (meter, reading, rdate ) VALUES ($meter, $reading, '$rdate')";
  $result=mysqli_query($connection,$qry);

  if($result){
    echo "done";

  }
  else{
    echo "failed";
  }
  mysqli_close($connection);

?>