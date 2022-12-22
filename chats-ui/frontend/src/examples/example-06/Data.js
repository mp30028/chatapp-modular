import React, { useState, useRef } from 'react';
import PersonList from "./components/PersonList";
import PersonEditor from "./components/PersonEditor";
import Logger, {appendLog} from '../../components/logger/Logger';
import {save, remove, update} from './services/PersonDataService';


function Data(){
	const [logMessages, setLogMessages]=useState([]);
	const [selectedPerson, setSelectedPerson] = useState(null);
	const initialEntry = "---- Logging Started ----";
	const logMessagesRef = useRef([[new Date().toLocaleString(), initialEntry ]]);
	
	const personSelectionHandler = (person) =>{
		setSelectedPerson(person);		
	}
	
	const writeLog = (message) => {
		const updatedLogs = appendLog(logMessagesRef.current, message);
		logMessagesRef.current = updatedLogs;
		setLogMessages(updatedLogs);	
	}
		
	const dataChangeHandler = (person, change) =>{
		writeLog("change=" + change );
		writeLog("person=" + JSON.stringify(person,null,2));
		if (change === "UPDATE"){
			update(person);
		}else if(change === "DELETE"){
			remove(person);
		}
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
							<Logger messages={logMessages} />
						</td>
					</tr>
				</tbody>
			</table>
		);
};

export default Data;