import React from 'react';
import {Link} from 'react-router-dom';



function Menu() {
  return (
	    <span>
			<Link to="/">Home</Link><br/>
			<Link to="about">About Us</Link><br/>
			<Link to="contact">Contact Us</Link><br/>  
			<Link to="data">Data</Link><br/>
	    </span>
  );
}


export default Menu;
