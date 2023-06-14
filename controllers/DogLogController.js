const AWS = require('aws-sdk');
const uuid = require('uuid');
const dayjs = require('dayjs');
const timezone = require('dayjs/plugin/timezone');
const utc = require('dayjs/plugin/utc');

const Dog_Log = require('../models/Dog_Log')
const dynamoDB = new AWS.DynamoDB.DocumentClient();

dayjs.extend(utc);
dayjs.extend(timezone);
dayjs.tz.setDefault('Asia/Seoul')

const TABLE = 'DOG_LOG'

async function createLog(payload, dogId) 
{
    const logId = uuid.v4()

    const newLog = new Dog_Log(logId, dogId, ...payload)

    const params =
    {
        TableName: TABLE,
        Item: newLog
    }

    try 
    {
        await dynamoDB.put(params).promise()
        return true
    }
    catch (error) {
        console.log(error)
        return false
    }
}


async function selectLog() 
{

}


async function getDogIdByDeviceId(deviceId) 
{
    const deviceParams =
    {
        TableName: 'DEVICE',
        Key:
        {
            deviceId: deviceId
        },
        ProjectionExpression: 'userId'
    }
    try 
    {
        const deviceResult = await dynamoDB.getItem(deviceParams).promise();
        const userId = deviceResult.Item.userId

        const dogParams = {
            TableName: 'DOG',
            IndexName: 'userId-index', // userId를 포함하는 GSI의 이름
            KeyConditionExpression: 'userId = :userId',
            ExpressionAttributeValues:
            {
                ':userId': userId
            },
            ProjectionExpression: 'dogId'
        }

        const dogResult = await dynamoDB.query(dogParams).promise()
        const items = dogResult.Items

        if (items && items.length > 0) 
        {
            const dogId = items[0].dogId
            return dogId;
        } 
        else 
        {
            return null;
        }
    } 
    catch (error) 
    {
        console.log(error);
        return null;
    }
}


module.exports = 
{
    create: createLog,
    select: selectLog,
    getDogIdByDeviceId
}