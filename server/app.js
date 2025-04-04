// android studio에서 ssl 인증서가 안먹혀서 걍 http로 함함

const express = require('express');
const path = require('path');
const mysql = require('mysql2');
//const https = require('https');
//const fs = require('fs');

const app = express();

/*
// load SSL certificate
const options = {
    key: fs.readFileSync('server.key'),
    cert: fs.readFileSync('server.cert')
};
*/

// connect mysql db
const connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    database: 'sound',
    port: 3306,
    password: '0007',
  });
  
  connection.addListener('error', (err) => {
    console.log(err);
  });


  //select
const sql = 'SELECT * FROM `test_table`';
const values = [];

connection.execute(sql, values, (err, rows, fields) => {
    if (err instanceof Error) {
      console.log(err);
      return;
    }
    
  
    console.log('rows:', rows);
    console.log('fields: ', fields);
    values.push(rows[0]);
    values.push(rows[1]);
  });





app.set('port', process.env.PORT || 3000);
app.use(express.json());

app.get('/', (req, res) => {
    //res.sendFile(path.join(__dirname, 'index.html'));
    res.send(JSON.stringify(values));
    console.log(values[0].id);
});
app.get('/users', (req, res) => {
  //res.sendFile(path.join(__dirname, 'index.html'));
  res.send(JSON.stringify(values));
  console.log(values[0].id);
});

app.post('/', (req, res) => {
    res.send('hello express');
});

app.get('/about', (req, res) => {
    res.send('hello express');
})

app.listen(app.get('port'), () => {
   console.log('익스프레스 서버 실행');
});
/*
https.createServer(options, app).listen(app.get('port'), () => {
    console.log('익스프레스 서버 실행');
});
*/