import React, { useState, useEffect } from 'react';
import {fetchOtherNameTypes as fetch} from '../services/OtherNameTypesDataService';


function OtherNameTypes(props){
	const firstItem = "--choose an option--";
	const [otherNameTypes, setOtherNameTypes] = useState([]);
	const [currentValue, setCurrentValue]=useState(props.currentValue ? props.currentValue : firstItem);
	const id = (props.id ? props.id : "default-id");
	
	const handleChange = (event) => {
		setCurrentValue(event.target.value);
		if (props.onChange){
			props.onChange(event);
		}
		if (props.valueSetter){
			props.valueSetter(event.target.value);
		}
	}

	useEffect(() =>{
		fetch()
			.then(data => {	
				setOtherNameTypes(data);
			});
	},[setOtherNameTypes]);
	

	useEffect(() =>{
		setCurrentValue(props.currentValue);
	},[props.currentValue]);
	
	
	
	return(
		<div>
			<select name={id} id={id} value={currentValue} onChange={handleChange}>
				<option value={firstItem}>{firstItem}</option>
				{(otherNameTypes).map((item) => 
						<option value={item} key={item}> 
							{item}
						</option>
				)}
			</select>		
		</div>

	);
};

export default OtherNameTypes;