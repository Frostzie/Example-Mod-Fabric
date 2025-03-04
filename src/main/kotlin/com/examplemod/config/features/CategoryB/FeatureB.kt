package com.examplemod.config.features.CategoryB

import com.google.gson.annotations.Expose
import com.examplemod.config.features.CategoryB.SubCategory.SubFeature
import io.github.notenoughupdates.moulconfig.annotations.Category
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorBoolean
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption

class FeatureB {

    @Expose
    @Category(name = "Sub Category", desc = "Description")
    var subCategory = SubFeature()

    @Expose
    @ConfigOption(name = "Toggle", desc = "Turn on or off feature")
    @ConfigEditorBoolean
    var on_off1: Boolean = false
}