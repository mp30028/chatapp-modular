import * as ServicePaths  from "../../common/js/ServicePaths";

const INITIAL_WAIT_SECONDS = 1;
const MAX_WAIT_SECONDS = 64;
var retryAfter = INITIAL_WAIT_SECONDS;
var sseSource = null;
var messageHandler = null;	

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
	
	sseSource = new EventSource(ServicePaths.getPersistenceEventsUrl("CHATS"));
	sseSource.onmessage = messageHandler;
	sseSource.onopen = onopenHandler;
	sseSource.onerror = onerrorHandler;
};
