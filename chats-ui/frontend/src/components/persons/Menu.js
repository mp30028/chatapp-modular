import React from 'react';
import {Link} from 'react-router-dom';



function Menu() {
  return (
	    <span>
	    	<Link to="data">Data</Link><br/>
			<Link to="/">Home</Link><br/>
	    </span>
  );
}


export default Menu;
