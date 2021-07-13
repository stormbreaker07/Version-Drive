import React , {Fragment} from 'react';
import classes from './NavigationBar.module.css';
import logo from '../../../logo/logo3.png';
import AccountCircleRoundedIcon from '@material-ui/icons/AccountCircleRounded';
import NotificationsActiveRoundedIcon from '@material-ui/icons/NotificationsActiveRounded';
import InfoRoundedIcon from '@material-ui/icons/InfoRounded';
import DashboardRoundedIcon from '@material-ui/icons/DashboardRounded';

const NavigationBar = () => {

    return (
        <Fragment>
            <div className={classes.topBar}>
                <div className={classes.logo}>
                    <a href="#news">
                        <img src={logo} alt="background will be uploaded shortly" />
                    </a>
                 </div>
                 <DashboardRoundedIcon href="#news" className={classes.LeftIconButton}/>
                <InfoRoundedIcon href="#news" className={classes.LeftIconButton}/>   
                <AccountCircleRoundedIcon href="#news" className={classes.RightIconButton} />
                <NotificationsActiveRoundedIcon href="#news" className={classes.RightIconButton}/>
            </div>
        </Fragment>
    )
}

export default NavigationBar;