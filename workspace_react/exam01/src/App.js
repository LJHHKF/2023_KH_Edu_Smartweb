import './App.css';
import image from './assets/기온별 옷차림.jpg';

const ULBox = () => {
  let message = 'Angular';
  return (
    <div className="ulBox">
      <ul>
        <li>{message}</li>
        <li>React</li>
        <li>Vue</li>
      </ul>
    </div>
  );
};

function TableBox() {
  return (
    <div className="tableBox">
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
}

function ImageBox() {
  return (
    <div className="imageBox">
      <img src={image} alt="기온 별 옷차림"></img>
    </div>
  );
}

function App() {
  return (
    <div className="container">
      <ULBox />
      <TableBox />
      <ImageBox />
    </div>
  );
}

export default App;
