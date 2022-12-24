import React, { useEffect, useState } from 'react';
import OtherNamesEditor from './components/OtherNamesEditor';


function Data(){
	
	const initialValue = {
		"id": "e30e5061-7b02-436e-8290-4e70cedcb3f3",
		"moniker": "Turtle",
		"firstname": "Lesley",
		"lastname": "CAMERON",
		"otherNames": [
			{
				"id": "17778483-9a1e-470a-9e64-aed0a9ab310d",
				"value": "Sophia",
				"nameType": "MIDDLE_NAME"
			},
            {
                "id": "6264c91a-acd3-45e8-8d64-789efea0c975",
                "value": "Isabella",
                "nameType": "MIDDLE_NAME"
            },
            {
                "id": "43568147-9ce0-4fe5-877a-107c72b87611",
                "value": "Mary",
                "nameType": "MIDDLE_NAME"
            },
            {
                "id": "51c23ffc-98bc-4d81-b1cb-295e04ef3c6a",
                "value": "Cam",
                "nameType": "NICK_NAME"
            }           			
		]
	};
	
	
	const [currentPerson, setCurrentPerson] = useState(initialValue);
	const [jsonString, setJsonString] = useState("");


	useEffect(() =>{
		setJsonString(JSON.stringify(currentPerson, null, 2));
	},[currentPerson]);
	
	const randomTrueFalse = () => (Math.random() < 0.5);
	
	const getIdNameAndValueFromEvent_1 = (event) => {
		const id = event.target.id;
		const name = event.target.name;
		const value = event.target.value; 
		return {id, name, value};
	}

	const getIdNameAndValueFromEvent_2 = (event) =>{
		const {id, name, value} = event.target;
		return {id, name, value};
	};
	
	const getIdNameAndValueFromEvent = (event) =>{
		// for illustrative purposes have shown two ways of 
		// reading values from event
		if (randomTrueFalse){
			return getIdNameAndValueFromEvent_1(event); // if true use method 1
		}else{
			return getIdNameAndValueFromEvent_2(event); // else use method 2
		}
	};
	
	const onChangeOfOtherNames = (event) => {
		// Get the updated values from the event
			const {id, name, value} =getIdNameAndValueFromEvent(event);
			
		// Find the value that needs to updated. Find creates a copy
			const otherNameToUpdate = currentPerson.otherNames.find(o => (o.id === id) ? o : null);
			
		// Do the update to the found item - remember this is a copy so the update is to the copied item
			const updatedOtherName =  {...otherNameToUpdate, [name]:value};
		
		// Replace the item with updated copy. The replacement creates a copy of otherNames
			const replaceIf = (item) => item.id === id ? updatedOtherName : item;
			const updatedOtherNames = currentPerson.otherNames.map(item => replaceIf(item));
			
		// Create a copy of the current person
			const currentPersonCopy = {...currentPerson};
		
		// Update the copy with the copy of otherNames
			currentPersonCopy.otherNames = updatedOtherNames;
			
		// replace the current person with the updated copy
			setCurrentPerson(currentPersonCopy);
	}

	const onDeleteOfOtherNames = (event) => {
		const {id, name, value} = getIdNameAndValueFromEvent(event);
		const isToBeKept = (item) => item.id !== id;
		const filteredOtherNames = currentPerson.otherNames.filter(isToBeKept);
		const currentPersonCopy = {...currentPerson};
		currentPersonCopy.otherNames = filteredOtherNames;
		setCurrentPerson(currentPersonCopy);
	}
	
	const onAddOfOtherNames = (event) => {
		const tempId = "tempid_" + (new Date().getTime());
		const emptyOtherName =  { id: tempId, value : "", nameType : ""};
		const currentPersonCopy = {...currentPerson};
		const updatedOtherNames = currentPersonCopy.otherNames.concat(emptyOtherName)
		currentPersonCopy.otherNames = updatedOtherNames;
		setCurrentPerson(currentPersonCopy);
	}
	
	return(
		<table>
			<tbody>
				<tr>
					<th>Person ID</th>
					<td>
						{currentPerson.id}
					</td>
				</tr>
				<tr>
					<th>Moniker</th>
					<td>{currentPerson.moniker}</td>
				</tr>
				<tr>
					<th>Firstname</th>
					<td>{currentPerson.firstname}</td>
				</tr>
				<tr>
					<th>Lastname</th>
					<td>{currentPerson.lastname}</td>
				</tr>	
				<tr>
					<th>Other Names</th>
					<td className="subtableContainer">
						<OtherNamesEditor onChange={onChangeOfOtherNames} onDelete={onDeleteOfOtherNames} onAdd={onAddOfOtherNames} otherNames={currentPerson.otherNames}/>
					</td>
				</tr>
				<tr>
					<td colSpan={2}>
					<pre>
						{jsonString}
					</pre>
					</td>
				</tr>								
			</tbody>
		</table>

	);
};

export default Data;