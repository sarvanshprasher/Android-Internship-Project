<?php
$con=mysqli_connect("localhost","root","root","signup");
if (mysqli_connect_errno($con))
{
echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$ticket = $_POST['ticket'];
$noofmembers = $_POST['noofmembers'];



$sql="INSERT INTO tickets (ticket,noofmembers) VALUES('$ticket','$noofmembers')";

 




if ($con->query($sql) === TRUE) {
echo "New record created successfully";
} else {
echo "Error: " . $sql . "<br>" . $con->error;
}

$con->close();?>

