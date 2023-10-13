import "react-bootstrap-table-next/dist/react-bootstrap-table2.min.css"
import { Route, BrowserRouter as Router, Switch } from "react-router-dom"
import CreateOrEditDepartmentComponent from "./components/CreateOrEditDepartmentComponent"
import CreateOrUpdateEmployeeComponent from "./components/CreateOrUpdateEmployee"
import CreateOrUpdatePositionComponent from "./components/CreateOrUpdatePositionComponent"
import DepartmentSummaryComponent from "./components/DepartmentSummaryComponent"
import EmployeeSummaryComponent from "./components/EmployeeSummaryComponent"
import HeaderComponent from "./components/HeaderComponent"
import PositionSummaryComponent from "./components/PositionSummaryComponent"

function App() {
	return (
		<div>
			<Router>
				<HeaderComponent />
				<div className='container'>
					<Switch>
						<Route path='/' exact component={EmployeeSummaryComponent} />
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
						<Route
							path='/add-position/:id'
							component={CreateOrUpdatePositionComponent}
						/>
						<Route
							path='/add-position/add'
							component={CreateOrUpdatePositionComponent}
						/>
						<Route
							path='/summary/employee'
							component={EmployeeSummaryComponent}
						/>
						<Route
							path='/add-employee/:id'
							component={CreateOrUpdateEmployeeComponent}
						/>
						<Route
							path='/add-employee/add'
							component={CreateOrUpdateEmployeeComponent}
						/>
					</Switch>
				</div>
			</Router>
		</div>
	)
}

export default App
