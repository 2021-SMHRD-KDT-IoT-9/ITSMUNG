const serverlessExpress = require('@vendia/serverless-express')
const app = require('./webApp');

exports.handler = serverlessExpress({app})