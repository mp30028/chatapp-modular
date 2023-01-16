import * as ServicePaths  from "../../common/js/ServicePaths";

export const fetchAll = async () => {
	const response = await fetch(
		ServicePaths.getApiUrl("PERSONS"), {
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
		ServicePaths.getApiUrl("PERSONS") + "/" + person.id, {
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
		ServicePaths.getApiUrl("PERSONS"),
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
		ServicePaths.getApiUrl("PERSONS") + "/" + person.id,
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
		ServicePaths.getApiUrl("PERSONS") + "/" + person.id,
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
