<?php
$con=mysqli_connect("localhost","root","root","signup");
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$id=$_POST['trip_id'];

$res = mysqli_query($con,"SELECT * FROM tripinfo where id='$id'");


 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('id'=>$row[0],
'tripname'=>$row[1],
'startdate'=>$row[2],
'returndate'=>$row[3],
'details'=>$row[4],
'expense'=>$row[5],
'events'=>$row[6],
'liked'=>$row[7],
'unliked'=>$row[8],

));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($con);
 
?>





