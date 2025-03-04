package com.examplemod.config

import com.google.gson.annotations.Expose
import com.examplemod.config.features.CategoryA.FeatureA
import com.examplemod.config.features.CategoryB.FeatureB
import io.github.notenoughupdates.moulconfig.Config
import io.github.notenoughupdates.moulconfig.Social
import io.github.notenoughupdates.moulconfig.annotations.Category
import io.github.notenoughupdates.moulconfig.common.MyResourceLocation

open class Features : Config() {
    override fun getTitle(): String {
        return "ExampleMod§r by §bFrostzie§r, config by §5Moulberry §rand §5nea89"

    }

    private val discord = MyResourceLocation("examplemod", "social/discord.png")
    private val github = MyResourceLocation("examplemod", "social/github.png")
    private val patreon = MyResourceLocation("examplemod", "social/patreon.png")

        override fun getSocials(): List<Social?>? {
        return listOf(
            Social.forLink("Discord", discord, "https://discord.gg"),
            Social.forLink("GitHub", github, "https://github.com"),
            Social.forLink("Patreon", patreon, "https://www.patreon.com"),
        )
    }

    //  Config Start

    @Expose
    @Category(name = "Category A", desc = "Category A features.")
    var featureListA: FeatureA = FeatureA()

    @Expose
    @Category(name = "Category B", desc = "Category B features.")
    var featureListB: FeatureB = FeatureB()

    //  Config End
}