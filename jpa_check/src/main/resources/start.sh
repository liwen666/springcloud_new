#!/bin/sh
PWD=`pwd`
RESOURCE_NAME=`find ./ -name portal*.jar`
RESOURCE_NAME=$PWD/$RESOURCE_NAME
profile=""
if [ -n "$1" ];then
profile="-Dspring.profiles.active=$1"
fi
echo $profile  $RESOURCE_NAME

tpid=`ps -ef|grep $RESOURCE_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
echo 'Stop Process...'
kill -15 $tpid
fi
sleep 5
tpid=`ps -ef|grep $RESOURCE_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
echo 'Kill Process!'
kill -9 $tpid
else
echo 'Stop Success!'
fi
 
tpid=`ps -ef|grep $RESOURCE_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'App is running.'
else
    echo 'App is NOT running.'
fi
 
rm -f tpid
nohup java -jar -Djava.ext.dirs=./lib $profile $RESOURCE_NAME --debug=false --portal.devMode=false &
echo $! > tpid
echo Start Success!
