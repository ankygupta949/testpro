package App.Services;

import App.dao.Repositories.AuthorRepository;
import App.dao.Repositories.BookRepository;
import App.dao.dto.BookDTO;
import App.dao.entity.Book;
import App.dao.entity.Grade;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestService {

    @PersistenceContext
    public EntityManager entityManager;

    @Autowired
    public AuthorRepository authorRepository;

    @Autowired
    public BookRepository bookRepository;

    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    public List<BookDTO> getBookDTOSBySql(Long bookId, Grade bookGrade) {
        String sqlQuery = "select a.name as authorName, "
                          + "b.book_name as bookName, "
                          + "a.id as id, "
                          + "b.grade as grade "
                          + "from author a "
                          + "inner join book b on a.book_id = b.id "
                          + "where b.id = :bookId "
                          + "and grade = :bookGrade ";
        List<Object[]> objects = entityManager
                .createNativeQuery(sqlQuery)
                .setParameter("bookId",bookId)
                .setParameter("bookGrade",bookGrade.name())
               .getResultList();

        List<BookDTO> bookDTOS = new ArrayList<>();
        for (Object[] obj : objects) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setAuthorName((String) obj[0]);
            bookDTO.setBookName((String) obj[1]);
            bookDTO.setId(((BigInteger) obj[2]).longValue());
            bookDTO.setGrade(Grade.valueOf((String) obj[3] ));
            bookDTOS.add(bookDTO);
        }
        return bookDTOS;
    }


    public List<BookDTO> getBookDTOSByHql(Long bookId, Grade bookGrade) {
        String hqlQuery = "select a.name as authorName, "
                          + "b.bookName as bookName, "
                          + "a.id as authorId, "
                          + "b.grade as grade "
                          + "from Author a "
                          + "inner join a.book b "
                          + "where b.id = :bookId "
                          + "and grade = :bookGrade ";
        return entityManager
                .createQuery(hqlQuery)
                .setParameter("bookId", bookId)
                .setParameter("bookGrade",bookGrade)
                .unwrap(Query.class)
                .setResultTransformer(Transformers.aliasToBean(BookDTO.class))
                .list();
    }


    /*public List<BookDTO> getBookDTOSBySql(Long bookId) {
        String sqlQuery = "select a.name as authorName, " +
                          "b.book_name as bookName " +
                          "from author a " +
                          "inner join book b on a.book_id = b.id " +
                          "where b.id = :bookId ";
        return entityManager
                .createNativeQuery(sqlQuery)
                .setParameter("bookId", bookId)
                .unwrap(Query.class)
                .setResultTransformer(Transformers.aliasToBean(BookDTO.class))
                .list();
    }*/

    public List<BookDTO> getBooksById(Long id){
        List<Map<String,Object>> books= bookRepository.bookByBookName(id);
       return books.stream().map(book -> {
           BookDTO bookDTO = new BookDTO();
           bookDTO.setBookName((String) book.get("bookName"));
           bookDTO.setBookPrice((BigDecimal) book.get("bookPrice"));
           return bookDTO;
       }).collect(Collectors.toList());
    }


}
