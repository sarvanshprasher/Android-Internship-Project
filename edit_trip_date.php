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
$startdate=$_POST['startdate'];
$returndate=$_POST['returndate'];
$noofdays=$_POST['noofdays'];





$sql = "UPDATE tripinfo SET startdate=$startdate,returndate=$returndate,noofdays=$noofdays WHERE trip_id=$id";

if ($conn->query($sql) === TRUE) {
    echo "Record updated successfully";
} else {
    echo "Error updating record: " . $conn->error;
}

$conn->close();
?>