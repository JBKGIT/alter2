// noise-log 관련 client의 request 처리, Rest API 엔드포인트 정의

const noiseService = require('../service/noise-service');

exports.getNoiseLogByUserId = async (req, res) => {
    try {
    const userId = req.query.userId;
    console.log("noise_controller:", userId);

    const result = await noiseService.getNoiseByUserId(userId);
    res.json(result);
    } catch (err) {
        console.error('controller-getNoiseLogByUserId_err: ', err.stack);
        res.status(500).json({error: 'failed to get noises'});
    }
};

exports.getMaxDecibelsForMonth = async (req, res) => {
    const userId = req.query.userId;
    const year = req.query.year;
    const month = req.query.month;
    try {
        console.log(userId, year, month);
        const result = await noiseService.getMaxDecibelsForMonth(userId, year, month);
        res.json(result); 
    } catch (err) {
        console.error('controller-getMax_err: ', err.stack);
        res.status(500).json({error: 'failed to get max_db list'});
    }
}

exports.insertNoiseLog = async (req, res) => {
    const noiseLevel = req.query.noiseLevel;
    const logTime = req.query.logTime;
    const location = req.query.location;
    const maxDb = req.query.maxDb;
    const avgDb = req.query.avgDb;
    const userId = req.query.userId;
    try {
        console.log(userId, year, month);
        const result = await noiseService.insertNoiseLog(noiseLevel, logTime, location, maxDb, avgDb, userId);
        res.json(result); 
    } catch (err) {
        console.error('controller-insertNoise_err: ', err.stack);
        res.status(500).json({error: 'failed to get max_db list'});
    }
}