import React, { useState, useEffect, useRef } from 'react';
import * as DataService from "../../services/ConversationDataService";
import * as EventListener from "../../services/ConversationListenerService";
import * as DataHandler from "../../services/ConversationDataHandler";

function Conversations(props){
	const conversationsRef = useRef([]);
	const [conversations, setConversations]= useState([]);
	const [selectedConversation, setSelectedConversation]= useState(null);
	
	useEffect(() => {	
		const onmessageHandler = (event) =>{
			var eventData = JSON.parse(event.data);
//			debugger;
			var updatedData = DataHandler.doUpdate(conversationsRef.current, eventData);
			setConversations(updatedData);
		};
		DataService.fetchByMoniker(props.moniker).then((data) => setConversations(data));
		EventListener.setupEventSource(onmessageHandler);		
	}, [setConversations]);

	useEffect(() =>{
		if (props.selectionHandler){
			props.selectionHandler(selectedConversation);
		}
	},[selectedConversation])

	useEffect(() => {
		conversationsRef.current = conversations;
		if(props.updateHandler) {props.updateHandler(conversations);};
	},[conversations]);
	
	const selectConversation = (event) => {
		const targetConversation = conversations.find(c => c.id === event.target.value);
		if (targetConversation){
			setSelectedConversation(targetConversation);	
		}else{
			setSelectedConversation(null);
		}	
	}
	
	const isChecked = (targetConversationId) =>{
		return ( selectedConversation ? selectedConversation.id === targetConversationId : false);
	}


	return(
		<table className="zsft-table" style={{width: "100%"}}>
			<thead>
				<tr>
					<th>Conversations</th>
					<th>.</th>
				</tr>
			</thead>
			<tbody>
				{conversations.map(c =>
					<tr key={c.id}>
						<td>{c.title}</td>
						<td>
							<input type="radio" name={"selectConversation_" + c.id} id={"selectConversation_" + c.id} value={c.id} onChange={selectConversation} checked={isChecked(c.id)} />
							<label htmlFor={"selectConversation_" + c.id} className="ellipses">...</label>
						</td>
					</tr>
				)}
			</tbody>
	</table>
	);
};

export default Conversations;