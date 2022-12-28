import React from 'react';
import '../../common/css/Zonesoft.css';
import Data from './Data';


function Layout(){

//	const menu = Menu();
  return (				
	    <table className="zsft-table">
	    	<thead>
	    		<tr>
	    			<th style={{ width: "100%" }}>
				    	Example 12  - Builds on example-06 and example-11 to start bringing everything together
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