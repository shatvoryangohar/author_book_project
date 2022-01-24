package author.model;

public interface AuthorBookCommands {


    String EXIT = "0";
    String ADD_AUTHOR = "1";
    String ADD_BOOK = "2";
    String SEARCH_AUTHOR = "3";
    String SEARCH_BOOKS_BY_TITLE = "4";
    String SEARCH_AUTHOR_BY_AGE = "5";
    String PRINT_AUTHORS = "6";
    String PRINT_BOOK = "7";
    String SEARCH_BOOKS_BY_AUTHOR = "8";
    String COUNT_BOOKS_BY_AUTHOR = "9";
    String CHANGE_AUTHOR = "10";
    String CHANGE_BOOK_AUTHOR = "11";
    String DELETE_AUTHOR = "12";
    String DELETE_BY_AUTHOR = "13";
    String DELETE_BOOK = "14";
    String ADD_TAG_TO_BOOK = "15";
    String REMOVE_TAG_FROM_BOOK = "16";

    //user command

    String LOGOUT = "17";
    String LOGIN = "1";
    String REGISTER = "2";

    static void printCommands() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + LOGIN + " for LOGIN");
        System.out.println("Please input " + REGISTER + " for REGISTER");

    }

    static void printUserCommands() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + ADD_AUTHOR + " for ADD_AUTHOR");
        System.out.println("Please input " + ADD_BOOK + " for ADD_BOOK");
        System.out.println("Please input " + SEARCH_AUTHOR + " for SEARCH_AUTHOR");
        System.out.println("Please input " + SEARCH_BOOKS_BY_TITLE + " for SEARCH_BOOKS_BY_TITLE");
        System.out.println("Please input " + SEARCH_AUTHOR_BY_AGE + " for SEARCH_AUTHOR_BY_AGE");
        System.out.println("Please input " + PRINT_AUTHORS + " for PRINT_AUTHORS");
        System.out.println("Please input " + PRINT_BOOK + " for PRINT_BOOK");
        System.out.println("Please input " + SEARCH_BOOKS_BY_AUTHOR + " for SEARCH_BOOKS_BY_AUTHOR");
        System.out.println("Please input " + COUNT_BOOKS_BY_AUTHOR + " for COUNT_BOOKS_BY_AUTHOR");
        System.out.println("Please input " + LOGOUT + " for LOGOUT");
    }


    static void printAdminCommands() {

        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + ADD_AUTHOR + " for ADD_AUTHOR");
        System.out.println("Please input " + ADD_BOOK + " for ADD_BOOK");
        System.out.println("Please input " + SEARCH_AUTHOR + " for SEARCH_AUTHOR");
        System.out.println("Please input " + SEARCH_BOOKS_BY_TITLE + " for SEARCH_BOOKS_BY_TITLE");
        System.out.println("Please input " + SEARCH_AUTHOR_BY_AGE + " for SEARCH_AUTHOR_BY_AGE");
        System.out.println("Please input " + PRINT_AUTHORS + " for PRINT_AUTHORS");
        System.out.println("Please input " + PRINT_BOOK + " for PRINT_BOOK");
        System.out.println("Please input " + SEARCH_BOOKS_BY_AUTHOR + " for SEARCH_BOOKS_BY_AUTHOR");
        System.out.println("Please input " + COUNT_BOOKS_BY_AUTHOR + " for COUNT_BOOKS_BY_AUTHOR");
        System.out.println("Please input " + CHANGE_AUTHOR + " for CHANGE_AUTHOR");
        System.out.println("please input " + CHANGE_BOOK_AUTHOR + " for CHANGE_BOOK_AUTHOR");
        System.out.println("Please input " + DELETE_AUTHOR + " for DELETE_AUTHOR");
        System.out.println("Please input " + DELETE_BY_AUTHOR + " for DELETE_BY_AUTHOR");
        System.out.println("Please input " + DELETE_BOOK + " for DELETE BOOK");
        System.out.println("Please input " + ADD_TAG_TO_BOOK + " for ADD_TAG_TO BOOK");
        System.out.println("Please input " + REMOVE_TAG_FROM_BOOK + " for REMOVE_TAG_FROM_BOOK");
        System.out.println("Please input " + LOGOUT + " for LOGOUT");


    }


}
