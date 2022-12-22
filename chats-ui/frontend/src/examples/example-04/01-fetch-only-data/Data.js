import React, {useState, useEffect} from 'react';

function Data(){
	
	const [persons, setPersons]= useState([]);
		
	const fetchPersons = () =>{
		fetch(
			"http://192.168.1.60:9999/api/persons",
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