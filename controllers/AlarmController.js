const AWS = require('aws-sdk');
const uuid = require('uuid');

const Alarm = require('../models/Alarm')
const dynamoDB = new AWS.DynamoDB.DocumentClient();

const TABLE = "ALARM"

async function createAlarm(payload)
{
    const alarmId = uuid.v4()
    const newAlarm = new Alarm(alarmId, ...payload)

    const params = 
    {
        TableName : TABLE,
        Item: newAlarm
    }    

    try
    {
        await dynamoDB.put(params).promise()
        return true
    }
    catch(error)
    {
        console.log(error)
        return false
    }
}

async function selectAlarm()
{

}

module.exports = 
{
    create : createAlarm,
    select : selectAlarm
}

