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
				    	Example 10  - Builds on example-09 to add create and delete functionality to OtherNamesEditor component
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