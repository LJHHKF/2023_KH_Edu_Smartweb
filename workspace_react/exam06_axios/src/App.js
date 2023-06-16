import axios from 'axios';
import './App.css';
import { useEffect, useState } from 'react';

function App() {

  const [movies, setMovies] = useState([]);

  useEffect(()=>{
    selectAll();
  },[])

  function selectAll(){
    axios.request({
      method: "get",
      url:"/data/selectAll",
    }).then((response)=>{
      setMovies([]);
      response.data.forEach((item, index)=>{
        setMovies((prev)=>{return [...prev, item]});
      })
    }).catch((error)=>{
      console.log(error);
    });
  }

  function insert(){
    axios({
      method:"post",
      url:"/data/insert",
      params:{
        id:1001,
        title:"Movie01",
        genre:"Action"
      }
    }).then((response)=>{
      setMovies((prev)=>{return [...prev, response.data]});
    }).catch((error)=>{
      console.log(error);
    })
  }

  function deleteById(id){
    axios({
      method:"delete",
      url:"/data/deleteById",
      params:{
        id:id
      }
    }).then((response)=>{
      selectAll();
    }).catch((error)=>{
      console.log(error);
    })
  }

  return (
    <>
      <div>
        <button onClick={insert}>insert</button>
      </div>
      {
        movies.map((item, index)=>{
          return(
            <div key={index}>
              {`${item.id},${item.title},${item.genre}`}
              <button onClick={()=>deleteById(item.id)}>x</button>
            </div>
          );
        })
      }
    </>
    
  );
}

export default App;
