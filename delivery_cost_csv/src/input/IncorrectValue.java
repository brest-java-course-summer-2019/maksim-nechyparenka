package input;

public class IncorrectValue implements EnteredValue {

    @Override
    public Types getType() {
        return Types.INCORRECT;
    }
}
