package input;

public interface EnteredValue {

    enum Types {EXIT, INCORRECT, VALUE}
    Types getType();
}
