# Introduction

*Movie Ticketing System (MovieMazza) is a Swing and AWT based Java GUI application that allows users to scroll 
through latest released movies and allow them to buy tickets at a reasonable price, The project focuses on different aspects of GUI and 
Database applications like fast responding GUI, transaction handlings, and some basic security aspects for securing the application.*

## Technologies Used
- Javax Swing
- Java AWT
- Java SQL
## Features
- *User driven GUI application, for user usability, efficiency and faster response.*
- *Ticket booking and payment integrated within the application.*
- *Use of Singleton Architecture to creating Many Data Base connection classes and Data Base Operation classes to minimize memory usage and enhance concept of object reusability.*
- *Let users track their bookings, and get up to date with their movie ticket booking payments.*
- *Let user change their personal information , and also password for security measures.*
## Limitations And Further Development
- *Currently Lacks other effective ways for notifying users so we plan to add features , such as mail through Java Mail API to notify customers about their bookings and payments.*
- *Currently Lacks proper security measures, currently only password based authentication is in place, to further secure the application we can use authorization concepts and rules and policies implementation.*
- *Currently Lacks proper session management, currently only user will authenticate and object corresponding to an authenticated User gets created which acts as security object.*
- *Currently Lacks proper payment system, so we can integrate 3rd party -payment systems in the future.*



## Project Structure
![project structure](https://github.com/narotsitk/Movie-Ticketing-System/blob/main/images/Screenshot%202024-02-22%20233058.png?raw=true)

*Project Structure for the Movie Ticketing System is divided into mainly Two Parts / Packages.*
### BACKEND
![backend structure](https://github.com/narotsitk/Movie-Ticketing-System/blob/main/images/Screenshot%202024-02-22%20233330.png?raw=true)

*The Backend package has 3 main parts:*
- #### models:
    - *This package consists of all the class representation of the tables in the database.*
- #### operations:
    - *This package consists of interfaces and class implementations of database operation on tables defined in the models class.*
- #### DBconnection:
    - *This class is what helps to create singleton database connection.*
### GUI
![gui structure](https://github.com/narotsitk/Movie-Ticketing-System/blob/main/images/Screenshot%202024-02-22%20233917.png?raw=true)

  *The GUI also called the front end is the part where UI/UX handling takes place.*
  -	*account class for user account setting and changing password , email and other info.*
  -	*app class is the entry point for the whole project ( i.e starter for the application).*
  - *home class is the main frame which consists of all other components.*
  -	*login , register and logout are basic forms for user authentication and user registration.*
  -	*movieCard is a component that displays movie information in a card like structure.*
  -	*movieList components shows all the movies in Db as list of movieCard.*
  -	*your_bookings shows list of userbookings (i.e list of bookings made from user)*.  

## Database Schema
*Here is the Class Diagram for the Movie Ticketing System Project.*
![database schema](https://github.com/narotsitk/Movie-Ticketing-System/blob/main/images/Untitled.png?raw=true)

## Project Documentation
[Project Documentation](https://github.com/narotsitk/Movie-Ticketing-System/blob/main/Java_GUI_Project.pdf)
