import React, { useEffect, useState } from 'react';
import PersonEditor from './components/PersonEditor';
import * as PersonsDataService from './services/PersonDataService';


function Data(){
	const idToFetch =  "fefbf428-714b-4eb7-9c91-9dddf5af770d";
	const emptyPerson = {"id": "","moniker": "","firstname": "","lastname": "","otherNames": []};	
	const [currentPerson, setCurrentPerson] = useState(emptyPerson);
	const [jsonString, setJsonString] = useState("");

	useEffect(() =>{
		setJsonString(JSON.stringify(currentPerson, null, 2));
	},[currentPerson]);
	
	useEffect(() =>{
		fetchFromSource();
	},[setCurrentPerson]);
	
	const fetchFromSource = () => {
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
				fetchFromSource(); //Discard changes and refresh the data
				break;
			default:
				fetchFromSource(currentPerson.id); //Discard changes and refresh the data
				break;				
		}
	};
	
	return(
		<table>
			<tbody>
				<tr>
					<td>
						<PersonEditor person={currentPerson} onChange={onChangeOfCurrentPerson} onSubmit={onSubmitOfCurrentPerson} />
					</td>
				</tr>
				<tr>
					<td>
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