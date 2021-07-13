import AccountCircleRoundedIcon from '@material-ui/icons/AccountCircleRounded';
import classes from './ProfileButton.module.css';

const ProfileButton = (Props) => {

    return (
        <div className={classes.container}>
          <AccountCircleRoundedIcon className={classes.ProfileButton}/>
          <div className={classes.userName}>{Props.value}</div>    
        </div>
    ) 
}

export default ProfileButton;