import * as ServicePaths  from "../../common/js/ServicePaths";

export const fetchOtherNameTypes = async () => {
	const response = await fetch(
		ServicePaths.getApiUrl("PERSONS") + "/other-name-types", {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json;charset=UTF-8',
			'Accept': 'application/json, text/plain'
		}
	});

	var returnValue = await response
								.json()
								.then((data) => {
													return data
								});
	return returnValue;
}
