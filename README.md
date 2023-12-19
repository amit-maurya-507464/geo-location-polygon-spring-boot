# GeoLocation mapping using polygon and PostGIS
Spring Boot Project for Geo Location mapping using polygon, PostgreSQL, PostGIS.

## Running and Testing
**IDE:** 
Open project in any IDE and run as a spring boot Project. <br>
**Command line:** 
Open terminal and locate to pom.xml file directory and type command - 
`mvn dpring-boot:run`

Use the following url to test the Application:
`http://localhost:8080/swagger-ui/index.html`


## Database configuration

1. **Install PostGIS** <br>
for version 12 --> `sudo apt install postgis postgresql-12-postgis-3` <br>
for version 13 --> `sudo apt install postgis postgresql-13-postgis-3` <br>
<br>
2. **Create Extensions in postgres database for connection** <br>
`CREATE EXTENSION IF NOT EXISTS postgis;` <br>
`CREATE EXTENSION IF NOT EXISTS postgis_topology;` <br>

## Documentation
This project uses springdoc-openapi for documentation.
springdoc-openapi java library helps to automate the generation of API documentation using spring boot projects.

## Test Data 

### Insert User 1 with Google Maps coordinates with format [Longitude, Latitude] <br>
``` 
curl -X POST -H "Content-Type: application/json" -d '{
"name": "Amit",
"serviceArea": {
"type": "Polygon",
"coordinates": [[-73.981235, 40.748567], [-73.981235, 40.752345], [-73.973456, 40.752345], [-73.973456, 40.748567], [-73.981235, 40.748567]]
},
"userId": 1
}' http://localhost:8080/users
```

### Insert User 2 with different Google Maps coordinates with format [Longitude, Latitude] <br>
```
curl -X POST -H "Content-Type: application/json" -d '{
"name": "Anuj",
"serviceArea": {
"type": "Polygon",
"coordinates": [[-74.005678, 40.721234], [-74.005678, 40.725789], [-73.997654, 40.725789], [-73.997654, 40.721234], [-74.005678, 40.721234]]
},
"userId": 2
}' http://localhost:8080/users
```

### Check location against user service areas <br>
```
curl -X GET "http://localhost:8080/users/check-location?latitude=40.750000&longitude=-73.980000"
```

