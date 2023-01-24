import React, { useState, useEffect } from 'react';
import Participants from './participants/Participants';
import MessageInput from './message-input/MessageInput';
import Dialogue from './dialogue/Dialogue';

function Messages(props){
	const emptyConversation = {id: "", title: "", participants: [], messages:[]};
	const [conversation, setConversation] = useState(emptyConversation);
	
	useEffect(() =>{
		setConversation(props.conversation);	
	},[props.conversation]);
	
	return(
		<main>
			
			<h2>{(conversation)?conversation.title : ""}</h2>
			
			<Participants participants={(conversation)? conversation.participants : []}/>
			
			<Dialogue 
				messages={(conversation)? conversation.messages : []} 
				participants={(conversation)? conversation.participants : []} 
			/>			
			

			
			<h3>Message Input</h3>
				<MessageInput />
		</main>
	);
};

export default Messages;