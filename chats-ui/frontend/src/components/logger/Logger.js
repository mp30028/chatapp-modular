import React, {useState, useEffect,useRef} from 'react';

const Logger = (props) => {
	const displayLogRef = useRef("");
	const [displayLog, setDisplayLog] = useState("---- Logging Started at " +  new Date().toLocaleString() + " ----");
	
	useEffect(()=>{
		displayLogRef.current = displayLog;	
	},[displayLog]);
	
	useEffect(()=>{
		const appendToLog = (logMessage) => {
			var msg = new Date().toLocaleString() + ":   " + logMessage + "\n" +  displayLogRef.current;
			setDisplayLog(msg);
		};
		appendToLog(props.message)			
	},[props.message]); 
	
	
	return(
		<pre>
			{displayLog}
		</pre>
	);
}



export default Logger;