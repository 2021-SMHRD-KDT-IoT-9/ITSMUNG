const AWS = require('aws-sdk');

const Device = require('../models/Device')
const dynamoDB = new AWS.DynamoDB.DocumentClient();

const TABLE = 'DEVICE'

async function selectAllDevice()
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
    selectAll : selectAllDevice
}