import React from 'react';
import '../../common/css/Zonesoft.css';
import Data from './Data';

function Layout(){

  return (
	  <div>		
	    <table className="zsft-table">
	    	<thead>
	    		<tr>
	    			<th style={{ width: "100%" }}>
				    	Example 05 Handling EventSource disconnects and errors
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