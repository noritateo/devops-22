# Use the latest MySQL image
FROM mysql/mysql-server:latest

# Set the working directory
WORKDIR /tmp

# Copy the main SQL file to initialise the DB automatically
COPY db/world.sql /docker-entrypoint-initdb.d/world.sql

# Set the root password
ENV MYSQL_ROOT_PASSWORD=example
ENV MYSQL_ROOT_HOST=%
