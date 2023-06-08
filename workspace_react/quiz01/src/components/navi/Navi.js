import style from './Navi.module.css';
import Item from './NaviItem';

function Navi() {
  return (
    <div className={style.navi}>
      <Item>Home</Item>
      <Item>About</Item>
      <Item>Contact</Item>
    </div>
  );
}

export default Navi;
