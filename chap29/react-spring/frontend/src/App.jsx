import "./App.css";
import {SERVER_URL} from './constants.js';
import {useEffect, useState} from "react";
import {Button, ButtonGroup, Container, Form, Table} from 'react-bootstrap';


function ArticleModifyForm(props) {
  const articleToModify = props.selectedArticle;

  const [title, setTitle] = useState(articleToModify.title);
  const [content, setContent] = useState(articleToModify.content);
  const [writer, setWriter] = useState(articleToModify.writer);

  const modifyArticle = (article) => {
    fetch(`${SERVER_URL}/board/${article.articleNo}`, {
      method: 'PUT',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(article)
    })
      .then((response) => {
        if (response.ok) {
          props.onChangeMode('list')
        } else {
          alert('수정할 때, 뭔가 문제가 생겼습니다. 😅!');
        }
      })
      .catch((err) => console.error(err));
  };

  return (
    <>
      <h3>게시물 수정</h3>
      <Form onSubmit={(event) => {
        event.preventDefault()
        const articleNo = event.target.articleNo.value
        const title = event.target.title.value
        const content = event.target.content.value
        const writer = event.target.writer.value

        modifyArticle({articleNo: articleNo, title: title, content: content, writer: writer})
      }}>
        <Form.Group className="mb-3" controlId="formGroupId">
          <Form.Label>아이디</Form.Label>
          <Form.Control name="articleNo" className="plain-text" type="text" readOnly={true}
                        value={articleToModify.articleNo}/>
        </Form.Group>
        <Form.Group className="mb-3" controlId="formGroupTitle">
          <Form.Label>제목</Form.Label>
          <Form.Control name="title" type="text" placeholder="제목을 입력해주세요"
                        value={title}
                        onChange={(event) => {
                          setTitle(event.target.value)
                        }}
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="exampleForm.ControlContent">
          <Form.Label>본문</Form.Label>
          <Form.Control name="content" as="textarea" rows={3} placeholder="본문을 입력해주세요."
                        value={content}
                        onChange={(event) => {
                          setContent((event.target.value))
                        }}
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formGroupWriter">
          <Form.Label>작성자</Form.Label>
          <Form.Control name="writer" type="text" placeholder="작성자을 입력해주세요"
                        value={writer}
                        onChange={(event) => {
                          setWriter((event.target.value))
                        }}
          />
        </Form.Group>
        <ButtonGroup>
          <Button variant="outline-primary" className="me-2" type="submit">수정</Button>
          <Button variant="outline-dark" onClick={() => props.onChangeMode('list')} type="button">목록으로 돌아기기...</Button>
        </ButtonGroup>
      </Form>
    </>
  );
}


function ArticleAddForm(props) {

  const [articleNo, setArticleNo] = useState('');
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [writer, setWriter] = useState('');

  const addArticle = (article) => {
    fetch(`${SERVER_URL}/board/`, {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(article)
    })
      .then((response) => {
        if (response.ok) {
          props.onChangeMode('list')
        } else {
          alert('등록할 때, 문제가 생겼습니다. 😅!');
        }
      })
      .catch((err) => console.error(err));
  };

  return (
    <>
      <h3>게시물 등록</h3>
      <Form
        onSubmit={(event) => {
          event.preventDefault()
          const articleNo = event.target.articleNo.value
          const title = event.target.title.value
          const content = event.target.content.value
          const writer = event.target.writer.value

          addArticle({articleNo: articleNo, title: title, content: content, writer: writer})
        }}
      >
        <Form.Group className="mb-3" controlId="formGroupId">
          <Form.Label>아이디</Form.Label>
          <Form.Control name="articleNo" type="text" placeholder="아이디를 입력해주세요 (숫자)"
                        value={articleNo}
                        onChange={(event) => {
                          setArticleNo((event.target.value))
                        }}
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formGroupTitle">
          <Form.Label>제목</Form.Label>
          <Form.Control name="title" type="text" placeholder="제목을 입력해주세요"
                        value={title}
                        onChange={(event) => {
                          setTitle((event.target.value))
                        }}
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="exampleForm.ControlContent">
          <Form.Label>본문</Form.Label>
          <Form.Control name="content" as="textarea" rows={3} placeholder="본문을 입력해주세요."
                        value={content}
                        onChange={(event) => {
                          setContent((event.target.value))
                        }}
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formGroupWriter">
          <Form.Label>작성자</Form.Label>
          <Form.Control name="writer" content="writer" type="text" placeholder="작성자을 입력해주세요"
                        value={writer}
                        onChange={(event) => {
                          setWriter((event.target.value))
                        }}
          />
        </Form.Group>
        <ButtonGroup>
          <Button variant="outline-primary" className="me-2" type="submit">등록</Button>
          <Button variant="outline-dark" onClick={() => props.onChangeMode('list')} type="reset">목록으로 돌아기기...</Button>
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
      <td>
        <a href="#!" onClick={() => {
          props.setSelectedArticle(article);
          props.onChangeMode("modify")
        }}>{article.title}</a>
      </td>
      <td>{article.content}</td>
      <td>{article.writer}</td>
      <td><Button variant="outline-danger" size="sm" onClick={() => {
        props.deleteArticle(article.articleNo)
      }}>삭제</Button></td>
    </tr>
  )
}

function ArticleList(props) {
  const [articles, setArticles] = useState([]);


  useEffect(() => {
    fetchAllArticle();
  }, []);

  const fetchAllArticle = () => {
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
            alert('삭제할 때, 뭔가 문제가 생겼습니다. 😅!');
          }
        })
        .catch((err) => console.error(err));
    }
  }


  const articleTRs = articles.map(a =>
    <ArticleRow key={a.articleNo} article={a} setSelectedArticle={props.setSelectedArticle}
                onChangeMode={props.onChangeMode} deleteArticle={deleteArticle}
    />
  )

  return (
    <>
      <h3>게시물 목록</h3>
      <Button variant="primary" size="sm" className="mb-2" onClick={() => props.onChangeMode('add')}>새 게시물 작성</Button>
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
  const [mode, setMode] = useState('list');
  const [selectedArticle, setSelectedArticle] = useState([]);


  let contentLayout;
  if (mode === 'add') {
    contentLayout = <ArticleAddForm onChangeMode={setMode}/>
  } else if (mode === 'modify') {
    contentLayout = <ArticleModifyForm onChangeMode={setMode} selectedArticle={selectedArticle}/>
  } else {
    contentLayout = <ArticleList onChangeMode={setMode} setSelectedArticle={setSelectedArticle}/>
  }


  return (
    <Container className="p-3">
      <h1>React + Spring REST API 통합 테스트</h1>
      {contentLayout}
    </Container>
  );
}

export default App;
