# Search Engine Client #

Client application.

Call <b>"mvn clean install spring-boot:run"</b> command to build and run this application.
By default, client runs on the <b>8081</b> port.

Default login and password to call the client API:
Login: <b>login</b>
Password: <b>pwd</b>

Call the following urls to initiate client activity:
#### 1. http://localhost:8081/api/put/document/{document}/key/{key} ####
Put document into the search engine by key.
Example: http://localhost:8081/api/put/document/testdocument string/key/testkey

#### 2. http://localhost:8081/api/get/document/key/{key} ####
Get document by key

#### 3. http://localhost:8081/api/search/{searchString} ####
Search on a string of tokens to return keys of all documents that contain all tokens in the set


You can also call other helper methods:
#### 4. http://localhost:8081/api/insertTestData ####
Inserts some test data

#### 5. http://localhost:8081/api/getAll ####
Shows all data from the server's memory: all documents and their keys that was indexed.