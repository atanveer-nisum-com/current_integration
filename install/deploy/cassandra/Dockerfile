FROM cassandra:3.11

# Replace docker's official entrypoint file here
COPY cassandra/docker-entrypoint.sh /docker-entrypoint.sh

# Copy sql files to execute at startup
COPY cassandra/init.cql /docker-entrypoint-initdb.d/init.cql

# Make my modified entrypoint executable
RUN chmod a+x docker-entrypoint.sh

# ENTRYPOINT will be called in parent docker image

