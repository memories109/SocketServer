#!/bin/bash

export LANG="ko_KR.UTF-8"

JAVA_HOME=/vol3/jdk1.8
SERVER_HOME=/logichome/tbs/logs/hask/server

cd $SERVER_HOME
nohup $JAVA_HOME/bin/java -jar -Dname=haskReal-test -Dlog4jdbc.dump.sql.maxlinelength=0 -Dfile.encoding=UTF-8 -Dspring.profiles.active=dev -Dapp.log.home=$SERVER_HOME/logs/test $SERVER_HOME/com.realtime.jar &
