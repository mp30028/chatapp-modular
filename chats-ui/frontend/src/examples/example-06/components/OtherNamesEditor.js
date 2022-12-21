import React, { useState, useEffect } from 'react';
//import {OtherNameTypes} from './OtherNameTypes';

function OtherNamesEditor(props){
	const [currentOtherNames, setCurrentOtherNames] = useState([]);
	
	useEffect(() =>{
		setCurrentOtherNames(props.otherNames ? props.otherNames : []);
	},[props.otherNames]);
	
	const handleChangeToOtherName = (event) => {
//		const {name, value} = event.target;
//		const editedItemId = event.target.id.split("_");
//		const idToFind = parseInt(editedItemId[1]);
//		const otherName = props.otherNames.find(on => on.id === idToFind);
//		otherName[editedItemId[0]] = value;
//		let newOtherNames =[];
//		newOtherNames = props.otherNames.map(on => { return ((on.id === otherName.id) ? otherName : on) });
//		props.setOtherNames(newOtherNames);
	};
	
	const handleDeleteOtherName = (event) =>{
//		event.preventDefault();
//		const targetId = parseInt(event.target.value);
//		const newOtherNames = props.otherNames.filter((on) => { return (on.id === targetId ? null : on) });
//		props.setOtherNames(newOtherNames);
	};	
	
	const handleAddNewOtherName = (event) =>{
//		event.preventDefault();
//		const temporaryId = Date.now() * -1;
//		const emptyOtherName = {id: temporaryId , value: "", otherNameType: {id: 6}};
//		const newOtherNames = [...props.otherNames, emptyOtherName];
//		props.setOtherNames(newOtherNames);
	}
	
		return(
			<div style={{width: "100%"}}>
				<table>
					<thead>
						<tr>
							<th style={{ width: "40%" }}>Other Name</th>
							<th style={{ width: "40%" }}>Type</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						{currentOtherNames.map(otherName =>
							<tr key={otherName.id}>
								<td><input type="text" name={"otherNameId_" + otherName.id} id={"otherNameId_" + otherName.id} value={otherName.value} onChange={handleChangeToOtherName}/></td>								
								<td>
									<input type="text" name={"otherNameType_" + otherName.id} id={"otherNameType_" + otherName.id} value={otherName.nameType} onChange={handleChangeToOtherName}/>
{/*								
									<OtherNameTypes 
										dropDownName={"otherNameType_" + otherName.id} 
										dropDownId={"otherNameType_" + otherName.id} 
										otherNameTypeId={otherName.otherNameType.id} 
										setOtherNameTypeId= {(newId) => { otherName.otherNameType.id = newId}} />
*/}
								</td>
								
								<td style={{textAlign:"center"}}>
									<button className="deleteButton" name="deleteOtherName" id={'deleteOtherName_' + otherName.id} value={otherName.id}  onClick={handleDeleteOtherName}/>
								</td>
							</tr>	
						)}
						<tr>
							<td colSpan="3" style={{textAlign:"right"}}>
								<button className="addNewButton" id={'addNewOtherName'} onClick={handleAddNewOtherName} />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		);
};

export default OtherNamesEditor;