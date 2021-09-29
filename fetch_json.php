<?php
    $connection=mysqli_connect("sql6.freesqldatabase.com","sql6419860","Q9YIJ2crXS","sql6419860");
    $result=array();
    $result['data']=array();
    $select="select meter,name,phone,last_date,last_unit from customer,meter_info where customer.meter=meter_info.meter_number";
    $responce=mysqli_query($connection,$select);

    while($row =mysqli_fetch_array($responce)){
        $index['meter']=$row['0'];
        $index['name']=$row['1'];
        $index['phone']=$row['2'];
        $index['last_date']=$row['3'];
        $index['last_unit']=$row['4'];

        array_push($result['data'],$index);
    }
    $result["success"]="1";
    echo json_encode($result);
    mysqli_close($connection);
?>