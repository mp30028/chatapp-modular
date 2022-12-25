import React, {useState, useEffect, useRef} from 'react';

function Data(){

	const updateCountRef = useRef(0);
	const eventsDataRef = useRef([]);
	const [eventsData, setEventsData]= useState([]);	
	const [updateCount, setUpdateCount] = useState(0);


//	const updatePersons = (event) => {
//		var eventData = JSON.parse(event.data);
//		var isNotCurrentEvent = (item) => (item.id !== eventData.id)		
//		var filteredPersons = persons.filter(isNotCurrentEvent)
//		filteredPersons.push(eventData);
//		setEvents(filteredPersons);
//		setUpdateCount(updateCount + 1);
//
//	};

useEffect(() =>{
			updateCountRef.current = updateCount;
},[updateCount])

useEffect(() =>{
			eventsDataRef.current = eventsData;
},[eventsData])

useEffect(() => {
  const sse = new EventSource("http://192.168.1.60:9999/api/persons/persistence-events");


  function getRealtimeData(eventData) {
		var updatedEvents = [...eventsDataRef.current, eventData];
		setEventsData(updatedEvents);
		setUpdateCount(updateCountRef.current + 1);

  }
  sse.onmessage = e => getRealtimeData(JSON.parse(e.data));
}, [setEventsData]);
		
		return(
			<div style={{width: "100%"}}>
				<h2>Update Count = {updateCount}</h2>
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