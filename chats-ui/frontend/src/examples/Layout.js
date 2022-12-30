import React from 'react';
import { Routes, Route, NavLink } from 'react-router-dom';
import Example01Layout from "./example-01/Layout";
import Example01About from "./example-01/About";
import Example01Home from "./example-01/Home";
import Example02Layout from "./example-02/Layout";
import Example02Contact from "./example-02/Contact";
import Example02Data from "./example-02/DataDisplay";
import Example02About from "./example-02/About";
import Example03Layout from "./example-03/Layout";
import Example04Layout from "./example-04/Layout";
import Example05Layout from "./example-05/Layout";
import Example06Layout from "./example-06/Layout";
import Example07Layout from "./example-07/Layout";
import Example08Layout from "./example-08/Layout";
import Example09Layout from "./example-09/Layout";
import Example10Layout from "./example-10/Layout";
import Example11Layout from "./example-11/Layout";
import Example12Layout from "./example-12/Layout";

const Layout = () => {

  return (
    <div>
      <h1>Examples</h1>

      <nav>
        <NavLink to="/" >Home</NavLink><br/>
				<NavLink to="example-01">Example 01</NavLink><br />
				<NavLink to="example-02">Example 02</NavLink><br />
				<NavLink to="example-03">Example 03</NavLink><br />
				<NavLink to="example-04">Example 04</NavLink><br />
				<NavLink to="example-05">Example 05</NavLink><br />
				<NavLink to="example-06">Example 06</NavLink><br />
				<NavLink to="example-07">Example 07</NavLink><br />
				<NavLink to="example-08">Example 08</NavLink><br />
				<NavLink to="example-09">Example 09</NavLink><br />
				<NavLink to="example-10">Example 10</NavLink><br />
				<NavLink to="example-11">Example 11</NavLink><br />
				<NavLink to="example-12">Example 12</NavLink><br />	
      </nav>
			<Routes>
				<Route path="example-01" element={<Example01Layout />}>
 					<Route path="home" element={<Example01Home />} />
					<Route path="about" element={<Example01About />} />
				</Route>
				<Route path="example-02" element={<Example02Layout />}>
 					<Route path="contact" element={<Example02Contact />} />
					<Route path="about" element={<Example02About />} />
					<Route path="data" element={<Example02Data />} />
				</Route>
				<Route path="example-03" element={<Example03Layout />} />
				<Route path="example-04/*" element={<Example04Layout />} />
				<Route path="example-05/*" element={<Example05Layout />} />
				<Route path="example-06/*" element={<Example06Layout />} />
				<Route path="example-07/*" element={<Example07Layout />} />
				<Route path="example-08/*" element={<Example08Layout />} />
				<Route path="example-09/*" element={<Example09Layout />} />
				<Route path="example-10/*" element={<Example10Layout />} />
				<Route path="example-11/*" element={<Example11Layout />} />
				<Route path="example-12/*" element={<Example12Layout />} />
			</Routes>
    </div>
  );
};

export default Layout;
