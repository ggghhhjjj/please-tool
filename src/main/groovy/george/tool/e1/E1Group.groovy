package george.tool.e1

import george.tool.e1.client.ClientGroup
import picocli.CommandLine

@CommandLine.Command(name = 'e1', version = '1.0',
        description = 'E1 group of commands',
        subcommands = [
                ClientGroup.class,
                ReleaseCmd.class
        ]
)
class E1Group {
}
