package io.github.frostzie.examplemod.config.features.CategoryB.SubCategory

import com.google.gson.annotations.Expose
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorBoolean
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption

class SubFeature {

    @Expose
    @ConfigOption(name = "on / off", desc = "True or false feature")
    @ConfigEditorBoolean
    var true_false: Boolean = false

}