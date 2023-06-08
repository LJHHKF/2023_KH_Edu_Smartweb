import style from './Body.module.css';
import Contents from './Contents';
import Aside from './Aside';

function Body() {
  return (
    <div className={style.body}>
      <Contents></Contents>
      <Aside></Aside>
    </div>
  );
}

export default Body;
