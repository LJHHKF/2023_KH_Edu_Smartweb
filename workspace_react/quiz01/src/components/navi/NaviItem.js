import style from './NaviItem.module.css';

function NaviItem({ children }) {
  return <div className={style.naviItem}>{children}</div>;
}

export default NaviItem;
