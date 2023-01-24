import React, { useState, useEffect } from 'react';
import * as DataService from "../../../services/ConversationDataService"

function Dialogue(props){
	const [messages, setMessages] = useState([]);
	const [participants, setParticipants] = useState([]);
	
	useEffect(() =>{
		const sortedMessages = props.messages.sort((a,b) => (a.sentTime <= b.sentTime)? -1 : 1);
		setMessages(sortedMessages);
	},[props.messages]);
	
	const enrichAndSetParticipants = (inParticipants) => {
		if(Array.isArray(inParticipants)){
			const ids = inParticipants.map(({personId}) => personId);
			if (inParticipants.length > 0){
				DataService.fetchPersonsByIds(ids).then((data) => setParticipants(data));
			}
		}
	}
	
	useEffect(() =>{
		enrichAndSetParticipants(props.participants);
	},[props.participants]);	
	
	const getSenderMoniker = (senderId) =>{
		let foundParticipant = participants.find((p) => (p.id === senderId));
		return (foundParticipant)? foundParticipant.moniker : senderId;
	};
	
	return(
		<main>
			<h2  style={{display:"block", backgroundColor: "#777", color: "white"}}>Messages</h2>			
			<ul>
				{messages.map( (m) =>
					<li key={m.id}>
						<div>
							<h3 style={{display:"inline"}}>{getSenderMoniker(m.senderPersonId)}</h3> {m.sentTime}
						</div>
						<blockquote>
							<p>{m.messageText}</p>
						</blockquote>
					</li>
				)}
			</ul>
		</main>
	);
};

export default Dialogue;