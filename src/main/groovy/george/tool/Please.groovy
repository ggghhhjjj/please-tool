package george.tool

import george.tool.build.BuildGroup
import george.tool.e1.E1Group

import picocli.CommandLine
import picocli.CommandLine.Command
import picocli.CommandLine.Option

import java.util.concurrent.Callable

@Command(name = 'please', version = '1.0',
        description = ['Please tool from George'],
        header = [
                $/ /$,
                $/================================/$,
                $/@|red ╔═╗|@┬  ┌─┐┌─┐┌─┐┌─┐  @|blue ╔╦╗|@┌─┐┌─┐┬   /$,
                $/@|red ╠═╝|@│  ├┤ ├─┤└─┐├┤    @|blue ║ |@│ ││ ││   /$,
                $/@|red ╩  |@┴─┘└─┘┴ ┴└─┘└─┘   @|blue ╩ |@└─┘└─┘┴─┘ /$,
                $/================================/$,
                $/ /$
        ],
        footer = "@|bold,blue Copyright(c) 2023 |@",
        showDefaultValues = true,
        subcommands = [
                BuildGroup.class,
                E1Group.class
        ]
)
class Please implements Callable<Integer> {

    @Option(names = ["-x", "--extra"], description= "Extra options number.")
    int x

    @Option(names= ["-h", "--help"], usageHelp= true, description= "Show this help message and exit.")
    private boolean help

    @Option(names= ["-V", "--version"], versionHelp=true, description="Show version info and exit.")
    private boolean version

    @Override
    Integer call() throws Exception {
        System.out.println "hi from please, x=${x}"
        return 0
    }

    static void main(String... args) {
        int code = new CommandLine(new Please()).execute(args)
        System.exit(code)
    }
}
