const baseUrl = "http://localhost:9999";
const apiPath = "/api/persons";

export const getBaseUrl = () => baseUrl;
export const getPath = () => apiPath;

export const getUrl = () => {
	return getBaseUrl() + getPath();	
};

export const fetchAll = async () => {
	const response = await fetch(
		getUrl(), {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			'Accept': 'application/json, text/plain'
		}
	});
	return await response.json();
}

export const fetchById = async (person) => {
	const response = await fetch(
		getUrl() + "/" + person.id, {
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
		getUrl(),
		{
			method: 'POST',
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain'
			},
			body: JSON.stringify(person)
		}
	);
}

export const remove = async (person) => {
	await fetch(
		getUrl() + "/" + person.id,
		{
			method: 'DELETE',
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain'
			}
		}
	);
}

export const update = async (person) => {
	const jsonString = JSON.stringify(person, null, "    ");
	await fetch(
		getUrl() + "/" + person.id,
		{
			method: 'PUT',
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain'
			},
			body: jsonString
		}
	);
}
