class ASRequest 
{
    constructor(requestId, email, title, content, regDate, processStatus)
    {
        if (requestId && email && title && content && regDate)
        {
            this.requestId = requestId
            this.email = email
            this.title = title
            this.content = content
            this.regDate = regDate
            this.processStatus = "pending"
        }
    }
}

module.exports = ASRequest;