package App.dao.Repositories;

import App.dao.dto.BookDTO;
import App.dao.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select bookName as bookName,bookPrice as bookPrice from Book a where id=?1")
    public List<Map<String,Object>> bookByBookName(Long id);
}
