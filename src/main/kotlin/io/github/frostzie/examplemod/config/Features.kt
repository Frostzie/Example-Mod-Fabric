package io.github.frostzie.examplemod.config

import com.google.gson.annotations.Expose
import io.github.frostzie.examplemod.config.features.CategoryA.FeatureA
import io.github.frostzie.examplemod.config.features.CategoryB.FeatureB
import io.github.notenoughupdates.moulconfig.Config
import io.github.notenoughupdates.moulconfig.Social
import io.github.notenoughupdates.moulconfig.annotations.Category
import io.github.notenoughupdates.moulconfig.common.MyResourceLocation

open class Features : Config() {
    override fun getTitle(): String {
        return "ExampleMod§r by §bFrostzie§r, config by §5Moulberry §rand §5nea89"

    }


    // TODO: Make socials open directly in the browser
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


    //override fun saveNow() {
    //    ExampleMod.configManager.saveConfig(Features, "close-gui")
    //}




    //  Config Start

    @Expose
    @Category(name = "Category A", desc = "CategoryA features.")
    var featureListA: FeatureA = FeatureA()

    @Expose
    @Category(name = "Category B", desc = "CategoryA features.")
    var featureListB: FeatureB = FeatureB()

    //  Config End
}