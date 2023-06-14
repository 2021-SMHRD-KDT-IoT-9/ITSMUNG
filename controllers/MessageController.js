const AWS = require('aws-sdk');
const uuid = require('uuid');
const dayjs = require('dayjs');
const timezone = require('dayjs/plugin/timezone');
const utc = require('dayjs/plugin/utc');

const Message = require('../models/Message')
const dynamoDB = new AWS.DynamoDB.DocumentClient();

dayjs.extend(utc);
dayjs.extend(timezone);
dayjs.tz.setDefault('Asia/Seoul')

const TABLE = 'MESSAGE'

module.exports = 
{
    
}