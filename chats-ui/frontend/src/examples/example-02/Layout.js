import React from 'react';
import {Outlet} from 'react-router-dom';
import '../../common/css/Zonesoft.css';
import Menu from './Menu';


function Layout(){
	const title = "Example 02 - Layout";
	const menu = Menu();
  return (				
	    <table className="zsft-table">
	    	<thead>
	    		<tr>
	    			<th style={{ width: "100%" }}>
				    	<h1>{title}</h1>	
	    			</th>
	    		</tr>
	    	</thead>
			<tbody>
				<tr>
					<td  style={{ width: "10%" }} >
						{menu}
					</td>
				</tr>
				<tr>
					<td  style={{ width: "100%" }}>
						<Outlet />
					</td>
				</tr>
			</tbody>
	    </table>
  );
}

export default Layout;