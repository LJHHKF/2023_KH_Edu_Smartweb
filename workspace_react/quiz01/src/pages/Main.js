import Header from '../components/header/Header';
import Footer from '../components/footer/Footer';
import Navi from '../components/navi/Navi';
import Body from '../components/body_main/Body';

function Main() {
  return (
    <div className="container">
      <Header />
      <Navi />
      <Body />
      <Footer />
    </div>
  );
}

export default Main;
