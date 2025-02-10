# Jakarta Demo

A very simple programm to illustrate using the basic and most common Jakarta EE 10 technologies.

## Setup

### Glassfish
Define useful and necessary environment variable. Here we are using the Fish shell on a macOS.
```shell
set -Ux GLASSFISH_HOME /opt/homebrew/opt/glassfish/libexec/
set -Ux GLASSFISH_BIN $GLASSFISH_HOME/bin/
set -Ux DOMAIN_HOME $GLASSFISH_HOME/glassfish/domains/$domain
set -Ux DEPLOYMENT_DIR $DOMAIN_HOME/autodeploy
```

Create a new Glassfish domain
```shell
asadmin create-domain --instanceport 8082 --adminport 4852 domain2
asadmin start-domain domain2
```

Show logs (linux or macOS)
```shell
grc tail -f -n 20 $DOMAIN_HOME/logs/server.log
```

Download the PostgreSQL JDBC Driver from https://jdbc.postgresql.org/download/

Add PostgreSQL Driver
```shell
cp $HOME/Downloads/postgresql-42.7.5.jar $GLASSFISH_HOME/glassfish/domains/domain2/lib
asadmin restart-domain domain2
asadmin --port 4852 create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --restype javax.sql.DataSource \
  --property portNumber=5432:password=admin:user=admin:serverName=localhost:databaseName=jea2025 jea2025
asadmin --port 4852 create-jdbc-resource --connectionpoolid jea2025 jdbc/jea2025
```

### PostgreSQL
Run PostgreSQL as docker image. This is only necessary if the PostgreSQL server is not already running.
```shell
docker run --name my-postgres -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres
```
Access this database instance with `psql` to create a new database and user
```shell
psql postgres://postgres:postgres@localhost:5432
create user admin password 'admin';
create database jea2025 owner admin;
```