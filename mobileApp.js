const express = require('express')
const bodyParser = require('body-parser')
const logger = require('morgan')
const cors = require('cors')
const app = express()

const testFunction = require('./functions/test')
const userFunction = require('./functions/user')
const dogFunction = require('./functions/dog')
const matchFunction = require('./functions/match')
const dogLogFunction = require('./functions/dog_log')

app.locals.publicPath = 'https://itsmung.s3.ap-northeast-2.amazonaws.com/public';

app.use(express.urlencoded({extended: true}));
app.use(bodyParser.json());
app.use(logger("dev"));
app.use(cors())

app.use('/', testFunction)
app.use('/user', userFunction)
app.use('/dog', dogFunction)
app.use('/match', matchFunction)
app.use('/dog_log', dogLogFunction)

module.exports = app