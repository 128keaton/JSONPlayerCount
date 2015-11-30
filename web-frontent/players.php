<?php
header('Content-type: application/json');

$string = file_get_contents("http://play.128keaton.com/json/stats.json");
echo($string);
?>