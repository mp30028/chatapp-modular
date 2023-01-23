import React, { useEffect, useState } from "react";
import * as DataService from "../../../services/ConversationDataService"

function Participants(props){
	const emptyParticipant =   {id: "",moniker: "",firstname: "",lastname: "",otherNames: []};
	const emptyParticipants = [];
	const [participants, setParticipants] = useState(emptyParticipants);
	const [selectedParticipant, setSelectedParticipant]= useState(emptyParticipant);
	
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
		setSelectedParticipant(emptyParticipant);
	},[props.participants]);	
	
	
	
	const toggleCollapsible = (event) =>{
		const contentElement = event.target.nextElementSibling;
		(contentElement.style.display === "block") ? contentElement.style.display = "none": contentElement.style.display = "block"; 
	};
	
	const selectParticipant = (event) => {
		const targetParticipant = participants.find(p => p.id === event.target.value);
		if (targetParticipant){
			setSelectedParticipant(targetParticipant);	
		}else{
			setSelectedParticipant(null);
		}	
	}
	
	const isChecked = (targetParticipantId) =>{
		return ( selectedParticipant ? selectedParticipant.id === targetParticipantId : false);
	}
	
	return(
		<main>
			<div className="collapsible" onClick={toggleCollapsible}> <h2 style={{display:"inline"}}>Participants</h2> (click to see details)</div> 
			
			<table className="zsft-table" style={{display:"none"}}>
				<thead>
					<tr>
						<th>Moniker</th>
						<th>--</th>
					</tr>
				</thead>
					<tbody>
						{participants.map(p =>
							<tr key={p.id}>
								<td>{p.moniker}</td>
								<td>
									<input type="radio" name={"selectParticipant_" + p.id} id={"selectParticipant_" + p.id} value={p.id} onChange={selectParticipant} checked={isChecked(p.id)} />
									<label htmlFor={"selectParticipant_" + p.id} className="ellipses">...</label>
								</td>
							</tr>
						)}
					</tbody>
			</table>
			
{/* 		
			
			<pre className="collapsibleContent">
				{JSON.stringify(participants, null, 2)}
			</pre>
*/}	
		</main>
	);
};

export default Participants;