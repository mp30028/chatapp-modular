import React from 'react';
import {Outlet} from 'react-router-dom';
import '../../css/Zonesoft.css';
 import Menu from './Menu';


function Layout(){

//	const menu = Menu();
  return (				
	    <table className="zsft-table">
	    	<thead>
	    		<tr>
	    			<th style={{ width: "100%" }}>
				    	People Management	
	    			</th>
	    		</tr>
	    	</thead>
			<tbody>
				<tr>
					<td  style={{ width: "10%" }} >
						{<Menu/>} 
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