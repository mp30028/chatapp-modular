import * as ServicePaths  from "../../common/js/ServicePaths"

export const fetchByMoniker = async (moniker) => {
	const fetchUrl = ServicePaths.getApiUrl("CHATS") + "?moniker=" + moniker;
	console.log("fetch-by-moniker-url = ", fetchUrl);
	const response = await fetch(
		fetchUrl, 
		{	method: 'GET',
			headers: {
				'Content-Type': 'application/json;charset=U-8',
				'Accept': 'application/json, text/plain'
			}
		}
	);
	return await response.json();
}

export const fetchPersonsByIds = async (ids) => {
//	debugger;
	const fetchUrl = ServicePaths.getApiUrl("PERSONS") + "?id=" + String(ids);
	console.log("fetch-Persons-by-IDs = ", fetchUrl);
	const response = await fetch(
		fetchUrl, 
		{	method: 'GET',
			headers: {
				'Content-Type': 'application/json;charset=U-8',
				'Accept': 'application/json, text/plain'
			}
		}
	);
	return await response.json();
}

export const fetchPersonByMoniker = async (moniker) => {
	const fetchUrl = ServicePaths.getApiUrl("PERSONS") + "?moniker=" + moniker;
	console.log("fetchPersonByMoniker-Url = ", fetchUrl);
	const response = await fetch(
		fetchUrl, 
		{	method: 'GET',
			headers: {
				'Content-Type': 'application/json;charset=U-8',
				'Accept': 'application/json, text/plain'
			}
		}
	);
	return await response.json();
}

export const updateWithNewMessage = async (conversationId, message) => {
	const jsonString = JSON.stringify(message, null, "  ");
	await fetch(
		ServicePaths.getApiUrl("CHATS") + "/" + conversationId + "/messages",
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