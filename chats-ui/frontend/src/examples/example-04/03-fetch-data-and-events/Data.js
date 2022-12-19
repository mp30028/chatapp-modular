import React, {useState, useEffect, useRef} from 'react';

function Data(){

	const updateCountRef = useRef(0);
	const eventsDataRef = useRef([]);
	const personsDataRef = useRef([]);
	const [eventsData, setEventsData] = useState([]);	
	const [updateCount, setUpdateCount] = useState(0);
	const [personsData, setPersonsData] = useState([]);

	const updatePersonsData = (event) =>  {
//		debugger;
		var data = personsDataRef.current;
		if (event.eventType === "SAVE"){
			var personToSave = null;
			var existsInData = false;
			var update = (item) => {	if(item.id === personToSave.id) {
											existsInData = true; 
											return personToSave; 
										}else{
											return item;
										} 
									};
			for(let j = 0; j < event.persons.length; j++){
				personToSave = event.persons[j];
				existsInData = false;
				var mappedPersons = data.map(update);	
				if (!existsInData){
					mappedPersons.push(personToSave);
				}
				data = mappedPersons;
			}
		}else if (event.eventType === "DELETE"){
			var personToDelete = null;
			const toKeep = (item) => (item.id !== personToDelete.id);
			for(let k =0; k < event.persons.length; k++){
				personToDelete = event.persons[k];
				var filteredPersons = data.filter(toKeep);
				data = filteredPersons;
			}
		}
		setPersonsData(data);
	};

useEffect(() =>{
			updateCountRef.current = updateCount;
},[updateCount])

useEffect(() =>{
			eventsDataRef.current = eventsData;
},[eventsData])

useEffect(() =>{
			personsDataRef.current = personsData;
},[personsData])

useEffect(() => {
		  const sse = new EventSource("http://localhost:9999/api/persons/persistence-events");
		  function fetchRealtimeEvents(eventData) {
				var updatedEvents = [...eventsDataRef.current, eventData];
				setEventsData(updatedEvents);
				setUpdateCount(updateCountRef.current + 1);
				updatePersonsData(eventData);
		  }
		  sse.onmessage = e => fetchRealtimeEvents(JSON.parse(e.data));
}, [setEventsData]);

useEffect(() => {
		fetch(	"http://localhost:9999/api/persons",
				{
					method: 'GET',
					headers: {
						'Content-Type': 'application/json;charset=UTF-8',
						'Accept': 'application/json, text/plain'
					}
				}
		)
		.then((response) => response.json())
		.then((data) => setPersonsData(data));
},
[setPersonsData]);

		
		return(
			<div style={{width: "100%"}}>
				<h2>Update Count = {updateCount}</h2>
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

						{personsData.map(p =>
							<tr key={p.id}>
								<td style={{ width: "10%" }}>{p.id}</td>
								<td style={{ width: "10%" }}>{p.moniker}</td>
								<td style={{ width: "10%" }}>{p.firstname}</td>
								<td style={{ width: "10%" }}>{p.lastname}</td>
								<td style={{ width: "10%" }}>{p.otherNames.map(o => 
																					<div key={p.id + "_" + o.value}>
																						{o.nameType + ": " + o.value}
																					</div>
																				)
															  }
								</td>
							</tr>
						)}
					</tbody>
				</table>
				<table>
					<thead>
						<tr>
							<th>ID</th>
							<th>Event-Type</th>
							<th>Persons-Affected</th>
						</tr>
					</thead>
					<tbody>

						{eventsData.map(e =>
							<tr key={e.id}>
								<td style={{ width: "10%" }}>{e.id}</td>
								<td style={{ width: "10%" }}>{e.eventType}</td>
								<td style={{ width: "10%" }}>{e.persons.map(p => 
																					<div key={e.id + "_" +  p.id}>
																						{JSON.stringify(p)}
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