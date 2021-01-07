# Number generator 

## Introduction
Number generator system is a service that exposes REST API to perform following operations -
 - create number generation task  : API to create task for number generations
 - create bulk number generation task : API to create task with multiple number generations
 - get status of task : API to get status of task. 
 - get task result : API to get the result of task if completed, if not returns the status of task.

## Design 
Number generation system uses in memory H2 database to save the tasks/bulk tasks created by task API. The state of task is set to QUEUED when the task is created. 
A scheduled task processor (runs every 10 seconds by default) fetches all the task requests in QUEUED state for processing. The task state is changed to IN_PROGRESS when task is being processed. The number generator method will generate the number sequence and sleeps for a random time between 10 to 30 seconds. As a performance imporvement, the result of task is checked against the already run task and computed only if its not computed previously.

## How to run 
- clone the repository and run following commands to start service
```sh
$ mvn clean install
$ mvn spring-boot:run
```
- Use rest client such as Postman to test the APIS, API collection available at https://github.com/nareshm87/number-generator/blob/main/Number-Generator.postman_collection.json

## API collection and test Inputs 
- create number generation task :  http://localhost:8080/api/generate

```json
{
    "goal" : 10,
    "step" : 2
}
```
- create bulk number generation task : http://localhost:8080/api/bulkGenerate
```json
[
  {
    "goal" : 100,
    "step" : 2
  },
  {
    "goal" : 10,
    "step" : 2
 }
]
```
- get status of task : http://localhost:8080/api/tasks/{task-id}/status
- get task result : http://localhost:8080/api/tasks/{task-id}?action=get_numlist
