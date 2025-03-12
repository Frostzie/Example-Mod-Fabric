package com.examplemod.config.features.CategoryB.SubCategory

import com.google.gson.annotations.Expose
import io.github.notenoughupdates.moulconfig.annotations.Accordion
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorBoolean
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorButton
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorColour
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorInfoText
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorKeybind
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorSlider
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorText
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption
import org.lwjgl.glfw.GLFW

class SubFeature {

    @Expose
    @ConfigOption(name = "Info Text box", desc = "")
    @ConfigEditorInfoText
    var infoText2: String = ""

    @Expose
    @Accordion
    @ConfigOption(name = "Accordion", desc = "")
    var accordion2 = AccordionFeature()

    class AccordionFeature() {
        @Expose
        @ConfigOption(name = "Toggle", desc = "Turn on or off feature")
        @ConfigEditorBoolean
        var accordion_on_off2: Boolean = false
    }

    @Expose
    @ConfigOption(name = "Toggle", desc = "Turn on or off feature")
    @ConfigEditorBoolean
    var on_off2: Boolean = false

    @Expose
    @ConfigOption(name = "Keybind", desc = "Set a keybind for feature")
    @ConfigEditorKeybind(defaultKey = GLFW.GLFW_KEY_1)
    var keybind2 = GLFW.GLFW_KEY_1

    @Expose
    @ConfigOption(name = "Color", desc = "Color selector for feature")
    @ConfigEditorColour
    var color2: String = "0:0:0:197:64"

    @Expose
    @ConfigOption(name = "Slider", desc = "Slider for feature")
    @ConfigEditorSlider(minValue = 0.0F, maxValue = 10.0F, minStep = 0.1F)
    var slider2: Float = 0.0F

    @Expose
    @ConfigOption(name = "Text box", desc = "Text box for feature")
    @ConfigEditorText
    var text2: String = "Deafult text"

}