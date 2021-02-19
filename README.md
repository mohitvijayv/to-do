# Software Design Document for To-do web application.

## Table of Contents
1. [Project Overview](##project-overview)
2. [Database schema](##database-schema)
3. [Rest API](##rest-api)

## Project Overview
To-do application is task management web application where users will have role-based access and they can create and manage their tasks.
### Features: 
    Create/Delete/Update/view Task.
	Simple spring security.
	Login and Logout.
	Registration of new user.
	Notifications when task time is <1800 seconds.
	Custom error page mapping.
	Exception handling.
### Tech Stacks:
    Angular (Frontend)
	Spring boot (Backend)
	RESTful API 
	MySQL Database
	Kubernetes
### Architecture:
We will use Angular architecture for frontend to consume REST API created by backend. Different layers of angular architecture are view templates, components and services. View Templates will be the user interface, components will hold all the data i.e., application logic, services are used to communicate with server thru RESTful API. To-do application will have 2 screens.
Our home page will be the login screen, after successful authentication user will be directed to Dashboard page.
At Dashboard user can manage their task and they can logout after clicking on logout button and they will get directed to login page after hitting logout button.

We will use Spring boot to create and expose REST API (backend). Spring data JPA will communicate with the MySQL database and handle CRUD operations. Spring REST controller will expose REST endpoints. 
We will have Controller layer, Model layer, Repository layer, Exception layer. Controller layer controls the API endpoints and contains all control information of webapp. Model layer contains data model. Exception layer contains information regarding exception handling. Repository layer encapsulates the logic required to access data sources.
```
Architecture Diagram:
View Templates ↔ Components → Services  ↔  RestAPI  ↔  Spring Rest Controller, Spring Data JPA ↔ Database
```

### Project Structure:
    Spring boot
		Controller layer
		Model layer
		Repository layer
		Exception layer
	Angular
		Components
		Services
		View layer
			Login Page
			Home Page

## Database schema
In this project, schema diagram has been divided into two parts: task management and user management.
There are two tables involved in task management i.e., task table and task_status table. For showing task for a user, we can get user info by finding all his task using findByUsername. This table will hold all the task related information. task_status table will hold all different status available.
For User management, there are 3 tables involved in the process, auth prefix is for authentication, all authentication related tables will have this prefix. auth_user will hold all user related information, auth_role will hold all the roles available. Auth_user_role will join the auth_user by many to many relationship. Auth_user_role is the junction table consists of 2 primary keys that will define each row uniquely. Auth_user_role is joined to auth_role by many to many relationship.  

|Tasks|
|----|
•	Task_id (INT) (primary key)
•	Userid (INT)
•	Task_name (VARCHAR)
•	Task_due_date (TIMESTAMP)
•	Task_description(TEXT)
•	Task_status_id(INT)

|Task_status|
|--|
•	Task_status_id(INT) (primary key)
•	Task_status_description(VARCHAR)



|auth_user|
|------|
•	User_id (INT) (primary key)
•	Username(VARCHAR)
•	First_ name(VARCHAR)
•	Last_name(VARCHAR)
•	Password(VARCHAR)     


|auth_user_role|
|------|
•	User_id(INT) (primary key)
•	Role_id (INT) (primary key)

|auth_role|
|------|
•	Role_id (INT) (primary key)
•	Role_name (VARCHAR)
•	Role_description(TEXT)


## Rest API  

To-do application’s standard API allows standard http methods against tasks and users. Below are the resource URL and functions of various http methods of our web application.

|Resource URL|GET|POST|PUT|DELETE|
|------------|-----|-----|-----|---------|
|/api/todo/v1/tasks | Returns a list of tasks| Create a new task|Update task with given task id in request body|Delete all tasks
|/api/todo/v1/tasks/task-1| Returns a specific task|not allowed (status code: 405)|not allowed|Delete a specific task|
/api/todo/v1/users | Returns a list of users| Create a new user|Update user with given user id|Delete all users
|/api/todo/v1/users/user-1| Returns a specific user|not allowed (status code: 405)|not allowed|Delete a specific user|

### Example:
``` GET /api/todo/tasks```

    Request
    userid/username
    date
    task status

```
Response
response will be JSON array of tasks matching the request filters
response will contain below mentioned properties
    task name
    username
    description
    due date
    status

```




