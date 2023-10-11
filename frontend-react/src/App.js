import CreateVehicleComponent from './components/CreateVehicleComponent';
import HeaderComponent from './components/HeaderComponent';
import DepartmentSummaryComponent from './components/DepartmentSummaryComponent';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import Test from "./components/Test";

function App() {
  return (
      <div>
        <Router>
          <HeaderComponent />
          <div className='container'>
            <Switch>
              <Route path  = "/" exact component={Test} />
              <Route path = "/department" component={DepartmentSummaryComponent} />
              <Route path = "/add-vehicle/:id" component={CreateVehicleComponent} />
            </Switch>
          </div>
        </Router>
      </div>
  );
}

export default App
