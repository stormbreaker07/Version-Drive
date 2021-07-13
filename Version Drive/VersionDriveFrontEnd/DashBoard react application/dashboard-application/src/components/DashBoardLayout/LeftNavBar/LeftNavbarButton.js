import React from 'react';
import classes from './LeftNavBarButton.module.css'
import ProgressBarContainer from '../ProgressBar/ProgressBarContainer';
import FileCopyRoundedIcon from '@material-ui/icons/FileCopyRounded';
import ArrowBackRoundedIcon from '@material-ui/icons/ArrowBackRounded';
import ArrowForwardRoundedIcon from '@material-ui/icons/ArrowForwardRounded';
import StorageRoundedIcon from '@material-ui/icons/StorageRounded';


const LeftNavBarButton = (props) => {

    let x;
    if (props.value === "My Files") {
        x = <FileCopyRoundedIcon />;
    }
    else if (props.value === "Shared Files") {
        x = <ArrowBackRoundedIcon />;
    }
    else if (props.value === "Requested Files") {
        x = <ArrowForwardRoundedIcon />;
    } else {
        x = <div className={classes.outerContainer} >
                <div className={classes.column}>
                    <StorageRoundedIcon />
                    <div className={classes.innerText}>{props.value}</div>
                </div>
                <ProgressBarContainer />
        </div>
    }


    return (
        props.value === "Storage Used" ?
            x : <div>
                <div className={classes.button} type="button">
                    {x}
                    <div className={classes.innerText}>{props.value}</div>
                </div>
            </div>
    )
}

export default LeftNavBarButton;