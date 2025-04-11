// noise-log 관련 request 처리, Rest API 엔드포인트 정의

const noiseService = require('../service/noise-service');

exports.getNoiseLogByUserId = async (req, res) => {
    try {
    const userId = req.query.userId;
    console.log("noise_controller:", userId);

    const noises = await noiseService.getNoiseByUserId(userId);
    res.status(200).json(noises);
    } catch (err) {
        console.error("noise-controller_err: ", err.stack);
        res.status(500).json({error: 'failed to get noises'});
    }
};
