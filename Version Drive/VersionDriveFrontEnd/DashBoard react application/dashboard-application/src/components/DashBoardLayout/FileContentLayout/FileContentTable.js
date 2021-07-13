import classes from './FileContentTable.module.css'

const dummyFiles = [
  {
    id : 1,
    Name : "Alcamist",
    date : new Date().getDate,
    
  }
];

const ContentTable = () => {

    return (
        <div className={classes.tableContainer}>
            <table className={classes.table}>
        <thead className={classes.thead}>
          <tr className={classes.tr}>
            <th>S.No</th>
            <th>Name</th>
            <th>Date</th>
          </tr>
        </thead>
        <tbody className={classes.tbody}>
        <tr >
            <td>1.1</td>
            <td>1.1</td>
            <td>2.1</td>
          </tr>
          <tr>
          <td>1.1</td>
            <td>1.2</td>
            <td>2.2</td>
          </tr>
          <tr>
          <td>1.1</td>
            <td>1.3</td>
            <td>2.3</td>
          </tr>
          <tr>
          <td>1.1</td>
            <td>1.4</td>
            <td>2.4</td>
          </tr>
          <tr>
          <td>1.1</td>
            <td>1.5</td>
            <td>2.5</td>
          </tr>
          <tr>
          <td>1.1</td>
            <td>1.1</td>
            <td>2.1</td>
          </tr>
          <tr>
          <td>1.1</td>
            <td>1.2</td>
            <td>2.2</td>
          </tr>
          <tr>
          <td>1.1</td>
            <td>1.3</td>
            <td>2.3</td>
          </tr>
          <tr>
          <td>1.1</td>
            <td>1.4</td>
            <td>2.4</td>
          </tr>
          <tr>
          <td>1.1</td>
            <td>1.5</td>
            <td>2.5</td>
          </tr>
          <tr>
            <td>1.1</td>
            <td>1.1</td>
            <td>2.1</td>
          </tr>
          <tr>
          <td>1.1</td>
            <td>1.2</td>
            <td>2.2</td>
          </tr>
          <tr>
          <td>1.1</td>
            <td>1.3</td>
            <td>2.3</td>
          </tr>
          <tr>
          <td>1.1</td>
            <td>1.4</td>
            <td>2.4</td>
          </tr>
          <tr>
          <td>1.1</td>
            <td>1.5</td>
            <td>2.5</td>
          </tr>
          <tr>
          <td>1.1</td>
            <td>1.1</td>
            <td>2.1</td>
          </tr>
          <tr>
          <td>1.1</td>
            <td>1.2</td>
            <td>2.2</td>
          </tr>
          <tr>
          <td>1.1</td>
            <td>1.3</td>
            <td>2.3</td>
          </tr>
          <tr>
          <td>1.1</td>
            <td>1.4</td>
            <td>2.4</td>
          </tr>
          <tr>
          <td>1.1</td>
            <td>1.5</td>
            <td>2.5</td>
          </tr>
        </tbody>
      </table>
        </div>
    )
}

export default ContentTable;