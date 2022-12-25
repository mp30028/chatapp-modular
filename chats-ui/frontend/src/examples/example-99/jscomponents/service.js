//Iinstall the dotenv packagae as per instructions at https://github.com/motdotla/dotenv locally before using process.env 
// npm install dotenv --save
// Once installed creat a ".env" file in the folder where this script resides and make the following entry in it.
// TEST_STRING=this is a test string in the dot env file
// Add the next two lines of code to initialise
import * as dotenv from 'dotenv' // see https://github.com/motdotla/dotenv#how-do-i-use-dotenv-with-import
dotenv.config();

//Now you can read the environment variables (from .env file if not set) as follow
const testString = process.env.TEST_STRING;
const baseUrl = process.env.PERSONS_API_BASE_URL;
//const baseUrl = "http://192.168.1.60:9999";

const ssePath = "/api/persons/persistence-events";
const apiPathToGetAll = "/api/persons";
const apiPathToUpdate = "/api/persons/{id}";
const apiPathToDelete = "/api/persons/{id}";
const apiPathToAddNew = "/api/persons";


export const getTestString = () => testString;
export const getEventsPath = () => ssePath;
export const getBaseUrl = () => baseUrl;
export const getFetchAllPath = () => apiPathToGetAll;
export const getUpdatePath = () => apiPathToUpdate;
export const getDeletePath = () => apiPathToDelete;
export const getAddNewPath = () => apiPathToAddNew;

export const getFetchAllUrl = () => {
	return getBaseUrl() + getFetchAllPath();	
};


export const fetchPersons = () => {
	return (fetch(
		getFetchAllUrl(), {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			'Accept': 'application/json, text/plain'
		}
	})
	.then((response) => response.json())
	.finally((data) => data))
};


export const fetchPersonsAsync = async () => {
	const response = await fetch(
		getFetchAllUrl(), {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			'Accept': 'application/json, text/plain'
		}
	});
	return await response.json();
}