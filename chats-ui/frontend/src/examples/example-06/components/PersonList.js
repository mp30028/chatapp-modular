import React, {useState, useEffect, useRef} from 'react';
import * as DataService from "../services/PersonDataService";
import * as EventListener from "../services/PersonListenerService";
import * as DataHandler from "../services/PersonDataHandler";

function List(props){
	const personsRef = useRef([]);
	const [persons, setPersons]= useState([]);
	const [selectedPerson, setSelectedPerson]= useState(null);
			
	useEffect(() => {	
		const onmessageHandler = (event) =>{
			var eventData = JSON.parse(event.data);
			var updatedData = DataHandler.doUpdate(personsRef.current, eventData);
			setPersons(updatedData);
		};
		DataService.fetchPersons().then((data) => setPersons(data));;
		EventListener.setupEventSource(onmessageHandler);		
	}, [setPersons]);

	useEffect(() =>{
		if (props.selectionHandler){
			props.selectionHandler(selectedPerson);
		}
	},[selectedPerson])
	
	useEffect(() =>{
		var foundPerson = null;
		if (selectedPerson){
			foundPerson = persons.find((p)=>p.id === selectedPerson.id)
		}
		setSelectedPerson(foundPerson);
		personsRef.current = persons;
	},[persons])
	
	const selectPerson = (event) => {
		const targetPerson = persons.find(p => p.id === event.target.value);
		if (targetPerson){
			setSelectedPerson(targetPerson);	
		}else{
			setSelectedPerson(null);
		}	
	}
	
	const isChecked = (targetPersonId) =>{
		return ( selectedPerson ? selectedPerson.id === targetPersonId : false);
	}
	
	
		return(
					<table className="zsft-table" style={{width: "100%"}}>
						<thead>
							<tr>
								<th>ID</th>
								<th>Moniker</th>
								<th>Firstname</th>
								<th>Lastname</th>
								<th>Other-Names</th>
								<th>.</th>
							</tr>
						</thead>
						<tbody>
							{persons.map(p =>
								<tr key={p.id}>
									<td style={{ width: "20%" }}>{p.id}</td>
									<td style={{ width: "10%" }}>{p.moniker}</td>
									<td style={{ width: "10%" }}>{p.firstname}</td>
									<td style={{ width: "10%" }}>{p.lastname}</td>
									<td style={{ width: "20%" }}>{p.otherNames.map(o => 
																						<div key={o.id}>
																							{o.nameType + ": " + o.value}
																						</div>
																					)
																  }
									</td>
										<td style={{width: "5%", textAlign:"center"}}>
											<input type="radio" name={"selectPerson_" + p.id} id={"selectPerson_" + p.id} value={p.id} onChange={selectPerson} checked={isChecked(p.id)} />
											<label htmlFor={"selectPerson_" + p.id} className="ellipses">. . .</label>
										</td>
								</tr>
							)}
						</tbody>
				</table>
		);
};

export default List;