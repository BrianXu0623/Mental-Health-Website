
# Backend Controller 

## 1) Connection to the Database
Please note that, as version 1.0.0, the connection to the database relies on the connection to
Microsoft Azure Cloud Service for MySQL database. A firewall rule must be set prior to enable read & write access to the
cloud database. 

For future references, you can change the database connection by changing the connection information in <mark class="green_marker">application.properties</mark>.

## 2) API Endpoint

The backend application are using 3 Controllers for handling endpoints for important application entity. Those being: 
1. 🔥**UserController (/api/users/)** - is used for handling everything related to users.
2. 🔥**ThreadController (/api/threads/)** - is used for handling everything related to thread, thread tag and thread comment.
3. 🔥**AppointmentController (/api/appointments/)** - is used for handling everything related to appointments.
4. 🔥**InformationController (/)** - is used for handling everything related to the main page (information page).



### 2.1) UserController - (/api/users/)

> ❗<a>Note</a><br>
> All the parameters described in each controller should be passed from the frontend as *@RequestHeader("token")* for token<br>
> Otherwise *@RequestBody* by default


| EndPoint               | Method | Parameters                                                                                                  | Return                                                                                                          | Description                                                            |
|:-----------------------|:-------|:------------------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------|
| /register              | POST   | email: `String`, username: `String`, password: `String`                                                     | `String`: user token if the registration process success, otherwise descriptive error messages will be provided | Post method that allows user to register his/her account to the system |
| /login                 | POST   | email: `String`, password: `String`                                                                         | `String`: user token if the registration process success, otherwise descriptive error messages will be provided | Post method for verifying and log the user into the system             |
| /followers             | GET    | token: `String`                                                                                             | `List<Users>`: a list of users that followed the requested user, otherwise an empty list                        | Get method for getting a list of followers given a user token          |
| /followed              | GET    | token: `String`                                                                                             | `List<Users>`: a list of users that the requested user followed, otherwise an empty list                        | Get method for getting a list of users that the requester has followed |
| /follow                | POST   | token: `String`, toUsername: `String`                                                                       | `Boolean`: TRUE if the operation was successful, otherwise FALSE                                                | Post method that allows a user to follow another user                  |
| /mute                  | POST   | token: `String`, toUsername: `String`                                                                       | `Boolean`: TRUE if the operation was successful, otherwise FALSE                                                | Post method that allows a user to mute another user                    |
| /updateProfile         | POST   | token: `String`, newUsername: `String`, newEmail: `String`, newPhoneNumber: `String`, newBirthday: `String` | `Users`: a user object corresponded to all these changes                                                        | Post method that allows the user to update his/her profile             |
| /updatePassword        | POST   | token: `String`, oldPassword: `String`, newPassword: `String`                                               | `Boolean`: TRUE if the operation was successful, otherwise FALSE                                                | Post method that allows the user to update his/her password            |
| /profile               | GET    | token: `String`                                                                                             | `Users`: the user object requested if found, otherwise null                                                     | Get method for getting the user profile                                |
| /searchIUser           | GET    | username: `String`                                                                                          | `Users`: the user object requested if found, otherwise null                                                     | Get method for searching a specific user based on the username         |
| /getAllProfessional    | GET    | None                                                                                                        | `List<Users>`:  a list of year of user type professional                                                        | Get method for all of the professional accounts                        |
| /rateProfessional      | POST   | token: `String`,  professionalRatingRequest: `ProfessionalRatingRequest`                                    | `Long`: The average rating for the professional                                                                 | Post method for rating a professional                                  |
| /getProfessionalRating | GET    | `String`: professioanlUsername (Path variable)                                                              | `Long`: the average rating for the professional                                                                 | Get method for retrieving a professional's average rating              | 

### 2.2) ThreadController - (/api/threads/)

> ❗<a>Note</a><br>
> All the parameters described in each controller should be passed from the frontend as *@RequestBody* by default, only those of parameter token should be passed as *@RequestHeader*<br>
> The {attribute} is interpreted to be part of the url, and will be used to run certain query. For example:
> /api/threads/update/{threadId} - Using the provided threadId in the url, update its content


| Endpoint                    | Method | Parameters                                   | Return                                                                                             | Description                                                                                                                                                                                                                                 |
|-----------------------------|--------|----------------------------------------------|----------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| /create                     | POST   | context: `AppThreadInfo`                     | `AppThreadInfo`: a newly created thread if possible                                                | Post method for creating new thread                                                                                                                                                                                                         |
| /get/id/{threadId}          | GET    | None                                         | `AppThreadInfo`: the thread object associated with the requested id, otherwise null                | Get method for getting a single thread object from the database                                                                                                                                                                             |
| /get/id/tag/{threadId}      | GET    | None                                         | `List<ThreadTag>`: a list of tags associated with the thread id                                    | GET method for getting a list of tags based on requested thread id                                                                                                                                                                          |
| /get/ids                    | GET    | threadIds: `List<Long>`                      | `List<AppThreadInfo>`: a list of thread objects associated with their ids, otherwise an empty list | Get method for getting a list of threads provided by their ids                                                                                                                                                                              |
| /get/all                    | GET    | None                                         | `List<AppThreadInfo>`: a list of all existing threads in the system                                | Get method for getting all existing threads from the systems                                                                                                                                                                                |
| /get/byAuthorId             | POST   | authorId: `Long`                             | `List<AppThreadInfo>`: a list of threads matches the author id                                     | Post method for getting a list of thread given an author id                                                                                                                                                                                 |
| /get/byUserToken            | GET    | token: `String`                              | `List<AppThreadInfo>`: a list of threads matches the user token                                    | GET method for getting a list of thread given an author id                                                                                                                                                                                  |
| /search/tag                 | GET    | tagName: `Map<String, String>`               | `List<AppThreadInfo>`: a list of threads associated with the tag name                              | Get method for searching a list of thread given a tag name                                                                                                                                                                                  |
| /update/{threadId}          | PUT    | token: `String`, context: `AppThreadInfo`    | `Boolean`: TRUE if the operation was successful otherwise FALSE                                    | Put method for requesting a change in thread content, this ranging from thread content, a number of thread tags provided. It's worth noting that the requested user, must be the thread original creator or an admin to make the adjustment |
| /delete/{threadId}          | DELETE | token: `String`                              | `Boolean`: TRUE if the user is eligible to delete the thread, otherwise FALSE                      | Put method for removing a certain thread. Only the original creator and the admin can remove the thread                                                                                                                                     |
| /tag/add                    | POST   | tag: `TheadTag`                              | `ThreadTag`: the newly created tag                                                                 | Post method for adding new thread to the system                                                                                                                                                                                             |
| /tag/getAll                 | GET    | None                                         | `List<ThreadTag>`: a list of tags available in the system                                          | Get method for getting all the tags in the database                                                                                                                                                                                         |
| /tag/get                    | GET    | tagId: `Long`                                | `ThreadTag`: a single tag associated with the provided id                                          | Get method for getting a single thread tag from the database                                                                                                                                                                                |
| /tag/{tagId}/edit           | PUT    | tag: `ThreadTag`                             | None                                                                                               | Put method for editing existing thread tag                                                                                                                                                                                                  |
| /tag/{tagId}/remove         | DELETE | None                                         | None                                                                                               | Put method for removing thread tag                                                                                                                                                                                                          |
| /comment/create /           | GET    | comment: `ThreadRes`                         | `ThreadComment`: the newly created comment from the database                                       | a post method for creating a thread comment associated with a specific thread                                                                                                                                                               |
| /comment/{threadId}/getAll  | GET    | None                                         | `List<ThreadComment>`: a list of comments associated with the thread id                            | Get method for acquiring all comments associated with a specific thread id                                                                                                                                                                  |
| /comment/{commentId}/edit   | PUT    | token: `String`, newComment: `ThreadComment` | `Boolean`: TRUE if the operation is successful otherwise FALSE                                     | PUT method that allows eligible user to edit the thread comment                                                                                                                                                                             | 
| /comment/{commentId}/delete | DELETE | token: `String`                              | `Boolean`: TRUE if the operation is successful otherwise FALSE                                     | Put method that allows eligible user to remove the thread comment                                                                                                                                                                           |

### 2.3) AppointmentController - (/api/appointments/)

> ❗<a>Note</a><br>
> All the parameters described in each controller should be passed from the frontend as *@RequestHeader("token")* for token, otherwise *@RequestBody* by default
> The {attribute} is interpreted to be part of the url, and will be used to run certain query. For example:
> /cancel/{appointmentID} - Using the provided appointmentId in the url, set its status to 'cancelled'

| Endpoint                  | Method | Parameters                          | Return                                                                                                        | Description                                                                                                                                                    |
|---------------------------|--------|-------------------------------------|---------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| /make                     | POST   | appointment: `Appointment`          | `AppointmentInfo`: a newly created appointment with status 'In progress' if possible                          | Post method for creating a new appointment                                                                                                                     |
| /cancel/{appointmentID}   | PUT    | token: `String`                     | `Boolean`: TRUE if the user is eligible to cancel the appointment, otherwise FALSE                            | PUT method for setting the status of a certain appointment as 'cancelled'. Only the admin, professional and the user who created the appointment can cancel it |
| /complete/{appointmentID} | PUT    | token: `String`                     | `Boolean`: TRUE if the user is eligible to complete the appointment, otherwise FALSE                          | PUT method for setting the status of a certain appointment as 'completed'. Only the admin, professional of the certain appointment can complete it             |
| /get/{appointmentID}      | GET    | None                                | `Appointment`: the appointment object associated with the requested id, otherwise null                        | Get method for getting a single appointment object from database                                                                                               |
| /get/all                  | GET    | appointmentIds: `List<Long>`        | `List<AppointmentInfo>`: a list of appointment objects associated with their ids, otherwise an empty list     | Get method for getting a list of appointments provided by their ids                                                                                            |
| /get/byUser/              | GET    | token: `String`                     | List<AppointmentInfo>: a list of appointment objects associated with the the user id, otherwise an empty list | Get method for getting a list of appointments associated with a user id                                                                                        |                                                                                       |
| /get/byProfessionalUser/  | GET    | token: `String`                     | List<AppointmentInfo>: a list of appointment objects associated with the the user id, otherwise an empty list | Get method for getting a list of appointments associated with a professional user id                                                                           |
| /edit/{appointmentId}     | PUT    | token: `String`, apm: `Appointment` | `Boolean`: TRUE if the user is eligible to edit the appointment, otherwise FALSE                              | Put method for editing a certain appointment. Only the admin, professional and the user who created the appointment can edit it                                |
| /delete/{appointmentId}   | DELETE | token: `String`                     | `Boolean`: TRUE if the operation is successful otherwise FALSE                                                | DELETE method that allows admin to remove a certain appointment                                                                                                |


### 2.4) InformationController - (/)

> ❗<a>Note</a><br>
> All the parameters described in each controller should be passed from the frontend as *@RequestHeader("token")* for token, otherwise *@RequestBody* by default
> The {attribute} is interpreted to be part of the url, and will be used to run certain query.

| Endpoint              | Method | Parameters       | Return                                                       | Description                                                        |
|-----------------------|--------|------------------|--------------------------------------------------------------|--------------------------------------------------------------------|
| /information          | GET    |                  | `List<Information>`: All of the information on the main page | Get method that retrieve all the information on the main page      |
| /information/{infoId} | GET    | infoId: `String` | `Information`: The detail of a specific information          | Get method that retrieve a specific information based on its id.   |

### 3) Libraries and Versions
| Library                    | Version       | Description                                       |
|----------------------------|---------------|---------------------------------------------------|
| org.springframework.boot   | 3.1.3         | Spring Boot Starter for building web applications |
| org.springframework.boot   | 2.6.3         | Spring Boot Starter for Data JPA                  |
| org.springframework.boot   | 2.6.3         | Spring Boot Starter for testing                   |
| org.springframework.boot   | 2.6.3         | Spring Boot Starter for web development           |
| org.springframework.boot   | 2.6.3         | Spring Boot DevTools for faster development       |
| com.mysql                  | 8.0.23        | MySQL Connector for Java                          |
| org.springframework        | 6.0.11        | Spring Framework for dependency injection         |
| org.projectlombok          | -             | Lombok library for reducing boilerplate code      |
| com.microsoft.sqlserver    | 12.4.1.jre11  | Microsoft SQL Server JDBC Driver                  |
| com.azure.spring           | 4.11.0        | Spring Cloud Azure dependencies                   |
| org.json                   | 20230227      | JSON processing library                           |
| com.fasterxml.jackson.core | 2.15.2        | JSON data-binding library for Java                |
| io.jsonwebtoken            | 0.11.2        | JSON Web Token (JWT) library for Java             |

### 4) Java Version
- Java 17


### 5) Quick Start Guide

Follow these steps to run the Mental Health Support Website on your local machine:

### Prerequisites

1. Install Java 17 on your system.

### Backend Setup

1. Open a terminal and navigate to the project root directory.

2. Build the project using the following command:

    ```bash
    ./mvnw clean install
    ```

3. Run the backend server:

    ```bash
    ./mvnw spring-boot:run
    ```

   The backend server will start, and you can access it at `http://localhost:8080`.

### Frontend Setup

1. Navigate to the frontend directory:

    ```bash
    cd Mental_Health_Support_Website/frontend/mental_health_website
    ```

2. Make sure you have Node.js and npm installed.

3. Install the required dependencies:

    ```bash
    npm install
    ```

4. Start the frontend application:

    ```bash
    npm start
    ```

   The frontend server will start, and you can access it at `http://localhost:3000/information`.

### Accessing the Website

Visit [http://localhost:3000/information](http://localhost:3000/information) in your web browser to access the Mental Health Support Website.