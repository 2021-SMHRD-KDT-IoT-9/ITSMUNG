const UserController = require('../controllers/UserController')
const DogController = require('../controllers/DogController')
const DeviceController = require('../controllers/DeviceController')
const MatchingController = require('../controllers/MatchingController')

// 어드민 웹페이지 회원 관리 목록 불러오기
async function selectAllUser() 
{
    try 
    {

        const users = await UserController.selectAll()
        const devices = await DeviceController.selectAll()
        const matches = await MatchingController.selectAll()

        const matchingMap = {}

        matches.forEach((match) => {
            matchingMap[match.userId] = match
        })

        const items = users.map((user) => 
        {
            const match = matchingMap[user.userId]
            const subscription = match ? 'Y' : 'N'
            const device = devices.find((device) => device.userId === user.userId)
            const deviceId = device ? device.deviceId : null

            return {
                ...user,
                subscription,
                deviceId
            }
        })
        return items
    }
    catch(error)
    {
        console.log(error)
        return null
    }
}

module.exports =
{
    selectAll: selectAllUser
}