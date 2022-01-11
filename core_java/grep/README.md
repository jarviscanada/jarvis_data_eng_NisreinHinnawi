
# Introduction
In this project, we develop an application similar to the Linux 
built-in grep feature, the application searches for a text pattern 
recursively in a given directory, and outputs matched lines to a file. 
The application takes three arguments.
```
- regex: a special text string for describing a search pattern.
- rootPath: root directory path.
- outFile: output file name.
```
The technologies that were used during the implementation of the project:
- IntelliJ IDEA
- Maven
- Docker
- Java
- Git/GitHub


## Quick Start
- Compile and Package the Java code with Maven:
```
mvn clean compile package
```
- Launch a JVM and run the app the application using JAR file:
```
java -cp path to/grep-1.0-SNAPSHOT.jar [regex pattern] [rootPath] [outFile]
```
- Run the application with Docker container:
```
docker run --rm {docker_user}/grep [regex pattern] [rootPath] [outFile]
```

# Implementation
## Pseudocode  
process( ) method pseudocode:
```
matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
```
## Performance Issue
When trying to read data from a big file, 
the program will fail with an outOfMemory error
which is a runtime error that occurs when the 
Java Virtual Machine (JVM) is unable to allocate an object due to 
insufficient space in the Java heap.

To fix this issue we could update the grep app interface by replacing List with Buffer or Stream.

# Test
The test was done manually on sample text files that contain 
different amounts of data. Then the results were stored in an output text file.

# Deployment
- Maven was used to package the project with all the dependencies required to 
run the application.


- Docker file was created to build a new docker image locally.


- Then the application was deployed as a DockerFile on DockerHub.

# Improvement
1. Return file name and path matched the expression.


2. Return count of the matched expression.


3. Replace the regex pattern with any input in all matched files.

