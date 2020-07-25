package App.Services;

import App.dao.dto.BookDTO;
import App.dao.entity.Author;
import App.dao.entity.Book;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Service
public class StudentService extends TestService {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    public void getBookByGetMethod(Long bookId) {

        System.out.println("Calling get Method");
        Book b = getSession().get(Book.class, bookId); // it would hit query in db
        // creating author
        Author author = new Author();
        author.setName("abc");
        author.setBook(b);
        authorRepository.save(author);
    }

    public String getBookByLoadMethod(Long bookId) {
        if (Boolean.TRUE.equals(getResult())) {
            System.out.println(" done");

            return "done";
        } else {
            System.out.println("not done");
            return "not done";
        }
    }

    public String getbookByLike(String searchString) {
        Criteria criteria = getSession().createCriteria(Book.class);
        criteria.add(Restrictions.like("bookName", "S", MatchMode.START));
        List<Book> books = criteria.list();
        books.forEach(it -> {
            System.out.println("book id -> " + it.getId() + " book name -> " + it.getBookName());
            System.out.println();
            System.out.println();
        });
        return "done";
    }

    private Boolean getResult() {
        try {
            int i = 10 / 0;
            return true;
        } catch (ArithmeticException ex) {
            System.out.println("exception occur");
            throw new ArithmeticException(ex.getMessage());
        }
    }


    public List<BookDTO> getBooks(BigDecimal greaterAmount) {
        return entityManager.createQuery("select bookName as bookName, " +
                                         "bookPrice as bookPrice " +
                                         "from Book " +
                                         "where bookPrice > :greaterAmount")
                            .unwrap(Query.class)
                            .setParameter("greaterAmount", greaterAmount)
                            .setResultTransformer(Transformers.aliasToBean(BookDTO.class))
                            .list();
    }

    public void updateBooks(BookDTO bookDTO) {
        entityManager.createQuery("update Book set bookName = :bookName, " +
                                  "bookPrice = :bookPrice " +
                                  "where bookPrice > 80.00")
                     .setParameter("bookName", bookDTO.getBookName())
                     .setParameter("bookPrice", bookDTO.getBookPrice())
                     .executeUpdate();

    }

    public String updateAuthor(String authorName) {
        try {
            entityManager
                    .createQuery("update Author set name = :authorName "
                                 + "where id = 1 ")
                    .setParameter("authorName", authorName)
                    .executeUpdate();
        } catch (Exception ex) { //for handling GenericJDBCException
            System.out.println("exception occured");
            return "exception occured";
        }
        return "done";
    }

    public String updateAuthorExceptionHandled(String authorName) {
        try {
            entityManager
                    .createQuery("update Author set name = :authorName where id = 1 ").setParameter(
                    "authorName", authorName)
                    .executeUpdate();
        } catch (Exception ex) { //for handling GenericJDBCException
            System.out.println("exception occured");
        }

        return "done";
    }

    @Transactional
    public String updateAuthorAndBook(String authorName) {
        try {

            entityManager.createQuery("update Book set bookName = :bookName where id = 1")
                         .setParameter("bookName", authorName)
                         .executeUpdate();
            //  return authorService.updateAuthorAndBookName(authorName);
            return updateAuthorAndBookName(authorName);
        } catch (Exception e) {
            return "exception occured";
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String updateAuthorAndBookName(String authorName) {
        entityManager
                .createQuery("update Author set name = :authorName where id = 1 ").setParameter(
                "authorName", authorName)
                .executeUpdate();

        System.out.println("exception occured ");


        return "done";


    }


    public int getCount() {
        int count = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = null;

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb", "root", "ankit@mysql");

            String query = "select count(1) from student where id = 5";
            PreparedStatement preparedStatement = con.prepareStatement(query.toString());
            ResultSet rs =
                    preparedStatement.executeQuery();
            rs.next();
            count = rs.getInt(1);
            System.out.println(count);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

}


