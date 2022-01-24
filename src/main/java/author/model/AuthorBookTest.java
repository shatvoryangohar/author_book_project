package author.model;

import author.model.exception.BookNotFoundException;
import author.model.storage.AuthorStorage;
import author.model.storage.BookStorage;
import author.model.storage.UserStorage;
import author.model.util.DateUtil;

import java.text.ParseException;
import java.util.*;

public class AuthorBookTest implements AuthorBookCommands {
    static Scanner scanner = new Scanner(System.in);
    static AuthorStorage authorStorage = new AuthorStorage();
    static BookStorage bookStorage = new BookStorage();
    static UserStorage userStorage = new UserStorage();


    public static void main(String[] args) {

        initData();
        boolean isRun = true;
        while (isRun) {
            AuthorBookCommands.printCommands();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }


    private static void login() {
        System.out.println("Please input email");
        String email = scanner.nextLine();
        User byEmail = userStorage.getByEmail(email);
        if (byEmail != null) {
            System.out.println("Please input password");
            String password = scanner.nextLine();
            if (byEmail.getPassword().equals(password)) {
                if (byEmail.getType() == UserType.ADMIN) {
                    adminLogin();
                } else if (byEmail.getType() == UserType.USER)
                    userLogin();
            } else {
                System.out.println("password is wrong");
            }
        } else {
            System.out.println("user with " + email + "already exist");
        }
    }

    private static void userLogin() {
        boolean isRun = true;
        while (isRun) {
            String command = scanner.nextLine();
            AuthorBookCommands.printUserCommands();
            switch (command) {
                case EXIT:
                    System.exit(0);
                    break;
                case ADD_AUTHOR:
                    addAuthor();
                    break;
                case SEARCH_AUTHOR:
                    searchAuthor();
                    break;
                case SEARCH_AUTHOR_BY_AGE:
                    searchAuthorByAge();
                    break;
                case SEARCH_BOOKS_BY_AUTHOR:
                    searchBooksByAuthor();
                    break;
                case COUNT_BOOKS_BY_AUTHOR:
                    countBooksByAuthors();
                    break;
                case PRINT_AUTHORS:
                    authorStorage.print();
                    break;
                case ADD_BOOK:
                    addBook();
                    break;
                case PRINT_BOOK:
                    bookStorage.print();
                    break;
                case SEARCH_BOOKS_BY_TITLE:
                    searchBookByTitle();
                    break;
                case LOGOUT:
                    isRun = false;
                    break;
                default:
                    System.out.println("Invalid command");
            }

        }
    }

    private static void register() {
        System.out.println("Please input email");
        String email = scanner.nextLine();
        User byEmail = userStorage.getByEmail(email);
        if (byEmail == null) {
            System.out.println("Please input name");
            String name = scanner.nextLine();
            System.out.println("Please input surname");
            String surname = scanner.nextLine();
            System.out.println("Please input password");
            String password = scanner.nextLine();
            System.out.println("Please input type(ADMIN,USER)");
            String type = scanner.nextLine();
            User user = new User();
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPassword(password);
            user.setType(UserType.valueOf(type.toUpperCase()));
            userStorage.addUser(user);
            System.err.println("User was registered!");
        } else {
            System.err.println("user with " + email + "already exist");
        }
    }

    private static void adminLogin() {
        boolean isRun = true;
        while (isRun) {
            AuthorBookCommands.printAdminCommands();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT:
                    System.exit(0);
                    break;
                case ADD_AUTHOR:
                    addAuthor();
                    break;
                case SEARCH_AUTHOR:
                    searchAuthor();
                    break;
                case SEARCH_AUTHOR_BY_AGE:
                    searchAuthorByAge();
                    break;
                case SEARCH_BOOKS_BY_AUTHOR:
                    searchBooksByAuthor();
                    break;
                case COUNT_BOOKS_BY_AUTHOR:
                    countBooksByAuthors();
                    break;
                case PRINT_AUTHORS:
                    authorStorage.print();
                    break;
                case ADD_BOOK:
                    addBook();
                    break;
                case PRINT_BOOK:
                    bookStorage.print();
                    break;
                case SEARCH_BOOKS_BY_TITLE:
                    searchBookByTitle();
                    break;
                case CHANGE_AUTHOR:
                    changeAuthor();
                    break;
                case CHANGE_BOOK_AUTHOR:
                    changeBookAuthor();
                    break;
                case DELETE_AUTHOR:
                    deleteAuthor();
                    break;
                case DELETE_BY_AUTHOR:
                    deleteBooksByAuthor();
                    break;
                case DELETE_BOOK:
                    deleteBook();
                    break;
                case ADD_TAG_TO_BOOK:
                    addTagsOfBook();
                case REMOVE_TAG_FROM_BOOK:
                    removeTagFromBook();
                    break;
                case LOGOUT:
                    isRun = false;
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }


    private static void removeTagFromBook() {
        System.out.println("please choose book by serialId");
        System.out.println("-------");
        bookStorage.print();
        System.out.println("-------");
        String serialId = scanner.nextLine();
        try {
            Book book = bookStorage.getBySerialId(serialId);
            String tagsStr = scanner.nextLine();
            System.out.println("Please input tags separate ,");
            String[] tagsToRemove = tagsStr.split(",");
            Set<String> bookTags = book.getTags();
            if (bookTags == null) {
                System.err.println("Book does not have any tags to remove!!!");
            } else {
                bookTags.removeAll(Arrays.asList(tagsToRemove));
            }
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void addTagsOfBook() {
        System.out.println("please choose book by serialId");
        System.out.println("-------");
        bookStorage.print();
        System.out.println("-------");
        String serialId = scanner.nextLine();

        try {
            Book book = bookStorage.getBySerialId(serialId);
            String tagsStr = scanner.nextLine();
            System.out.println("Please input tags separate ,");
            String[] tags = tagsStr.split(",");
            Set<String> bookTags = book.getTags();
            if (bookTags == null) {
                book.setTags(new HashSet<>(Arrays.asList(tags)));
                System.err.println("Tags were added!");
            } else {
                bookTags.addAll(Arrays.asList(tags));
                System.err.println("Tags were added!");
            }
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void initData() {
        authorStorage.initData();
        bookStorage.initData();
        userStorage.initData();
    }

    private static void deleteBook() {
        System.out.println("please choose book by serialId");
        System.out.println("-------");
        bookStorage.print();
        System.out.println("-------");
        String serialId = scanner.nextLine();
        ;
        try {
            Book book = bookStorage.getBySerialId(serialId);

            bookStorage.deleteBook(book);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void deleteBooksByAuthor() {
        System.out.println("please choose author's email");
        System.out.println("-------");
        String email = scanner.nextLine();
        Author author = authorStorage.getByEmail(email);
        if (author != null) {
            bookStorage.deleteBooksByAuthor(author);

        } else {
            System.out.println("Author does not exists ");
        }
    }

    private static void deleteAuthor() {
        System.out.println("please choose author's email");
        System.out.println("-------");
        String email = scanner.nextLine();
        Author author = authorStorage.getByEmail(email);
        if (author != null) {
            authorStorage.deleteAuthor(author);
        } else {
            System.out.println("Author does not exists ");
        }
    }

    private static void changeBookAuthor() {
        System.out.println("please choose book by serialId");
        System.out.println("-------");
        bookStorage.print();
        System.out.println("-------");
        String serialId = scanner.nextLine();

        try {
            Book book = bookStorage.getBySerialId(serialId);
            System.out.println("please choose author's email ");
            System.out.println("-------");
            String emails = scanner.nextLine();
            String[] emailArray = emails.split(",");
            if (emailArray.length == 0) {
                System.out.println("Please choose authors");
                return;
            }
            Set<Author> authors = new HashSet<>();

            for (String email : emailArray) {
                Author author = authorStorage.getByEmail(email);
                if (author != null) {
                    authors.add(author);
                } else {
                    System.out.println("Please input correct author's email");
                    return;
                }
            }
            book.setAuthors(authors);
            bookStorage.serialize();
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void changeAuthor() {
        System.out.println("please choose author's email");
        System.out.println("-------");
        authorStorage.print();
        System.out.println("-------");
        String email = scanner.nextLine();
        Author author = authorStorage.getByEmail(email);
        if (author != null) {
            System.out.println("please input name");
            String name = scanner.nextLine();
            System.out.println("please input surname");
            String surname = scanner.nextLine();
            System.out.println("please,input author's age");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.println("please,input author's gender");
            try {
                Gender gender = Gender.valueOf(scanner.nextLine());
                author.setName(name);
                author.setSurname(surname);
                author.setAge(age);
                author.setGender(gender);
                authorStorage.serialize();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.err.println("Author does not exists");
        }
    }


    private static void countBooksByAuthors() {
        System.out.println("please input author's email");
        System.out.println("-------");
        authorStorage.print();
        System.out.println("-------");
        String email = scanner.nextLine();
        Author author = authorStorage.getByEmail(email);
        if (author != null) {
            bookStorage.countBookByAuthor(author);
        } else {
            System.err.println("Author does not exists");
        }

    }

    private static void searchBooksByAuthor() {
        System.out.println("please input author's email");
        System.out.println("-------");
        authorStorage.print();
        System.out.println("-------");
        String email = scanner.nextLine();
        Author author = authorStorage.getByEmail(email);
        if (author != null) {
            bookStorage.searchBooksByAuthor(author);
        } else {
            System.err.println("Author does not exists");
        }
    }

    private static void searchBookByTitle() {
        System.out.println("Please input keyword");
        String keyword = scanner.nextLine();
        System.out.println(bookStorage.searchBookByTitle(keyword));
    }

    private static void addBook() {
        System.out.println("please choose author's email separate ,");
        System.out.println("----------");
        authorStorage.print();
        System.out.println("----------");
        String emails = scanner.nextLine();
        String[] emailArray = emails.split(",");
        if (emailArray.length == 0) {
            System.out.println("Please choose authors");
            return;
        }
        Set<Author> authors = new HashSet<>();

        for (String email : emailArray) {
            Author author = authorStorage.getByEmail(email);
            if (author != null) {
                authors.add(author);
            } else {
                System.out.println("please input correct author's email");
                return;
            }
        }

        System.out.println("please input book's serialId");
        String serialId = scanner.nextLine();
        try {
            bookStorage.getBySerialId(serialId);
            System.out.println("Book is duplicate");
        } catch (BookNotFoundException e) {
            System.out.println("please input book's title");
            String title = scanner.nextLine();
            System.out.println("please input book's description");
            String description = scanner.nextLine();
            System.out.println("please input book's price");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.println("please input book's count");
            int count = Integer.parseInt(scanner.nextLine());
            System.out.println();
            System.out.println("Please input book's tags separate ,");
            String tagsStr = scanner.nextLine();
            String[] tags = tagsStr.split(",");

            Book book = new Book(serialId, title, description, price, count, authors, new HashSet<>(Arrays.asList(tags)));

            bookStorage.add(book);

            System.out.println("Thank you! Book's was added");
        }
    }


    private static void searchAuthorByAge() {
        System.out.println("Please input min age");
        int minAge = Integer.parseInt(scanner.nextLine());
        System.out.println("Please input max age");
        int maxAge = Integer.parseInt(scanner.nextLine());
        authorStorage.searchAuthorByAge(minAge, maxAge);
    }

    private static void searchAuthor() {
        System.out.println(" Please input keyword");
        String keyword = scanner.nextLine();
        authorStorage.searchAuthor(keyword);
    }

    private static void addAuthor() {

        System.out.println("please,input author's name,surname,email,age,gender,dateOfBirth[12/12/2021]");
        String authorDataStr = scanner.nextLine();
        String[] authorData = authorDataStr.split(",");
        if (authorData.length == 6) {
            int age = Integer.parseInt(authorData[3]);
            Date data = null;
            try {
                data = DateUtil.stringToDate(authorData[5]);
            } catch (ParseException e) {
                System.out.println("invalid date format,please  respect this format[12/12/2021]");
            }
            Author author = new Author(authorData[0], authorData[1], authorData[2], age, Gender.valueOf(authorData[4]), data);

            if (authorStorage.getByEmail(author.getEmail()) != null) {
                System.err.println("Invalid email. Author with this email already exist");
            } else {
                authorStorage.add(author);
                System.out.println("Thank you! Authors was added");
            }
        } else {
            System.err.println("Invalid data");
        }
    }
}
