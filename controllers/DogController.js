const AWS = require('aws-sdk');
const uuid = require('uuid');

const Dog = require('../models/Dog');
const UserController = require('./UserController');
const dynamoDB = new AWS.DynamoDB.DocumentClient();

const TABLE = 'DOG'

// 애견 정보 생성
async function createDog(dog, userId)
{
    const dogName = dog.dogName
    const dogBirth = dog.dogBirth
    const dogGender = dog.dogGender
    const dogBreed = dog.dogBreed
    const dogNeutered = dog.dogNeutered
    const dogWeight = dog.dogWeight
    const dogId = uuid.v4()
    const newDog = new Dog(dogId, userId, dogName, dogBirth, dogGender, dogBreed, dogNeutered, dogWeight)

    const params = {
        TableName : TABLE,
        Item: newDog
    }

    try
    {
        await dynamoDB.put(params).promise()
        const result = await UserController.regDoginfo(userId, dogId)

        if(result)
        {
            return true
        }
        else
        {
            return false;
        }
    }
    catch(error)
    {
        return false
    }

}
async function updateDog(dog, userId)
{
    const dogId = dog.dogId
    const dogName = dog.dogName
    const dogBirth = dog.dogBirth
    const dogGender = dog.dogGender
    const dogBreed = dog.dogBreed
    const dogNeutered = dog.dogNeutered
    const dogWeight = dog.dogWeight
    const newDog = new Dog(dogId, userId, dogName, dogBirth, dogGender, dogBreed, dogNeutered, dogWeight)

    const params = {
        TableName : TABLE,
        Item: newDog
    }

    try
    {
        await dynamoDB.put(params).promise()
        return true
    }
    catch(error)
    {
        return false
    }
}

// 회원아이디로 애견검색
async function selectOneDog(user)
{
    const userId = user.userId
    
    const params = 
    {
        TableName: TABLE,
        IndexName: 'userId-index', // GSI 인덱스 이름
        KeyConditionExpression: 'userId = :id', // 보조 인덱스의 파티션 키 조건
        ExpressionAttributeValues: 
        {
            ':id': userId,
        },
    }

    try
    {
        const result = await dynamoDB.query(params).promise();
        const dog = result.Items[0];
        if (dog !== undefined) 
        {
            return dog
        } 
        else 
        {
            return null;
        }
    }
    catch(error)
    {
        console.log(error)
        return null
    }
}

module.exports = 
{
    create: createDog,
    update: updateDog,
    selectOne: selectOneDog
}