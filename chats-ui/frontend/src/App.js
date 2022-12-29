import * as React from 'react';
import { Routes, Route, NavLink, Outlet } from 'react-router-dom';
import AppHome from "./Home";
import ChatsLayout from "./chats/Layout"
import ChatsData from "./chats/Data"
import PersonsLayout from "./persons/Layout";
import PersonsData from "./persons/Data";
import Example01Layout from "./examples/example-01/Layout";
import Example01About from "./examples/example-01/About";
import Example01Home from "./examples/example-01/Home";
import Example02Layout from "./examples/example-02/Layout";
import Example02Contact from "./examples/example-02/Contact";
import Example02Data from "./examples/example-02/DataDisplay";
import Example02About from "./examples/example-02/About";
import Example03Layout from "./examples/example-03/Layout";
import Example04Layout from "./examples/example-04/Layout";
import Example05Layout from "./examples/example-05/Layout";
import Example06Layout from "./examples/example-06/Layout";
import Example07Layout from "./examples/example-07/Layout";
import Example08Layout from "./examples/example-08/Layout";
import Example09Layout from "./examples/example-09/Layout";
import Example10Layout from "./examples/example-10/Layout";
import Example11Layout from "./examples/example-11/Layout";
import Example12Layout from "./examples/example-12/Layout";
const App = () => {
	return (
		<>
		    <h1>From App Component</h1>
			<nav>
				<NavLink to="/chats">Chat-App</NavLink><br />
				<NavLink to="/persons">People Management App</NavLink><br />
				<NavLink to="/example-01">Example 01</NavLink><br />
				<NavLink to="/example-02">Example 02</NavLink><br />
				<NavLink to="/example-03">Example 03</NavLink><br />
				<NavLink to="/example-04">Example 04</NavLink><br />
				<NavLink to="/example-05">Example 05</NavLink><br />
				<NavLink to="/example-06">Example 06</NavLink><br />
				<NavLink to="/example-07">Example 07</NavLink><br />
				<NavLink to="/example-08">Example 08</NavLink><br />
				<NavLink to="/example-09">Example 09</NavLink><br />
				<NavLink to="/example-10">Example 10</NavLink><br />
				<NavLink to="/example-11">Example 11</NavLink><br />
				<NavLink to="/example-12">Example 12</NavLink><br />					
				<NavLink to="/" >Home</NavLink><br /><br />
			</nav>


			<Routes>
				<Route path="chats" element={<ChatsLayout />}>
					<Route path="data" element={<ChatsData />} />
				</Route>
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
				<Route path="example-05/*" element={<Example05Layout />}>
				</Route>
				<Route path="example-06/*" element={<Example06Layout />}>
				</Route>
				<Route path="example-07/*" element={<Example07Layout />}>
				</Route>
				<Route path="example-08/*" element={<Example08Layout />}>
				</Route>
				<Route path="example-09/*" element={<Example09Layout />}>
				</Route>
				<Route path="example-10/*" element={<Example10Layout />}>
				</Route>
				<Route path="example-11/*" element={<Example11Layout />}>
				</Route>
				<Route path="example-12/*" element={<Example12Layout />}>
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