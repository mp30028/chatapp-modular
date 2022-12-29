import React from 'react';
import "../common/css/Zonesoft.css"
import Data from './Data';


function Layout(){

  return (				
	    <table className="zsft-table" style={{ width: "30%" }}>
	    	<thead>
	    		<tr>
	    			<th >
				    	Chat App Login
	    			</th>
	    		</tr>
	    	</thead>
			<tbody>
				<tr>
					<td>
						<Data />
					</td>
				</tr>
			</tbody>
	    </table>
  );
}

export default Layout;