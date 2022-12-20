import React, {useState, useEffect, useRef} from 'react';
import * as FetchService from "./services/FetchService";
import * as EventListenerService from "./services/EventListenerService";
function Data(){
	const emptyPerson = {
							"firstname": "",
							"id": "NOT-SELECTED",
							"lastname": "",
							"moniker": "",
							"otherNames":[]
						}
	const personsRef = useRef([]);
	const [persons, setPersons]= useState([]);
	const [selectedPerson, setSelectedPerson]= useState(emptyPerson);
		
	useEffect(() =>{
		personsRef.current = persons;
	},[persons]);
	
	useEffect(() => {
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
		
		const updatePersons = (eventData) =>  {
			var currentData = personsRef.current;
			if (eventData.eventType === "SAVE"){
				currentData = doSave(currentData, eventData);
			}else if (eventData.eventType === "DELETE"){
				currentData = doDelete(currentData, eventData);
			}
			setPersons(currentData);
		};
		
		const onmessageHandler = (event) =>{
			var eventData = JSON.parse(event.data);
			updatePersons(eventData);
		};
		
		FetchService.fetchPersons().then((data) => setPersons(data));;
		EventListenerService.setupEventSource(onmessageHandler);
		
	}, [setPersons]);


	const personRowClicked = (selectedRow) => {
		console.log(selectedRow.moniker);
		setSelectedPerson(selectedRow);
	}
		
		return(
			<div style={{width: "100%"}}>
				<table>
					<thead>
						<tr>
							<th>ID</th>
							<th>Moniker</th>
							<th>Firstname</th>
							<th>Lastname</th>
							<th>Other-Names</th>
						</tr>
					</thead>
					<tbody>
 
						<tr>
							<td colSpan="2">
								<table>
									<tbody>
										<tr><th>ID</th><td>{selectedPerson.id}</td></tr>
										<tr><td>Moniker</td><td>{selectedPerson.moniker}</td></tr>
										<tr><td>Firstname</td><td>{selectedPerson.firstname}</td></tr>
										<tr><td>Lastname</td><td>{selectedPerson.lastname}</td></tr>
										<tr><td>Other-Names</td><td>
																	{selectedPerson.otherNames
																		.map(o => 
																			<div key={o.id}>
																				{o.nameType + ": " + o.value}
																			</div>
																		)
															  		}
																</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>

						{persons.map(p =>
							<tr key={p.id} onClick={()=> personRowClicked(p)}>
								<td style={{ width: "10%" }}>{p.id}</td>
								<td style={{ width: "10%" }}>{p.moniker}</td>
								<td style={{ width: "10%" }}>{p.firstname}</td>
								<td style={{ width: "10%" }}>{p.lastname}</td>
								<td style={{ width: "10%" }}>{p.otherNames.map(o => 
																					<div key={o.id}>
																						{o.nameType + ": " + o.value}
																					</div>
																				)
															  }
								</td>
							</tr>
						)}
					</tbody>
				</table>
			</div>
		);
};

export default Data;