import React from 'react';
import '../../css/Zonesoft.css';
import Data from './Data';


function Layout(){

//	const menu = Menu();
  return (				
	    <table className="zsft-table">
	    	<thead>
	    		<tr>
	    			<th style={{ width: "100%" }}>
				    	Example 08  - Using the OtherNameTypes component which provides a picklist. The data for the picklish is fetched from a REST endpoint.
				    	The rest endpoint converts Java Enums into data for the response.
				    	This is an extension of Example 07. Also the component has been enhanced a little with onChange property
	    			</th>
	    		</tr>
	    	</thead>
			<tbody>
				<tr>
					<td  style={{ width: "100%" }}>
						<Data />
					</td>
				</tr>
			</tbody>
	    </table>
  );
}

export default Layout;