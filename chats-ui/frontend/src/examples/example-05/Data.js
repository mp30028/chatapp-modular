import React, {useState, useEffect, useRef} from 'react';

function Data(){
	const EVENTS_URL = "http://localhost:9999/api/persons/persistence-events";
	const INITIAL_WAIT_SECONDS = 1;
	const MAX_WAIT_SECONDS = 64;
	var retryAfter = INITIAL_WAIT_SECONDS;
	var sseSource = null;
	const updateCountRef = useRef(0);
	const eventsDataRef = useRef([]);
	const displayLogRef = useRef("");
	const [eventsData, setEventsData]= useState([]);	
	const [updateCount, setUpdateCount] = useState(0);
	const [displayLog, setdisplayLog] = useState("---- Logging Started at " +  new Date().toLocaleString() + " ----");
	
	const writeLog = (logMessage) =>{
		var msg = new Date().toLocaleString() + ":   " + logMessage + "\n"+  displayLogRef.current
		displayLogRef.current = msg;
		setdisplayLog(msg);
	};

	useEffect(() =>{
				updateCountRef.current = updateCount;
	},[updateCount])
	
	useEffect(() =>{
				eventsDataRef.current = eventsData;
	},[eventsData])

	
	useEffect(() => {	
		const updateEvents = (newEvent) => {
			var updatedEvents = [...eventsDataRef.current, newEvent];
			setEventsData(updatedEvents);
			setUpdateCount(updateCountRef.current + 1);
		};
		
		const milliSecondsToWait = () => retryAfter * 1000;
	
		const tryToSetupEventSource = () => {
			writeLog("EventSource setup about to be tried");
		    setupEventSource();
		    retryAfter *= 2;
		    if (retryAfter >= MAX_WAIT_SECONDS) {
		        retryAfter = MAX_WAIT_SECONDS;
		    }
		};
	
		const waitAndReconnect = () => {
			writeLog("Will wait and try reconnect in " + milliSecondsToWait() + " milli-seconds");
			setTimeout(tryToSetupEventSource, milliSecondsToWait());
		};
		
		const onerrorHandler = () =>{
			try{
				writeLog("EventSource-Error handler invoked");
				writeLog("Start: Wait and reconnect");
				waitAndReconnect();
			writeLog("Completed: Wait and reconnect");
					}catch(err){
				writeLog(err.message);
			}
		};
		
		const onmessageHandler = (event) =>{
			try{
				var eventData = JSON.parse(event.data);
				writeLog("EventSource-Message handler invoked");
				writeLog("Start: Updating Events with event having id=" + eventData.id + " and event-type=" + eventData.eventType);
				updateEvents(eventData);
				writeLog("Completed: Updating Events with event having id=" + eventData.id);
			}catch(err){
				writeLog(err.message);
			}
		};
		
		const onopenHandler = () =>{
			try{
				writeLog("EventSource-Open handler invoked");
				writeLog("Start: Resetting retryAfter parameter which is currently set to " + retryAfter);
				retryAfter = INITIAL_WAIT_SECONDS;
				writeLog("Completed: Resetting retryAfter parameter it is now set to " + retryAfter);
			}catch(err){
				writeLog(err.message);
			}
		};
		
		const setupEventSource = () => {
			writeLog("SetupEventSource invoked");
			if (sseSource){
				writeLog("Start: sseSource close()");
				sseSource.close();
				sseSource = null;
				writeLog("Completed: sseSource close()");
			}else{
				writeLog("sseSource already null so close() will be skipped");
			}
			writeLog("Start: Creating new EventSource object with URL = " + EVENTS_URL);
			sseSource = new EventSource(EVENTS_URL);
			writeLog("Completed: Creating new EventSource object with URL = " + EVENTS_URL);
			writeLog("Start: attaching onmessage, onopen and onerror handlers to EventSource");
			sseSource.onmessage = onmessageHandler;
			sseSource.onopen = onopenHandler;
			sseSource.onerror = onerrorHandler;
			writeLog("Completed: attaching onmessage, onopen and onerror handlers to EventSource");
		};
		writeLog("About to call SetupEventSource()");
		setupEventSource();
		writeLog("Completed call to SetupEventSource()");
	
	}, [setdisplayLog]);

		return(
			<div>			
					<table style={{width: "100%"}}>
						<tbody>
							<tr>
								<td colSpan="2">
									<h1>Logs and Events Console: Enables observation of EventSource disconnect and recovery handling</h1>
									<h2>Update Count = {updateCount}</h2>
								</td>
							</tr>
							<tr>
								<td style={{width: "50%", textAlign: "center"}}>
									<h2>Logs (Most Recent first)</h2>									
								</td>
								<td style={{width: "50%", textAlign: "center"}}>
									<h2>Events (Most Recent First)</h2>
								</td>
							</tr>
							<tr>
								<td style={{width: "50%" , verticalAlign: "top"}}>									
									<pre>
										{displayLog}
									</pre>
								</td>
								<td style={{width: "50%" , verticalAlign: "top"}}>
									<pre>
										{JSON.stringify(eventsData.slice().reverse(),null,2)}
									</pre>
								</td>
							</tr>
						</tbody>
					</table>
			</div>
		);
};

export default Data;