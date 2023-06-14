const express = require('express')
const router = express.Router()

const ExpertController = require('../controllers/ExpertController')
const ASController = require('../controllers/AfterServiceController')
const JoinController = require('../controllers/JoinController')
const Expert = require('../models/Expert')
const UserController = require('../controllers/UserController')

router.get('/admin_main', function(req, res)
{
    res.render('admin_main')
})

router.get('/admin_member', async function(req, res)
{
    const users = await JoinController.selectAll()
    res.render('admin_member', {users})
})

router.post('/admin_member/delete', async function(req, res)
{
    const user = JSON.parse(req.body.user)
    const isSuccess = await UserController.delete(user)

    if(isSuccess)
    {
        res.send("<script>alert('회원 삭제 성공'); window.location.href='/admin_member'</script>")
    }
    else
    {
        res.send("<script>alert('회원 삭제 실패 (서버 오류)'); window.location.href='/admin_member'</script>")
    }
})

router.get('/admin_doctor', async function(req, res)
{
    const items = await ExpertController.selectAll()
    res.render('admin_doctor', {items})
})

router.post('/admin_doctor/join', async function(req, res)
{
    const expert = JSON.parse(req.body.expert)
    const isSuccess = await ExpertController.join(expert)

    if(isSuccess)
    {
        res.send("<script>alert('전문가 등록 성공'); window.location.href='/admin_doctor'</script>")
    }
    else
    {
        res.send("<script>alert('전문가 등록 실패 (오류 발생)'); window.location.href='/admin_doctor'</script>")
    }
})

router.post('/admin_doctor/delete', async function(req, res)
{
    const expert = JSON.parse(req.body.expert)
    const isSuccess = await ExpertController.delete(expert)

    if(isSuccess)
    {
        res.send("<script>alert('전문가 삭제 성공'); window.location.href='/admin_doctor'</script>")
    }
    else
    {
        res.send("<script>alert('전문가 삭제 실패'); window.location.href='/admin_doctor'</script>")
    }
})

router.get('/admin_as', async function(req, res)
{
    const page = req.query.page
    const items = await ASController.selectAll()
    res.render('admin_as', {items, page})
})

module.exports = router