import React, { useState } from 'react';
import * as DataService from "../services/ConversationDataService";

function Conversations(props){
	const [conversations, setConversations]= useState([]);
//	const [selectedConversation, setSelectedConversation]= useState(null);

	useEffect(() => {	
		DataService.fetchByMoniker(prop.moniker).then((data) => setConversations(data));;
	}, [setConversations]);	



	return(
		<main>
			<h2> Conversations Panel </h2>
		</main>
	);
};

export default Conversations;