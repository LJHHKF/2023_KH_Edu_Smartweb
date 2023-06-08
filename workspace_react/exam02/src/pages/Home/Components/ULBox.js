import './ULBox.css';

function ULBox(){
    let message = "변수 메시지1"
    const message2 = "const 메시지2"
    return(
      <div className='ulBox'>
        <ul>
          <li>{message}</li>
          <li>{message2}</li>
          <li>직접 입력 메시지</li>
        </ul>
      </div>
    );
  };

  export default ULBox;