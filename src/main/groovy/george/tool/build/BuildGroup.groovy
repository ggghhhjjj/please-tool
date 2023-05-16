package george.tool.build

import picocli.CommandLine.Command
import picocli.CommandLine.Option

import java.util.concurrent.Callable

@Command(name = 'build', description = 'Build command')
class BuildGroup implements Callable<Integer> {
    @Option(names = '-y')
    int y

    @Override
    Integer call() throws Exception {
        System.out.println "bi from bar y=${y}"
        return 23
    }

    @Command(name = 'collections', description = "I'm subcommand of bar")
    int baz(@Option(names = '-z') int z) {
        System.out.println "Hi, from collections, z=${z}"
        return 45
    }
}
