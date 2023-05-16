package george.tool.e1.client

import picocli.CommandLine

@CommandLine.Command (name = 'client', description = 'E1 Client group of commands')
class ClientGroup {

    @CommandLine.Command(name = 'token', description = "Token command")
    int token(@CommandLine.Option(names = '-z') int z) {
        System.out.println "Hi, from token, z=${z}"
        return 45
    }
}
