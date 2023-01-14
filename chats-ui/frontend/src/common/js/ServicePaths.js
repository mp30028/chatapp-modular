const personsBaseUrl = process.env.REACT_APP_PERSONS_API_BASE_URL;
const personsPath = process.env.REACT_APP_PERSONS_API_PATH;
const chatsBaseUrl = process.env.REACT_APP_CHATS_API_BASE_URL;
const chatsPath = process.env.REACT_APP_CHATS_API_PATH;
const persistenceEventsSubPath = process.env.REACT_APP_PERSISTENCE_EVENTS_SUBPATH;


export const getApiUrl = (api) => {
	switch(api){
		case "CHATS":
			return chatsBaseUrl + chatsPath;
		case "PERSONS":
			return personsBaseUrl + personsPath;
		default:
			return "Invalid API specified.Can only be CHATS or PERSONS";
	}
};

export const getPersistenceEventsUrl = (api) => {
	return getApiUrl(api) + persistenceEventsSubPath;
}