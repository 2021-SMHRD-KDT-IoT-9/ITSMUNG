const express = require('express')

const router = express.Router()
const DogLogController = require('../controllers/DogLogController')

router.post('/create', async function(req, res)
{
    // deviceId 헤더에서 추출
    const deviceId = req.headers.deviceId
    // excretaSeperation
    // excretaState
    // weight
    // imgPathS3
    // logDate
    const payload = req.body

    try
    {
        // dogId 받아와야 됨 => 어떻게? deviceId로 추적해서 들어가야됨
        const dogId = await DogLogController.getDogIdByDeviceId(deviceId)

        // DOG_LOG 테이블에 저장해야됨(payload와 dogId)
        const result = await DogLogController.create(payload, dogId)

        if (result)
        {
            res.status(200).end()
        }
        else
        {
            res.status(401).end()
        }
    }
    catch(error)
    {
        res.status(500).json( {message: `${error}`})
    }
})

module.exports = router

