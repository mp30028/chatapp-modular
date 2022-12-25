
const doSave = (currentData, eventData) =>{
	var personToSave = null;
	var existsInData = false;
	var update = (item) => {	if(item.id === personToSave.id) {
									existsInData = true; 
									return personToSave; 
								}else{
									return item;
								} 
							};
	for(let j = 0; j < eventData.persons.length; j++){
		personToSave = eventData.persons[j];
		existsInData = false;
		var mappedPersons = currentData.map(update);	
		if (!existsInData){
			mappedPersons.push(personToSave);
		}
		currentData = mappedPersons;
	}
	return currentData;			
}

const doDelete = (currentData, eventData) =>{
	var personToDelete = null;
	const toKeep = (item) => (item.id !== personToDelete.id);
	for(let k =0; k < eventData.persons.length; k++){
		personToDelete = eventData.persons[k];
		var filteredPersons = currentData.filter(toKeep);
		currentData = filteredPersons;
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