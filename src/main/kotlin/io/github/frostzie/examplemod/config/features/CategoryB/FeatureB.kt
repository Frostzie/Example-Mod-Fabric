package io.github.frostzie.examplemod.config.features.CategoryB

import com.google.gson.annotations.Expose
import io.github.frostzie.examplemod.config.features.CategoryB.SubCategory.SubFeature
import io.github.notenoughupdates.moulconfig.annotations.Category
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorBoolean
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption

class FeatureB {

    @Expose
    @Category(name = "Sub Category", desc = "This is a test toggle")
    var subCategory = SubFeature()

    @Expose
    @ConfigOption(name = "on / off", desc = "True or false feature")
    @ConfigEditorBoolean
    var true_false: Boolean = false

}