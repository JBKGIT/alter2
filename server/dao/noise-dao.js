// noise-log 관련 데이터 접근 객체
const db = require('../config/db');

/* SELECT */
// query로 받은 user_id를 가진 유저의 noiselog list 전달
exports.getNoiseLogByUserId = async (userId) => {
    console.log("noise-dao-getNoiseLogByUserId:", userId);
    const sql = 'SELECT * FROM `noise_log` WHERE `user_id` = ?';
    const [results] = await db.execute(sql, [userId]);

    return results;
};

// query로 받은 년월(year, month)의 일별 max_db list 전달
exports.getMaxDecibelsForMonth = async (userId, year, month) => {
  //console.log("noise-dao-getMaxDecibelsForMonth:", userId);
  const sql = 'SELECT ANY_VALUE(`log_time`) as log_time, max(`max_db`) as max_db FROM `noise_log` '
                      + 'WHERE `user_id` = ? AND YEAR(`log_time`) = ? AND MONTH(`log_time`) = ? '
                      + 'GROUP BY DATE(`log_time`) ORDER BY DATE(`log_time`)';
  const [results] = await db.execute(sql, [userId, year, month]);

  return results;
};

/* INSERT */
// query로 받은 noise_log를 디비에 추가
exports.insertNoiseLog = async (noiseLevel, logTime, location, maxDb, avgDb, userId) => {
  console.log("noise-dao-getNoiseLogByUserId:", userId);
  const sql = 'INSERT INTO `noise_log` (`noise_level`, `log_time`, `location`, `max_db`, `avg_db`, `user_id`) '
                          + 'VALUES(?, ?, ?, ?, ?, ?)';
  const [results] = await db.execute(sql, [noiseLevel, logTime, location, maxDb, avgDb, userId]);

  return results;
};


/* DELETE */
// 저장 시점으로부터 30일이 지난 noise log 삭제
/* 
 * mysql event scheduler에 event(delete_expired_noise_log) 등록해놓음.
 * 서버 재실행 시 삭제되지 않은 로그들 삭제용
 * (mysql의 event는 꺼져있을 때 event 실행X)
 */
exports.deleteExpiredNoiseLogs = async () => {
  const sql = 'DELETE FROM `noise_log` WHERE `log_time` <= DATE_ADD(NOW(), INTERVAL -30 DAY)';
  const [results, fields] = await db.execute(sql, []);
  return results;
}
