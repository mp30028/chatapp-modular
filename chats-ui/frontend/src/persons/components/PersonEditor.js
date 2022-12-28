import React from 'react';
import OtherNamesEditor from './OtherNamesEditor';

export function PersonEditor(props) {
	const handleChange = (event) => {
		event.preventDefault();
		const {name, value} = event.target;
		props.onChange({event:event, name:name, value: value});
	}
	
	const handleSubmit = (event) =>{
		const {name, value} = event.target;
		props.onSubmit({event, name, value});
	}
	
	const getIdNameAndValueFromEvent = (event) =>{
		const {id, name, value} = event.target;
		return {id, name, value};
	};
	
	const onChangeOfOtherNames = (event) => {
		event.preventDefault();
			const {id, name, value} =getIdNameAndValueFromEvent(event);
			const otherNameToUpdate = props.person.otherNames.find(o => (o.id === id) ? o : null);
			const updatedOtherName =  {...otherNameToUpdate, [name]:value};
			const replaceIf = (item) => item.id === id ? updatedOtherName : item;
			const updatedOtherNames = props.person.otherNames.map(item => replaceIf(item));
			props.onChange({event:event, name:"otherNames", value: updatedOtherNames});
	}

	const onDeleteOfOtherNames = (event) => {
		event.preventDefault();
		const {id} = getIdNameAndValueFromEvent(event);
		const isToBeKept = (item) => item.id !== id;
		const filteredOtherNames = props.person.otherNames.filter(isToBeKept);
		props.onChange({event:event, name:"otherNames", value: filteredOtherNames});
	}
	
	const onAddOfOtherNames = (event) => {
		event.preventDefault();
		const tempId = "tempid_" + (new Date().getTime());
		const emptyOtherName =  { id: tempId, value : "", nameType : ""};
		const updatedOtherNames = props.person.otherNames.concat(emptyOtherName)
		props.onChange({event:event, name:"otherNames", value: updatedOtherNames});
	}
	
	return (
		<form style={{width:"100%" }}>
			<table  className="zsft-table" style={{width:"100%" }}>
				<tbody>
					<tr>
						<th>Person ID</th>
						<td>
							<input type="text" name="personId" id={props.person.id} value={props.person.id} readOnly style={{width:"90%" }} />
						</td>
					</tr>
					<tr>
						<th>Moniker</th>
						<td><input type="text" name="moniker" id={props.person.id} value={props.person.moniker} onChange={handleChange} /></td>
					</tr>
					<tr>
						<th>Firstname</th>
						<td><input type="text" name="firstname" id={props.person.id} value={props.person.firstname} onChange={handleChange} /></td>
					</tr>
					<tr>
						<th>Lastname</th>
						<td><input type="text" name="lastname" id={props.person.id} value={props.person.lastname} onChange={handleChange} /></td>
					</tr>	
				<tr>
					<th>Other Names</th>
					<td className="subtableContainer">
						<OtherNamesEditor onChange={onChangeOfOtherNames} onDelete={onDeleteOfOtherNames} onAdd={onAddOfOtherNames} otherNames={props.person.otherNames}/>
					</td>
				</tr>
					<tr>
						<td colSpan="2" style={{textAlign:"right"}}>
							<button type="submit" onClick={handleSubmit} name="submit" value="CREATE">Add New</button>
							<button type="submit" onClick={handleSubmit} name="submit" value="DELETE">Delete</button>
							<button type="submit" onClick={handleSubmit} name="submit" value="UPDATE">Save</button>					
							<button type="submit" onClick={handleSubmit} name="submit" value="CANCEL">Cancel</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>


	);

}

export default PersonEditor;