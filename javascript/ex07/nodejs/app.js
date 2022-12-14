// express 라이브러리 로딩하기
const express = require('express');

// HTTP요청을 다루는 라이브러리 로딩하기
const request = require('request');

//POST 요청으로 보낸 payload를 분석하는 라이브러리 로딩하기
//const bodyParser = require('body-parser');

const port = 3000; //웹서버 포트 번호

// express()를 호출하여 웹서버를 준비한다.
const app = express();

//POST 요청으로 보낸 payload 데이터를 분석할 객체를 지정하기
// => Content-Type: application/x-www-form-urlencoded 형식으로 된 payload처리
//    예) name=hong&ae=20    이런 형식만 분석하도록 함

app.use(express.urlencoded({extended: true}));  //빈 객체를 만들고 extended값을 담음
//urlencoded로 된 분석 객체를 사용하겠다고 지정

// 클라이언트 요청에 대해 호출될 메서드를 등록
app.get(                     //GET 요청이 들어 왔을 때 호출될 메서드 지정
   '/exam01-1',                 // 요청 URL
   (req, res) => {      // 요청 핸들러 : 요청이 들어왔을때 호출되는 메서드
      res.set('Access-Control-Allow-Origin', '*');  //CORS 문제 해결
      res.set('Content-Type', 'text/plain; charset=UTF-8'); 
      res.send('Hello!(민주)');
});

 //exam 2-1//
app.get('/exam02-1',(req, res) => {            
      res.set('Access-Control-Allow-Origin', '*'); 
      res.set('Content-Type', 'text/plain; charset=UTF-8'); 

      // res.send(`이름 : ${req.query.name} \n  //역슬래시n은 줄바꿈
      //   나이 : ${req.query.age}`);
      var payload = `이름: ${req.query.name}\n`;  //get 요청 파라미터 꺼내기
      payload += `나이: ${req.query.age}\n`;  //req박스 내 query박스 내 age정보 꺼내

      res.send(payload);
});

//exam 2-2// url로 post요청 들어왔을 때 호출될 함수를 등록하기
app.post('/exam02-2',(req, res) => {            
  res.set('Access-Control-Allow-Origin', '*'); 
  res.set('Content-Type', 'text/plain; charset=UTF-8'); 

  var payload = `이름: ${req.body.name}\n`;  //호출될 함수
  payload += `나이: ${req.body.age}\n`;  

  res.send(payload);
});

//3-1//
app.get('/exam03-1',(req, res) => {            
  res.set('Access-Control-Allow-Origin', '*'); 
  res.set('Content-Type', 'text/plain; charset=UTF-8'); 

  setTimeout(()=> {
    res.send("Hello");    //10초 후 호출될 함수
  }, 15000)                      
});

//3-4//
app.get('/exam03-4',(req, res) => {            
  res.set('Access-Control-Allow-Origin', '*'); 
  res.set('Content-Type', 'text/plain; charset=UTF-8');
  
  let a = parseInt(req.query.a);
  let b = parseInt(req.query.b);

  res.send(`${a + b}`);

});

//4-1//
app.get('/header',(req, res) => {            
  res.set('Access-Control-Allow-Origin', '*'); 
  res.set('Content-Type', 'text/html; charset=UTF-8');
  
  res.send(`<h1>비트캠프 네이버 클라우드 AIaaS 개발자 양성과정</h1>`);
});


//4-3
app.get('/exam04-3',(req, res) => {            
  res.set('Access-Control-Allow-Origin', '*'); 
  res.set('Content-Type', 'text/html; charset=UTF-8');
  
  let arr = [
    {no:1,title:'제목111111',writer:'홍길동',viewCnt:19},
    {no:2,title:'제목2222222',writer:'임꺽정',viewCnt:312},
    {no:3,title:'제목333333',writer:'유관순',viewCnt:31},
    {no:4,title:'제목4444444',writer:'안중근',viewCnt:100},
    {no:5,title:'제목555555555555',writer:'윤봉길',viewCnt:200}
  ];

  //배열 객체를 JSON 문자열로 변환해 클라이언트에게 보낸다.
  // => serialization(직렬화)
  res.send(JSON.stringify(arr));
});



// 클라이언트 요청을 다른 서버에게 보낸다
app.get('/proxy', (req, res) => { 

   res.set('Access-Control-Allow-Origin', '*');  
   res.set('Content-Type', 'text/plain; charset=UTF-8'); 
    
    // const options ={    //객체 리터럴로 프로퍼티를 바로 받을때 쓰는 법
    //   uri : req.query.url  //req상자 내 query상자 내 url
    // };
    
    request.get({
      uri : req.query.url,
     },  (error, response, body) => {
      res.send(body);
    });      
});



// 웹서버 실행하기 //node js에서 출력됨
app.listen(
  3000,     // 포트 번호 지정
  () => {          // 서버가 시작됐을 때 호출될 함수 = 리스너 = 이벤트 핸들러                                  
    console.log(`${port}번 포트에서 서버 시작했음!`);  //문자열안에 변수지정시 backtick사용 ``
  }                        
);

//proxy2     과제
app.get('/proxy2', (req, res) => { 

  res.set('Access-Control-Allow-Origin', '*');  
  res.set('Content-Type', 'application/json; charset=UTF-8'); 
   
  let openApiUrl ="http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?" + 
  "serviceKey=Zpt6QC54oC2MY5KKVFXESua95mlolWWbuWnbMb0e2qMk3jTtVY2jyTJTpWUnrv42%2BnV1qENjZCoea3fF%2BnY%2B6g%3D%3D" + 
  "&pageNo=1" +
  "&numOfRows=1000" +
  "&dataType=JSON" +
  "&base_date=" + req.query.base_date +
  "&base_time=0600" +
  "&nx=" + req.query.nx +
  "&ny=" + req.query.ny;
  
  request.get({
     uri : openApiUrl
    },  (error, response, body) => {
     res.send(body);
   });      
  });
  
  //post요청으로 들어온 이메일을 처리할때
  app.post('/login',(req, res) => {            
    res.set('Access-Control-Allow-Origin', '*'); 
    res.set('Content-Type', 'text/plain; charset=UTF-8'); 
  
    var payload = `이메일: ${req.body.email}\n`;  //호출될 함수
    payload += `암호: ${req.body.password}\n`;  
  
    res.send(payload);
  });