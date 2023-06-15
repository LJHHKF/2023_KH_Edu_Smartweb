import './App.css';
import { useState } from 'react';

function App() {
  const [count, setCount] = useState(0);

  function plus() {
    setCount(count + 1);
  }
  function minus() {
    setCount(count - 1);
  }

  return (
    <div>
      <div>{count}</div>
      <div>
        <button onClick={plus}>+</button>
        <button onClick={minus}>-</button>
      </div>
    </div>
  );
}

function App2() {
  const [str, setStr] = useState('');

  function onChangeStr(e) {
    setStr(e.target.value);
  }

  return (
    <div>
      <div>{str}</div>
      <input type="text" onChange={onChangeStr} />
    </div>
  );
}

function App3() {
  const [writer, setWriter] = useState('');
  const [music, setMusic] = useState('');

  function onChangeWriter(e) {
    setWriter(e.target.value);
  }
  function onChangeMusic(e) {
    setMusic(e.target.value);
  }
  function submit() {
    console.log(writer, music);
    setWriter('');
    setMusic('');
  }

  return (
    <div>
      <input
        type="text"
        placeholder="input writer"
        onChange={onChangeWriter}
        value={writer}
      />
      <br />
      <input
        type="text"
        placeholder="music name"
        onChange={onChangeMusic}
        value={music}
      />
      <br />
      <button onClick={submit}>확인</button>
    </div>
  );
}

function App4() {
  const [data, setData] = useState({ wrtier: '', music: '' });

  function onChangeWriter(e) {
    setData({ ...data, writer: e.target.value });
    //setData({writer:e.target.value, music:data.music});
  }
  function onChangeMusic(e) {
    setData({ ...data, music: e.target.value });
    //setData({writer:data.wrtier, music:e.target.value});
  }
  function submit() {
    console.log(data);
    setData({ writer: '', music: '' });
  }

  return (
    <div>
      <input
        type="text"
        placeholder="input writer"
        onChange={onChangeWriter}
        value={data.writer}
      />
      <br />
      <input
        type="text"
        placeholder="music name"
        onChange={onChangeMusic}
        value={data.music}
      />
      <br />
      <button onClick={submit}>확인</button>
    </div>
  );
}

function App5() {
  const [arr, setArr] = useState([]);
  const [str, setStr] = useState('');

  function handleChange(e) {
    setStr(e.target.value);
  }

  function handleAdd() {
    //setArr([...arr, str]);
    setArr((prev) => {
      return [...prev, str];
    }); //권고사항. 오동작(SideEffect)를 확실히 피할 수 있음.
    setStr('');
  }

  return (
    <div>
      <input
        type="text"
        placeholder="input text to add"
        onChange={handleChange}
        value={str}
      />
      <button onClick={handleAdd}>Add to array</button>
      {arr.map((item, index) => {
        return <div key={index}>{item}</div>;
      })}
    </div>
  );
}

function App6() {
  const [contacts, setContacts] = useState([
    { seq: 1, name: 'Susan', contact: '01012344321' },
    { seq: 2, name: 'Tome', contact: '101098761234' },
    { seq: 3, name: 'Jane', contact: '01040302050' },
  ]);
  const [contact, setContact] = useState({seq:"", name:"", contact:""});

  function handleChange(e){
    const {name, value} = e.target;
    setContact({...contact, [name] : value });
  }
  function onSubmit(){
    setContacts((prev)=>{return [...prev, contact]});
    setContact({seq:"", name:"", contact:""});
  }

  return (
    <div>
      {contacts.map((item, index) => {
        return (
          <div key={index}>{`${item.seq} ${item.name} ${item.contact}`}</div>
        );
      })}
      {
        ["seq", "name", "contact"].map((item, index) => {
          return (
            <div key={index}>
              <input type="text" placeholder={`input ${item}`} name={`${item}`} onChange={handleChange} value={contact[item]||null} />
              <br />
            </div>
          )
      })}
      {/* <input type="text" placeholder='input seq' name="seq" onChange={handleChange} value={contact.seq} /><br/>
      <input type="text" placeholder='input name' name="name" onChange={handleChange} value={contact.name} /><br/>
      <input type="text" placeholder='input contact' name="contact" onChange={handleChange} value={contact.contact} /><br/> */}
      <button onClick={onSubmit}>Add</button>
    </div>
  );
}

export default App6;
