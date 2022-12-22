import React, {useState, useEffect,useRef} from 'react';

const Logger = (props) => {
	const displayLogRef = useRef("");	
	const [displayLog, setDisplayLog] = useState("---- Logging Started at " +  new Date().toLocaleString() + " ----");
	
	useEffect(()=>{
		const msg = new Date().toLocaleString() + ":   " + props.message + "\n" +  displayLogRef.current;
		setDisplayLog(msg);	
		displayLogRef.current = msg;
	},[props.message]); 
	


	
	return(
		<div style={{ width: "100%", fontSize: "8pt", whiteSpace: "pre-line" }}>
			{displayLog}
		</div>
	);
}

export default Logger;