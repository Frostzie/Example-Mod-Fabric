package io.github.frostzie.examplemod.config

import com.google.gson.GsonBuilder
import io.github.frostzie.examplemod.ExampleMod
import io.github.frostzie.examplemod.utils.SimpleTimeMark

import io.github.notenoughupdates.moulconfig.observer.PropertyTypeAdapterFactory
import io.github.notenoughupdates.moulconfig.processor.BuiltinMoulConfigGuis
import io.github.notenoughupdates.moulconfig.processor.ConfigProcessorDriver

import io.github.notenoughupdates.moulconfig.processor.MoulConfigProcessor
import java.io.BufferedReader
import java.io.BufferedWriter

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import kotlin.concurrent.fixedRateTimer


object ConfigManager {
        val gson = GsonBuilder().setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .serializeSpecialFloatingPointValues()
            .registerTypeAdapterFactory(PropertyTypeAdapterFactory())
            /*
            .registerTypeAdapter(UUID::class.java, object : TypeAdapter<UUID>() {
                override fun write(out: JsonWriter, value: UUID) {
                    out.value(value.toString())
                }
                override fun read(reader: JsonReader): UUID {
                    return UUID.fromString(reader.nextString())
                }
            }.nullSafe())
             */
            .enableComplexMapKeySerialization()
            .create()
        //.registerTypeAdapter

        lateinit var features: Features

        var configDirectory = File("config/examplemod")
        private var configFile: File? = null
        lateinit var processor: MoulConfigProcessor<Features>

        fun firstLoad() {
            if (ConfigManager::features.isInitialized) {
                println("Loading config despite config alr being loaded?..")
            }
            configDirectory.mkdirs()

            configFile = File(configDirectory, "config.json")
            println("Trying to load config from $configFile")

            if (configFile!!.exists()) {
                try {
                    println("load-config-now")
                    val inputStreamReader = InputStreamReader(FileInputStream(configFile!!), StandardCharsets.UTF_8)
                    val bufferedReader = BufferedReader(inputStreamReader)
                    features = gson.fromJson(bufferedReader.readText(), Features::class.java)
                    println("Loaded config file")
                } catch (e: Exception) {
                    e.printStackTrace()
                    val backupFile = configFile!!.resolveSibling("config-${SimpleTimeMark.now().toMillis()}-backup.json")
                    println("Exception while reading $configFile. Will load fresh config and save backup to $backupFile")
                    println("Exception was $e")
                    try {
                        configFile!!.copyTo(backupFile)
                    } catch (e: Exception) {
                        println("Couldn't create a backup file for config")
                        e.printStackTrace()
                    }
                }
            }


            if (!ConfigManager::features.isInitialized) {
                println("Creating a new config file and saving it")
                features = Features()
                saveConfig("blank config")
            }

            fixedRateTimer(name = "examplemod-config-auto-save", period = 60_000L, initialDelay = 60_000L) {
                try {
                    saveConfig("auto-save-60s")
                } catch (e: Throwable) {
                    println("Error auto-saving config!")
                }
            }

            //TODO: Add UpdateManager
            val features = ExampleMod.feature
            processor = MoulConfigProcessor(ExampleMod.feature)
            BuiltinMoulConfigGuis.addProcessors(processor)
            //UpdateManager.injectConfigProcessor(processor)
            val driver = ConfigProcessorDriver(processor)
            driver.warnForPrivateFields = false
            driver.processConfig(features)

        }

        fun saveConfig(reason: String) {
            println("saveConfig: $reason")
            val file = configFile ?: throw Error("Can't save config, configFile is null")
            try {
                println("Saving config file")
                file.parentFile.mkdirs()
                val unit = file.parentFile.resolve("config.json.write")
                unit.createNewFile()
                BufferedWriter(OutputStreamWriter(FileOutputStream(unit), StandardCharsets.UTF_8)).use { writer ->
                    writer.write(gson.toJson(ExampleMod.feature))
                }

                Files.move(
                    unit.toPath(),
                    file.toPath(),
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE
                )
            } catch (e: IOException) {
                println("Couldn't save config file to $file")
                e.printStackTrace()
            }
        }
    }











































/*


    private val logger = Logger("config_manager")

    //private val jsonHolder: Map<ConfigFileType, Any> = enumMapOf()

    lateinit var processor: MoulConfigProcessor<Features>
    private var disableSaving = false

    private fun setConfigHolder(type: ConfigFileType, value: Any) {
        require(value.javaClass == type.clazz)
        @Suppress("UNCHECKED_CAST")
        (type.property as KMutableProperty0<Any>).set(value)
        (jsonHolder as MutableMap<ConfigFileType, Any>)[type] = value
    }

    fun firstLoad() {
        if (jsonHolder.isNotEmpty()) {
            logger.log("Loading config despite config being alr loaded?")
        }
        configDirectory.mkdirs()

        for (fileType in ConfigFileType.entries) {
            setConfigHolder(fileType, firstLoad(fileType.file, fileType, fileType.clazz.newInstance()))
        }

        fixedRateTimer(name = "examplemod-config-auto-save", period = 60_000L, initialDelay = 60_000L) {
        saveConfig(ConfigFileType.FEATURES, "auto-save-60s")
        }

        val features = ExampleMod.features
        processor = MoulConfigProcessor(ExampleMod.feature)
        BuiltinMoulConfigGuis.addProcessors(processor)
        val driver = ConfigProcessorDriver(processor)
        driver.warnForPrivateFields = false
        driver.processConfig(features)
    }


    fun saveConfig(reason: String) {
        logger.log("saveConfig: $reason")
        val file = configFile ?: throw Error("Can't save config, configFile is null!")
        try {
            logger.log("Saving config file")
            file.parentFile.mkdirs()
            val unit = file.parentFile.resolve("config.json.write")
            unit.createNewFile()
            BufferedWriter(OutputStreamWriter(FileOutputStream(unit), StandardCharsets.UTF_8)).use { writer ->
                writer.write(gson.toJson(ExampleMod.feature))
            }
        }
    }












































    enum class ConfigFileType(val fileName: String, val clazz: Class<*>, val property: KMutableProperty0<*>) {
        FEATURES("config", Features::class.java, ExampleMod::Feature),
    }

}


 */

















































/*
class ConfigManager {
    fun <T> loadConfig(configClass: Class<T>, file: File, gson: Gson): T? {
        return loadConfig(configClass, file, gson, false)
    }

    fun <T> loadConfig(configClass: Class<T>, file: File, gson: Gson, useGzip: Boolean): T? {
        return loadConfig(configClass, file, gson, useGzip, true)
    }

    fun <T> loadConfig(
        configClass: Class<T>,
        file: File,
        gson: Gson,
        useGzip: Boolean,
        handleError: Boolean
    ): T? {
        if (!file.exists()) return null
        return try {
            BufferedReader(
                InputStreamReader(
                    if (useGzip) GZIPInputStream(Files.newInputStream(file.toPath()))
                    else Files.newInputStream(file.toPath()),
                    StandardCharsets.UTF_8
                )
            ).use { reader ->
                gson.fromJson(reader, configClass)
            }
        } catch (e: Exception) {
            if (!handleError) return null
            RuntimeException(
                "Invalid config file '${file}'. This will reset the config to default", e
            ).printStackTrace()
            // Try to save a version of the corrupted config for debugging purposes
            makeBackup(file, ".corrupted")
            null
        }
    }

    private val unimportantConfigs = listOf("")


    fun saveConfig(config: Any, file: File, gson: Gson) {
        saveConfig(config, file, gson, false)
    }

    fun saveConfig(config: Any, file: File, gson: Gson, useGzip: Boolean) {
        val tempFile = File(file.parent, "${file.name}.temp")
        try {
            tempFile.createNewFile()
            BufferedWriter(
                OutputStreamWriter(
                    if (useGzip) GZIPOutputStream(Files.newOutputStream(tempFile.toPath()))
                    else Files.newOutputStream(tempFile.toPath()),
                    StandardCharsets.UTF_8
                )
            ).use { writer ->
                writer.write(gson.toJson(config))
            }

            if (loadConfig(config::class.java, tempFile, gson, useGzip, false) == null) {
                println("Config verification failed for $tempFile, could not save config properly.")
                if (!unimportantConfigs.contains(tempFile.name)) makeBackup(tempFile, ".backup")
                return
            }

            try {
                Files.move(tempFile.toPath(), file.toPath(), StandardCopyOption.ATOMIC_MOVE)
            } catch (e: IOException) {
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun makeBackup(file: File, suffix: String) {
        // Implementation of makeBackup method
    }



    //fun openConfigGui() {
    //    IMinecraft.instance.openWrappedScreen(getEditor())
    //}

}
*/

//TODO: Separate the old test with new test
//TODO: Both fail D:


/*
class ConfigManager<T : Config>(private val builder: ManagedConfigBuilder<T>) :
    ManagedDataFile<T>(builder.apply {
        afterLoad = Consumer<ManagedDataFile<T>> { (it as ManagedConfig<T>).rebuildConfigProcessor() }
            .andThen(afterLoad)
    }) {

    companion object {
        @JvmStatic
        @JvmOverloads
        fun <T : Config> create(
            file: File,
            clazz: Class<T>,
            consumer: (ManagedConfigBuilder<T>.() -> Unit) = {}
        ): ManagedConfig<T> {
            return ManagedConfig(ManagedConfigBuilder(file, clazz).apply(consumer))
        }
    }

    lateinit var processor: MoulConfigProcessor<T>
        private set

    override fun injectIntoInstance() {
        instance.saveRunnables?.add(this::saveToFile)
    }

    fun rebuildConfigProcessor() {
        rebuildConfigProcessor(builder)
    }

    private fun rebuildConfigProcessor(builder: ManagedConfigBuilder<T>) {
        processor = buildProcessor(builder)
    }

    /**
     * Helper function to introduce the A type parameter so that two objects can be cast to be that same A variable.
     */
    @Suppress("NOTHING_TO_INLINE")
    private inline fun <A : Annotation> cast(
        processor: MoulConfigProcessor<T>,
        annotation: Class<A>,
        method: Any
    ) {
        @Suppress("UNCHECKED_CAST")
        processor.registerConfigEditor(
            annotation,
            method as BiFunction<ProcessedOption, A, GuiOptionEditor>
        )
    }

    private fun buildProcessor(builder: ManagedConfigBuilder<T>): MoulConfigProcessor<T> {
        val processor =
            MoulConfigProcessor(this.instance)
        if (builder.useDefaultProcessors) {
            BuiltinMoulConfigGuis.addProcessors(
                processor
            )
        }
        builder.customProcessors.forEach { (annotation, method) ->
            cast(processor, annotation, method)
        }
        val driver = ConfigProcessorDriver(processor)
        driver.checkExpose = builder.checkExpose
        driver.processConfig(this.instance)
        return processor
    }

    fun getEditor(): MoulConfigEditor<T> {
        return MoulConfigEditor(processor)
    }

    fun openConfigGui() {
        IMinecraft.instance.openWrappedScreen(getEditor())
    }
}
*/