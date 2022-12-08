import React from 'react';
import {Outlet} from 'react-router-dom';
import '../css/Zonesoft.css';


function MainContainer(props){
  return (				
	    <table className="zsft-table">
	    	<thead>
	    		<tr>
	    			<th style={{ width: "100%" }}>
				    	<h1>{props.title}</h1>	
	    			</th>
	    		</tr>
	    	</thead>
			<tbody>
				<tr>
					<td  style={{ width: "10%" }} >
						{props.menu}
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

export default MainContainer;