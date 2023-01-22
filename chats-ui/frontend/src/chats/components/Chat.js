import React, { useState } from 'react';
import Conversations from './conversations/Conversations';
import Messages from './messages/Messages';

function Chat(props){
	const emptyConversation = {id: "", title: "", participants: [], messages:[]};
		
	const [currentConversation, setCurrentConversation] = useState(emptyConversation);
		
	const conversationSelectionHandler = (conversation) =>{
		setCurrentConversation(conversation);		
	}
	
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
						<Conversations moniker={props.username} selectionHandler={conversationSelectionHandler}/>
					</td>
					<td style={{width: "80%", height:"500px",verticalAlign: "top"}}>
						<Messages conversation={currentConversation}  />
					</td>
				</tr>	
			</tbody>
		</table>
	);
};

export default Chat;