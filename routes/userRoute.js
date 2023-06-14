const express = require('express')
const router = express.Router()

const ASController = require('../controllers/AfterServiceController')

router.get('/', function (req, res) 
{
    res.render('user_main')
})

router.get('/user_intro', function (req, res) 
{
    res.render('user_intro')
})

router.get('/user_faq', function (req, res) 
{
    res.render('user_faq')
})

router.get('/user_as', async function (req, res) 
{
    const page = req.query.page
    const items = await ASController.selectAll()
    res.render('user_as', {items, page})
})

router.get('/user_as_write', function (req, res) 
{
    res.render('user_as_write')
})

router.post('/user_as_write', async function (req, res) 
{
    const email = req.body.email
    const title = req.body.title
    const content = req.body.content

    const isSuccess = await ASController.create(email, title, content)

    if(isSuccess)
    {
        res.send("<script>alert('성공적으로 저장되었습니다'); window.location.href='/user_as';</script>")
    }
    else
    {
        res.send("<script>alert('저장에 실패하였습니다'); window.location.href='/user_as_write';</script>")
    }  
})

module.exports = router