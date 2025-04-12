// DAO를 이용해 noise-log 데이터 받아옴
// && 데이터 처리

const noiseDao = require('../dao/noise-dao');

exports.getNoiseByUserId = async (userId) => {
    // 데이터 처리
    const result = await noiseDao.getNoiseLogByUserId(userId);
    return result;
}

exports.getMaxDecibelsForMonth = async (userId, year, month) => {
    const result = await noiseDao.getMaxDecibelsForMonth(userId, year, month);
    return result;
}

exports.insertNoiseLog = async (noiseLevel, logTime, location, maxDb, avgDb, userId) => {
    const result = await noiseDao.insertNoiseLog(noiseLevel, logTime, location, maxDb, avgDb, userId);
    return result;
}

exports.deleteExpiredNoiseLogs = async () => {
    // 데이터 처리 
    const result = await noiseDao.deleteExpiredNoiseLogs();
    return result;
}