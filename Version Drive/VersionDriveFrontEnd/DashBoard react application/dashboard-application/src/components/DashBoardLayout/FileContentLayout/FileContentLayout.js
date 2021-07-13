import ContentTable from "./FileContentTable";
import classes from './FIleContentLayout.module.css';


const FileContentLayout = () => {

    return (
        <div className={classes.fileContentbox}>
            <ContentTable></ContentTable>
        </div>
    )
}

export default FileContentLayout;