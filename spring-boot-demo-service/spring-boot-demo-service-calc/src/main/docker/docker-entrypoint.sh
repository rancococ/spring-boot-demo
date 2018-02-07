#!/bin/bash

set -e

# gen conf
envsubst '${XMS} ${XMX} ${XSS} \
          ${RMI_REGISTRY_IP} \
          ${REMOTE_DEBUG_PORT} ${HTTP_LISTEN_PORT} \
          ${ZK_ADDRESS} ${ZK_ENABLE} ${SERVICE_ADDRESS}' \
         < /data/myapp/conf/wrapper-java-additional.temp \
         > /data/myapp/conf/wrapper-java-additional.conf

# current user is root
if [ "$(id -u)" = "0" ]; then
    # no parameter
    if [ -z "$1" ]; then
        # exec default command
        exec /bin/sh -c "/usr/sbin/sshd -D"
    fi
    # has parameter
    if [ -n "$1" ]; then
        # exec by myapp
        exec gosu myapp "$@"
    fi
fi

# current user is not root
# exec by spec user
exec "$@"
