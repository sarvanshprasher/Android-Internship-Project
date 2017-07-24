<?php
$con=mysqli_connect("localhost","root","root","signup");
if (mysqli_connect_errno($con))
{
echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$startdate = $_POST['startdate'];
$returndate = $_POST['returndate'];
$noofdays= $_POST['noofdays'];
$sql="INSERT INTO tripdate (startdate,returndate,noofdays) VALUES('$startdate','$returndate','$noofdays')";
if ($con->query($sql) === TRUE) {
echo "success";
} else {
echo "Error: " . $sql . "<br>" . $con->error;
}

$con->close();?>

