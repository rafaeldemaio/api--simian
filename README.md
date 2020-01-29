# API--SIMIAN

## API REST to define if determined DNA is simian or human

The API is published in Google App Engine (https://meli-simian.appspot.com)

- **/simian** Endpoint to define if DNA is Simian or not
	- Method: POST
	- RequestBoby: application/json
		- Exemple: { "dna": ["CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"] }
	- Produces: application/json

- **/simian/status** Endpoint to retrive the status of all DNAs analized
	- Method: GET
	- Produces: application/json
	

## The project was build using this technologies:

- Java 1.8
- Spring Boot 2.2.0
- Lomobok 1.18.10
- JUnit 5
- Mockito 3
- Google App Engine
- Firestore 

