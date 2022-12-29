import React from 'react';
import Conversations from './conversations/Conversations';
import Messages from './messages/Messages';
import MessageInput from './messages/MessageInput';

function Chat(){

	return(
		<table style={{width: "100%"}}>
			<tbody>
				<tr>
					<td style={{width: "20%", height: "600px"}} rowSpan="2" >
						<Conversations />
					</td>
					<td style={{width: "80%", height:"500px",verticalAlign: "top"}}>
						<Messages  />
					</td>
				</tr>
				<tr>
					<td>
						<MessageInput />
					</td>
				</tr>			
			</tbody>
		</table>
	);
};

export default Chat;