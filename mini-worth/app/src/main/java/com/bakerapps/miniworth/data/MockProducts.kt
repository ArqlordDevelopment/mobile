package com.bakerapps.miniworth.data

object MockProducts {
    val products = listOf(
        Product(
            id = 1,
            name = "Primaris Intercessor Squad",
            gameSystem = "Warhammer 40,000",
            faction = "Space Marines",
            productType = "Boxed Kit",
            category = "Infantry",
            description = "The Primaris Intercessors are core battleline infantry for Space Marines. This kit is commonly found as sealed boxes, new-on-sprue models, built squads, painted squads, and bits lots.",
            contents = listOf("10 Primaris Intercessor miniatures", "10x 32mm round bases", "Transfer sheet", "Weapon options"),
            keywords = listOf("Warhammer 40K Primaris Intercessors", "Space Marines Intercessor Squad", "Intercessors Built Painted", "Intercessors New on Sprue"),
            confidence = 82,
            ebaySearchQuery = "Warhammer 40K Primaris Intercessor Squad"
        ),
        Product(2, "Redemptor Dreadnought", "Warhammer 40,000", "Space Marines", "Boxed Kit", "Vehicle", "A heavy Space Marines walker kit with strong resale demand across sealed, built, and painted conditions.", listOf("Redemptor Dreadnought", "Base", "Weapon options", "Transfer sheet"), listOf("Redemptor Dreadnought", "Space Marines Redemptor", "Redemptor built painted"), 76, "Warhammer 40K Redemptor Dreadnought"),
        Product(3, "Combat Patrol: Tyranids", "Warhammer 40,000", "Tyranids", "Army Box", "Combat Patrol", "A starter-sized Tyranid force box with multiple units and broad appeal for new collections.", listOf("Tyranid miniatures", "Bases", "Assembly guide", "Starter force contents"), listOf("Combat Patrol Tyranids", "Tyranids army box", "Tyranid combat patrol sealed"), 73, "Warhammer 40K Combat Patrol Tyranids"),
        Product(4, "Necron Warriors", "Warhammer 40,000", "Necrons", "Boxed Kit", "Infantry", "Core Necron battleline infantry often traded in squads, sprues, and starter-set lots.", listOf("Necron Warriors", "Canoptek Scarabs", "Bases", "Weapon options"), listOf("Necron Warriors", "Necron Warriors new on sprue", "Necron Warriors painted"), 71, "Warhammer 40K Necron Warriors"),
        Product(5, "Bladeguard Veterans", "Warhammer 40,000", "Space Marines", "Boxed Kit", "Elite Infantry", "Shield-bearing Space Marines veterans with strong demand for characterful army builds.", listOf("3 Bladeguard Veterans", "Bases", "Head options", "Weapon options"), listOf("Bladeguard Veterans", "Space Marines Bladeguard", "Bladeguard Veterans painted"), 69, "Warhammer 40K Bladeguard Veterans"),
        Product(6, "Ork Boyz", "Warhammer 40,000", "Orks", "Boxed Kit", "Infantry", "Classic Ork infantry commonly sold as mobs, bits, built units, and painted squads.", listOf("Ork Boyz miniatures", "Bases", "Melee options", "Ranged options"), listOf("Ork Boyz", "Warhammer Ork Boyz", "Ork Boyz built painted"), 68, "Warhammer 40K Ork Boyz"),
        Product(7, "Kill Team: Kommandos", "Kill Team", "Orks", "Boxed Kit", "Skirmish Team", "A specialist Ork Kill Team with varied sculpts and a healthy secondary market.", listOf("Ork Kommando miniatures", "Bases", "Specialist options", "Tokens or accessories"), listOf("Kill Team Kommandos", "Ork Kommandos Kill Team", "Kommandos new on sprue"), 80, "Kill Team Ork Kommandos"),
        Product(8, "Terminator Squad", "Warhammer 40,000", "Space Marines", "Boxed Kit", "Elite Infantry", "Iconic Space Marines infantry with broad buyer interest across editions and conditions.", listOf("5 Terminator miniatures", "Bases", "Heavy weapon options", "Sergeant options"), listOf("Space Marines Terminator Squad", "Terminator Squad 40K", "Terminators painted"), 74, "Warhammer 40K Terminator Squad")
    )

    fun product(id: Int): Product = products.firstOrNull { it.id == id } ?: products.first()
}
