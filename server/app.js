const express = require('express');
const noiseRouter = require('./router/noise-router');

const app = express();

// port 번호 설정
app.set('port', process.env.PORT || 3000);

// middleware
app.use(express.json());

// router 연결 제대로 됐는지 확인용
app.use((req, res, next) => {
  console.log('요청:', req.method, req.url);
  next();
});


// routers 연결
app.use('/api/noise', noiseRouter);

// 서버 실행
app.listen(app.get('port'), () => {
  console.log('익스프레스 서버 실행');
});