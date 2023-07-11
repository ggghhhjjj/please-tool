package george.tool.lib

import picocli.CommandLine
import picocli.CommandLine.IDefaultValueProvider

class ConfigPropertiesProvider implements IDefaultValueProvider {

    static ConfigPropertiesProvider INSTANCE

    private final IDefaultValueProvider provider
    private final File configFile

    ConfigPropertiesProvider(String configPath) {
        if (INSTANCE != null) {
            throw new IllegalStateException("Multiple configuration instances are forbidden!")
        }

        this.configFile = new File(
                configPath == null ?
                        new File(ConfigPropertiesProvider.class.getProtectionDomain().getCodeSource().getLocation()
                                .toURI()).getPath() + "config.properties" :
                        configPath
        )

        INSTANCE = this
        provider = new CommandLine.PropertiesDefaultProvider(this.configFile)
    }

    @Override
    String defaultValue(CommandLine.Model.ArgSpec argSpec) throws Exception {
        return provider.defaultValue(argSpec)
    }

    void store(AbstractMap.SimpleEntry<String, String>... keyValues) {
        Properties result = new Properties()

        try (def cfgFile = new FileInputStream(configFile)) {
            result.load(cfgFile)
        }

        boolean hasStore = false
        keyValues.each {
            if (!Objects.equals(result.getProperty(it.getKey()), it.getValue())) {
                result.setProperty(it.getKey(), it.getValue())
                hasStore = true
            }
        }

        if (hasStore) {
            try (def cfgFile = new FileOutputStream(configFile)) {
                result.store(cfgFile, null)
            }
        }
    }
}
