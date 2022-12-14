import React from 'react';
import {Route, NavLink, Routes} from 'react-router-dom';
import '../../common/css/Zonesoft.css';
import FetchOnlyDataLayout from "./01-fetch-only-data/Layout";
import FetchOnlyEventsLayout from "./02-fetch-only-events/Layout"
import FetchBothDataAndEventsLayout from "./03-fetch-data-and-events/Layout";
//import FetchOnlyEventsLayout from "./02-fetch-only-events/Layout";

function Layout(){

  return (
	  <div>
	  		<h1>Example 04. Fetching Data and Events</h1>		
			<nav>
				<NavLink to="01-fetch-only-data">Fetch only data - NO-EVENTS involved</NavLink><br />
				<NavLink to="02-fetch-only-events">Fetch only events- NO-DATA involved</NavLink><br />
				<NavLink to="03-fetch-data-and-events">Fetch both DATA and EVENTS</NavLink><br />
				<NavLink to="/" >Home</NavLink><br /><br />
			</nav>
			<Routes>
				<Route path="01-fetch-only-data" element={<FetchOnlyDataLayout />}></Route>
				<Route path="02-fetch-only-events" element={<FetchOnlyEventsLayout />}></Route>
				<Route path="03-fetch-data-and-events" element={<FetchBothDataAndEventsLayout />}></Route>
			</Routes>
	  </div>	
  );
}

export default Layout;