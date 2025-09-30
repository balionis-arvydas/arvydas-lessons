# Local Environment

This module holds resources needed to create development environment
on localhost.

The directory contains a set of composable docker-compose files
that can be used to create a local functional develop environment.

## Run

In order to start a full environment, run:
```shell
make up
```

To kill all the containers, execute:
```shell
make down
```

## Test

In order to validate the database, run:
```shell
% docker exec -it local-mysql bash 
bash-4.4# mysql arvydas_db --user root --password
Enter password: root_pwd 

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| arvydas_db         |
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
5 rows in set (0.00 sec)

mysql> use arvydas_db;
Database changed
mysql> show tables;
+-----------------------+
| Tables_in_arvydas_db  |
+-----------------------+
| DATABASECHANGELOG     |
| DATABASECHANGELOGLOCK |
| pets                  |
+-----------------------+
3 rows in set (0.01 sec)

mysql> select * from pets;
Empty set (0.01 sec)
```
