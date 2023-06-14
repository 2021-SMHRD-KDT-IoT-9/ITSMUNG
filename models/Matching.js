class Matching
{
    constructor(matchingId, userId, expertId, regDate)
    {
        if(matchingId && userId && expertId && regDate)
        {
            this.matchingId = matchingId
            this.userId = userId
            this.expertId = expertId
            this.regDate = regDate
        }
    }
}

module.exports = Matching