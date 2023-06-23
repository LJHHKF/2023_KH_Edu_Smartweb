import './App.css';
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom';
import { useState } from 'react';

function Index(){
  return (
    <div>
      <table border='1' align='center'>
        <thead>
          <tr>
            <th colSpan={2}>
              Index
            </th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td><Link to='/input'>input</Link></td>
            <td><Link to='/output'>output</Link></td>
          </tr>
        </tbody>
      </table>
    </div>
  )
}

function Input({setData, id, setId}){

  const [title, setTitle] = useState("");
  const [genre, setGenre] = useState("");
  const [release_date, setReleas_date] = useState();

  function handleTitle(e){
    setTitle(e.target.value);
  }
  function handleGenre(e){
    setGenre(e.target.value);
  }
  function handleReleas_date(e){
    setReleas_date(e.target.value);
  }

  function submit(){
    if(title && genre && release_date){
      setData((prev)=>{return [...prev, {id:id, title:title, genre:genre, release_date:release_date}]});
      setId((prev)=>{return prev+1});
  
      setTitle("");
      setGenre("");
      setReleas_date();
    }else{
      alert("모든 값은 반드시 입력되어야 합니다.");
    }
  }

  return(
    <div>
      <table border='1' align='center'>
        <thead>
          <tr>
            <th colSpan={2}>
              Input
            </th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th>title</th>
            <td>
              <input type="text" placeholder='input title' onChange={handleTitle} value={title} />
            </td>
          </tr>
          <tr>
            <th>genre</th>
            <td>
              <input type="text" placeholder='input genre' onChange={handleGenre} value={genre}/>
            </td>
          </tr>
          <tr>
            <th>release date</th>
            <td>
              <input type="date" placeholder='input releaseDate' onChange={handleReleas_date} value={release_date ? release_date : new Date()} />
            </td>
          </tr>
          <tr>
            <td colSpan={2} align="center">
              <button onClick={submit}>addMovie</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  )
}

function Output({data, setData}){
  function deleteSubmit(targetIndex){
    const copied = data;
    setData([]);
    copied.forEach((item, index) => {
      setData((prev)=>{return targetIndex !== index ? [...prev, item] : [...prev]});
    });
  }

  return(
    <div>
      <table border={1} align='center'>
        <thead>
          <tr>
            <th colSpan={5}>Output</th>
          </tr>
          <tr>
            <th>id</th>
            <th>title</th>
            <th>genre</th>
            <th>release_date</th>
            <th>deleteBtn</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item, index)=>{
            return(
              <tr key={item.id}>
                <td>{item.id}</td>
                <td>{item.title}</td>
                <td>{item.genre}</td>
                <td><input type="date" value={item.release_date} readOnly/></td>
                <td><button onClick={()=>{deleteSubmit(index)}}>Delete</button></td>
              </tr>
            )
          })}
        </tbody>
      </table>
    </div>
  )
}

function App() {

  const [id, setId] = useState(1);
  //{id:id, title:"", genre:"", release_date:new Date()}
  const [data, setData] = useState([]);

  return (
    <BrowserRouter>
      <Index />
      <Routes>
        <Route path='/input' element={
          <Input
            setData={setData}
            id={id}
            setId={setId}
          />
        }/>
        <Route path='/output' element={
          <Output
            data={data}
            setData={setData}
          />
        }/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
