<?php
$con=mysqli_connect("localhost","root","","signup");
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$user_id = $_POST['user_id'];
$pass = $_POST['password'];
$res = mysqli_query($con,"SELECT * FROM signuptable where user_id='$user_id' and password='$pass'");


 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('id'=>$row[0],
'user_id'=>$row[1],
'password'=>$row[2]
));
}
 
echo json_encode(array("result"=>$result));


if ($res) {
echo "success";
} else {
echo "Error: " . $sql . "<br>" . $con->error;
}



 
mysqli_close($con);
 
?>





