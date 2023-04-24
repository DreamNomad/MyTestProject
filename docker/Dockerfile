# 使用 centos 作为基础镜像
FROM centos:centos7

# 用户
RUN useradd -ms /bin/bash myuser
#RUN groupadd -g 1000 mygroup && useradd -u 1000 -ms /bin/bash myuser

# 指定工作目录  后续的RUN、COPY命令将在该位置下进行
WORKDIR /home/myuser

# 创建java和tomcat的安装目录以及项目数据卷目录
RUN mkdir java && \
    mkdir tomcat && \
    mkdir web && \
    mkdir -p tomcat/apache-tomcat-9.0.73/conf/Catalina/localhost

# 安装java和tomcat
ADD jdk-8u331-linux-x64.tar.gz java/
ADD apache-tomcat-9.0.73.tar.gz tomcat/
# 环境变量
ENV JAVA_HOME /home/myuser/java/jdk1.8.0_331
ENV CLASSPATH .:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV CATALINA_HOME /home/myuser/tomcat/apache-tomcat-9.0.73
ENV CATALINA_BASE /home/myuser/tomcat/apache-tomcat-9.0.73
ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINA_HOME/bin

# 安装 gosu  https://github.com/tianon/gosu
ENV GOSU_VERSION 1.14
RUN yum install -y curl \
    && curl -L -o /usr/local/bin/gosu https://github.com/tianon/gosu/releases/download/$GOSU_VERSION/gosu-amd64 \
    && chmod +x /usr/local/bin/gosu \
    && gosu nobody true

# USER myuser

COPY entrypoint.sh /home/myuser/entrypoint.sh
RUN chmod +x /home/myuser/entrypoint.sh

ENTRYPOINT ["/home/myuser/entrypoint.sh"]

# 对外暴露 Tomcat 端口
EXPOSE 8080

# 启动 Tomcat
# CMD ["catalina.sh", "run"]
