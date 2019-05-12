# Code Review / Refactoring exercise #

### Questions ###

1.  If you were to review the following code, what feedback would you give? Please be specific and indicate any errors that would occur as well as other best practices and code refactoring that should be done.
    * I would suggest not to handle too many boolean variables for log message type and log site type, instead of that i think is better use polymorphism declaring interfaces and implementations according to similar calculations (eg. logging a message using the Java.Util Class "Logger") 
    * For the logMessage method params, i think its better to declare those as finals to avoid bad practices as re assign them.
    * I know that is valid to enter any String into the Logger.getLogget() method, anyway, i prefer to pass the name of the main class where all the logging is happening.
    * In the first line inside of the logMessage function it was used the trim method for strings but you didn't assign the result to a variable, which causes that even if the message is composed by blank spaces only, it will log that.
    * To handle the Application Exceptions i prefer to create a new custom Exception class that implements the Exception class, this class also would allocate all the possible error messages as constants. 
    * I would prefer to declare the message type log as int constants to assure readability.
    * Also in the message type log validation it has no real purpose to validate the entered booleans for every message type with the self named class properties because in the app itself the user selectively chooses the message type to log.
    * I prefer to declare the logger as a private constant because it will be the same in every calculation.
    * I would split the code into 3 packages: exception to handle all the application exception classes, controller to handle the main task of the project and services to allocate all the implementations of logging into a especific site (Database, File and Console)
    * For the tests, i think it is mandatory to verify all the implementations of the message site type. 
2.  Rewrite the code based on the feedback you provided in question 1. Please include unit tests on your code.
