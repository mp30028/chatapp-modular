import React from 'react';
import '../../css/Zonesoft.css';
import Data from './Data';

function Layout(){

  return (
	  <div>
	  		Example 04. Fetching Events<br/>		
	    <table className="zsft-table">
	    	<thead>
	    		<tr>
	    			<th style={{ width: "100%" }}>
				    	Example 04. Fetching Data and listening for persistence events
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
	  </div>	
  );
}

export default Layout;