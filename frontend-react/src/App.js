import "react-bootstrap-table-next/dist/react-bootstrap-table2.min.css"
import { Route, BrowserRouter as Router, Switch } from "react-router-dom"
import DepartmentSummaryComponent from "./components/DepartmentSummaryComponent"
import HeaderComponent from "./components/HeaderComponent"

function App() {
	return (
		<div>
			<Router>
				<HeaderComponent />
				<div className='container'>
					<Switch>
						<Route path='/' exact component={DepartmentSummaryComponent} />
						<Route path='/department' component={DepartmentSummaryComponent} />
						<Route
							path='/add-vehicle/:id'
							component={DepartmentSummaryComponent}
						/>
					</Switch>
				</div>
			</Router>
		</div>
	)
}

export default App
