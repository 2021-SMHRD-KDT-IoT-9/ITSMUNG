class Message
{
    constructor(messageId, matchingId, userId, expertId, senderType, content, sendTime)
    {
        if (messageId && matchingId && userId && expertId && senderType && content && sendTime)
        {
            this.messageId = messageId
            this.matchingId = matchingId
            this.userId = userId
            this.expertId = expertId
            this.senderType = senderType
            this.content = content
            this.sendTime = sendTime
        }
    }
}

module.exports = Message