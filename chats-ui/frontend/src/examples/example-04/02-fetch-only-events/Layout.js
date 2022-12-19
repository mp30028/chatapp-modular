import React from 'react';
import '../../../css/Zonesoft.css';
import Data from './Data';

function Layout(){

  return (
	  <div>		
	    <table className="zsft-table">
	    	<thead>
	    		<tr>
	    			<th style={{ width: "100%" }}>
				    	Example 04.02 Fetching just the events. Data not changed or shown
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