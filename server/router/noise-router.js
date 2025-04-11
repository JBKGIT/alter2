// 라우터 설정

const noiseController = require('../controller/noise-controller');
const express = require('express');
const router = express.Router();

router.get('/', noiseController.getNoiseLogByUserId);

module.exports = router;