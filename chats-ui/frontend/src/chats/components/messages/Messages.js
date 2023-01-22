import React, { useState, useEffect } from 'react';
import Participants from './participants/Participants';
import MessageInput from './MessageInput';

function Messages(props){
	const emptyConversation = {id: "", title: "", participants: [], messages:[]};
	const [conversation, setConversation] = useState(emptyConversation);
	
	useEffect(() =>{
		setConversation(props.conversation);	
	},[props.conversation]);
	
	return(
		<main>
			<h2> Messages Panel </h2>
			
			<h3>{(conversation)?conversation.title : ""}</h3>
			
			<Participants participants={(conversation)? conversation.participants : ""}/>
			
			
			<h2  style={{display:"block", backgroundColor: "#777", color: "white"}}>Messages</h2>
			<pre>
				{(conversation)? conversation.id : ""}
			</pre>
			<pre>
				{(conversation)? JSON.stringify(conversation.messages,null,2): ""}
			</pre>
			
			<h3>Message Input</h3>
				<MessageInput />
		</main>
	);
};

export default Messages;