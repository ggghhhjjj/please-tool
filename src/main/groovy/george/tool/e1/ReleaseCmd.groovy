package george.tool.e1

import picocli.CommandLine

import java.util.concurrent.Callable

@CommandLine.Command (name = 'release', description = 'E1 release command')
class ReleaseCmd  implements Callable<Integer> {
    @Override
    Integer call() throws Exception {
        System.out.println "Hi, from release command"
        return 0
    }
}
