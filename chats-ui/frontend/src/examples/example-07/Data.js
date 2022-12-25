import React, {useState} from 'react';
import OtherNameTypes from './components/OtherNameTypes';


function Data(){
	const [value1, setValue1] = useState(null);
	const [value2, setValue2] = useState(null);
	const [value3, setValue3] = useState("MIDDLE_NAME");
	const [value4, setValue4] = useState("");	

	
	return(
		<table>
			<tbody>
				<tr>
					<td>Item One</td>
					<td><OtherNameTypes id="one" valueSetter={setValue1} /> </td>
					<td>Not Initialised. i.e. currentValue not specified at all</td>
					<td> The value selected is {value1} .</td>
				</tr>
				<tr>
					<td>Item Two</td>
					<td><OtherNameTypes id="two" currentValue={value2} valueSetter={setValue2} /> </td>
					<td>Initialised currentValue with value2 which is null </td>
					<td> The value selected is {value2} .</td>
				</tr>
				<tr>
					<td>Item Three</td>
					<td><OtherNameTypes id="three" currentValue={value3} valueSetter={setValue3}/> </td>
					<td>Initialised currentValue with value3 which was initialised with "MIDDLE_NAME" </td>
					<td> The value selected is {value3} .</td>
				</tr>
				<tr>
					<td>Item Four</td>
					<td><OtherNameTypes id="four" currentValue={value4} valueSetter={setValue4} /> </td>
					<td>Initialised currentValue with value4 which was initialised with "", i.e. an empty string</td>
					<td> The value selected is {value4} .</td>
				</tr>								
			</tbody>
		</table>

	);
};

export default Data;