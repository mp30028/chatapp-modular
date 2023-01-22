import React, { useEffect, useState } from "react";
import * as DataService from "../../../services/ConversationDataService"

function Participants(props){
	const emptyParticipants = [];
	const [participants, setParticipants] = useState(emptyParticipants);
	
	const enrichAndSetParticipants = (inParticipants) => {
		if(Array.isArray(inParticipants)){
			const ids = inParticipants.map(({personId}) => personId);
			console.log("ids=", ids);
			if (inParticipants.length > 0){
				DataService.fetchPersonsByIds(ids).then((data) => setParticipants(data));
			}
		}
	}
	
	useEffect(() =>{
		enrichAndSetParticipants(props.participants);
	},[props.participants]);	
	
	
	
	const toggleCollapsible = (event) =>{
		const contentElement = event.target.nextElementSibling;
		(contentElement.style.display === "block") ? contentElement.style.display = "none": contentElement.style.display = "block"; 
	};
	
	return(
		<main>
			<div className="collapsible" onClick={toggleCollapsible}> <h2 style={{display:"inline"}}>Participants</h2> (click to see details)</div> 
			<pre className="collapsibleContent">
				{JSON.stringify(participants, null, 2)}
			</pre>
		</main>
	);
};

export default Participants;