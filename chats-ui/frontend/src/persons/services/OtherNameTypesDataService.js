const baseUrl = "http://localhost:9999";
const apiPath = "/api/persons/other-name-types";

export const getBaseUrl = () => baseUrl;
export const getPath = () => apiPath;

export const getUrl = () => {
	return getBaseUrl() + getPath();	
};

export const fetchOtherNameTypes = async () => {
	const response = await fetch(
		getUrl(), {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			'Accept': 'application/json, text/plain'
		}
	});
//	response.then((data) => writeLog("response-data-text = " + data.text())) ;
	var returnValue = await response
								.json()
								.then((data) => {
													return data
								});
	return returnValue;
}
