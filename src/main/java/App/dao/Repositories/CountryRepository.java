package App.dao.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import App.dao.entity.*;
import org.springframework.data.jpa.repository.Query;

public interface CountryRepository extends JpaRepository<Country,Long> {

    @Query("select a from Author a where name=?1")
    public Author authorByName(String name);
}
