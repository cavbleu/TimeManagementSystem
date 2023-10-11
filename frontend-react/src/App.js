import { Route, BrowserRouter as Router, Switch } from "react-router-dom"
import CreateVehicleComponent from "./components/CreateVehicleComponent"
import DepartmentSummaryComponent from "./components/DepartmentSummaryComponent"
import HeaderComponent from "./components/HeaderComponent"
import Test from "./components/Test"

import "react-bootstrap-table-next/dist/react-bootstrap-table2.min.css"

function App() {
	return (
		<div>
			<Router>
				<HeaderComponent />
				<div className='container'>
					<Switch>
						<Route path='/' exact component={Test} />
						<Route path='/department' component={DepartmentSummaryComponent} />
						<Route path='/add-vehicle/:id' component={CreateVehicleComponent} />
					</Switch>
				</div>
			</Router>
		</div>
	)
}

export default App
