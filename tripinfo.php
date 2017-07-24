<?php
$con=mysqli_connect("localhost","root","","signup");
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$tripname = $_POST['tripname'];
$startdate = $_POST['startdate'];
$returndate = $_POST['returndate'];
$details = $_POST['details'];
$expense = $_POST['expense'];
$events = $_POST['events'];
$liked = $_POST['liked'];
$unliked = $_POST['unliked'];
$user_id=$_POST['user_id'];

$sql="INSERT INTO tripinfo (tripname,startdate,returndate,details,expense,events,liked,unliked,user_id) VALUES('$tripname','$startdate','$returndate','$details','$expense','$events','$liked','$unliked','$user_id')";

 




if ($con->query($sql) === TRUE) {
echo "New record created successfully";
} else {
echo "Error: " . $sql . "<br>" . $con->error;
}

$con->close();?>