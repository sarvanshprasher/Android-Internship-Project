<?php
$con=mysqli_connect("localhost","root","root","signup");
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$destinationcountry = $_POST['destinationcountry'];
$destinationstate = $_POST['destinationstate'];
$destinationplace = $_POST['destinationplaces'];
$tripwith = $_POST['tripwith'];


$sql="INSERT INTO tripinformation (destinationcountry,destinationstate,destinationplaces,tripwith) VALUES('$destinationcountry','$destinationstate','$destinationplace','$tripwith')";

 




if ($con->query($sql) === TRUE) {
echo "New record created successfully";
} else {
echo "Error: " . $sql . "<br>" . $con->error;
}

$con->close();?>