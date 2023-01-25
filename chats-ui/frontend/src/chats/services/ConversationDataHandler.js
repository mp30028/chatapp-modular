
const doSave = (currentData, eventData) =>{
	var conversationToSave = null;
	var existsInData = false;
	var update = (item) => {	if(item.id === conversationToSave.id) {
									existsInData = true; 
									return conversationToSave; 
								}else{
									return item;
								} 
							};
	for(let j = 0; j < eventData.conversations.length; j++){
		conversationToSave = eventData.conversations[j];
		existsInData = false;
		var mappedConversations = currentData.map(update);	
		if (!existsInData){
			mappedConversations.push(conversationToSave);
		}
		currentData = mappedConversations;
	}
	return currentData;			
}

const doDelete = (currentData, eventData) =>{
	var conversationToDelete = null;
	const toKeep = (item) => (item.id !== conversationToDelete.id);
	for(let k =0; k < eventData.conversations.length; k++){
		conversationToDelete = eventData.conversations[k];
		var filteredConversations = currentData.filter(toKeep);
		currentData = filteredConversations;
	}
	return currentData;	
}

export const doUpdate = (currentData, eventData) =>  {
	if (eventData.eventType === "SAVE"){
		currentData = doSave(currentData, eventData);
	}else if (eventData.eventType === "DELETE"){
		currentData = doDelete(currentData, eventData);
	}
	return currentData;
};