# Number generator 

## Introduction
Number generator system is a web service that exposes REST API to perform following operations -
 - create number generation task  : API to create task for number generations
 - create bulk number generation task : API to create task with multiple number generations
 - get status of task : API to get status of task. 
 - get task result : API to get the result of task if completed, if not returns the status of task.

## Design 

## How to run 
- clone the repository and run following commands to start service
```sh
$ mvn clean install
$ mvn spring-boot:run
```
- Use rest client such as Postman to test the APIS, API collection available at https://github.com/nareshm87/number-generator/blob/main/Number-Generator.postman_collection.json
## API collection and test Inputs 
- create number generation task :  http://localhost:8080/api/generate
- create bulk number generation task : http://localhost:8080/api/bulkGenerate
- get status of task : http://localhost:8080/api/tasks/{task-id}/status
- get task result : http://localhost:8080/api/tasks/{task-id}?action=get_numlist
