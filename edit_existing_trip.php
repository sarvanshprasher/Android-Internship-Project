<?php
$servername = "localhost";
$username = "root";
$password = "root";
$dbname = "signup";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
$trip_id=$_POST['id'];
$tripname=$_POST['tripname'];
$startdate=$_POST['startdate'];
$returndate=$_POST['returndate'];
$details=$_POST['details'];
$expense=$_POST['expense'];
$events=$_POST['events'];
$liked=$_POST['liked'];
$unliked=$_POST['unliked'];


$sql = "UPDATE tripinfo SET tripname='$tripname',startdate='$startdate',returndate='$returndate',details='$details',expense='$expense',events='$events',liked='$liked',unliked='$unliked' WHERE id='$trip_id'";

if ($conn->query($sql) === TRUE) {
    echo "success";
} else {
    echo "Error updating record: " . $conn->error;
}

$conn->close();
?>