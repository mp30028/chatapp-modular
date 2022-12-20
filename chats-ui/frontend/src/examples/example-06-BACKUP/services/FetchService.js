const baseUrl = "http://localhost:9999";
const apiPath = "/api/persons";

export const getBaseUrl = () => baseUrl;
export const getFetchAllPath = () => apiPath;

export const getFetchPersonsUrl = () => {
	return getBaseUrl() + getFetchAllPath();	
};

export const fetchPersons = async () => {
	const response = await fetch(
		getFetchPersonsUrl(), {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			'Accept': 'application/json, text/plain'
		}
	});
	return await response.json();
}