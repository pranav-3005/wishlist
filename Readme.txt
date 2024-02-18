Project description:
 This project features the wishList feature functionalities.I've structured this Project code in MVC architectural layout, used MySQL DB for local database,
 connected using JDBC and handled using JPA/Hibernate ORM.
 Created model classes and its relevant repository interfaces to connect and work with the Relational-tables in DB.
 Whereas, controller layer acts as the endpoint and contains list of APIs, Service layer provides the business logics to those endpoints in controller layer.
 Utilized useful dependencies to improve project's functionality and performance like.., Lombok,Swagger..etc.
 Implemented functionalities like DTO(request & response classes for model classes) and Transformers (functions for DTO->model,model->DTO).
 Also written unit-testing codes, to check the completeness and effectiveness of API functions and finally
 Implemented user-based authentication using Spring security functions.


Instructions to run locally :

1.Install the required softwares.
    -> java JDK
    -> Maven
    -> Mysql server
    -> Intellij IDEA - community edition
    -> Postman (OPTIONAL - for testing APis)
    -> DBeaver (OPTIONAL - Database management app)

2. Clone this project to local folder and open it with Intellij

3. initiate connection to database
    ->create a mysql database with name as "wishList" locally
    ->In Intellij,go to src->resource->application.properties
    ->Replace the username and password with your mysql credentials

3. Go to pom.XML file and load Maven to update the dependencies

4. Now run WishlistApplication.java file (the starting point of the project)
    -> hit this post API URL ( http://localhost:8080/api/signup?username=user1&password=pass1 ) to sign your account in postman.
    -> You can perform all the APIs easily using SWAGGER(a simple UI) dependency, which I've imported. access with this url - http://localhost:8080/swagger-ui/index.html# on your browser
    -> Initially it'll take you login page,after entering the correct credentials,
    -> Now, you are good to go, access and can perform the APIs.

    ->to switch user accounts, hit http://localhost:8080/logout and again hit http://localhost:8080/swagger-ui/index.html# on your browser

*** Input format for APIs :

1. signup (POST)
    URl : http://localhost:8080/api/signup?userName=pranav&password=pranav123
    body : {}

2. Login
    URL : http://localhost:8080/login
    body : {
                "userName":"pranav",
                "password":"pranav123"
           }

2. addWishList (POST)
    URL : http://localhost:8080/api/wishlists
    body : {
               "productName":"pr_name",
                "price":500.0
           }

3. getWishList (GET)
    URl : http://localhost:8080/api/wishLists
    body : {}

4. deleteWishList (DELETE)
    URL : http://localhost:8080/api/deleteWishList/{product_id}
    body : {}

5. logout
    URL : http://localhost:8080/logout
    body : {}