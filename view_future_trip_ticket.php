<?php
$con=mysqli_connect("localhost","root","root","signup");
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$id = $_POST['trip_id'];

$res = mysqli_query($con,"SELECT * FROM tickets where id='$id'");
$result = array();
 while($row = mysqli_fetch_array($res)){
array_push($result,
array('id'=>$row[0],
'ticket'=>$row[1],
'noofmembers'=>$row[2]



));}

 
echo json_encode(array("result"=>$result));
 
mysqli_close($con);
 
?>


