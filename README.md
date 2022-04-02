### 🤷🏼‍♂️ PREREQUISITE DEV
- Java version 11 (at least)
- Maven
- Docker environment
- Docker compose

### 🐳 RUNNING THE APP DOCKER

```shell
mvn clean install # install all deps for backend from parent pom file
docker-compose --env-file .env up --build -d
```

### 🚚 SERVICES
*Request*
```shell
curl --location --request POST 'http://localhost:8081/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "user1",
    "password": "password1"
}'
```
*Response*
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNjI5MDI5MjQ3LCJpc3MiOiJBdXRoU2VydmljZSIsImV4cCI6MTYyOTAzMDE0N30.elcks-Vmt9LDQ7f7jActiYiAfnA36MFfGCHE3-lMc98",
    "expire": 1629030147840,
    "scope": "admin"
}
```

### Get users
*Request*
```shell
curl --location --request GET 'http://localhost:8081/users' \
--header 'Authorization:  eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNjI5MDI5MjQ3LCJpc3MiOiJBdXRoU2VydmljZSIsImV4cCI6MTYyOTAzMDE0N30.elcks-Vmt9LDQ7f7jActiYiAfnA36MFfGCHE3-lMc98'
```
*Response*
```json
[
    {
        "id": 1,
        "username": "user1",
        "scope": "admin",
        "fullName": "Name 1"
    },
    {
        "id": 2,
        "username": "user2",
        "scope": "user",
        "fullName": "Name 2"
    },
    {
        "id": 3,
        "username": "user3",
        "scope": "user",
        "fullName": "Name 3"
    }
]
```

