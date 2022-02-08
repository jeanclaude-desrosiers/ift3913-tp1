package ift3913.tp1.app;

import ift3913.tp1.data.MeasureResult;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jclaude
 */
public class App {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    
    private static final String PROJECT_PATH = "project_path";
    private static final String OUT_FILENAME = "out_filename";
    
    public static void main(String[] args) {
        LOGGER.info("Parsing arguments...");
        Map<String, Optional<String>> parsedArgs = parseArguments(args);
        LOGGER.info("Done parsing arguments");
        
        if (parsedArgs.get(PROJECT_PATH).isPresent()) {
            Path projectPath = Path.of(parsedArgs.get(PROJECT_PATH).get());
            ProjectExplorer projectExplorer = new ProjectExplorer(projectPath);
            
            Collection<MeasureResult> measureResults = projectExplorer.explore();
            
            for(MeasureResult measureResult : measureResults) {
                LOGGER.info(measureResult.toString());
            }
        } else {
            LOGGER.error("No project path...");
        }
    }
    
    private static Map<String, Optional<String>> parseArguments(String[] args) {
        Map<String, Optional<String>> parsedArgs = new HashMap<>();
        
        if (args.length >= 1) {
            parsedArgs.put(PROJECT_PATH, Optional.ofNullable(args[0]));
        } else {
            parsedArgs.put(PROJECT_PATH, Optional.empty());
        }
        
        if (args.length >= 2) {
            parsedArgs.put(OUT_FILENAME, Optional.ofNullable(args[1]));
        } else {
            parsedArgs.put(OUT_FILENAME, Optional.empty());
        }
        
        return parsedArgs;
    }
}
