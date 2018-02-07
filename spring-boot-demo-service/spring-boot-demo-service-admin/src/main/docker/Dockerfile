# from gdxw/centos:wrapper-3.5
FROM 192.168.8.251/gdxw/centos:wrapper-3.5

# maintainer
MAINTAINER "rancococ" <rancococ@qq.com>

# set arg info
ARG MYAPP_HOME=/data/myapp
ARG MYAPP_LAUNCHER=org.springframework.boot.loader.JarLauncher
ARG MYAPP_NAME=@project.build.finalName@.@project.packaging@
ARG MYAPP_VERSION=@project.version@

# copy script
COPY ${MYAPP_NAME} ${MYAPP_HOME}/lib
COPY docker-entrypoint.sh /
COPY wrapper-java-additional.temp ${MYAPP_HOME}/conf/

# set environment
ENV MYAPP_NAME @project.build.finalName@.@project.packaging@
ENV MYAPP_VERSION @project.version@

# set work home
WORKDIR /data

# expose port
EXPOSE 8080 10087 10001 10002

# stop signal
STOPSIGNAL SIGTERM

# entry point
ENTRYPOINT ["/docker-entrypoint.sh"]

# default command
CMD ["/data/myapp/bin/appcli.sh", "console"]
