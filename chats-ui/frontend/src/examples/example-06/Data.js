import React, { useState } from 'react';
import PersonList from "./components/PersonList";
import PersonEditor from "./components/PersonEditor";

function Data(){
	
	const [selectedPerson, setSelectedPerson] = useState(null);
	
	const personSelectionHandler = (person) =>{
//		if(person){
//			alert(selectedPerson.moniker);
			setSelectedPerson(person);
//		}
		
	}
	
	const addNew = (event) =>{
		alert("Add New Clicked");
	};


		return(
			<table style={{ width: "100%"}}>
				<tbody>
					<tr>
						<td style={{width: "70%", borderWidth: "0"}} >
							<PersonList selectionHandler={personSelectionHandler} />
						</td>
						<td style={{verticalAlign: "top", borderWidth: "0"}} >
							<PersonEditor person={selectedPerson} />
						</td>
					</tr>
					<tr>
						<td colSpan="2">
							<div style={{ textAlign: "right" }}>
								<button type="button" onClick={addNew}>New</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		);
};

export default Data;