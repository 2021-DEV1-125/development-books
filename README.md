# Development Books

## Java Version
Java 17

## Tools & Frameworks

Spring Boot

Maven

### Application Run


```
  mvn spring-boot
```

## API

Available endpoints can be seen via [url](http://localhost:8080/swagger-ui/index.html):

Available endpoints:        
1. Get all books
 ```
GET /books
```
2. calculate shopping cart price. Input parameter is a list of book ids with specified amount.
Amount should be greater than 0 and book ids should appear in the list once.
```
POST /shopping-cart/price/calculate
```


