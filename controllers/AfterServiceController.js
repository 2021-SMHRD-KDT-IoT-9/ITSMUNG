const AWS = require('aws-sdk');
const uuid = require('uuid');
const dayjs = require('dayjs');
const timezone = require('dayjs/plugin/timezone');
const utc = require('dayjs/plugin/utc');

const ASRequest = require('../models/ASRequest')
const dynamoDB = new AWS.DynamoDB.DocumentClient();

dayjs.extend(utc);
dayjs.extend(timezone);
dayjs.tz.setDefault('Asia/Seoul')

const TABLE = 'AFTER_SERVICE'

// After Service 테이블에 1개 저장
async function createAfterService(email, title, content) 
{
    const requestId = uuid.v4()
    const regDate = dayjs().tz().format('YYYY-MM-DD HH:mm:ss')

    const asRequest = new ASRequest(requestId, email, title, content, regDate)
    const params = {
        TableName: TABLE,
        Item: asRequest
    };

    try {
        await dynamoDB.put(params).promise();
        return true;
    }
    catch (error) {
        return false;
    }
}

// After Service 테이블의 전체 항목 불러오기
async function selectAllAfterService() 
{
    const params = 
    {
        TableName: TABLE
    }

    try 
    {
        const result = await dynamoDB.scan(params).promise();
        const items = result.Items
        
        // regDate 속성을 기준으로 정렬
        items.sort(function(a, b)
        {
            const dateA = new Date(a.regDate)
            const dateB = new Date(b.regDate)
            return dateA - dateB;
        })

        return items
    }
    catch (error) {
        return null
    }
}

module.exports =
{
    create : createAfterService,
    selectAll : selectAllAfterService
}