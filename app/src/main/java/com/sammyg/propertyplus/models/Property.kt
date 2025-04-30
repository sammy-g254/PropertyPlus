package com.sammyg.propertyplus.models

class Property {
    var title: String = ""
    var description: String = ""
    var price: String = ""
    var location: String = ""
    var id: String = ""
    var userId: String = ""

    // Primary constructor with all properties
    constructor(
        title: String,
        description: String,
        price: String,
        location: String,
        id: String,
        userId: String
    ) {
        this.title = title
        this.description = description
        this.price = price
        this.location = location
        this.id = id
        this.userId = userId
    }

    // Secondary constructor with no parameters
    constructor()
}