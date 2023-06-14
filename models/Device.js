class Device 
{
    constructor(deviceId, userId, regStatus, videoPath) 
    {
        if (deviceId && videoPath) 
        {
            this.deviceId = deviceId
            this.userId = null
            this.regStatus = 'N'
            this.videoPath = videoPath
        }
        else if (deviceId && userId && regStatus && videoPath)
        {
            this.deviceId = deviceId
            this.userId = userId
            this.regStatus = regStatus
            this.videoPath = videoPath
        }
    }
}

module.exports = Device