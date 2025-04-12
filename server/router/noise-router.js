// 라우터 설정

const noiseController = require('../controller/noise-controller');
const express = require('express');
const router = express.Router();

router.get('/', noiseController.getNoiseLogByUserId);
router.get('/maxDbList', noiseController.getMaxDecibelsForMonth);

router.post('/insert', noiseController.insertNoiseLog);

module.exports = router;