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
		<main>
			<h2> Conversations Panel </h2>
			<pre>
				{props.moniker + "\n"}
				
				{JSON.stringify(conversations,null,2)}
			</pre>
		</main>
	);
};

export default Conversations;