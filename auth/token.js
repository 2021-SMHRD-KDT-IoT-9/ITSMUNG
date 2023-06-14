function createToken()
{
    return 'Itsmung Token'
}

function verifyToken(token)
{
    if(token === "Itsmung Token")
    {
        return true
    }
    else
    {
        return false
    }
}




module.exports = {
    createToken,
    verifyToken
}