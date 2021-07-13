import React , {Fragment} from 'react';
import classes from './App.module.css';
import NavigationBar from './components/DashBoardLayout/HorizontalNavigationBar/NavigationBar.js';
import LeftNavBar from './components/DashBoardLayout/LeftNavBar/NavBar';
import ContentLayout from './components/DashBoardLayout/FileContentLayout/ContentLayout';


function App() {
  return (
        <Fragment>
          <NavigationBar/>
            <div className={classes.containerClass}>
                <div className={classes.box1}>
                  <div className={classes.imageBox}></div> 
                  <LeftNavBar /> 
                </div>
                <div className={classes.box2}>
                    <ContentLayout />
                </div>
            </div>
        </ Fragment>
  );
}

export default App;
