import * as React from 'react';
import { Routes, Route, NavLink, Outlet } from 'react-router-dom';
import AppHome from "./Home";
import Example01Layout from "./examples/example-01/Layout";
import Example01About from "./examples/example-01/About";
import Example01Home from "./examples/example-01/Home";
import Example02Layout from "./examples/example-02/Layout";
import Example02Contact from "./examples/example-02/Contact";
import Example02Data from "./examples/example-02/DataDisplay";
import Example02About from "./examples/example-02/About";
import Example03Layout from "./examples/example-03/Layout";
import Example04Layout from "./examples/example-04/Layout";
import PersonsLayout from "./components/persons/Layout";
import PersonsData from "./components/persons/Data";

const App = () => {
	return (
		<>
		    <h1>From App Component</h1>
			<nav>
				<NavLink to="/persons">People Management App</NavLink><br />
				<NavLink to="/example-01">Example 01</NavLink><br />
				<NavLink to="/example-02">Example 02</NavLink><br />
				<NavLink to="/example-03">Example 03</NavLink><br />
				<NavLink to="/example-04">Example 04</NavLink><br />
				<NavLink to="/" >Home</NavLink><br /><br />
			</nav>


			<Routes>
				<Route path="persons" element={<PersonsLayout />}>
					<Route path="data" element={<PersonsData />} />
				</Route>
				<Route path="example-01" element={<Example01Layout />}>
 					<Route path="home" element={<Example01Home />} />
					<Route path="about" element={<Example01About />} />
				</Route>
				<Route path="example-02" element={<Example02Layout />}>
 					<Route path="contact" element={<Example02Contact />} />
					<Route path="about" element={<Example02About />} />
					<Route path="data" element={<Example02Data />} />
				</Route>
				<Route path="example-03" element={<Example03Layout />}>
				</Route>
				<Route path="example-04/*" element={<Example04Layout />}>
				</Route>				
				<Route path="/" element={<AppHome />} />
				<Route index element={<AppHome />} />
				<Route path="*" element={<p>There's nothing here: 404!</p>} />
			</Routes>
			<main>
				<Outlet />
			</main>
		</>
	);
};

export default App;