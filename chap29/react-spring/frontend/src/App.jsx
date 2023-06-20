import "./App.css";
import {SERVER_URL} from './constants.js';
import {useEffect, useState} from "react";
import {Button, ButtonGroup, Container, Form, Table} from 'react-bootstrap';


function ArticleModifyForm() {
  return (
    <>
      <h3>게시물 수정</h3>
      <Form>
        <Form.Group className="mb-3" controlId="formGroupId">
          <Form.Label>아이디</Form.Label>
          <Form.Control className="plain-text" type="text" readOnly={true} value={"게시물 실제 아이디"}/>
        </Form.Group>
        <Form.Group className="mb-3" controlId="formGroupTitle">
          <Form.Label>제목</Form.Label>
          <Form.Control type="text" placeholder="제목을 입력해주세요"/>
        </Form.Group>
        <Form.Group className="mb-3" controlId="exampleForm.ControlContent">
          <Form.Label>본문</Form.Label>
          <Form.Control as="textarea" rows={3} placeholder="본문을 입력해주세요."/>
        </Form.Group>
        <Form.Group className="mb-3" controlId="formGroupWriter">
          <Form.Label>작성자</Form.Label>
          <Form.Control type="text" placeholder="작성자을 입력해주세요"/>
        </Form.Group>
        <ButtonGroup>
          <Button variant="outline-primary" className="me-2" type="submit">수정</Button>
          <Button variant="outline-secondary" className="me-2" type="reset">취소</Button>
          <Button variant="outline-dark" type="reset">목록으로 돌아기기...</Button>
        </ButtonGroup>
      </Form>
    </>
  );
}


function ArticleAddForm() {
  return (
    <>
      <h3>게시물 등록</h3>
      <Form>
        <Form.Group className="mb-3" controlId="formGroupId">
          <Form.Label>아이디</Form.Label>
          <Form.Control type="text" placeholder="아이디를 입력해주세요 (숫자)"/>
        </Form.Group>
        <Form.Group className="mb-3" controlId="formGroupTitle">
          <Form.Label>제목</Form.Label>
          <Form.Control type="text" placeholder="제목을 입력해주세요"/>
        </Form.Group>
        <Form.Group className="mb-3" controlId="exampleForm.ControlContent">
          <Form.Label>본문</Form.Label>
          <Form.Control as="textarea" rows={3} placeholder="본문을 입력해주세요."/>
        </Form.Group>
        <Form.Group className="mb-3" controlId="formGroupWriter">
          <Form.Label>작성자</Form.Label>
          <Form.Control type="text" placeholder="작성자을 입력해주세요"/>
        </Form.Group>
        <ButtonGroup>
          <Button variant="outline-primary" className="me-2" type="submit">등록</Button>
          <Button variant="outline-secondary" className="me-2" type="reset">취소</Button>
          <Button variant="outline-dark" type="reset">목록으로 돌아기기...</Button>
        </ButtonGroup>
      </Form>
    </>
  );
}


function ArticleRow(props) {
  const article = props.article;
  return (
    <tr>
      <td>{article.articleNo}</td>
      <td><a href="">{article.title}</a></td>
      <td>{article.content}</td>
      <td>{article.writer}</td>
      <td><Button variant="outline-danger" size="sm" onClick={(e) => {
        props.deleteArticle(article.articleNo)
      }}>삭제</Button></td>
    </tr>
  )
}

function ArticleList() {
  const [articles, setArticles] = useState([]);

  useEffect(() => {
    fetchAllArticle();
  }, []);

  const fetchAllArticle = () => {
    // 세션 저장소에서 토큰을 읽고,
    // Authorization 헤더에 이를 포함한다.

    fetch(`${SERVER_URL}/board/all`)
      .then((response) => response.json())
      .then((data) => setArticles(data))
      .catch((err) => console.error(err));
  };


  const deleteArticle = (articleNo) => {
    if (window.confirm(`${articleNo}번 게시물을 삭제하시겠습니까?`)) {
      fetch(`${SERVER_URL}/board/${articleNo}`, {method: 'DELETE'})
        .then((response) => {
          if (response.ok) {
            fetchAllArticle();
          } else {
            alert('뭔가 문제가 생겼습니다. 😅!');
          }
        })
        .catch((err) => console.error(err));
    }
  }


  const articleTRs = articles.map(a =>
    <ArticleRow key={a.articleNo} article={a} deleteArticle={deleteArticle}/>
  )

  return (
    <>
      <h3>게시물 목록</h3>
      <Table striped bordered hover>
        <thead>
        <tr>
          <th className="col-sm-1">아이디</th>
          <th>제목</th>
          <th>내용</th>
          <th>작성자</th>
          <th className="col-sm-1">삭제</th>
        </tr>
        </thead>
        <tbody>
        {articleTRs}
        </tbody>
      </Table>
    </>
  );
}


function App() {
  return (
    <Container className="p-3">
      <h1>React + Spring REST API 통합 테스트</h1>
      <ArticleList/>
      <ArticleAddForm/>
      <ArticleModifyForm/>
    </Container>
  );
}

export default App;
