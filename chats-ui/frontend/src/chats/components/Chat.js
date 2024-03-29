import React, { useState } from 'react';
import Conversations from './conversations/Conversations';
import Messages from './messages/Messages';

function Chat(props){
	const emptyConversation = {id: "", title: "", participants: [], messages:[]};
		
	const [currentConversation, setCurrentConversation] = useState(emptyConversation);
		
	const conversationSelectionHandler = (conversation) =>{
		setCurrentConversation(conversation);		
	};
	
	const onConversationsUpdate = (conversations) =>{
		if (currentConversation){
			const updatedConversation = conversations.find((c) => c.id === currentConversation.id);
			if(updatedConversation){
				setCurrentConversation(updatedConversation);
			};
		}
	};
	
	return(
		<table style={{width: "100%"}}>
			<tbody>
				<tr>
					<td colSpan={2}>
						User: {props.username}
					</td>
				</tr>
				<tr>
					<td style={{width: "20%", height: "600px"}} rowSpan="2" >
						<Conversations moniker={props.username} selectionHandler={conversationSelectionHandler} updateHandler={onConversationsUpdate}/>
					</td>
					<td style={{width: "80%", height:"500px",verticalAlign: "top"}}>
						<Messages conversation={currentConversation} moniker={props.username} />
					</td>
				</tr>	
			</tbody>
		</table>
	);
};

export default Chat;