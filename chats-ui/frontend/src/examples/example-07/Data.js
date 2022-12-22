import React, { useState, useRef } from 'react';
import Logger, {appendLog} from '../../components/logger/Logger';



function Data(){

/*******************************LOGGING RELATED********************************** */
	const [logMessages, setLogMessages]=useState([]);
	const initialEntry = "---- Logging Started ----";
	const logMessagesRef = useRef([[new Date().toLocaleString(), initialEntry ]]);
	
	// eslint-disable-next-line
	const writeLog = (message) => {
		const updatedLogs = appendLog(logMessagesRef.current, message);
		logMessagesRef.current = updatedLogs;
		setLogMessages(updatedLogs);	
	}
/******************************************************************************* */

	
	return(
		<div>
			TODO: Put a picklist component here
			<div>
				<Logger messages={logMessages} />
			</div>
		</div>	
	);
};

export default Data;