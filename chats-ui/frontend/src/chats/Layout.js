import React from 'react';
import { useLocation } from 'react-router-dom';
import "../common/css/Zonesoft.css"
import Data from './Data';


function Layout(){
	const { state } = useLocation();
	const username = state.username;


  return (				
	    <table className="zsft-table">
	    	<thead>
	    		<tr>
	    			<th style={{ width: "100%" }}>
				    	Main Chat App
	    			</th>
	    		</tr>
	    	</thead>
			<tbody>
				<tr>
					<td  style={{ width: "100%" }}>
						<Data username={username} />
					</td>
				</tr>
			</tbody>
	    </table>
  );
}

export default Layout;