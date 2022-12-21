import React, { useState, useEffect } from 'react';
import OtherNamesEditor from './OtherNamesEditor';

export function Editor(props) {
	const emptyPerson = {
							"firstname": "",
							"id": "",
							"lastname": "",
							"moniker": "",
							"otherNames":[]
						}
	const [currentPerson, setCurrentPerson] = useState(emptyPerson);


	useEffect(() =>{
		setCurrentPerson(props.person ? props.person : emptyPerson);
	},[props.person]);
	
	const handleChange = (event) => {
		const {name, value} = event.target;
		setCurrentPerson({...currentPerson, [name]:value})
	}
	
	const handleSubmit = (event) =>{
		event.preventDefault();	
		switch(event.target.value){
			case "UPDATE":
//				props.updatePerson(currentPerson);
				break;
			case "CREATE":
//				props.addNewPerson(currentPerson);
				break;
			case "CANCEL":
//				navigate("/list", { replace: true });
				break;
			case "DELETE":
//				props.deletePerson(currentPerson);
				break;
			default:
		}
	}


	
	return (
		<form style={{width:"100%" }}>
			<table  className="zsft-table" style={{width:"100%" }}>
				<tbody>
					<tr>
						<th>Person ID</th>
						<td>
							<input type="text" name="personId" id="personId" value={currentPerson.id} readOnly style={{width:"90%" }} />
						</td>
					</tr>
					<tr>
						<th>Moniker</th>
						<td><input type="text" name="moniker" id="moniker" value={currentPerson.moniker} onChange={handleChange} /></td>
					</tr>
					<tr>
						<th>Firstname</th>
						<td><input type="text" name="firstname" id="firstname" value={currentPerson.firstname} onChange={handleChange} /></td>
					</tr>
					<tr>
						<th>Lastname</th>
						<td><input type="text" name="lastname" id="lastname" value={currentPerson.lastname} onChange={handleChange} /></td>
					</tr>	
					<tr>
						<th>Other Names</th>
						<td className="subtableContainer">
							<OtherNamesEditor otherNames={currentPerson.otherNames}/>
						</td>
					</tr>
					<tr>
						<td colSpan="2" style={{textAlign:"right"}}>
							<button type="submit" onClick={handleSubmit} value="DELETE">Delete</button>
							<button type="submit" onClick={handleSubmit} value="UPDATE">Save</button>					
							<button type="submit" onClick={handleSubmit} value="CANCEL">Cancel</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>


	);

}

export default Editor;