import React from 'react';
import Button from './LeftNavbarButton';
import classes from './NavBar.module.css'
import Profile from './ProfileButton';  


const NavBar = () => {
    
    return (
        <div className={classes.navbar}>
            <Profile value="Tanuj"/>
            <Button value="My Files" />
            <div className={classes.underLine}></div>
            <Button value="Shared Files" />
            <div className={classes.underLine}></div>
            <Button value="Requested Files" />
            <div className={classes.underLine}></div>
            <Button value="Storage Used" />
            <div className={classes.underLine}></div>
            
        </div>
    )
} 

export default NavBar;