package App.Services;

import App.dao.dto.AuthorDTO;
import App.dao.entity.Author;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AuthorService extends TestService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String updateAuthorAndBookName(String authorName) {


        entityManager
                .createQuery("update Author set name = :authorName where id = 1 ").setParameter(
                "authorName", authorName)
                .executeUpdate();

        System.out.println("exception occured ");


        return "done";


    }

    public String saveAuthor() {
        Author author = new Author();
        author.setName("vvv");
        author.setDob(new Date());

        authorRepository.save(author);
        return "done";

    }

    public void getAuthorWithBookByEntityManager(Long id) {
        Author author = (Author) entityManager.createQuery("select auth from Author auth "
                                                           + "inner join auth.book b where auth.id =  :id ")
                                              .setParameter("id", id)
                                              .unwrap(Query.class)
                                              .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                                              .list().get(0);
        System.out.println("author name : " + author.getName());
        System.out.println("book name : " + author.getBook().getBookName());
        System.out.println("book price : " + author.getBook().getBookPrice());
    }

    public void getAuthorWithBookBySessionCriteria(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Author.class)
                                   .createAlias("book", "book_")
                                   .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List<Author> authors = criteria.list();

        for (Author author :authors) {

            System.out.println("author name : " + author.getName());
            System.out.println("book name : " + author.getBook().getBookName());
            System.out.println("book price : " + author.getBook().getBookPrice());
        }
        }


    public List<AuthorDTO> getAllAuthors() {
        String hql = "select a.name as authName , a.dob as dob from author a " +
                     "where case when a.book_id=1 then a.active=true " +
                     "else a.book_id is not null end ";
       List<Map<String,Object>> resultList = entityManager.createNativeQuery(hql)
                               .unwrap(Query.class)
               .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
               .list();
        System.out.println("authname : "+resultList.get(0).get("authName"));

        return null;
    }


}