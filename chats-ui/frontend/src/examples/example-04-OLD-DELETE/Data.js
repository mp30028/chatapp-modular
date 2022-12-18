import React, {useState, useEffect} from 'react';

function Data(){
	
	const [persons, setPersons]= useState([]);
		
	const fetchPersons = () =>{
		fetch(
			"http://localhost:9999/api/persons",
			{
				method: 'GET',
				headers: {
					'Content-Type': 'application/json;charset=UTF-8',
					'Accept': 'application/json, text/plain'
				}
			}
		)
//		.then((response) => {console.log(response.body); pJson = response; return response;});
		.then((response) => response.json())
		.then((data) => { console.log(`data.length=`, data.length); return data; })
		.then((data) => { setPersons(data); });
	};
	
	useEffect(() => {fetchPersons();}, [] );
	
	const [currentEvent, setCurrentEvent]= useState("Setting Current Event with this message");
	
//	const doSave = (person) => {
//		var isFound = false;
//		var filteredPersons = persons.map((p) => {				 
//				if(p.id === person.id) {
//					isFound = true;
//					return person;
//				}else{
//				  	return p;	
//				} 
//			});
//		if (!isFound){
//			filteredPersons.push(person);
//		}
//		setPersons(filteredPersons);
//		setCurrentEvent(currentEvent + "\n" + filteredPersons.length); 
//	};
	
	const ifPresentThenReplaceElseAdd = (dataToUpdate, checkItem) => {
		var isReplaced = false;
		var replaceCheckItem = (item) => {
			if (item.id === checkItem.id) {
				isReplaced = true;
				return checkItem;
			} else {
				return item;
			}
		};
		var upddatedData = dataToUpdate.map(replaceCheckItem);
		if (!isReplaced) {
			upddatedData.push(checkItem);
		}
		return upddatedData;

	}
	
	const doSave = (personsAffected) => {
		var updatedData = [...persons];
		for (var j = 0; j < personsAffected.length; j++) {
			var resultData = ifPresentThenReplaceElseAdd(updatedData, personsAffected[j]);
			updatedData = resultData;
			setPersons(updatedData);
		}
		
	}
	
	const doDelete = (personsAffected) => {
		var updatedData = [...persons];
		var personAffected = null;
		var notMatchingCheckItem = (item) => (item.id !== personAffected.id)	
		for (var j = 0; j < personsAffected.length; j++) {
			personAffected = personsAffected[j];
			var filteredData = updatedData.filter(notMatchingCheckItem);
			updatedData = filteredData;
			setPersons(updatedData);
		}
		
	}
	
//	const doDelete = (person) => {
//		var filteredPersons = persons.filter((p) => {				 
//				if(p.id === person.id) {
//					return null;
//				}else{
//				  	return p;	
//				} 
//			});
//		setPersons(filteredPersons);
//		setCurrentEvent(currentEvent + "\n" + filteredPersons.length); 
//	};
	
	const updateView = (event) => {
		var eventData = JSON.parse(event.data);
		setCurrentEvent(currentEvent + "\n" + JSON.stringify(eventData, null, 2));
		var personsAffected = eventData.persons;
		var eventType = eventData.eventType;
			if (eventType === 'SAVE') {
				doSave(personsAffected);
//				personsAffected.forEach(doSave);
			}else if (eventType === 'DELETE') {
				doDelete(personsAffected);
//				personsAffected.forEach(doDelete);
			}
	};

	
	

	const eventSource = new EventSource("http://localhost:9999/api/persons/persistence-events");
	eventSource.onmessage = updateView;
		
		return(
			<div style={{width: "100%"}}>
			<pre>
				{currentEvent}
			</pre>
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

						{persons.map(p =>
							<tr key={p.id}>
								<td style={{ width: "10%" }}>{p.id}</td>
								<td style={{ width: "10%" }}>{p.moniker}</td>
								<td style={{ width: "10%" }}>{p.firstname}</td>
								<td style={{ width: "10%" }}>{p.lastname}</td>
								<td style={{ width: "10%" }}>{p.otherNames.map(o => 
																					<div>
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