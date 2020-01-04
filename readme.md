# Getting Started

### Reference Documentation

##### contact-manager:
port : 8080
swagger: http://localhost:8080/api/swagger-ui.html#
h2 console: http://localhost:8080/api/h2-console

##### The following server is used to manage configurations
spring cloud config:
Currently all config is moved to respective applications.
To re-enable, uncomment spring.cloud.config and comment the rest of the properties except apring.application.name, and start the config server
rename application.yml to bootstrap.yml
port: 8888

##### The following server is used as service discovery:
###### Eureka naming server:
port: 8761
http://localhost:8761

##### The following server is used as gateway proxy:
###### Zuul Api gateway
port: 8765

##### Calling contactbook through zuul:
http://localhost:8765/contact-book/api/contacts/100001
##### Calling addressbook through zuul:
http://localhost:8765/address-book/api/contacts/100001/addresses

##### Calling contactbook directly:
http://localhost:8080/api/contacts/id/100001
