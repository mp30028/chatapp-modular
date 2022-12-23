import React, { useState, useEffect } from 'react';
import {fetchOtherNameTypes as fetch} from '../services/OtherNameTypesDataService';


function OtherNameTypes(props){
	const firstItem = "--choose an option--";
	const [otherNameTypes, setOtherNameTypes] = useState([]);
	const [value, setvalue]=useState(props.value ? props.value : firstItem);
	const id = (props.id ? props.id : "default-id");
	
	const handleChange = (event) => {
		console.log("event-id=" + event.target.id);
		console.log("event-value=" + event.target.value);
		setvalue(event.target.value);
		if (props.onChange){
			props.onChange(event.target.value);
		}
	}
	

	useEffect(() =>{
		fetch()
			.then(data => {	
				console.log("-- data --", data); 
				const firtsItemArray = [firstItem];
				const dataArray = [...data];
				setOtherNameTypes(firtsItemArray.concat(dataArray));
			});
	},[setOtherNameTypes]);
	

	useEffect(() =>{
		setvalue(props.value);
	},[props.value]);
	
	
	
	return(
		<div>
			<select name={id} id={id} value={value} onChange={handleChange}>
				{(otherNameTypes?otherNameTypes:[]).map((item) => 
						<option value={item} key={item}> 
							{item}
						</option>
				)}
			</select>		
		</div>

	);
};

export default OtherNameTypes;