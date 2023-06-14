const AWS = require('aws-sdk');
const dayjs = require('dayjs');
const timezone = require('dayjs/plugin/timezone');
const utc = require('dayjs/plugin/utc');

const Expert = require('../models/Expert')
const dynamoDB = new AWS.DynamoDB.DocumentClient();

dayjs.extend(utc);
dayjs.extend(timezone);
dayjs.tz.setDefault('Asia/Seoul')


const TABLE = 'EXPERT'

// 전문가 승인하기
async function joinExpert(expert)
{
    const regDate = dayjs().tz().format('YYYY-MM-DD HH:mm:ss')
    const params = 
    {
        TableName: TABLE,
        Key : 
        {
            expertId : expert.expertId
        },
        UpdateExpression: 'SET regDate = :newRegDate',
        ExpressionAttributeValues:
        {
            ":newRegDate": regDate
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

// 전문가 삭제하기
async function deleteExpert(expert)
{
    const params = 
    {
        TableName: TABLE,
        Key: 
        {
            expertId: expert.expertId
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

// Expert 테이블의 전체 항목 불러오기
async function selectAllExpert()
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
    join : joinExpert,
    delete : deleteExpert,
    selectAll : selectAllExpert
}