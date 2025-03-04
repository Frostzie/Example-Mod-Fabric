package com.examplemod.config.gui

import com.examplemod.ExampleMod
import com.examplemod.config.Features
import io.github.notenoughupdates.moulconfig.gui.GuiElementWrapper
import io.github.notenoughupdates.moulconfig.gui.MoulConfigEditor

object ConfigGuiManager {
    var editor:  MoulConfigEditor<Features>? = null

    fun getEditorInstance() = editor ?: MoulConfigEditor(ExampleMod.configManager.processor).also { editor = it }

    fun openConfigGui(search: String? = null) {
        val editor = getEditorInstance()
        if (search != null) {
            editor.search(search)
        }
        ExampleMod.screenToOpen = GuiElementWrapper(editor)
    }
}