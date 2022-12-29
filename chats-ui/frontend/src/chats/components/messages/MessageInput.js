import React from 'react';


function MessageInput(){

	const onChangeHandler = (event) =>{
		console.log("event=",event);
	}

	return(
		<form>
			<textarea rows="5" wrap='true' value="Type your message here" onChange={onChangeHandler} style={{width:"99%", height:"100%", border:"0", resize:"none" }}>
			
			</textarea>
		</form>
	);
};

export default MessageInput;