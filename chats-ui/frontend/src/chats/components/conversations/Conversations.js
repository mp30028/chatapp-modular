import React, { useState, useEffect } from 'react';
import * as DataService from "../../services/ConversationDataService"

function Conversations(props){
	const [conversations, setConversations]= useState([]);
//	const [selectedConversation, setSelectedConversation]= useState(null);
	console.log("props.moniker=",props.moniker);
	useEffect(() => {	
		DataService.fetchByMoniker(props.moniker).then((data) => setConversations(data));
	}, [setConversations]);	

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
					</tr>
				)}
			</tbody>
	</table>
	);
};

export default Conversations;