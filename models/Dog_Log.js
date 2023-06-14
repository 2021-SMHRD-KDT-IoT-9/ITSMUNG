class Dog_Log
{
    constructor(logId, dogId, excretaSeperation, excretaState, weight, imgPath, logDate)
    {
        if (logId && dogId && excretaSeperation && excretaState && weight && imgPath && logDate)
        {
            this.logId = logId
            this.dogId = dogId
            this.excretaSeperation = excretaSeperation
            this.excretaState = excretaState
            this.weight = weight
            this.imgPath = imgPath
            this.logDate = logDate
        }
    }
}

module.exports = Dog_Log