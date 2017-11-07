package settheory;

import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.cli.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@CommonsLog
public class SetTheory {

    public static void main (String [] arr) {
        Options options = new Options();
        Option fileOption = Option.builder("if") //builder pattern
                .argName("Input File")
                .desc("The file that has the sets to process")
                .required(true)
                .numberOfArgs(1)
                .build();
        options.addOption(fileOption);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, arr);
            if(cmd.hasOption("if")){
                String fileName = cmd.getOptionValue("if");
                try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                    stream.forEach(SetTheory::processSetLine);
                } catch (Exception e){
                    log.error(e);
                    System.out.print("Application had error exiting");
                }
            }
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("settheory.SetTheory", options);
        }
    }

    public static void processSetLine(String line){

        Stream<String>stringStream = line.chars().mapToObj(i-> Character.toString((char) i)); //breaking string into stream and stream feeds single letter at a time
        Collection<SetCharacter<?>> characters = stringStream.map(SetCharacterFactory.getInstance()::of).collect(toList());

        boolean isReflective = new ReflectiveSet(characters).process().isValid();
        boolean isSymmetric = new SymmetricSet(characters).process().isValid();
        boolean isTransitive = new TransitiveSet(characters).process().isValid();

        System.out.print(line + " ");
        if(isReflective){
            System.out.print("is Reflective\n");
        } else if (isSymmetric){
            System.out.print("is Symmetric\n");
        } else if (isTransitive){
            System.out.print("is Transitive\n");
        } else {
            System.out.print("is not a set\n");
        }

    }
}
