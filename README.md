# INFORMATION RETRIEVAL AND WEB SEARCH
## Assignment 1: Lucene and Cranfield


The AWS instance contains the source code in the LuceneCranfield folder.

Link to github repository: https://github.com/srijang97/LuceneCranfield/
  
## Login Credentials to the AWS instance
- Username: srijan
- Password: srijan

## AWS Instance's IP address
 - IPv4 Public IP: **3.80.163.47**
 - Public DNS (IPv4): **ec2-3-80-163-47.compute-1.amazonaws.com**

## How to access the AWS instance: 
  ```sh
Copy the following commands in the terminal to connect.
  
$ ssh srijan@ec2-3-80-163-47.compute-1.amazonaws.com

Enter password: srijan
```

Now you are inside the AWS instance. Again copy the commands and run at a time

## How to build the source code

 ```sh
$ cd LuceneCranfield

$ mvn package


$[sudo] password for srijan: srijan (IF PROMPTED)
```
## How to run the source code
 ```sh
$ java -jar target/Assignmentt1-0.0.1-SNAPSHOT.jar
```
## How to run trec-eval
```sh
$ cd src/trec_eval-9.0.7

$ make

$ ./trec_eval test/Qrel.test ../results.out
```

## To display only Mean Average Precision (MAP) & Recall
```sh
$ ./trec_eval -m map -m recall test/Qrel.test ../results.out
```

