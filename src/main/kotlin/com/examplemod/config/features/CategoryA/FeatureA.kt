package com.examplemod.config.features.CategoryA

import com.google.gson.annotations.Expose
import io.github.notenoughupdates.moulconfig.annotations.Accordion
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
import io.github.notenoughupdates.moulconfig.observer.Property
import org.lwjgl.glfw.GLFW

class FeatureA {

    @Expose
    @Accordion
    @ConfigOption(name = "Accordion", desc = "")
    var accordion = AccordionFeature()

    class AccordionFeature() {
        @Expose
        @ConfigOption(name = "Toggle", desc = "Turn on or off feature")
        @ConfigEditorBoolean
        var accordion_on_off: Boolean = false
    }

    @Expose
    @ConfigOption(name = "Toggle", desc = "Turn on or off feature")
    @ConfigEditorBoolean
    var on_off: Boolean = false

    @Expose
    @ConfigOption(name = "Keybind", desc = "Set a keybind for feature")
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
    var text: String = "Default text"

    @Expose
    @ConfigOption(name = "Button", desc = "Button for feature")
    @ConfigEditorButton(buttonText = "Text")
    var button = ""

    @Expose
    @ConfigOption(name = "Draggable List", desc = "Draggable text to change the order of the list")
    @ConfigEditorDraggableList(
        exampleText = ["hi", "hello", "bye", "goodbye"]
    )
    var draggableList: List<Int> = ArrayList(mutableListOf(0, 1, 2, 3))

    @Expose
    @ConfigOption(name = "Enum Draggable List", desc = "Draggable Enum text to change the order of the list")
    @ConfigEditorDraggableList
    var enumDraggableList: List<EnumDraggableList> = ArrayList(
        listOf(
            EnumDraggableList.ONE,
            EnumDraggableList.THREE,
            EnumDraggableList.TWO,
        )
    )

    enum class EnumDraggableList(private val str: String) {
        ONE("First"),
        TWO("Second"),
        THREE("Thrice Upon A Time "),
        ;

        override fun toString(): String {
            return str
        }
    }

    @Expose
    @ConfigOption(name = "Number Dropdown", desc = "Number dropdown for feature")
    @ConfigEditorDropdown(values = ["0", "1", "2", "3"])
    var dropdown: Int = 0

    @Expose
    @ConfigOption(name = "Enum Dropdown", desc = "Enum dropdown for feature")
    @ConfigEditorDropdown
    var enumdropdown: Property<DropdownEnum> = Property.of(DropdownEnum.FOUR)

    enum class DropdownEnum(private val label: String) {
        ONE("1"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        ;

        override fun toString(): String {
            return label
        }
    }

    @Expose
    @ConfigOption(name = "Info Text box", desc = "Info text box description")
    @ConfigEditorInfoText
    var infoText: String = "test"
}