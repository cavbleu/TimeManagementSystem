import CreateVehicleComponent from './components/CreateVehicleComponent';
import HeaderComponent from './components/HeaderComponent';
import DepartmentSummaryComponent from './components/DepartmentSummaryComponent';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';

function App() {
  return (
      <div>
        <Router>
          <HeaderComponent />
          <div className='container'>
            <Switch>
              <Route path  = "/" exact component={DepartmentSummaryComponent} />
              <Route path = "/department" component={DepartmentSummaryComponent} />
              <Route path = "/add-vehicle/:id" component={CreateVehicleComponent} />
            </Switch>
          </div>
        </Router>
      </div>
  );
}

export default App
