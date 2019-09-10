package event.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import com.even.logger.processor.impl.EventProcessorImpl;
import com.even.logger.processor.impl.Processor;
import com.even.logger.util.CommandArgumentParser;
import com.even.logger.util.EventReader;


/**
 * @author ranjan kumar
 * 
 * ## Main Activator class to kick start the Spring Boot application
 * Summary of task 
 * Our custom-build server logs different events to a file. Every event has 2 entries in a log - one entry 
 * when the event was started and another when the event was finished. The entries in a log file have no 
 * specific order (it can occur that a specific event is logged before the event starts) Every line in 
 * the file is a JSON object containing event data: • id - the unique event identifier • state - whether 
 * the event was started or finished (can have values "STARTED" or "FINISHED" • timestamp - the timestamp 
 * of the event in milliseconds Application Server logs also have the additional attributes: • type - type 
 * of log • host - hostname 
 */
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootApplication.class);
	
	public static void main(String[] args) {

		logger.info("Activating Spring Boot Application to process Log Events!!!");
		SpringApplication.run(SpringBootApplication.class, args);

		CommandArgumentParser commandArgumentParser = new CommandArgumentParser(args);
		String inputFile = commandArgumentParser.switchValue("-inputFile");
		logger.info("Reading events from file:"+inputFile);

		EventReader reader = new EventReader();
		Processor processor = new EventProcessorImpl();
		processor.pocessEvents(reader.readInputFile(inputFile));

		logger.info("Terminating Spring Boot Application to process Log Events!!!");
	}
}
