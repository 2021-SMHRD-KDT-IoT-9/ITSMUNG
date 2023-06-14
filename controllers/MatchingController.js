const AWS = require('aws-sdk');
const uuid = require('uuid');
const dayjs = require('dayjs');
const timezone = require('dayjs/plugin/timezone');
const utc = require('dayjs/plugin/utc');

const Matching = require('../models/Matching')
const dynamoDB = new AWS.DynamoDB.DocumentClient();

dayjs.extend(utc);
dayjs.extend(timezone);
dayjs.tz.setDefault('Asia/Seoul')

const TABLE = 'MATCHING'

async function createMatching() 
{

}

async function selectOneMatching(userId) 
{
    
    const params = 
    {
        TableName: TABLE,
        IndexName: 'userId-index', // GSI 인덱스 이름
        KeyConditionExpression: 'userId = :id', // 보조 인덱스의 파티션 키 조건
        ExpressionAttributeValues: 
        {
            ':id': userId,
        },
    };

    try 
    {
        const result = await dynamoDB.query(params).promise();

        if (result.Items.length > 0) 
        {
            return result.Items[0];
        } 
        else 
        {
            return null;
        }
    } catch (error) 
    {
        console.log(`error: ${error}`);
        return null;
    }
}

async function selectAllMatching() 
{
    const params =
    {
        TableName: TABLE
    }

    try {
        const result = await dynamoDB.scan(params).promise()
        const items = result.Items
        return items
    }
    catch (error) {
        return null
    }
}

module.exports =
{
    create: createMatching,
    selectOne: selectOneMatching,
    selectAll: selectAllMatching
}