const BASE_URL = "http://192.168.1.60:9999";
const API_PATH = "/api/persons/persistence-events";
const INITIAL_WAIT_SECONDS = 1;
const MAX_WAIT_SECONDS = 64;
var retryAfter = INITIAL_WAIT_SECONDS;
var sseSource = null;
var messageHandler = null;	

export const getBaseUrl = () => BASE_URL;

export const getEventsApiPath = () => API_PATH;

export const getEventsApiUrl = () => {
	return getBaseUrl() + getEventsApiPath();	
};

const milliSecondsToWait = () => retryAfter * 1000;

const tryToSetupEventSource = () => {
    setupEventSource(messageHandler);
    retryAfter *= 2;
    if (retryAfter >= MAX_WAIT_SECONDS) {
        retryAfter = MAX_WAIT_SECONDS;
    }
};

export const setupEventSource = (onMessageHandler) => {
	messageHandler = onMessageHandler;
	const onerrorHandler = () =>{
		setTimeout(tryToSetupEventSource, milliSecondsToWait());
	};
	const onopenHandler = () =>{
		retryAfter = INITIAL_WAIT_SECONDS;
	};

	if (sseSource){
		sseSource.close();
		sseSource = null;
	}
	
	sseSource = new EventSource(getEventsApiUrl());
	sseSource.onmessage = messageHandler;
	sseSource.onopen = onopenHandler;
	sseSource.onerror = onerrorHandler;
};
