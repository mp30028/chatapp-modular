import * as React from 'react';
import { NavLink, Outlet } from 'react-router-dom';

const Layout = () => {

  return (
    <div>
      <h1>Example 01 Layout</h1>

      <nav>
        <NavLink to="/" >Home</NavLink><br/>
        <NavLink to="home" >Example 01 Home</NavLink><br/>
        <NavLink to="about">Example 01 About</NavLink>
      </nav>
		<Outlet />
    </div>
  );
};

export default Layout;