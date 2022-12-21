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

   $ git clone https://github.com/jerryshao2012/spring-boot-echo.git
2. Run the application the gradle command

   $ cd spring-boot-echo

   $ gradle bootRun

   or $ mvn spring-boot:run
3. Test the application: try it using your browser, REST client, PostMan:
* http://localhost:8081
* http://localhost:8081/echo
* http://localhost:8081/echo?product=1111&category=dummy
* http://localhost:8081/echo?name=jerry
* http://localhost:8081/echo/1/cars
* http://localhost:8081/echoJson

Note: Click HTML response item in highlight will copy content into clipboard
Notes for Docker:
* You can use **docker build -t springt-boot-echo .** to build a Docker Image
* Use **docker images** to view images
* Use **docker run -p 8082:8081 spring-boot-echo** to run Docker Image in a Container using port 8082

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

### Step 3: Use OpenShift Action to deploy to AWS OpenShift
#### Book your reservation via the following collection on the IBM Technology Zone (ITZ):
1. https://techzone.ibm.com/collection/ibm-cloud-satellite-level-3#tab-1

    If this is your first visit to the ITZ, you will first need to agree to the terms and conditions before accessing the environment and resources.

    1a. From along the top of the page, select the Complete learning activity for IBM Cloud Satellite Enablement tab.

    1b: Scrolling down the page, click the Reserve button (in blue) located under the tile with the number 4 (“Demonstration Environment”).
 
2. From the two reservation options, select Reserve Now (unless you are planning to make use of this environment at a later date). The default reservation period will be for 3 days. If additional time is required, you can rapidly provision a new instance. Provisioning takes approximately 5-10 minutes to perform. When ready, click Submit.
3. Set Name to a unique value of your choosing.
4. Set Purpose to Practice / Self-Education.
5. Add an optional Description to the reservation.
6. Set Preferred Geography to Washington DC.
7. Specify an End Date and Time for your reservation. The default reservation period of 3 days is more than plenty to complete the coursework associated with this lab.
8. When satisfied, click Submit to finalize the request. After submitting your reservation request, you will be directed to a Thank You page with instructions to wait for the environment to be prepared. Typically this takes 5 to 10 minutes, but results may vary depending on resources available at the time of reservation.
9. You can track that status of your reservation by clicking My Reservations from the drop-down at the top of the page, as shown. If your reservation shows as Scheduled or Provisioning, it is still in the process of being deployed. Wait until the status shows as Ready before attempting to access the environment. Refresh this page periodically while awaiting the status change.

    Note: You will also receive an email from IBM Cloud when your environment is ready, with important instructions on how to access your OpenShift cluster dashboard. If you immediately get an email that your environment is expiring shortly, disregard that email.

    IMPORTANT: You must await this invitation to the ITZ – Satellite IBM Cloud account before accessing your cluster. Otherwise the OpenShift cluster will not appear in your IBM Cloud account deployment inventory.
10. Periodically refresh your email inbox until you receive a note from IBM Cloud, similar to the one documented on the right, titled Account: You are invited to join an account in IBM Cloud. Click the Join Now hyperlink to associate your IBM Cloud account with the shared IBM Cloud Satellite environment. Log in with your own IBM Cloud credentials as normal and click Accept Invite. After accepting the invitation, you will see multiple accounts (your personal account, an ITZ-Satellite account, and potentially more) from the drop-down menu shown here. After your lab reservation has expired, the ITZ-Satellite account will be removed from your list.
11. Toggle to the ITZ-Satellite account.
12. From the left-hand sidebar of the IBM Cloud dashboard, select the Resource List tab. You can expand this list by clicking the stacked bars in the top-left corner.
13. From the table of resources, click the Clusters category (which should show a value of 3 in parenthesis) to expand the list. From the clusters listed, click the name of the yl-l3-ibm-roks-1 resource to open a summary screen on the status of the deployment.
14. From the top-right corner of the summary page, click the OpenShift web console button to launch the OpenShift dashboard, from which you can begin your labs.

    Note: If the OpenShift console is not loading, check to ensure your web browser is allowing pop-ups for this (IBM Cloud) page.

#### Add OpenShift Action
1. Open your github repository and click **Actions**
2. Find **OpenShit** workflow and click **Configure**
3. Fill in **OPENSHIFT_NAMESPACE**, **APP_NAME** & **APP_PORT**. Disable **crda-scan** if you want to have a quick start
4. In github repository **Settings**, define **OPENSHIFT_SERVER** and **OPENSHIFT_TOKEN** for **Actions**
5. Commit OpenShit workflow & it is good to go!

#### CI/CD using Argo CD

1. In GitLab, create **a blank project** under [**cp4a-credit**](https://ca-tor.git.cloud.ibm.com/cp4a-credit) group
2. Setup Webhook
    1. Go to **Settings** -> **Webhooks**
    2. Enter [**https://robo-gitops-cron-pipelines.cp2022-60b41835e65227550a2031aa4f2061fc-0000.ca-tor.containers.appdomain.cloud**](https://robo-gitops-cron-pipelines.cp2022-60b41835e65227550a2031aa4f2061fc-0000.ca-tor.containers.appdomain.cloud/) in the URL field.
    3. Enter **garage-builder-secret** as the **Secret token**
    4. Make sure the **Push events** trigger is checked.
    5. Uncheck **Enable SSL Verification** option and click **Add Webhook**.
3. Create a folder under [cp4a-credit/Deployments](https://ca-tor.git.cloud.ibm.com/cp4a-credit/deployments), the same as the project name from step #1. Target branch as **main**.
4. Create a **deployment.yaml** file and save it under the folder created in step #3 copy content from [https://ca-tor.git.cloud.ibm.com/cp4a-credit/deployments/-/blob/main/echoapi/deployment.yaml](https://ca-tor.git.cloud.ibm.com/cp4a-credit/deployments/-/blob/main/echoapi/deployment.yaml). Update app pod, service, router names
5. Commit and Push your changes for [cp4a-credit/deployments](https://ca-tor.git.cloud.ibm.com/cp4a-credit/deployments) project.
6. Clone the project you created from step #1, and add your code to the project
7. Add pipeline-run.yaml file to your project similar to [pipeline-run.yaml Example](https://ca-tor.git.cloud.ibm.com/cp4a-credit/graphqlopenapi/-/blob/main/pipeline-run.yaml) and making changes according to your project name is not enough. Must update **deployment-repo** to value: “[https://ca-tor.git.cloud.ibm.com/cp4a-credit/deployments.git](https://ca-tor.git.cloud.ibm.com/cp4a-credit/deployments.git)"
8. Commit and push changes. you may have to config GitLab in your local machine through steps in GitLab for **IBM SSH key pair for GitLab**
9. Upon commit, it should trigger the pipeline under [cron-pipelines](https://console-openshift-console.cp2022-60b41835e65227550a2031aa4f2061fc-0000.ca-tor.containers.appdomain.cloud/pipelines/ns/cron-pipelines)
10. Once the pipeline is completed successfully, it will create a new image and also update deployment.yaml file with a new image tag.
11. Create Argo CD Project
    1. Go to [Argo CD](https://gitops.cp22.robobob.ca/applications) and click **Login with openshift**
    2. Click **New App** and enter your project name as the application name.
    3. Select **cron** under Project Name.
    4. Keep Sync Policy **Manual**.
    5. For the Repository URL under the Source section, enter [**https://ca-tor.git.cloud.ibm.com/cp4a-credit/deployments.git**](https://ca-tor.git.cloud.ibm.com/cp4a-credit/deployments.git)
    6. Keep revision as **HEAD**
    7. Under path, enter the folder name you created in the step private channel. (use application name)
    8. Select cluster URL as [**https://kubernetes.default.svc**](https://kubernetes.default.svc/)
    9. Enter **cron** under the namespace
    10. Click **Create** on the top.
    11. Once you see the newly created project, click **Sync** to deploy your changes.
12. Click route → Details. In LIVE MANIFEST you can find exposed public API host’s name

#### Test Enabled CORS

In browser console:
```javascript
var xhr = new XMLHttpRequest();
xhr.withCredentials = true;
xhr.addEventListener("readystatechange", function() {
    if(this.readyState === 4) console.log(this.responseText);
});
xhr.open("GET", "https://echo-api-cron.cp2022-60b41835e65227550a2031aa4f2061fc-0000.ca-tor.containers.appdomain.cloud/echoJson");
xhr.send();
```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.1/gradle-plugin/reference/html/)
  * Build the JAR file by using **./gradle build** and then run the JAR file, as follows:
  ```
    java -jar build/libs/echo-0.0.1-SNAPSHOT
  ```
  * Note for Maven: build the JAR file with **./mvn clean** package and then run the JAR file, as follows:
  ```
    java -jar target/echo-0.0.1-SNAPSHOT
  ```
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.1/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#web)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#web.servlet.spring-mvc.template-engines)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#using.devtools)
  A common feature of developing web applications is coding a change, restarting your application, and refreshing the browser to view the change. This entire process can eat up a lot of time. To speed up this refresh cycle, Spring Boot offers with a handy module known as [spring-boot-devtools](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using-boot-devtools):
  * Enables [hot swapping](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-hotswapping) 
  * Switches template engines to disable caching
  * Enables LiveReload to automatically refresh the browser
  * Other reasonable defaults based on development instead of production

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

