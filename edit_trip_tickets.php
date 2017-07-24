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
$ticket=$_POST['ticket'];
$noofmembers=$_POST['noofmembers'];





$sql = "UPDATE tripinfo SET ticket=$ticket,noofmembers=$noofmembers WHERE trip_id=$id";

if ($conn->query($sql) === TRUE) {
    echo "Record updated successfully";
} else {
    echo "Error updating record: " . $conn->error;
}

$conn->close();
?>