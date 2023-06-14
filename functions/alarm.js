const express = require('express')

const router = express.Router()
const AlarmController = require('../controllers/AlarmController')

router.post('/create', async function(req, res)
{
    // userId
    // content
    // alarmDate
    const payload = req.body

    try
    {
        // Alarm 테이블에 저장(payload)
        const result = await AlarmController.create(payload)

        if(result)
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