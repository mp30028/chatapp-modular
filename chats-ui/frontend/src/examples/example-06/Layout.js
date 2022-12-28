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
				    	Example 06  - CRUD functions
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