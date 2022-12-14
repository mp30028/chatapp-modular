import React from 'react';
import '../../common/css/Zonesoft.css';
import Data from './Data';

function Layout(){

  return (
	  <div>
	  		Example 03. Fetching Events<br/>		
	    <table className="zsft-table">
	    	<thead>
	    		<tr>
	    			<th style={{ width: "100%" }}>
				    	Example 03. Fetching Data	
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