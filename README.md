# Echo Website with Spring MVC
This is a small application that echoes back the HTTP request in HTML or JSON:
- Request path
- Request protocol
- Request method
- Request headers
- Request cookies
- Request params (query string)
- Request body (Base64 encoded)
## Steps to run & test the application:
1. Clone the repository:

   $ git clone https://github.com/jerryshao2012/spring-echo-example.git
2. Run the application the gradle command

   $ cd spring-boot-echo

   $ gradlew bootRun

    or $ mvn spring-boot:run
3. Test the application: try it using your browser, REST client, PostMan:
* http://localhost:8081
* http://localhost:8081/echo
* http://localhost:8081/echo?product=1111&category=dummy
* http://localhost:8081/echo?name=jerry
* http://localhost:8081/echo/1/cars
* http://localhost:8081/echoJson

## Steps to build the application in about 15 minutes:
### Before you start
* A favorite text editor or IDE
* [Java SE Development Kit 18](https://www.oracle.com/java/technologies/downloads/) or later
* [Gradle](https://gradle.org/install/) or [Maven](https://maven.apache.org/download.cgi)
* You can also import the code straight into your IDE:
  * [Spring Tool Suite](https://spring.io/guides/gs/sts/)
  * [IntelliJ IDEA](https://spring.io/guides/gs/intellij-idea/)
### Step 1: Starting with Spring Initializr
1. Navigate to https://start.spring.io. This service pulls in all the dependencies you need for an application and does most of the setup for you. 
2. Choose either Gradle or Maven and the language you want to use. This guide assumes that you chose Gradle & Java.
3. Click **Dependencies** and select **Spring Web**, **Thymeleaf**, and **Spring Boot DevTools**.
4. Click **Generate**.
5. Download the resulting ZIP file, which is an archive of a web application that is configured with your choices.
### Step 2: Create a Web Controller
In Spring's approach to building web sites, HTTP requests are handled by a controller. You can easily identify the controller by the [@Controller](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/stereotype/Controller.html) annotation. In the
following example, `EchoController` handles GET requests for `/echo` by returning
the name of a [View](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/servlet/View.html) (in this case, `echo`). A `View` is responsible for
rendering the HTML content.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.1/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.1/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#web)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#web.servlet.spring-mvc.template-engines)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#using.devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

