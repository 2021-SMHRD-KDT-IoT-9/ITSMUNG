const express = require('express')
const router = express.Router()

const MatchingController = require('../controllers/MatchingController')

router.post('/userId', async function(req, res)
{
    const userId = req.body.userId
    let match = false
    try
    {
        const result = await MatchingController.selectOne(userId)
        
        if(result !== null)
        {
            match = true
            res.status(200).json({ match : match })
        }
        else
        {
            res.status(401).json({ match : match })
        }
        
    }
    catch(error)
    {
        res.status(500).json({match : match})
    }
})

module.exports = router