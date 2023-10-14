
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

> ❗<a>Note</a><br>
> All the parameters described in each controller should be passed from the frontend as *@RequestBody*<br>
> The {attribute} is interpreted to be part of the url, and will be used to run certain query. For example:
> /api/threads/update/{threadId} - Using the provided threadId in the url, update its content


### 2.1) UserController - (/api/users/)


### 2.2) ThreadController - (/api/threads/)

| Endpoint                    | Method | Parameters                          | Return                                                                                         | Description                                                                                                                                                                                                                                 |
|-----------------------------|-------|-------------------------------------|------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| /create                     | POST  | thread: `AppThread`                 | `AppThread`: a newly created thread if possible                                                | Post method for creating new thread                                                                                                                                                                                                         |
| /get/id                     | GET   | threadId: `Long`                    | `AppThread`: the thread object associated with the requested id, otherwise null                | get method for getting a single thread object from the database                                                                                                                                                                             |
| /get/ids                    | GET   | threadIds: `List<Long>`             | `List<AppThread>`: a list of thread objects associated with their ids, otherwise an empty list | get method for getting a list of threads provided by their ids                                                                                                                                                                              |
| /update/{threadId}          | PUT   | userId: `Long`, thread: `AppThread` | `AppThread`: the newly **updated** thread                                                      | Put method for requesting a change in thread content, this ranging from thread content, a number of thread tags provided. It's worth noting that the requested user, must be the thread original creator or an admin to make the adjustment |
| /delete/{threadId}          | PUT   | userId: `Long`                      | `Boolean`: TRUE if the user is eligible to delete the thread, otherwise FALSE                  | Put method for removing a certain thread. Only the original creator and the admin can remove the thread                                                                                                                                     |
| /tag/add                    | POST  | tag: `TheadTag`                     | `ThreadTag`: the newly created tag                                                             | Post method for adding new thread to the system                                                                                                                                                                                             |
| /tag/getAll                 | GET   | None                                | `List<ThreadTag>`: a list of tags available in the system                                      | Get method for getting all the tags in the database                                                                                                                                                                                         |
| /tag/get                    | GET   | tagId: `Long`                       | `ThreadTag`: a single tag associated with the provided id                                      | Get method for getting a single thread tag from the database                                                                                                                                                                                |
| /tag/{tagId}/edit           | PUT   | tag: `ThreadTag`                    | None                                                                                           | Put method for editing existing thread tag                                                                                                                                                                                                  |
| /tag/{tagId}/remove         | PUT   | None                                | None                                                                                           | Put method for removing thread tag                                                                                                                                                                                                          |
| /comment/{threadId}/getAll  | GET   | None                                | `List<ThreadComment>`: a list of comments associated with the thread id                        | Get method for acquiring all comments associated with a specific thread id                                                                                                                                                                 |
| /comment/{commentId}/edit | PUT | userId: `Long`, newComment: `ThreadComment`| `Boolean`: TRUE if the operation is successful otherwise FALSE | PUT method that allows eligible user to edit the thread comment | 
| /comment/{commentId}/delete | PUT   | userId: `Long`                      | `Boolean`: TRUE if the operation is successful otherwise FALSE                                 | Put method that allows eligible user to remove the thread comment                                                                                                                                                                           |


### 2.3) AppointmentController - (/api/appointments/)

<table> 
    <thead>
        <th>EndPoint</th>
        <th>Method</th>
        <th>Parameters</th>
        <th>Description</th>
    </thead>
    <tbody>
    </tbody>
</table>



---
<style> 
    body { 
        font-family: "Space Grotesk";
    }

    h1 {
        color: red;
    }

    h2 {
        color: orange;
    }

    h3 {
        color: aquamarine;
    }

    .green_marker { 
        border-radius: 5px;
        padding: 2px;
        color: black;
        background-color: #BBFABBA6;
    }

</style>
---
[//]: # (table template)
[//]: # (<table> )

[//]: # (    <thead>)

[//]: # (        <th>EndPoint</th>)

[//]: # (        <th>Method</th>)

[//]: # (        <th>Parameters</th>)

[//]: # (        <th>Description</th>)

[//]: # (    </thead>)

[//]: # (    <tbody>)

[//]: # (    </tbody>)

[//]: # (</table>)