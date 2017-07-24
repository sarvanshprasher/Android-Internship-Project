<?php
$con=mysqli_connect("localhost","root","root","signup");
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$id = $_POST['trip_id'];

$res = mysqli_query($con,"SELECT * FROM tripinformation where id='$id'");


 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('id'=>$row[0],
'destinationcountry'=>$row[1],
'destinationstate'=>$row[2],
'destinationplaces'=>$row[3],
'tripwith'=>$row[4],


));}

 
echo json_encode(array("result"=>$result));
 
mysqli_close($con);
 
?>


