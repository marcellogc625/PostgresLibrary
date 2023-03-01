# PostgresLibrary

An JavaFX-based program using Hibernate ORM to work with a PostgreSQL-based database from a book library.

# </br> How to run

#### 1. Clone this repository.
#### 2. Create the database with SQL script.
#### 3. (Optional) Add content for database with SQL script.
#### 4. Install the Maven dependances.
#### 5. Run the program by executing the 'Main' file in 'application' package.

# </br> Contents 

## 1. Main View

- When you launch the application, you'll receive the main window from the program, which shows the relation of all books present in the database, showing their ISBNs, prices, number of sequence, their author and their publisher.

![Captura de tela_2023-02-28_15-58-07](https://user-images.githubusercontent.com/109742262/222169796-c99d9b5c-2667-40c5-8ba3-1667fc0b4060.png)

## </br> 2. Add option...

> 2.1 Adding an author...

- When you chose this option, it would be open a window, which needs to receive the author data which the entry will be created on the database.

![create author](https://user-images.githubusercontent.com/109742262/222169959-948244c1-15ef-4456-94e0-d86fb0430561.png)

> 2.2 Adding an book...

 - When you chose this option, it would be open a window, which needs to receive the book data which the entry will be created on the database.

![create book](https://user-images.githubusercontent.com/109742262/222170145-e39a4ace-5c2a-4b21-bdea-fd18e843c503.png)

 > 2.3 Adding an publisher...
 
 - When you chose this option, it would be open a window, which needs to receive the publisher data which the entry will be created on the database.
 
 ![create publisher](https://user-images.githubusercontent.com/109742262/222170208-8d066840-b3a6-4bba-bc9a-49bddc7277bf.png)

 ## </br> 3. Edit option...

> 3.1 Editing an author...

- When you chose this option, it would be open a window, which the user needs to chose the author in the list to edit their data.

![edit author](https://user-images.githubusercontent.com/109742262/222170515-544b0cba-47ea-41c1-b82f-550dc2a17c94.png)

> 3.2 Editing an book...

 - When you chose this option, it would be open a window, which the user needs to chose the book in the list to edit their data.

![edit book](https://user-images.githubusercontent.com/109742262/222170826-815cd0e0-86d5-4b74-8dd7-7a46254d17e3.png)

 > 3.3 Editing an publisher...
 
 - When you chose this option, it would be open a window, which the user needs to chose the publisher in the list to edit their data.

![edit publisher](https://user-images.githubusercontent.com/109742262/222170897-10df9e34-4907-4615-84fd-a7dbde9414b7.png)

 ## </br> 4. Delete option...

> 4.1 Deleting an author...

- When you chose this option, it would be open a window, which the user needs to chose the author in the list to delete their entry.

![delete author](https://user-images.githubusercontent.com/109742262/222171188-021bb4a1-4362-4658-a4d9-cae9548a83fd.png)

- Note : It's necessary to delete all book entries on the database before to delete the author entry, if exists any book related with the author.

> 4.2 Deleting an book...

- When you chose this option, it would be open a window, which the user needs to chose the book in the list to edit their data.

![delete book](https://user-images.githubusercontent.com/109742262/222171599-85bb7742-764b-43df-9a29-e00e6a412d73.png)

 > 4.3 Deleting an publisher...
 
- When you chose this option, it would be open a window, which the user needs to chose the publisher in the list to edit their data.

![delete publisher](https://user-images.githubusercontent.com/109742262/222171645-3df5fbc7-5a49-43a6-9362-ee15f3307c7f.png)

- Note : It's necessary to delete all book entries on the database before to delete the publisher entry, if exists any book related with the publisher.

# Frameworks

1. [Maven](https://maven.apache.org) - Used for managing project. - Responsible to manage and download all dependances necessary to run the program. 
2. [Hibernate](https://hibernate.org/orm/) - Estabilishes the mapping from the Java objects with the database.
3. [JavaFX](https://openjfx.io) - Library necessary to develop the program's.
