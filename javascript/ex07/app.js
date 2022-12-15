// express 라이브러리 로딩하기
const express = require('express');
const port = 3000; //웹서버 포트 번호

// express()를 호출하여 웹서버를 준비한다.
const app = express();

// 클라이언트 요청에 대해 호출될 메서드를 등록한다.
app.get(                        //GET 요청이 들어 왔을 때 호출될 메서드 지정
   '/exam01-1',                 // 요청 URL
   (req, res) => {              // 요청 핸들러 : 요청이 들어왔을때 호출되는 메서드
      res.set('Access-Control-Allow-Origin', '*');  
      res.set('Content-Type', 'text/plain; charset=UTF-8'); 
      res.send('Hello!');
   } 
);
                            
// 웹서버 실행하기
app.listen(
  3000,     // 포트 번호 지정
  () => {                                       
    console.log(`${port}번 포트에서 서버 시작했음!`);  //문자열안에 변수지정시 backtick사용 ``
  }                     // 서버가 시작됐을 때 호출될 함수 = 리스너 = 이벤트 핸들러
);


