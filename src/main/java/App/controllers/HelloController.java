package App.controllers;

import App.Services.AuthorService;
import App.Services.CountryService;
import App.Services.StudentService;
import App.dao.dto.AuthorDTO;
import App.dao.dto.BookDTO;
import App.dao.entity.Grade;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class HelloController {

    @Autowired
    StudentService studentService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CountryService countryService;

    @RequestMapping("/hello")
    public String say() {
        return "hello ankit";
    }

    @RequestMapping("/get/book/{id}")
    public String bookByGetMethod(@PathVariable("id") Long bookId) {
        studentService.getBookByGetMethod(bookId);
        return "done";
    }

    @RequestMapping("/load/book/{id}")
    public String bookByLoadMethod(@PathVariable("id") Long bookId) {
        return studentService.getBookByLoadMethod(bookId);
    }

    @RequestMapping("/like/{str}")
    public String bookByLike(@PathVariable("str") String searchString) {
        studentService.getbookByLike(searchString);
        return "done";
    }

    @RequestMapping("/book")
    public BookDTO getBook() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookName("Harry Porter The End Game");
        bookDTO.setBookPrice(new BigDecimal("120.00"));
        return bookDTO;
    }


    @RequestMapping("/books/all")
    public List<BookDTO> getBooks() {
        List<BookDTO> bookDTOS = studentService.getBooks(new BigDecimal(130));
        if (CollectionUtils.isNotEmpty(bookDTOS)) {
            BookDTO bookDTO = bookDTOS.get(0);
        }
        return bookDTOS.stream().filter(it -> it.getBookName().equals("123")).collect(Collectors.toList());
    }


    @RequestMapping("/books/update")
    public String updateBooks() {
        studentService.updateBooks(new BookDTO());
        return "done";
    }

    @RequestMapping("/books/list/native/test")
    public List<BookDTO> getBookListBynative() {
        return studentService.getBookDTOSBySql(1l, Grade.A);
    }
    @RequestMapping("/books/list/hql/test")
    public List<BookDTO> getBookListByJpql() {
        return studentService.getBookDTOSByHql(1l, Grade.A);
    }

    @RequestMapping("/books/list/from/repository")
    public List<BookDTO> getBookListFromRepository() {
        return studentService.getBooksById(1l);
    }

    @RequestMapping("/author/update/{name}")
    public String updateAuthorNoExceptionHandled(@PathVariable("name") String aname) {
        String result = studentService.updateAuthor(aname);
        return result;
    }


    @RequestMapping("/author/update/sol/{name}")
    public String updateAuthorExceptionHandled(@PathVariable("name") String aname) {
        String result = null;
        try {

            result = studentService.updateAuthorExceptionHandled(aname);
        } catch (RuntimeException ex) { // for handling rollback exception thrown when updateAuthorExceptionHandled
            // complete execution
            result = "exception occured";
            System.out.println("exception occured");
        }
        return result;
    }


    @RequestMapping("update/both/{name}")
    public String updateAuthorAndBookName(@PathVariable("name") String aname) {
        String result = null;

        result = studentService.updateAuthorAndBook(aname);
        return result;
    }

    @RequestMapping("/save/author")
    public String saveAuthor() {
       return authorService.saveAuthor();
    }


    @RequestMapping("/get/parent/entity/by/entityMngr")
    public String getAuthorWithBookByEntityMngr() {
         authorService.getAuthorWithBookByEntityManager(5l);
         return "done";
    }

    @RequestMapping("/get/parent/entity/by/sessionCriteria")
    public String getAuthorWithBook() {
        authorService.getAuthorWithBookBySessionCriteria(5l);
        return "done";
    }


    @RequestMapping("/get/authors")
    public List<AuthorDTO> getAuthors(){
        return authorService.getAllAuthors();
    }

    @RequestMapping("/map/example")
    public List<BookDTO> getAuthorDTO(@RequestBody Map<String,Long> request){
       return studentService.getBooksById(request.get("bookId"));
    }

    @RequestMapping("/save/country")
    public String saveCountry(){
        return countryService.saveCountry();
    }

    @RequestMapping("/print/country")
    public void printCountry(){
        countryService.getCountry();
    }


    @RequestMapping(name = "/count", method = RequestMethod.POST, consumes = "application/json")
    public int getCount(@RequestBody HttpServletRequest servletRequest){
        return studentService.getCount();
    }

}


