import React from 'react';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import MainContainer from './components/MainContainer';
import About from './components/About';
import Contact from './components/Contact';
import CountryPickList from './components/CountryPickList';
import Menu from './components/Menu';


function App() {
	const mainContainerMenu = Menu();
	const APPLICATION_TITLE = "Demo Application";
  return (
	    <div>
	    	<Router>	    	
				<Routes>
					<Route path="/" element={<MainContainer menu={mainContainerMenu} title={APPLICATION_TITLE} />}>
						<Route path='about' element={<About/>}></Route>
						<Route path='contact' element={<Contact/>}></Route>
						<Route path='countries' element={<CountryPickList/>}></Route>
					</Route>
				</Routes>
			</Router>							
	    </div>
  );
}


export default App;
