#!/bin/bash

chown -R myuser /home/myuser/
chmod -R 770 /home/myuser/

# 使用myuser用户远程程序
exec gosu myuser /home/myuser/tomcat/apache-tomcat-9.0.73/bin/catalina.sh run