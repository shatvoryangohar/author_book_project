package author.model.storage;


import author.model.Author;
import author.model.Book;
import author.model.exception.BookNotFoundException;
import author.model.util.FileUtil;

import java.util.LinkedList;
import java.util.List;

public class BookStorage {

    List<Book> books = new LinkedList<>();

    public void add(Book book) {
        books.add(book);
        serialize();
    }

    public void print() {
        for (Book book : books) {
            System.out.println(book);
        }

    }

    public Book getBySerialId(String serialId) throws BookNotFoundException {
        for (Book book : books) {

            if (book.getSerialId().equals(serialId)) {
                return book;
            }
        }

        throw new BookNotFoundException("Book does not exist. SerialId" + serialId);

    }

    public Book searchBookByTitle(String keyword) {
        for (Book book : books) {
            if (book.getTitle().contains(keyword)) {
                System.out.println(book);
            }
        }
        return null;
    }


    public void searchBooksByAuthor(Author author) {
        for (Book book : books) {
            if (book.getAuthors().contains(author)) {
                System.out.println(book);
            }
        }

    }

    public void countBookByAuthor(Author author) {
        int count = 0;
        for (Book book : books) {
            if (book.getAuthors().contains(author)) {
                count++;
            }
        }
        System.out.println("Count of" + author.getEmail() + "author's book is " + count);
    }


    public void deleteBooksByAuthor(Author author) {
        for (Book book : books) {
            if (book.getAuthors().contains(author)) {
                books.remove(book);
            }
        }
        serialize();
    }


    public void deleteBook(Book book) {
        books.remove(book);
        serialize();
    }

    public void initData() {
        List<Book> bookList = FileUtil.deserializeBooks();
        if (bookList != null) {
            books = bookList;
        }
    }

    public void serialize() {
        FileUtil.serializeBooks(books);
    }
}






