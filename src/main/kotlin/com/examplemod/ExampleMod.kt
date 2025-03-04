package com.examplemod

import com.examplemod.config.ConfigManager
import com.examplemod.config.Features
import com.examplemod.config.gui.ConfigGuiManager
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.gui.screen.Screen

class ExampleMod : ModInitializer {

	override fun onInitialize() {
		registerTickEvent()
		configManager = ConfigManager
		configManager.firstLoad()
		Runtime.getRuntime().addShutdownHook(Thread {
			configManager.saveConfig("shutdown-hook")
		})

		ClientCommandRegistrationCallback.EVENT.register { dispatcher, _ ->
			dispatcher.register(literal("config").executes {
				ConfigGuiManager.openConfigGui()
				0
			})
		}
	}

	fun registerTickEvent() {
		ClientTickEvents.END_CLIENT_TICK.register { client ->
			if (screenToOpen != null) {
				client.setScreen(screenToOpen)
				screenToOpen = null
			}
		}
	}

	companion object {
		lateinit var configManager: ConfigManager

		@JvmStatic
		val feature: Features get() = configManager.features

		var screenToOpen: Screen? = null
	}
}