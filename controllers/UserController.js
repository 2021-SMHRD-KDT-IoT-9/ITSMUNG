const AWS = require('aws-sdk');
const dayjs = require('dayjs');
const timezone = require('dayjs/plugin/timezone');
const utc = require('dayjs/plugin/utc');

const User = require('../models/User')
const dynamoDB = new AWS.DynamoDB.DocumentClient();

dayjs.extend(utc);
dayjs.extend(timezone);
dayjs.tz.setDefault('Asia/Seoul')

const TABLE = 'USER'

// 회원 생성하기
async function createUser(user)
{
    const userId = user.userId
    const userPw = user.userPw
    const userName = user.userName
    const nickname = user.nickname
    const userTel = user.userTel
    const regDate = dayjs().tz().format('YYYY-MM-DD HH:mm:ss')

    const newUser = new User(userId, userPw, nickname, userName, userTel, regDate)

    const params = {
        TableName: TABLE,
        Item: newUser
    };

    try {
        await dynamoDB.put(params).promise();
        return true;
    }
    catch (error) {
        return false;
    }
}

async function updateUser(user)
{
    const userId = user.userId
    const userPw = user.userPw
    const nickname = user.nickname
    const userName = user.userName
    const userTel = user.userTel
    const regDate = user.regDate
    const kakaoEmail = user.kakaoEmail
    const kakaoRegDate = user.kakaoRegDate
    const updateUser = new User(userId, userPw, nickname, userName, userTel, regDate, kakaoEmail, kakaoRegDate)

    const params = 
    {
        TableName : TABLE,
        Item: updateUser
    }

    try
    {
        await dynamoDB.put(params).promise();
        return true;
    }
    catch(error)
    {
        console.log(error)
        return false
    }
    
}

// 존재하는 회원이 있는지 파악하기
async function isNewUser(user)
{
    const userId = user.userId

    const params = 
    {
        TableName : TABLE,
        Key:
        {
            userId: userId
        }
    }

    try
    {
        const result = await dynamoDB.get(params).promise()
        const userInfo = result.Item

        if(userInfo === null)
        {
            return true
        }
        else
        {
            return false
        }
    }
    catch(error)
    {
        return false
    }
}


// 로그인 기능
async function loginUser(user)
{
    const userId = user.userId
    const userPw = user.userPw
    const params = 
    {
        TableName : TABLE,
        Key:
        {
            userId: userId
        }
    }

    try
    {
        const result = await dynamoDB.get(params).promise()
        const userInfo = result.Item
        if (userInfo && userInfo.userPw === userPw)
        {
            return userInfo
        } 
        else 
        {
            return null;
        }
    }
    catch(error)
    {
        return null;
    }
}

// 개 등록 시 유저정보에 dogId 등록
async function regDoginfo(userId, dogId)
{
    const params = 
    {
        TableName: TABLE,
        Key : 
        {
            userId : userId
        },
        UpdateExpression: 'SET processDogId = :newDogId',
        ExpressionAttributeValues:
        {
            ":newDogId": dogId
        }
    }
    console.log(params)
    try
    {
        await dynamoDB.update(params).promise()
        return true
    }
    catch(error)
    {
        return false
    }
}

//회원 삭제하기
async function deleteUser(user)
{
    const params = 
    {
        TableName: TABLE,
        Key: 
        {
            userId: user.userId
        }
    }

    try
    {
        await dynamoDB.delete(params).promise()
        return true
    }
    catch(error)
    {
        return false
    }
}

// User 한 항목 불러오기
async function selectOneUser(user)
{
    const userId = user.userId
    
    const params = 
    {
        TableName : TABLE,
        Key:
        {
            userId: userId
        }
    }

    try
    {
        const result = await dynamoDB.get(params).promise()
        const userItem = result.Item

        return userItem
    }
    catch(error)
    {
        return null
    }
}

// User 테이블 전체 항목 불러오기
async function selectAllUser()
{
    const params =
    {
        TableName: TABLE
    }

    try
    {
        const result = await dynamoDB.scan(params).promise()
        const items = result.Items
        return items
    }
    catch(error)
    {
        return null
    }
}

module.exports = 
{
    isNewUser,
    regDoginfo,
    create : createUser,
    update : updateUser,
    delete : deleteUser,
    login : loginUser,
    selectOne : selectOneUser,
    selectAll : selectAllUser
}