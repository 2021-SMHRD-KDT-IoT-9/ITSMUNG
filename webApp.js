const express = require('express')
const { getCurrentInvoke } = require('@vendia/serverless-express')
const bodyParser = require('body-parser')
const logger = require('morgan')
const ejs = require('ejs')
const cors = require('cors')
const path = require('path')

const app = express()

const userRoutes = require('./routes/userRoute')
const adminRoutes = require('./routes/adminRoute')

app.set('view engine', 'ejs')
app.set('views', path.join(__dirname, 'views'))
app.engine('.ejs', ejs.__express)

app.locals.publicPath = 'https://itsmung.s3.ap-northeast-2.amazonaws.com/public';

app.use(express.urlencoded({extended: true}));
app.use(bodyParser.json());
app.use(logger("dev"));
app.use(cors())

app.use('/', userRoutes)
app.use('/', adminRoutes)

app.use(function(req, res)
{
    res.render('./error/404');
});

app.use(function(error, req, res, next)
{
    res.render('./error/500');
});

module.exports = app