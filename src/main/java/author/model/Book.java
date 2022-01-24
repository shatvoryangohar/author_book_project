package author.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    private String serialId;
    private String title;
    private String description;
    private double price;
    private int count;
    private Set<Author> authors;
    private Set<String> tags;

    public Book(String serialId, String title, String description, double price, int count, Set<Author> authors) {
        this.serialId = serialId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.count = count;
        this.authors = authors;
    }
}

