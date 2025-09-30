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
docker exec -it local-postgres /bin/sh
# psql -U postgres 
psql (16.2 (Debian 16.2-1.pgdg120+2))
postgres=# \l
                                                       List of databases
    Name    |  Owner   | Encoding | Locale Provider |  Collate   |   Ctype    | ICU Locale | ICU Rules |   Access privileges   
------------+----------+----------+-----------------+------------+------------+------------+-----------+-----------------------
 arvydas_db | postgres | UTF8     | libc            | en_US.utf8 | en_US.utf8 |            |           | 
 postgres   | postgres | UTF8     | libc            | en_US.utf8 | en_US.utf8 |            |           | 
 template0  | postgres | UTF8     | libc            | en_US.utf8 | en_US.utf8 |            |           | =c/postgres          +
            |          |          |                 |            |            |            |           | postgres=CTc/postgres
 template1  | postgres | UTF8     | libc            | en_US.utf8 | en_US.utf8 |            |           | =c/postgres          +
            |          |          |                 |            |            |            |           | postgres=CTc/postgres
(4 rows)
postgres=# \c arvydas_db
You are now connected to database "arvydas_db" as user "postgres".
arvydas_db=# \d
        List of relations
 Schema | Name | Type  |  Owner   
--------+------+-------+----------
 public | pets | table | postgres
(1 row)
arvydas_db=# select * from pets limit 10;
 pet_id | name | status | created_at | last_modified_at 
--------+------+--------+------------+------------------
(0 rows)
```
