import React from 'react';


function MessageInput(){

	return(
		<form>
			<textarea rows="5" resi wrap='true' style={{width:"99%", height:"100%", border:"0", resize:"none" }}>
  				Type your message here
			</textarea>
		</form>
	);
};

export default MessageInput;