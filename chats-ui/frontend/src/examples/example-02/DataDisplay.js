import React, {useState} from 'react';
import { getAll } from "./Data";

function DataDisplay(){
	const countryList = getAll();
	
	const [selectedCountry, setSelectedCountry]= useState(countryList[0]);
	
	const countryRowClicked = (selectedRowCountry) => {
//		alert(selectedRowCountry.country);
		setSelectedCountry(selectedRowCountry);
	}
	
		return(
			<div style={{width: "100%"}}>
				<table>
					<thead>
						<tr>
							<th>id</th>
							<th>country</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colSpan="2">
								<table>
									<tbody>
										<tr><th>ID</th><td>{selectedCountry.id}</td></tr>
										<tr><td>Country</td><td>{selectedCountry.country}</td></tr>
										<tr><td>Population</td><td>{selectedCountry.population}</td></tr>
										<tr><td>Area</td><td>{selectedCountry.area}</td></tr>
										<tr><td>Density</td><td>{selectedCountry.density}</td></tr>
									</tbody>
								</table>
							</td>
						</tr>
						{countryList.map(c =>
							<tr key={c.id} onClick={()=> countryRowClicked(c)}>
								<td style={{ width: "10%" }}>{c.id}</td>
								<td style={{ width: "90%" }}>{c.country}</td>
							</tr>
						)}
					</tbody>
				</table>
			</div>
		);
};

export default DataDisplay;