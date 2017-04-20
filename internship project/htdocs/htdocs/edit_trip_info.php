<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "signup";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
$id=$_POST['id'];
$destinationcountry=$_POST['destinationcountry'];
$destinationstate=$_POST['destinationstate'];
$destinationplace=$_POST['destinationplace'];
$tripwith=$_POST['tripwith'];

$startdate=$_POST['startdate'];
$returndate=$_POST['returndate'];
$noofdays=$_POST['noofdays'];

$ticket=$_POST['ticket'];
$noofmembers=$_POST['noofmembers'];






$sql = "UPDATE tripinformation SET destinationcountry='$destinationcountry',destinationstate='$destinationstate',destinationplaces='$destinationplace',tripwith='$tripwith' WHERE user_id='$id'";

if ($conn->query($sql) === TRUE) {
    echo "Record updated successfully";
} else {
    echo "Error updating record: " . $conn->error;
}
$sql1 = "UPDATE tripdate SET startdate='$startdate',returndate='$returndate',noofdays='$noofdays' WHERE user_id='$id'";

if ($conn->query($sql1) === TRUE) {
    echo "Record updated successfully";
} else {
    echo "Error updating record: " . $conn->error;
}
$sql2 = "UPDATE tickets SET ticket='$ticket',noofmembers='$noofmembers' WHERE user_id=$id";

if ($conn->query($sql2) === TRUE) {
    echo "Record updated successfully";
} else {
    echo "Error updating record: " . $conn->error;
}

$conn->close();
?>s