<?php
$con=mysqli_connect("localhost","root","root","signup");
if (mysqli_connect_errno($con))
{
echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$firstname = $_POST['firstname'];
$lastname = $_POST['lastname'];

$username= $_POST['username'];

$mobile= $_POST['mobile'];

$email= $_POST['email'];

$passwrd= $_POST['password'];

$gender= $_POST['gender'];
$user_id=$_POST['user_id'];



$sql="INSERT INTO signuptable (firstname,lastname,username,mobile,email,password,gender,user_id) VALUES('{$firstname}','{$lastname','$username','$mobile','$email','$passwrd','$gender','$user_id')";

$result = mysqli_query($con, $sql); 




if ($result) {
echo "success";
} else {
echo "Error: " . $sql . "<br>" . $con->error;
}

$con->close();?>

