import React from 'react';
import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { NavigationBar } from './components/NavigationBar';
import { Emanetler } from './pages/Emanetler';
import { NoMatch } from './NoMatch';
import Sidebar from './components/Sidebar';
import {Kitaplar} from "./pages/Kitaplar";
import {Yazarlar} from "./pages/Yazarlar";
import {Uyeler} from "./pages/Uyeler";
import {Kutuphaneler} from "./pages/Kutuphaneler";

function App() {
  return (
    <React.Fragment>
      <Router>
        <NavigationBar />
        <Sidebar />
        <Switch>
          <Route exact path="/" component={Kitaplar} />
          <Route path="/emanetler" component={Emanetler} />
          <Route path="/kutuphaneler" component={Kutuphaneler} />
          <Route path="/uyeler" component={Uyeler} />
          <Route path="/yazarlar" component={Yazarlar} />
          <Route component={NoMatch} />
        </Switch>
      </Router>
    </React.Fragment>
  );
}

export default App;
