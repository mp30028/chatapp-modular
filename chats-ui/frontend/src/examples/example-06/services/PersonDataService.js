const baseUrl = "http://localhost:9999";
const apiPath = "/api/persons";

export const getBaseUrl = () => baseUrl;
export const getPath = () => apiPath;

export const getPersonsUrl = () => {
	return getBaseUrl() + getPath();	
};

export const fetchPersons = async () => {
	const response = await fetch(
		getPersonsUrl(), {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			'Accept': 'application/json, text/plain'
		}
	});
	return await response.json();
}

export const save = async (person) => {
	await fetch(
		getPersonsUrl(),
		{
			method: 'POST',
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain'
			},
			body: JSON.stringify(person)
		}
	);
//	return await response.json();
}

export const remove = async (person) => {
	await fetch(
		getPersonsUrl() + "/" + person.id,
		{
			method: 'DELETE',
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain'
			}
		}
	);
//	return await response.json();
}

export const update = async (person) => {
	const jsonString = JSON.stringify(person, null, "    ");
	await fetch(
		getPersonsUrl() + "/" + person.id,
		{
			method: 'PUT',
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain'
			},
			body: jsonString
		}
	);
//	return await response.json();
}
