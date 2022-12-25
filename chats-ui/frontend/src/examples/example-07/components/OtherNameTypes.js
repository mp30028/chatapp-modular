import React, { useState, useEffect } from 'react';
import {fetchOtherNameTypes as fetch} from '../services/OtherNameTypesDataService';


function OtherNameTypes(props){
	const firstItem = "--choose an option--";
	const [otherNameTypes, setOtherNameTypes] = useState([]);
	const [currentValue, setCurrentValue]=useState(props.currentValue ? props.currentValue : firstItem);
	const id = (props.id ? props.id : "default-id");
	
	const handleChange = (event) => {
		console.log("event-id=" + event.target.id);
		console.log("event-value=" + event.target.value);
		setCurrentValue(event.target.value);
		if (props.valueSetter){
			props.valueSetter(event.target.value);
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
		setCurrentValue(props.currentValue);
	},[props.currentValue]);
	
	
	
	return(
		<div>
			<select name={id} id={id} value={currentValue} onChange={handleChange}>
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