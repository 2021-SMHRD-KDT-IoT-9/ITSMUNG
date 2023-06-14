class Expert
{
    constructor(expertId, expertPw, expertName, expertTel, expertLicense, regDate)
    {
        if(expertId && expertPw && expertName && expertTel && expertLicense)
        {
            this.expertId = expertId
            this.expertPw = expertPw
            this.expertName = expertName
            this.expertTel = expertTel
            this.expertLicense = expertLicense
            this.regDate = null
        }
    }
}

module.exports = Expert