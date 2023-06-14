const express = require('express')
const router = express.Router()

const DogController = require('../controllers/DogController')

router.post('/info', async function(req, res)
{
    const user = req.body.user

    try
    {
        const dog = await DogController.selectOne(user)
        
        if(dog !== null)
        {
            res.status(200).json({dog : dog});
        }
        else
        {
            res.status(401).json({ message: '정보가 존재하지 않습니다'})
        }
    }
    catch(error)
    {
        console.log(error)
        res.status(500).json({ message: '서버 오류' });
    }
})

router.post('/update', async function(req, res)
{
    const dog = req.body.dog
    const userId = req.body.userId

    try
    {
        const result = await DogController.update(dog, userId)

        if(result)
        {
            res.status(200).json({ success: true, message: '애견 정보 변경 성공' });
        }
        else
        {
            res.status(401).json({ success: false, message: '애견 정보 변경 실패'});
        }
    }
    catch(error)
    {
        console.log(error)
        res.status(500).json({ success: false, message: '서버 오류'});
    }
})

router.post('/join', async function(req, res)
{
    const dog = req.body.dog
    const userId = req.body.userId

    try
    {
        const result = await DogController.create(dog, userId)

        if(result)
        {
            res.status(200).json({ success: true, message: '애견 등록 성공' });
        }
        else
        {
            res.status(401).json({ success: false, message: '애견 등록 실패'});
        }
    }
    catch(error)
    {
        res.status(500).json({ success: false, message: '서버 오류'});
    }
})

module.exports = router