import React, { useState, useEffect } from 'react';


function Dialogue(props){
//	const emptyMessage = {id: "",messageText: "",senderPersonId: "",sentTime: ""};
	const [messages, setMessages] = useState([]);
	
	useEffect(() =>{
		setMessages(props.messages);	
	},[props.messages]);
	
	return(
		<main>
			<h2  style={{display:"block", backgroundColor: "#777", color: "white"}}>Messages</h2>
				
			<pre>
				{(messages)? JSON.stringify(messages,null,2): ""}
			</pre>

		</main>
	);
};

export default Dialogue;