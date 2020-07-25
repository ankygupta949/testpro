package App.Services;

import App.dao.Repositories.CountryRepository;
import App.dao.entity.Country;
import App.dao.entity.CountryStateDTO;
import App.dao.entity.State;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public String saveCountry() {
        List<Country> countries = new ArrayList<>();

        Country country = new Country();
        country.setCountryName("India");

        State state1 = new State();
        state1.setSname("Delhi");
        state1.setCountry(country);

        State state2 = new State();
        state2.setSname("noida");
        state2.setCountry(country);

        country.setStates(Arrays.asList(state1, state2));

        Country country2 = new Country();
        country2.setCountryName("bangladesh");

        State state3 = new State();
        state3.setSname("dhaka");
        state3.setCountry(country2);

        country2.setStates(Arrays.asList(state3));

        countries.add(country);
        countries.add(country2);


        countryRepository.save(countries);
        return "done";
    }

    public void getCountry() {
        String hql = "select st.sid as stateId ,"
                     + "st.sname as sname , "
                     + "c.cid as countryId , "
                     + "c.countryName as countryName " +
                     "from State st inner join st.country c ";
        List<CountryStateDTO> countryStateDTOS =
                entityManager.createQuery(hql)
                             .unwrap(Query.class)
                             .setResultTransformer(Transformers.aliasToBean(CountryStateDTO.class))
                             .list();


        Map<Long, Country> map = new HashMap<>();
        for (CountryStateDTO countryStateDTO : countryStateDTOS) {
            Country country = map.computeIfAbsent(countryStateDTO.getCountryId(),
                                                  k -> new Country());
            if(country.getCid() == null){
                country.setCid(countryStateDTO.getCountryId());
                country.setCountryName(countryStateDTO.getCountryName());
            }
            State state = new State();
            state.setSid(countryStateDTO.getStateId());
            state.setSname(countryStateDTO.getSname());
            country.getStates().add(state);
        }

        List<Country> countries = new ArrayList<>(map.values());
        for (Country country : countries){
            System.out.println();
            System.out.println(" country -> " +country);
            System.out.println(" states -> " +country.getStates());
        }
    }
}
