import "./App.css";
import {useState} from "react";

function App() {
  const [welcome, setWelcome] = useState("...");
  return (<div className="App">
        <h1>React와 Spring 프로젝트 통합 테스트</h1>
        <h2>/api/welcome API 호출 결과 "{welcome}"</h2>
        <button onClick={() => {
          fetch("/api/welcome")
          .then((response) => {
            return response.json();
          })
          .then((data) => {
            setWelcome(data.message);
          })
        }}
        >호출 하기
        </button>

        <button onClick={() => {
          setWelcome("...")
        }}>결과 지우기
        </button>
      </div>);
}

export default App;
