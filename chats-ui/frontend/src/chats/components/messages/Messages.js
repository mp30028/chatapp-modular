import React, { useState, useEffect } from 'react';
import Participants from './participants/Participants';
import MessageInput from './message-input/MessageInput';
import Dialogue from './dialogue/Dialogue';
import * as DataService from "../../services/ConversationDataService"


function Messages(props){
	const emptyConversation = {id: "", title: "", participants: [], messages:[]};
	const emptyUser = {personId:"", moniker:""};
	const [conversation, setConversation] = useState(emptyConversation);
	const [currentUser, setCurrentUser]=useState(emptyUser);
	
	const enrichAndSetUser = (inMoniker) => {
		DataService.fetchPersonByMoniker(inMoniker).then((data) => setCurrentUser(Array.isArray(data)?data[0]:emptyUser));
	}
	
	useEffect(() =>{
		setConversation(props.conversation);
		console.log("FROM-MESSAGES");	
	},[props.conversation]);
	
	useEffect(() =>{
		enrichAndSetUser(props.moniker);
	},[props.moniker]);
	
	return(
		<main>
			
			<h2>{(conversation)?conversation.title : ""}</h2>
			
			<Participants participants={(conversation)? conversation.participants : []}/>
			
			<Dialogue 
				messages={(conversation)? conversation.messages : []} 
				participants={(conversation)? conversation.participants : []} 
			/>
			
			<MessageInput 
				user={currentUser} 
				conversationId={(conversation)? conversation.id : ""} 
			/>
			
		</main>
	);
};

export default Messages;