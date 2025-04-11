// DAO를 이용해 noise-log 데이터 받아옴
// && 데이터 처리

const noiseDao = require('../dao/noise-dao');

exports.getNoiseByUserId = async (userId) => {
    // 데이터 처리
    const result = await noiseDao.getNoiseLogByUserId(userId);
    return result;
}