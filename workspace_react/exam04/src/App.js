//import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Link, Outlet, Route, Routes } from 'react-router-dom';

const Index = function () {
  return (
    <div className="container">
      <table border="1" align="center">
        <thead>
          <tr>
            <th colSpan={3}>Index</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>
              <Link to="/input">toInput</Link>
            </td>
            <td>
              <Link to="/output">toOutput</Link>
            </td>
            <td>
              <Link to="/sub">toSub</Link>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

const Input = function () {
  return (
    <div className="container">
      <table border={1} align={'center'}>
        <thead>
          <tr>
            <th>Input message</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>
              <input type="text" placeholder="Input writer"></input>
            </td>
          </tr>
          <tr>
            <td>
              <input type="text" placeholder="Input message"></input>
            </td>
          </tr>
          <tr>
            <td style={{ display: 'flex', justifyContent: 'space-evenly' }}>
              <button
                onClick={() => {
                  console.log('Write Click!');
                }}
              >
                write
              </button>
              <Link to="/">
                <button>back</button>
              </Link>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};
const Output = function () {
  const message = [
    { seq: 1, writer: 'Jane', message: 'React Router' },
    { seq: 2, writer: 'Ryan', message: 'Router Practice' },
    { seq: 3, writer: 'Tom', message: 'Practice Hard' },
  ];

  return (
    <div className="container">
      <table border={1} align="center">
        <thead>
          <tr>
            <th colSpan={3}>Message List</th>
          </tr>
          <tr>
            <th>Seq</th>
            <th>Writer</th>
            <th>Message</th>
          </tr>
        </thead>
        <tbody>
          {message.map((item, index) => {
            return (
              <tr key={item.seq}>
                <td>{item.seq}</td>
                <td>{item.writer}</td>
                <td>{item.message}</td>
              </tr>
            );
          })}
          <tr>
            <td colSpan={3} style={{ textAlign: 'center' }}>
              <Link to="/">
                <button>back</button>
              </Link>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

const RedBox = function () {
  return (
    <div
      style={{ backgroundColor: 'pink', width: '100%', height: '100%' }}
    ></div>
  );
};

const BlueBox = function () {
  return (
    <div
      style={{ backgroundColor: 'blue', width: '100%', height: '100%' }}
    ></div>
  );
};

const GreenBox = function () {
  return (
    <div
      style={{ backgroundColor: 'green', width: '100%', height: '100%' }}
    ></div>
  );
};

const Sub = function () {
  return (
    <div className="container">
      <table border="1" align="center">
        <thead>
          <tr>
            <th colSpan={3}>Sub Page</th>
          </tr>
          <tr>
            <th>
              <Link to="/sub/red">Red</Link>
            </th>
            <th>
              <Link to="/sub/green">Green</Link>
            </th>
            <th>
              <Link to="/sub/blue">Blue</Link>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr>
            {/* baseUri : /sub/ */}
            {/*
                <Routes>
                <Route path="red" element={<RedBox />}></Route>
                <Route path="green" element={<GreenBox />}></Route>
                <Route path="blue" element={<BlueBox />}></Route>
                </Routes>
                */}
            <td colSpan={3} width={200} height={200}>
              <Outlet></Outlet>
            </td>
          </tr>
          <tr>
            <td colSpan={3} style={{ textAlign: 'center' }}>
              <Link to="/">
                <button>back</button>
              </Link>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

function App() {
  // React SPA 에서 페이지를 전환하는 방법
  // Browser 의 주소창을 활용한다는 측면에선 기존 방식과 다르지 않음.
  // - location.href, a 도 여전히 활용 가능. (주소창을 바꾸고 엔터를 치는 기능. 서버로 요청이 날라감. 불필요한 리퀘스트가 서버에 발송됨.)
  // - React에서는 일반적으로 react-router-dom 라이브러리를 활용하여 전환한다.
  return (
    <BrowserRouter>
      <Index />
      <Routes>
        <Route path="/input" element={<Input />} />
        <Route path="/output" element={<Output />} />
        <Route path="/sub/*" element={<Sub />}>
          <Route path="red" element={<RedBox />} />
          <Route path="green" element={<GreenBox />} />
          <Route path="blue" element={<BlueBox />} />
        </Route>
        <Route path="*" element={<Index />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
