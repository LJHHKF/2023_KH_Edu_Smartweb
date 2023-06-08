import './TableBox.css';

const TableBox = () => {
    return(
      <div className='tableBox'>
        <table>
          <thead>
            <tr>
              <th>Seq</th>
              <th>Writer</th>
              <th>Message</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>1001</td>
              <td>Tom</td>
              <td>Hello React</td>
            </tr>
            <tr>
              <td>1002</td>
              <td>Jack</td>
              <td>React Practice</td>
            </tr>
          </tbody>
        </table>
  
      </div>
    );
  };

  export default TableBox;