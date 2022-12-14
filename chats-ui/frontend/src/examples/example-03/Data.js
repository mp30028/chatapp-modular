import React, {useState, useEffect} from 'react';

function Data(){
	const emptyPerson = {
							"firstname": "",
							"id": "EMPTY: NOTHING-SELECTED",
							"lastname": "",
							"moniker": "",
							"otherNames":[]
						}
	const [persons, setPersons]= useState([]);
	const [selectedPerson, setSelectedPerson]= useState(emptyPerson);
		
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
	
	useEffect(() => {fetchPersons();}, [] )



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
																			<div>
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