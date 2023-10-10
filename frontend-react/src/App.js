import CreateVehicleComponent from './components/CreateVehicleComponent';
import HeaderComponent from './components/HeaderComponent';
import ListVehicleComponent from './components/ListVehicleComponent';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';

function App() {
  return (
      <div>
        <Router>
          <HeaderComponent />
          <div className='container'>
            <Switch>
              <Route path  = "/" exact component={ListVehicleComponent} />
              <Route path = "/vehicles" component={ListVehicleComponent} />
              <Route path = "/add-vehicle/:id" component={CreateVehicleComponent} />
            </Switch>
          </div>
        </Router>
      </div>
  );
}

export default App
