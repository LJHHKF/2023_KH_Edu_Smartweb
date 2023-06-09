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

//전개 연산자(spread operator)
const arr1 = ["Apple", "Orange", "Mango"];
const arr2 = [...arr1];
console.log(arr2);

const obj1 = {name:"Tom", phone:"01012341234"}
const obj2 = {...obj1};
console.log(obj2);

//Desturct
const [a,b,c] = arr1;
console.log(`${a} ${b} ${c}`);

const {name, phone} = obj1;
console.log(`${name}, ${phone}`);

//arr.map
arr1.map((e)=>{
  console.log(e);
});
const result = arr1.map((e, index)=>{
  return e; //값 하나씩 result에 들어갈 값에 배열로 들어가기 시작함.
});
console.log(result);

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
