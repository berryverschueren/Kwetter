# Image to extend
FROM java:8-jdk

# Set the author
MAINTAINER Berry Verschueren

# Setting environment variables.
ENV PAYARA_PKG https://s3-eu-west-1.amazonaws.com/payara.fish/Payara+Downloads/Payara+4.1.1.171.0.1/payara-4.1.1.171.0.1.zip
ENV PAYARA_VERSION 171
ENV PKG_FILE_NAME payara-full-${PAYARA_VERSION}.zip
ENV PAYARA_PATH /opt/payara41
ENV ADMIN_USER admin
ENV ADMIN_PASSWORD admin
ENV DRIVER_CLASS_NAME "com.mysql.jdbc.Driver"
ENV DATABASE_PASSWORD admin
ENV DATABASE_USERNAME root
ENV DATABASE_NAME kwetter
ENV DATABASE_SERVER "127.0.0.1"
ENV DATABASE_PORT 3306
ENV DATABASE_POOL_NAME security_pool
ENV RES_TYPE "java.sql.Driver"

# Download and install the payara server
RUN \
 apt-get update && \ 
 apt-get install -y unzip 

RUN wget --quiet -O /opt/${PKG_FILE_NAME} ${PAYARA_PKG}
RUN unzip -qq /opt/${PKG_FILE_NAME} -d /opt

RUN mkdir -p ${PAYARA_PATH}/deployments
RUN useradd -b /opt -m -s /bin/bash -d ${PAYARA_PATH} payara && echo payara:payara | chpasswd
RUN chown -R payara:payara /opt

# Default payara ports to expose
EXPOSE 4848 8009 8080 8181

# Set working variables
USER payara
WORKDIR ${PAYARA_PATH}

# set credentials to admin/admin 
RUN echo 'AS_ADMIN_PASSWORD=\n\
AS_ADMIN_NEWPASSWORD='${ADMIN_PASSWORD}'\n\
EOF\n'\
>> /opt/tmpfile

RUN echo 'AS_ADMIN_PASSWORD='${ADMIN_PASSWORD}'\n\
EOF\n'\
>> /opt/pwdfile

# Download the MySQL driver using curl -> for glassfish/lib
RUN wget -q http://central.maven.org/maven2/mysql/mysql-connector-java/6.0.6/mysql-connector-java-6.0.6.jar -O ${PAYARA_PATH}/glassfish/lib/mysql-connector-java-6.0.6.jar
RUN wget -q http://central.maven.org/maven2/mysql/mysql-connector-java/6.0.6/mysql-connector-java-6.0.6.jar -O ${PAYARA_PATH}/glassfish/domains/domain1/lib/mysql-connector-java-6.0.6.jar
RUN wget -q http://central.maven.org/maven2/mysql/mysql-connector-java/6.0.6/mysql-connector-java-6.0.6.jar -O ${PAYARA_PATH}/glassfish/domains/domain1/lib/ext/mysql-connector-java-6.0.6.jar

# Append to classpath
ENV CLASSPATH=/mysql-connector-java-6.0.6.jar:${CLASSPATH}

# Run some glassfish commands to setup a domain with the desired configurations
RUN \
 ${PAYARA_PATH}/bin/asadmin start-domain && \
 ${PAYARA_PATH}/bin/asadmin --user ${ADMIN_USER} --passwordfile=/opt/tmpfile change-admin-password && \
 ${PAYARA_PATH}/bin/asadmin --user ${ADMIN_USER} --passwordfile=/opt/pwdfile enable-secure-admin && \
 ${PAYARA_PATH}/bin/asadmin --user ${ADMIN_USER} --passwordfile=/opt/pwdfile create-jdbc-connection-pool --driverclassname ${DRIVER_CLASS_NAME} --restype ${RES_TYPE} --property  user=${DATABASE_USERNAME}:password=${DATABASE_PASSWORD}:url="jdbc\\:mysql\\://${DATABASE_SERVER}\\:${DATABASE_PORT}/${DATABASE_NAME}" ${DATABASE_POOL_NAME} && \
 ${PAYARA_PATH}/bin/asadmin stop-domain

# cleanup
RUN rm /opt/tmpfile
RUN rm /opt/${PKG_FILE_NAME}

# Start the domain
ENTRYPOINT ${PAYARA_PATH}/bin/asadmin start-domain --verbose domain1