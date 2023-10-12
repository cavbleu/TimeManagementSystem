import "react-bootstrap-table-next/dist/react-bootstrap-table2.min.css"
import { Route, BrowserRouter as Router, Switch } from "react-router-dom"
import CreateOrEditDepartmentComponent from "./components/CreateOrEditDepartmentComponent"
import DepartmentSummaryComponent from "./components/DepartmentSummaryComponent"
import HeaderComponent from "./components/HeaderComponent"
import PositionSummaryComponent from "./components/PositionSummaryComponent"

function App() {
	return (
		<div>
			<Router>
				<HeaderComponent />
				<div className='container'>
					<Switch>
						<Route path='/' exact component={DepartmentSummaryComponent} />
						<Route
							path='/summary/department'
							component={DepartmentSummaryComponent}
						/>
						<Route
							path='/add-department/:id'
							component={CreateOrEditDepartmentComponent}
						/>
						<Route
							path='/add-department/add'
							component={CreateOrEditDepartmentComponent}
						/>
						<Route
							path='/summary/position'
							component={PositionSummaryComponent}
						/>
					</Switch>
				</div>
			</Router>
		</div>
	)
}

export default App
