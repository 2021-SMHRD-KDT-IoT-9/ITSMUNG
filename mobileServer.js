const serverlessExpress = require('@vendia/serverless-express')
const app = require('./mobileApp');

exports.handler = serverlessExpress({app})