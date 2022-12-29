import React from 'react';
import Chat from './components/Chat';


function Data(props){
	return(
		<div style={{width: "100%"}}>
			<Chat username={props.username}  />
		</div>
	);
};

export default Data;