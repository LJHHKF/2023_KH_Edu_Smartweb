import image from '../../../assets/postman-icon.png'
import style from './ImageBox.module.css';

const ImageBox = () => (
    <div className='imageBox'>
      <img src={image} className={style.image} alt="포스트맨 아이콘"></img>
    </div>
  );

export default ImageBox;