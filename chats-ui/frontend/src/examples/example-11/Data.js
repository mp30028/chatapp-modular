import React, { useEffect, useState } from 'react';
import PersonEditor from './components/PersonEditor';


function Data(){
	
	const initialValue = {
		"id": "e30e5061-7b02-436e-8290-4e70cedcb3f3",
		"moniker": "Turtle",
		"firstname": "Lesley",
		"lastname": "CAMERON",
		"otherNames": [
			{
				"id": "17778483-9a1e-470a-9e64-aed0a9ab310d",
				"value": "Sophia",
				"nameType": "MIDDLE_NAME"
			},
            {
                "id": "6264c91a-acd3-45e8-8d64-789efea0c975",
                "value": "Isabella",
                "nameType": "MIDDLE_NAME"
            },
            {
                "id": "43568147-9ce0-4fe5-877a-107c72b87611",
                "value": "Mary",
                "nameType": "MIDDLE_NAME"
            },
            {
                "id": "51c23ffc-98bc-4d81-b1cb-295e04ef3c6a",
                "value": "Cam",
                "nameType": "NICK_NAME"
            }           			
		]
	};
	
	const [currentPerson, setCurrentPerson] = useState(initialValue);
	const [jsonString, setJsonString] = useState("");

	useEffect(() =>{
		setJsonString(JSON.stringify(currentPerson, null, 2));
	},[currentPerson]);
	
	const onChangeOfCurrentPerson = (eventIn) => {
		const {event, name, value } = eventIn;
		event.preventDefault();
		const currentPersonCopy = { ...currentPerson };
		currentPersonCopy[name] = value;
		setCurrentPerson(currentPersonCopy);
	}
	
	return(
		<table>
			<tbody>
				<tr>
					<td>
						<PersonEditor person={currentPerson} onChange={onChangeOfCurrentPerson} />
					</td>
				</tr>
				<tr>
					<td>
						<pre>
							{jsonString}
						</pre>
					</td>
				</tr>								
			</tbody>
		</table>
	);
};

export default Data;