import React, { useEffect, useState } from 'react';
import PersonEditor from './components/PersonEditor';
import PersonList from "./components/PersonList";
import * as PersonsDataService from './services/PersonDataService';


function Data(){

	const emptyPerson = {"id": "","moniker": "","firstname": "","lastname": "","otherNames": []};	
	const [currentPerson, setCurrentPerson] = useState(emptyPerson);
	const [jsonString, setJsonString] = useState("");

	useEffect(() =>{
		setJsonString(JSON.stringify(currentPerson, null, 2));
	},[currentPerson]);
	
	const personSelectionHandler = (person) =>{
		setCurrentPerson(person);		
	}
	
	const fetchFromSource = (idToFetch) => {
		PersonsDataService.fetchById({"id":idToFetch})
			.then(data => {	
				setCurrentPerson(data);
			});
	}
	const onChangeOfCurrentPerson = (eventIn) => {
		const {event, name, value } = eventIn;
		event.preventDefault();
		const currentPersonCopy = { ...currentPerson };
		currentPersonCopy[name] = value;
		setCurrentPerson(currentPersonCopy);
	};
	
	const onSubmitOfCurrentPerson = (eventIn) =>{
		const {event, name, value } = eventIn;
		event.preventDefault();
		console.log("name=", name, "   value=", value, "   event=", event);
		switch (value){
			case "CREATE":
				const tempId = "tempid_" + (new Date().getTime());
				emptyPerson.id = tempId;
				setCurrentPerson(emptyPerson);
				break;
			case"DELETE":
				PersonsDataService.remove(currentPerson);
				setCurrentPerson(emptyPerson);
				break;
			case"UPDATE":
				PersonsDataService.update(currentPerson);
				break;
			case"CANCEL":
				fetchFromSource(currentPerson.id); //Discard changes and refresh the data
				break;
		}
	};
	
	return(
		<table>
			<tbody>
				<tr>
					<td style={{width: "70%", borderWidth: "0"}} >
						<PersonList selectionHandler={personSelectionHandler}/>
					</td>
					<td style={{verticalAlign: "top", borderWidth: "0"}}>
						<PersonEditor person={(currentPerson?currentPerson:emptyPerson)} onChange={onChangeOfCurrentPerson} onSubmit={onSubmitOfCurrentPerson} />
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