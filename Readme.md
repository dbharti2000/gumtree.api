# API Rest-Assured Automation

I've developed a cucumber + rest-assured test which covers below :
- Create a random userId
- Make a GET request to api
- Validate status code and response contains newly generated id and id is in between 1 and 100
- Make a POST request
- Validate Status Code 201 and response returned

### Prerequisites
- Language - Java 1.8
- Java Library - Rest Assured (Version 2.7)
- Test Framework - Junit 4.12
- Build Tool - Maven
- IDE - Intellij Idea Tool

### Execution Steps

- Clone the repository or download project as a zip file
- Download all the required libraries
- Run the tests using RunTest class

OR

Through command line, use maven commands e.g.

```
mvn test

```

