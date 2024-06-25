# RaspberryDataServer
A simple Spring boot data storage server made with Raspberry that you can use with all the device in the same network!

Status: In Progress <img src="https://github.com/SbrakkDev/iconeReadme/blob/main/In_progress.png" alt = "In Progress" width="10" height="10">

## Requirements

 <img src="https://github.com/SbrakkDev/iconeReadme/blob/main/Raspberry.png" alt = "Raspberry" width="60" height="60"> <img src="https://github.com/SbrakkDev/iconeReadme/blob/main/java.png" alt="java" width="60" height="60"> <img src="https://github.com/SbrakkDev/iconeReadme/blob/main/Spring.png" alt = "Spring" width="60" height="60"> <img src="https://github.com/SbrakkDev/iconeReadme/blob/main/Mysql.png" alt="MySql" width="60" height="60">  

# Initialize the Raspberry

- Mount the latest Debian OS
- Install the JDK: ```$ sudo apt install default-jdk```
- Install Maven: ```$ sudo apt install maven```
- Set root user password to secure Mysql installation: ```$ sudo mysql_secure_installation```
- Access the Mysql server: ```sudo mysql -u root -p```
- Create the database: ```CREATE DATABASE dataStoragePi;```

# Running the application

- Run the deployed application from the raspberry: ```$ java -jar "deployed_app"```
- Create the Storage directory in the raspberry desktop
- Connect to the device through the raspberry local ip

## Service available 
- /upload endpoint to upload the file
- /download?file="file_name.extension" endpoint to download the file


# Roadmap

- Files entity ✔
- service to Upload a file ✔
- service to Download a file ✔
- duplicate file management ✔
- delete from db and raspberry ✔
- FilesEvent entity ✔
- service to create a directory
- service to rename a file ✔
- service to delete a file ✔
- service to search files by various variables
