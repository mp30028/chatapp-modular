import * as ApiPaths from "./service.js";

console.log("test-string = ", ApiPaths.getTestString());

console.log("base-url = ", ApiPaths.getBaseUrl());

ApiPaths.fetchPersons().then( (data) => console.log("fetched-persons = ", data ) );

ApiPaths.fetchPersonsAsync().then( (data) => console.log("fetched-persons-async = ", data ) );