const express = require('express')
const router = express.Router()

const UserController = require('../controllers/UserController')
const auth = require('../auth/token')



router.post('/login', async function (req, res) 
{
    const user = req.body.user

    try 
    {
        const result = await UserController.login(user)

        if (result !== null) {
            const token = auth.createToken()
            const userId = result.userId
            res.status(200).json({ token: token, userId: userId });
        }
        else {
            res.status(401).json({ message: "로그인 실패" });
        }
    }
    catch (error) {
        console.log(error)
        res.status(500).json({message : `${error}`})
    }

})

router.post('/info', async function(req, res)
{
    const user = req.body.user

    try
    {
        const userItem = await UserController.selectOne(user)

        if( userItem !== null)
        {
            res.status(200).json({user : userItem})
        }
        else
        {
            res.status(401).json({message : "유저 정보가 없습니다"})
        }
    }
    catch(error)
    {
        console.log(error)
        res.status(500).json({message : `${error}`})
    }
})

router.post('/update', async function(req, res)
{
    const user = req.body.user

    try
    {
        const result = await UserController.update(user)
        
        if(result)
        {
            res.status(200).json({ success: true, message: '회원 정보 변경 성공' });
        }
        else
        {
            res.status(401).json({ success: true, message: '회원 정보 변경 실패' });
        }
    }
    catch(error)
    {
        res.status(500).json({message : `${error}`})
    }
})

router.post('/join', async function (req, res) 
{
    const user = req.body.user

    try 
    {
        const isNewUser = await UserController.isNewUser(user)
        if (isNewUser) 
        {
            const result = await UserController.create(user)

            if (result) 
            {
                res.status(200).json({ success: true, message: '회원 가입 성공' });
            }
            else {
                res.status(401).json({ success: false, message: '회원 가입 실패' });
            }
        }
        else
        {
            res.status(401).json({ success: false, message: '동일 회원이 존재합니다'})
        }
    }
    catch (error) {
        res.status(500).json({message : `${error}`})
    }
})

module.exports = router