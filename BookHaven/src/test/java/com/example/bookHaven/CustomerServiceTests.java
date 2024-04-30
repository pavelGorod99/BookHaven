package com.example.bookHaven;

import com.example.bookHaven.dto.AuthorDto;
import com.example.bookHaven.dto.BookDto;
import com.example.bookHaven.dto.CustomerDto;
import com.example.bookHaven.dto.CustomerUserDto;
import com.example.bookHaven.model.*;
import com.example.bookHaven.repository.AuthorRepository;
import com.example.bookHaven.repository.BookRepository;
import com.example.bookHaven.repository.CustomerRepository;
import com.example.bookHaven.repository.UserRepository;
import com.example.bookHaven.service.AuthorService;
import com.example.bookHaven.service.BookService;
import com.example.bookHaven.service.CustomerService;
import com.example.bookHaven.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTests extends ELibraryApplicationTests {

    @MockBean
    CustomerRepository customerRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    AuthorRepository authorRepository;

    @MockBean
    BookRepository bookRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Test
    public void itShouldAddCustomer() {

        User user = new User();
        user.setUsername("pavel-test-customer");
        user.setPassword("12345");
        user.setId(1L);
        user.setRole(Role.CUSTOMER);
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonExpired(true);

        when(userRepository.save(user)).thenReturn(user);

        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setUser(user);
        customer.setFirstName("Pavel");
        customer.setLastName("Gorodetchi");
        customer.setEmail("pavel.gorod@gmail.com");
        customer.setPhone("6412339556");
        customer.setAddress("1000 N 4th St, Fairfield, IA, USA");

        when(customerRepository.save(any(Customer.class))).thenAnswer(
                invocation -> {
                    Customer savedCustomer = invocation.getArgument(0);
                    if (savedCustomer == null) {
                        throw new IllegalArgumentException("Customer cannot be null");
                    }
                    savedCustomer.setCustomerId(1L); // Set an example ID
                    return savedCustomer;
                }
        );

        CustomerUserDto customerUserDto = new CustomerUserDto();
        customerUserDto.setCustomerId(1L);
        customerUserDto.setFirstName("Pavel");
        customerUserDto.setLastName("Gorodetchi");
        customerUserDto.setEmail("pavel.gorod@gmail.com");
        customerUserDto.setUsername("pavel-test-customer");
        customerUserDto.setPassword("12345");
        customerUserDto.setPhone("6412339556");
        customerUserDto.setAddress("1000 N 4th St, Fairfield, IA, USA");

        CustomerDto cd = new CustomerDto();
        cd.setCustomerId(customer.getCustomerId());
        cd.setFirstName(customer.getFirstName());
        cd.setLastName(customer.getLastName());
        cd.setPhone(customer.getPhone());
        cd.setEmail(customer.getEmail());
        cd.setAddress(customer.getAddress());

        assertEquals(cd, customerService.saveCustomer(customerUserDto));
    }

    @Test
    public void itShouldAddAuthor() {

        Author author = new Author();
        author.setId(1L);
        author.setFirstName("John");
        author.setLastName("Smith");
        author.setBooks(new HashSet<>());

        when(authorRepository.save(author)).thenReturn(author);

        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(1L);
        authorDto.setFirstName("John");
        authorDto.setLastName("Smith");
        author.setBooks(new HashSet<>());

        assertEquals(authorDto, authorService.saveAuthor(authorDto));
    }

    @Test
    public void itShouldCreateBook() {

        Author author = new Author();
        author.setId(1L);
        author.setFirstName("John");
        author.setLastName("Smith");
        author.setBooks(new HashSet<>());

        BookDto bookDto = new BookDto();
        bookDto.setBookId(1L);
        bookDto.setTitle("Part 1");
        bookDto.setGenre(Genre.ACTION);
        bookDto.setPrice(200);
        bookDto.setQuantity(5);
        bookDto.setInTrend(true);

        Set<Long> authors = new HashSet<>();
        authors.add(1L);
        bookDto.setAuthors(authors);

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        when(bookRepository.save(any(Book.class))).thenAnswer(
                invocation -> {
                    Book savedBook = invocation.getArgument(0);
                    if (savedBook == null) {
                        throw new IllegalArgumentException("Book cannot be null");
                    }
                    savedBook.setBookId(1L); // Set an example ID
                    return savedBook;
                }
        );

        assertEquals(bookDto, bookService.saveBook(bookDto));
    }

    @Test
    public void itShouldGiveMeBookList() {
        Book book = new Book();
        book.setBookId(1L);
        book.setTitle("Title 1");
        book.setQuantity(5);
        book.setGenre(Genre.ACTION);
        book.setInTrend(true);
        book.setPrice(200);

        Author author = new Author();
        author.setId(1L);
        author.setFirstName("John");
        author.setLastName("Smith");
        author.setBooks(new HashSet<>());

        Set<Author> authors = new HashSet<>();
        authors.add(author);
        book.setAuthors(authors);

        List<Book> books = new ArrayList<>();
        books.add(book);

        when(bookRepository.findAll()).thenReturn(books);

        assertNotNull(bookService.getAllBooks());
    }
}
