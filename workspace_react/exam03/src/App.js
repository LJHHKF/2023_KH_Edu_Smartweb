//import logo from './logo.svg';
import './App.css';
import {Container} from 'reactstrap';
import TopNavi from './Components/TopNavi/TopNavi';
import Main from './pages/Main/Main';

function App() {
  return (
    <>
      <TopNavi></TopNavi>
      <Container style={{ marginTop: '50px' }}>
        <Main></Main>
      </Container>
    </>
  );
}

export default App;
