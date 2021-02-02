import React from 'react';
import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
} from 'react-router-dom';
import LandingPage from './pages/LandingPage'
import LoginPage from './pages/LoginPage'
import AdminPage from './pages/AdminPage'
import UserPage from './pages/UserPage'
import UpdateChallenge from './components/UpdateChallenge';

function App() {

  return (
    <div className="App" >
      <Router>
        <Switch>
          <Route exact path="/" component={LandingPage} />
          <Route exact path="/login" component={LoginPage} />
          <Route exact path="/challenge" component={AdminPage} />
          <Route exact path="/commitment" component={UserPage} />
          <Route exact path="/update-challenge/:id" component={UpdateChallenge} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;