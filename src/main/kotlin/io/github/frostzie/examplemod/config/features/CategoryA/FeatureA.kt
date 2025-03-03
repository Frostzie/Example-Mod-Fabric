package io.github.frostzie.examplemod.config.features.CategoryA

import com.google.gson.annotations.Expose
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorBoolean
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorButton
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorColour
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorDraggableList
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorDropdown
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorInfoText
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorKeybind
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorSlider
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorText
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption
import org.lwjgl.glfw.GLFW

class FeatureA {

    @Expose
    @ConfigOption(name = "on / off", desc = "True or false feature")
    @ConfigEditorBoolean
    var true_false: Boolean = false

    @Expose
    @ConfigOption(name = "Keybind", desc = "Keybind for feature")
    @ConfigEditorKeybind(defaultKey = GLFW.GLFW_KEY_1)
    var keybind = GLFW.GLFW_KEY_1

    @Expose
    @ConfigOption(name = "Color", desc = "Color selector for feature")
    @ConfigEditorColour
    var color: String = "0:0:0:197:64"

    @Expose
    @ConfigOption(name = "Slider", desc = "Slider for feature")
    @ConfigEditorSlider(minValue = 0.0F, maxValue = 10.0F, minStep = 0.1F)
    var slider: Float = 0.0F

    @Expose
    @ConfigOption(name = "Text box", desc = "Text box for feature")
    @ConfigEditorText
    var text: String = ""

    @Expose
    @ConfigOption(name = "Button", desc = "Button for feature")
    @ConfigEditorButton
    var button: String = ""

//    @Expose
//    @ConfigOption(name = "Draggable list", desc = "Draggable list for feature")
//    @ConfigEditorDraggableList
//    var draggableList: List<String> = listOf("")

//    @Expose
//    @ConfigOption(name = "Dropdown", desc = "Dropdown for feature")
//    @ConfigEditorDropdown
//    var dropdown: String = ""

    @Expose
    @ConfigOption(name = "Info Text", desc = "Info text")
    @ConfigEditorInfoText
    var infoText: String = ""
}