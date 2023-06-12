//import logo from './logo.svg';
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom';
import './App.css';

const SideMenu = function(){
  return (
    <table border={1} className='table'>
      <tr>
        <td>
          <Link to="/red">Red</Link>
        </td>
      </tr>
      <tr>
        <td>
          <Link to="/green">Green</Link>
        </td>
      </tr>
      <tr>
        <td>
          <Link to="/blue">Blue</Link>
        </td>
      </tr>
    </table>
  );
}

const RedBox = function(){
  return (
    <div className='box' style={{backgroundColor:"pink"}}></div>
  );
}

const BlueBox = function(){
  return(
    <div className='box' style={{backgroundColor:"blue"}}></div>
  )
}

const GreenBox = function(){
  return(
    <div className='box' style={{backgroundColor:"green"}}></div>
  )
}

function App() {
  return (
    <BrowserRouter>
      <div className='container'>
        <SideMenu />
        <Routes>
          <Route path="/" element={<RedBox />}></Route>
          <Route path="/red" element={<RedBox />}></Route>
          <Route path="/blue" element={<BlueBox />}></Route>
          <Route path="/green" element={<GreenBox />}></Route>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
