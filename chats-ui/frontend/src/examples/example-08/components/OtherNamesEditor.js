import React, { useEffect, useState } from 'react';
import OtherNameTypes from './OtherNameTypes';


function OtherNamesEditor(props){
	const [otherNames, setOtherNames] = useState([]);
	
	useEffect(() =>{
		setOtherNames(props.otherNames ? props.otherNames : []);
	},[props.otherNames]);
	
	
	const onChangeValue = (event) =>{
		console.log("onChangeValue-event=",event);	
	}
	
	const onChangeType = (event) =>{
		console.log("onChangeType-event=",event);	
	}
	
	const onClickDelete = (event) =>{
		console.log("onClickDelete-event=",event);	
	}
	
	const onClickAddNew = (event) =>{
		console.log("onClickAddNew-event=",event);	
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
						{otherNames.map(oName =>
							<tr key={oName.id}>
								<td><input type="text" name={"onv_" + oName.id} id={"onv_" + oName.id} value={oName.value} onChange={onChangeValue}/></td>								
								<td>
									<OtherNameTypes id={"ont_" + oName.id} value={oName.nameType} onChange={onChangeType}/>
								</td>
								
								<td style={{textAlign:"center"}}>
									<button className="deleteButton" name="deleteButton" id="deleteButton" onClick={onClickDelete(oName)}/>
								</td>
							</tr>	
						)}
						<tr>
							<td colSpan="3" style={{textAlign:"right"}}>
								<button className="addNewButton" name="addNewButton" id="addNewButton" onClick={onClickAddNew} />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		);
};

export default OtherNamesEditor;