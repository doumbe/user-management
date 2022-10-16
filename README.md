# user-management
Projet Service REST - Doumbe Traore
A spring boot application for users CRUD
#Clone the project

git clone https://github.com/doumbe/user-management.git
open the project with an IDE
java jdk-17.0.4
maven maven-4.0.0
#Lunch the project

in the package fr.doumbe.usermanagement.demo, choose DemoApplication and run it
test the code with Postman Collection in src/main/resources/Collection-UserManagement.postman_collection.json
the documentation of the API is in the swagger http://localhost:8080/swagger-ui.html#/
#User Model @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_user") @SequenceGenerator(name = "s_user") private Long id;

@Size(min = 2)
@Column(name = "username", nullable = false, unique = true)
private String username;

@Column(name = "birthdate", nullable = false)
private LocalDate birthdate;

@Size(min = 2)
@Column(name = "country", nullable = false)
private String country;

@Column(name = "phoneNumber")
private Long phoneNumber;

@Size(min = 1)
@Column(name = "genre")
private String genre;
