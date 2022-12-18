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
				    	Example 04.01 Fetching just the Data with no events involved
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