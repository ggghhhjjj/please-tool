package george.tool.build

import george.tool.lib.ConfigPropertiesProvider
import picocli.CommandLine.Command
import picocli.CommandLine.Option

import java.util.concurrent.Callable

@Command(name = 'build', description = 'Build command')
class BuildGroup implements Callable<Integer> {
    @Option(names = '-y', arity = "0..1", fallbackValue = "-2")
    int y

    @Override
    Integer call() throws Exception {
        System.out.println "bi from bar y=${y}"

        ConfigPropertiesProvider.INSTANCE.store(new AbstractMap.SimpleEntry("please.build.y", y.toString()))
        return 23
    }

    @Command(name = 'collections', description = "I'm subcommand of bar")
    int baz(@Option(names = '-z') int z) {
        System.out.println "Hi, from collections, z=${z}"
        return 45
    }
}
