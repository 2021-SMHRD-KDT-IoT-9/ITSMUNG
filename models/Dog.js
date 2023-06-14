class Dog
{
    constructor(dogId, userId, dogName, dogBirth, dogGender, dogBreed, dogNeutered, dogWeight)
    {
        if (dogId && userId && dogName && dogBirth && dogGender && dogBreed && dogNeutered && dogWeight)
        {
            this.dogId = dogId
            this.userId = userId
            this.dogName = dogName
            this.dogBirth = dogBirth
            this.dogGender = dogGender
            this.dogBreed = dogBreed
            this.dogNeutered = dogNeutered
            this.dogWeight = dogWeight
        }
    }
}

module.exports = Dog