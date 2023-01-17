import React from 'react';


function Messages(props){

	return(
		<main>
			<h2> Messages Panel </h2>
			<pre>
				{JSON.stringify(props.conversation,null,2)}
			</pre>
		</main>
	);
};

export default Messages;