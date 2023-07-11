package george.tool.lib

import picocli.CommandLine
import picocli.CommandLine.IDefaultValueProvider

class ConfigPropertiesProvider implements IDefaultValueProvider {

    static ConfigPropertiesProvider INSTANCE

    private final IDefaultValueProvider provider
    private final File configFile
    private final Properties configProperties

    ConfigPropertiesProvider(String configPath) {
        if (INSTANCE != null) {
            throw new IllegalStateException("Multiple configuration instances are forbidden!")
        }

        configFile = configPath == null ? new File(System.getProperty('user.home'), '.please/config.properties') : new File(configPath)

        configProperties = new Properties()
        if (configFile.exists() && configFile.canRead()) {
            try (def ins = new FileInputStream(configFile)) {
                configProperties.load(ins)
            }
        }

        INSTANCE = this
        provider = new CommandLine.PropertiesDefaultProvider(configProperties)
    }

    @Override
    String defaultValue(CommandLine.Model.ArgSpec argSpec) throws Exception {
        return provider.defaultValue(argSpec)
    }

    void commit(AbstractMap.SimpleEntry<String, String>... keyValues) {
        keyValues.each {
            configProperties.setProperty(it.getKey(), it.getValue())
        }
    }

    void push() {
        if (!configFile.getParentFile().exists()) {
            configFile.getParentFile().mkdirs()
        }

        try (def cfgFile = new FileOutputStream(configFile)) {
            configProperties.store(cfgFile, null)
        }
    }
}
