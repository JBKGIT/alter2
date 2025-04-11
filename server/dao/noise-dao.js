// noise-log 관련 데이터 접근 객체
const db = require('../config/db');

exports.getNoiseLogByUserId = async (userId) => {
    console.log("noise-dao:", userId);
    const sql = 'SELECT * FROM `noise_log` WHERE `user_id` = ?';
    const [results, fields] = await db.execute(sql, [userId], (err, results, fields) => {
        if (err instanceof Error) {
          console.log(err);
          return;
        }
      });

      return results;
};
