import React, {useState, useEffect} from 'react';

const Logger = (props) => {
//	const initialEntry = "---- Logging Started ----";
//	const displayLogRef = useRef([[new Date().toLocaleString(), initialEntry ]]);	
	const [displayLogs, setDisplayLogs] = useState([]);
	
	useEffect(()=>{
//		const msg = [new Date().toLocaleString(), props.message];
//		const updatedDisplayLogs = [...displayLogRef.current, msg];
//		setDisplayLogs(updatedDisplayLogs);	
//		displayLogRef.current = updatedDisplayLogs;
		setDisplayLogs(props.messages);
		
	},[props.messages]); 
	


	
	return(
		<div style={{ width: "100%", fontSize: "8pt", whiteSpace: "pre-line" }}>
			{
				(displayLogs ? displayLogs : [])
				.slice().reverse()
				.map((entry) => entry[0] + ":" + entry[1] + "\n")
			}
		</div>
	);
}

export const appendLog = (currentMessages, message) => {
	const msg = [new Date().toLocaleString(), message];
	const updatedLogMessages = [...currentMessages, msg];
	return updatedLogMessages;
}

export default Logger;