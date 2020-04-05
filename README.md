# RESTAURANT REVIEW WEB API

Platforma de review pentru restaurante.

## ToDos

- [X] Ratings table + entity
- [X] Columns constraints
- [X] In-memory db H2
- [X] MySql connection
- [X] Dev and prod spring profiles
- [X] Repositories
- [X] Services
- [X] Logging
- [X] Aspect
- [X] Exception handling
- [ ] Pagination for restaurants and reviews
- [ ] Unit Tests
- [X] Request models validation
- [X] Controllers
- [X] Security (roles: admin and user) JWT
- [ ] Routing + secured routes

## Echipa de proiect

Ene Mihai-Lucian, Radu Razvan Marian

## MySql

```docker
docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:latest
```

## DB Schema

![db-schema](https://raw.githubusercontent.com/rrady/spring-restaurant-review-api/master/db-schema.png)
