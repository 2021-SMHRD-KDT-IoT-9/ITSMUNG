class User
{
    constructor(userId, userPw, nickname, userName, userTel, regDate, kakaoEmail, kakaoRegDate, processDogId)
    {
        if(userId && userPw && nickname && userName && userTel && regDate)
        {
            this.userId = userId
            this.userPw = userPw
            this.nickname = nickname
            this.userName = userName
            this.userTel = userTel
            this.regDate = regDate
            this.kakaoEmail = null
            this.kakaoRegDate = null
            this.processDogId = null
        }
        else if(userId && userPw && nickname && userName && userTel && regDate && kakaoEmail && kakaoRegDate && processDogId)
        {
            this.userId = userId
            this.userPw = userPw
            this.nickname = nickname
            this.userName = userName
            this.userTel = userTel
            this.regDate = regDate
            this.kakaoEmail = kakaoEmail
            this.kakaoRegDate = kakaoRegDate
            this.processDogId = processDogId
        }
    }
}

module.exports = User