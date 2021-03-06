package com.example.myapplication.mock

object MultipleGamesResponse : JsonMockProvider {
    override val json = """
        {
          "_embedded": {
            "gameEntities": [
              {
                "gameName": "Counter Strike: GO",
                "availableModes": [
                  "FREE_FOR_ALL"
                ],
                "image": "csgo.png",
                "icon": "https://www.freeiconspng.com/uploads/csgo-icon-6.png",
                "_links": {
                  "self": {
                    "href": "http://localhost:8080/game/csgo"
                  },
                  "gameEntity": {
                    "href": "http://localhost:8080/game/csgo"
                  }
                }
              },
              {
                "gameName": "World of Warcraft",
                "availableModes": [
                  "OPEN_WORLD"
                ],
                "image": "wow.png",
                "icon": "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/WoW_icon.svg/1024px-WoW_icon.svg.png",
                "_links": {
                  "self": {
                    "href": "http://localhost:8080/game/wow"
                  },
                  "gameEntity": {
                    "href": "http://localhost:8080/game/wow"
                  }
                }
              },
              {
                "gameName": "Heroes of The Storm",
                "availableModes": [
                  "RANKED"
                ],
                "image": "hots.png",
                "icon": "https://cdn6.aptoide.com/imgs/0/9/4/094ee1175eab5284bcc800e9adf9078c_icon.png?w=120",
                "_links": {
                  "self": {
                    "href": "http://localhost:8080/game/lol"
                  },
                  "gameEntity": {
                    "href": "http://localhost:8080/game/lol"
                  }
                }
              }
            ]
          },
          "_links": {
            "self": {
              "href": "http://localhost:8080/game{?page,size,sort}",
              "templated": true
            },
            "profile": {
              "href": "http://localhost:8080/profile/game"
            },
            "search": {
              "href": "http://localhost:8080/game/search"
            }
          },
          "page": {
            "size": 20,
            "totalElements": 3,
            "totalPages": 1,
            "number": 0
          }
        }
    """.trimIndent()
}