import React from 'react';
import OtherNamesEditor from './components/OtherNamesEditor';


function Data(){
	
	const currentPerson = {
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
,
            {
                "id": "51c23ffc-98bc-4d81-b1cb-295e04ef3c6a",
                "value": "Cam",
                "nameType": "NICK_NAME"
            }           			
		]
	};

	
	return(
		<table>
			<tbody>
				<tr>
					<th>Person ID</th>
					<td>
						<input type="text" name="personId" id="personId" value={currentPerson.id} readOnly style={{width:"90%" }} />
					</td>
				</tr>
				<tr>
					<th>Moniker</th>
					<td><input type="text" name="moniker" id="moniker" value={currentPerson.moniker} /></td>
				</tr>
				<tr>
					<th>Firstname</th>
					<td><input type="text" name="firstname" id="firstname" value={currentPerson.firstname}/></td>
				</tr>
				<tr>
					<th>Lastname</th>
					<td><input type="text" name="lastname" id="lastname" value={currentPerson.lastname} /></td>
				</tr>	
				<tr>
					<th>Other Names</th>
					<td className="subtableContainer">
						<OtherNamesEditor otherNames={currentPerson.otherNames}/>
					</td>
				</tr>								
			</tbody>
		</table>

	);
};

export default Data;