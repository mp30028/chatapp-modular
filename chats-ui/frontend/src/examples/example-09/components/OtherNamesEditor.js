import React from 'react';
import OtherNameTypes from './OtherNameTypes';


function OtherNamesEditor(props){

	const onChange = (event) =>{
		props.onChange(event);
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
						{(props.otherNames ? props.otherNames : []).map(oName =>
							<tr key={oName.id}>
								<td><input type="text" name="value" id={oName.id}  value={oName.value} onChange={onChange}/></td>								
								<td>
									<OtherNameTypes name="nameType" id={oName.id} currentValue={oName.nameType} onChange={onChange}/>
								</td>
								
								<td style={{textAlign:"center"}}>
									<button className="deleteButton" name="deleteButton" id="deleteButton" onClick={onClickDelete}/>
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