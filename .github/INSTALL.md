<h1>INSTALL</h1>
<h1>DB & RDMS (Docker)</h1>
to start Docker container:

```
$ docker-compose up -d
```

to stop Docker container:

```
$ docker-compose down --volumes
```

<h1>Psql in Docker</h1>
<p>in docker terminal:</p>

```
# psql -U postgress -d spring_db
```

<h1>PGAdmin in Docker</h1>

https://habr.com/ru/articles/578744/

<h1>Make a jar file</h1>
Remove target folder:
Maven ->  demo -> lifecycle -> clean

Click on
Maven ->  demo -> lifecycle -> install

inside target reveals jar file

Go to this dir and type:
```
$ java -jar demo-0.0.1-SNAPSHOT.jar --server.port=5500 --spring.datasource.url=jdbc:postgresql://localhost:5432/spring_db --spring.datasource.username=postgress --spring.datasource.password=javascript
```
short way:
```
$ java -jar demo-0.0.1-SNAPSHOT.jar --server.port=5500
```
or:
```
$ ./mvnw package
```
if make and test:
```
$ ./mvnw package && java -jar demo-0.0.1-SNAPSHOT.jar
```

<h1>in Docker</h1>

```
docker build -t demo-0.0.1 .
```
https://skillbox.ru/media/base/kak_rabotat_s_docker_upakovka_spring_boot_prilozheniya_v_konteyner/