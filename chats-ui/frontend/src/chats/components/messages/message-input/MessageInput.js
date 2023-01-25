import React, { useState,useEffect } from 'react';
import * as DataService from "../../../services/ConversationDataService";

function MessageInput(props){
	const emptyUser = {id:"", moniker:""};
	const defaultMessage = "Type your message here";
	const conversationId = props.conversationId;
	const [messageText,setMessageText] = useState(defaultMessage);
	const [currentUser, setCurrentUser]=useState(emptyUser);
	
	const onChangeHandler = (event) =>{
		event.preventDefault();
		setMessageText(event.target.value)
	};
	
	useEffect(() =>{
		setCurrentUser(props.user);
	},[props.user]);
	
	const handleSubmit = (event) =>{
		event.preventDefault();
		const {name, value} = event.target;
		console.log("name=", name, "value=", value);
		const newMessage = {id: null, senderPersonId: currentUser.id, messageText: messageText, sentTime: null};
		DataService.updateWithNewMessage(conversationId, newMessage).then((data) => setMessageText("Message sent successfully. " + defaultMessage));
//		props.onSubmit({event, name, value});
	}
	
	return(
		<form>
			<h3 style={{display:"inline"}}>Message Input</h3>  {conversationId}  {currentUser.id}
			<textarea rows="5" wrap='true' value={messageText} onChange={onChangeHandler} style={{width:"99%", height:"100%", border:"0", resize:"none" }}>
			
			</textarea>
			<button type="submit" onClick={handleSubmit} name="submit" value="SEND">Send</button>
		</form>
	);
};

export default MessageInput;