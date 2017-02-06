package commands;

import cli_interpreter.CommandParser;
import model.Train;
import model.vehicle.Wagon;

public class GetNumSeatsTrain extends Command {
    public GetNumSeatsTrain() {
        super("getnumseats train");
    }

    @Override
    public cli_interpreter.Command getInterpreterCommand() {
        cli_interpreter.Command command;
        command = new cli_interpreter.Command("getnumseats train");
        command.setRequiredValue(true);

        return command;
    }

    @Override
    public String execute(CommandParser parser) throws Exception {
        String trainName = parser.getRequiredArgument();
        Train train = richRail.getTrain(trainName);

        if (null != train) {
            return "Train '" + train.getName() + "' has " + train.getSeats() + " seats";
        }

        return "Train '" + trainName + "' does not exist";
    }
}
