import classes from './ContentLayout.module.css';
import FileContentLayout from './FileContentLayout';


const ContentLayout = () => {

    return (
        <div className={classes.ContentLayout}>
            <FileContentLayout />
        </div>
    )
}

export default ContentLayout;