# Paurus Task #2

Job application second task to resolve.

## Task description:

~~~
In the file (data set) you can find 4 columns of data (MATCH_ID, MARKET_ID, OUTCOME_ID, SPECIFIERS) separated with pipe. Your task is to put this data into DB as fast as possible, but in ordered manner (asc) for single MATCH_ID starting with first column and so on. Please add one more column - date_insert, which should signal the timestamp of insertion. If you order data within one MATCH_ID by 'date_insert', data should be ordered as specified. It's important to know that file mentioned below (data set) is just a snippet. In real environment this is represented as a steady data stream.
Data set can be found here: https://www.dropbox.com/scl/fi/fdol7apabtoc8jxcjcewt/fo_random.txt.zip?rlkey=61munv04hbp0r8ibmj9wpra0y&dl=0 Please provide min(date_insert) and max(date_insert). Please provide source code (and everything else needed) - use Java and relational database of your choice.
We're sharing another perspective to this problem for better understanding. Let's say that we mark events (data in data set) with a number (match_id) and type of the event (timestamp of processing to be exact) and another number that tells us time of occurrence/sequence in queue. Format is then: number, letter and number (i.e. 1A1 translates as: match_id 1, event type A, first event of such type 1).
A = processed in 1s
B = processed in 1ms
Queue (fills from left to right, first element to be processed is on far right):

3B3 3A2 2A2 1A4 1B3 1B2 3B1 2A1 1A1
1. What can be done to speed up the process of writing it to the output? It's important to answer this before you continue.
2. When you have the answer for the question above you have to make sure that 1A1 is written to output before 1B2, even though that (let's hope so) event 1B2 should be written before 1A1 due to processing speed.

~~~

### Prerequisites

- Java 21
- Spring boot 3.5.5
- Maven (wrapper provided)

### Run instructions

- IntelliJ:
    - IntelliJ should detect it as maven project and download dependencies
    - TasktwoApplication.java right click run (check that you have project SDK set to 21)
    - app will start on localhost:8080
    - http://localhost:8080/actuator/health should show status up.
    - swagger is available on http://localhost:8080/swagger-ui/index.html

CLI:

~~~
.\mvnw.cmd clean verify
.\mvnw.cmd spring-boot:run
~~~

### Example requests

lorem ipsum


### Clarification, thought process
- ~~On my home development machine docker is disabled because of random BSOD. I have one MySQL server in my local network. So decision is based really on convenience.~~ Will use H2 for your convenience. If this was "real development" I would configure PostgreSQL because it's known for its speed, parrallelization and I've had less concurrency issues (comparing with MySQL where I had to debug some random locks). Using PostreSQL mode of H2 because of that reason.
- I've also added local-dev properties to see what would otherwise be in .gitignore
- I'm using hibernates mechanism for creating DB. Again if this was "real development" I would use Flyway for changes and use hibernates "validate" value for validation of changes.
- 

### TODO:

- [ ] Unit tests
- [ ] Integration tests
- [ ] complete readme  