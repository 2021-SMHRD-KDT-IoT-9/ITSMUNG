const express = require('express')
const router = express.Router()

router.get('/test', function(req, res)
{
    res.json(req.headers, null, 2)
})


module.exports = router