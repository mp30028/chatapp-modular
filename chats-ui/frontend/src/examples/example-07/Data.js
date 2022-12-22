import React, {useState, useEffect, useRef} from 'react';

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
		const EVENTS_URL = "http://192.168.1.60:9999/api/persons/persistence-events";
		const FETCH_URL = "http://192.168.1.60:9999/api/persons";
		const INITIAL_WAIT_SECONDS = 1;
		const MAX_WAIT_SECONDS = 64;
		var retryAfter = INITIAL_WAIT_SECONDS;
		var sseSource = null;	
		
		const fetchPersons = () =>{
				fetch(
					FETCH_URL,{	method: 'GET',
								headers: { 
									'Content-Type': 'application/json;charset=UTF-8',
									'Accept': 'application/json, text/plain'
								}
							  }
				)
				.then((response) => response.json())
				.then((data) => setPersons(data));
		};
		
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
		
		const milliSecondsToWait = () => retryAfter * 1000;
	
		const tryToSetupEventSource = () => {
		    setupEventSource();
		    retryAfter *= 2;
		    if (retryAfter >= MAX_WAIT_SECONDS) {
		        retryAfter = MAX_WAIT_SECONDS;
		    }
		};
		
		const onerrorHandler = () =>{
			setTimeout(tryToSetupEventSource, milliSecondsToWait());
		};
		
		const onmessageHandler = (event) =>{
			var eventData = JSON.parse(event.data);
			updatePersons(eventData);
		};
		
		const onopenHandler = () =>{
			retryAfter = INITIAL_WAIT_SECONDS;
		};
		
		const setupEventSource = () => {
			if (sseSource){
				sseSource.close();
				sseSource = null;
			}
			sseSource = new EventSource(EVENTS_URL);
			sseSource.onmessage = onmessageHandler;
			sseSource.onopen = onopenHandler;
			sseSource.onerror = onerrorHandler;
		};
		
		fetchPersons();
		setupEventSource();
		
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