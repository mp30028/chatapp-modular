import React, { useState } from 'react';
import PersonList from "./components/PersonList";
import PersonEditor from "./components/PersonEditor";
import Logger from '../../components/logger/Logger';


function Data(){
	const [logMessage, writeLog]=useState("");
	const [selectedPerson, setSelectedPerson] = useState(null);
	
	const personSelectionHandler = (person) =>{
		setSelectedPerson(person);		
	}
	
	const dataChangeHandler = (person, change) =>{
		writeLog("change=" + change);
		writeLog("person=" + JSON.stringify(person));
	}
	


	
	const addNew = (event) =>{
		alert("Add New Clicked");
	};

	
		return(
			<table style={{ width: "100%"}}>
				<tbody>
					<tr>
						<td style={{width: "70%", borderWidth: "0"}} >
							<PersonList selectionHandler={personSelectionHandler} writeLog={writeLog} />
						</td>
						<td style={{verticalAlign: "top", borderWidth: "0"}} >
							<PersonEditor person={selectedPerson} writeLog={writeLog} updateHandler={dataChangeHandler} />
						</td>
					</tr>
					<tr>
						<td colSpan="2">
							<div style={{ textAlign: "right" }}>
								<button type="button" onClick={addNew}>New</button>
							</div>
						</td>
					</tr>
					<tr>
						<td colSpan="2">
							<Logger message={logMessage} />
						</td>
					</tr>
				</tbody>
			</table>
		);
};

export default Data;