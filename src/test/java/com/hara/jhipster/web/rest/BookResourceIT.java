package com.hara.jhipster.web.rest;

import com.hara.jhipster.BookstoreApp;
import com.hara.jhipster.domain.Book;
import com.hara.jhipster.domain.Chapter;
import com.hara.jhipster.domain.Subject;
import com.hara.jhipster.repository.BookRepository;
import com.hara.jhipster.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.hara.jhipster.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.hara.jhipster.domain.enumeration.Lang;
import com.hara.jhipster.domain.enumeration.StatusFlag;
/**
 * Integration tests for the {@link BookResource} REST controller.
 */
@SpringBootTest(classes = BookstoreApp.class)
public class BookResourceIT {

    private static final String DEFAULT_BOOKTITLE = "AAAAAAAAAA";
    private static final String UPDATED_BOOKTITLE = "BBBBBBBBBB";

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_ISBN = "AAAAAAAAAA";
    private static final String UPDATED_ISBN = "BBBBBBBBBB";

    private static final String DEFAULT_PUBLISHER = "AAAAAAAAAA";
    private static final String UPDATED_PUBLISHER = "BBBBBBBBBB";

    private static final Lang DEFAULT_LANGUAGE = Lang.English;
    private static final Lang UPDATED_LANGUAGE = Lang.Hindi;

    private static final Integer DEFAULT_PUBYEAR = 1;
    private static final Integer UPDATED_PUBYEAR = 2;

    private static final Integer DEFAULT_PAGES = 1;
    private static final Integer UPDATED_PAGES = 2;

    private static final Integer DEFAULT_PRICE = 1;
    private static final Integer UPDATED_PRICE = 2;

    private static final String DEFAULT_AUTHOR = "AAAAAAAAAA";
    private static final String UPDATED_AUTHOR = "BBBBBBBBBB";

    private static final StatusFlag DEFAULT_STATUS = StatusFlag.Active;
    private static final StatusFlag UPDATED_STATUS = StatusFlag.Inactive;

    private static final LocalDate DEFAULT_DATE_ADDED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ADDED = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_MODIFIED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_MODIFIED = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restBookMockMvc;

    private Book book;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BookResource bookResource = new BookResource(bookRepository);
        this.restBookMockMvc = MockMvcBuilders.standaloneSetup(bookResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Book createEntity(EntityManager em) {
        Book book = new Book()
            .booktitle(DEFAULT_BOOKTITLE)
            .text(DEFAULT_TEXT)
            .isbn(DEFAULT_ISBN)
            .publisher(DEFAULT_PUBLISHER)
            .language(DEFAULT_LANGUAGE)
            .pubyear(DEFAULT_PUBYEAR)
            .pages(DEFAULT_PAGES)
            .price(DEFAULT_PRICE)
            .author(DEFAULT_AUTHOR)
            .status(DEFAULT_STATUS)
            .dateAdded(DEFAULT_DATE_ADDED)
            .dateModified(DEFAULT_DATE_MODIFIED);
        // Add required entity
        Chapter chapter;
        if (TestUtil.findAll(em, Chapter.class).isEmpty()) {
            chapter = ChapterResourceIT.createEntity(em);
            em.persist(chapter);
            em.flush();
        } else {
            chapter = TestUtil.findAll(em, Chapter.class).get(0);
        }
        book.getIsbns().add(chapter);
        // Add required entity
        Subject subject;
        if (TestUtil.findAll(em, Subject.class).isEmpty()) {
            subject = SubjectResourceIT.createEntity(em);
            em.persist(subject);
            em.flush();
        } else {
            subject = TestUtil.findAll(em, Subject.class).get(0);
        }
        book.setSubjectName(subject);
        return book;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Book createUpdatedEntity(EntityManager em) {
        Book book = new Book()
            .booktitle(UPDATED_BOOKTITLE)
            .text(UPDATED_TEXT)
            .isbn(UPDATED_ISBN)
            .publisher(UPDATED_PUBLISHER)
            .language(UPDATED_LANGUAGE)
            .pubyear(UPDATED_PUBYEAR)
            .pages(UPDATED_PAGES)
            .price(UPDATED_PRICE)
            .author(UPDATED_AUTHOR)
            .status(UPDATED_STATUS)
            .dateAdded(UPDATED_DATE_ADDED)
            .dateModified(UPDATED_DATE_MODIFIED);
        // Add required entity
        Chapter chapter;
        if (TestUtil.findAll(em, Chapter.class).isEmpty()) {
            chapter = ChapterResourceIT.createUpdatedEntity(em);
            em.persist(chapter);
            em.flush();
        } else {
            chapter = TestUtil.findAll(em, Chapter.class).get(0);
        }
        book.getIsbns().add(chapter);
        // Add required entity
        Subject subject;
        if (TestUtil.findAll(em, Subject.class).isEmpty()) {
            subject = SubjectResourceIT.createUpdatedEntity(em);
            em.persist(subject);
            em.flush();
        } else {
            subject = TestUtil.findAll(em, Subject.class).get(0);
        }
        book.setSubjectName(subject);
        return book;
    }

    @BeforeEach
    public void initTest() {
        book = createEntity(em);
    }

    @Test
    @Transactional
    public void createBook() throws Exception {
        int databaseSizeBeforeCreate = bookRepository.findAll().size();

        // Create the Book
        restBookMockMvc.perform(post("/api/books")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(book)))
            .andExpect(status().isCreated());

        // Validate the Book in the database
        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeCreate + 1);
        Book testBook = bookList.get(bookList.size() - 1);
        assertThat(testBook.getBooktitle()).isEqualTo(DEFAULT_BOOKTITLE);
        assertThat(testBook.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testBook.getIsbn()).isEqualTo(DEFAULT_ISBN);
        assertThat(testBook.getPublisher()).isEqualTo(DEFAULT_PUBLISHER);
        assertThat(testBook.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
        assertThat(testBook.getPubyear()).isEqualTo(DEFAULT_PUBYEAR);
        assertThat(testBook.getPages()).isEqualTo(DEFAULT_PAGES);
        assertThat(testBook.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testBook.getAuthor()).isEqualTo(DEFAULT_AUTHOR);
        assertThat(testBook.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBook.getDateAdded()).isEqualTo(DEFAULT_DATE_ADDED);
        assertThat(testBook.getDateModified()).isEqualTo(DEFAULT_DATE_MODIFIED);
    }

    @Test
    @Transactional
    public void createBookWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bookRepository.findAll().size();

        // Create the Book with an existing ID
        book.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBookMockMvc.perform(post("/api/books")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(book)))
            .andExpect(status().isBadRequest());

        // Validate the Book in the database
        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkBooktitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookRepository.findAll().size();
        // set the field null
        book.setBooktitle(null);

        // Create the Book, which fails.

        restBookMockMvc.perform(post("/api/books")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(book)))
            .andExpect(status().isBadRequest());

        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTextIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookRepository.findAll().size();
        // set the field null
        book.setText(null);

        // Create the Book, which fails.

        restBookMockMvc.perform(post("/api/books")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(book)))
            .andExpect(status().isBadRequest());

        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsbnIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookRepository.findAll().size();
        // set the field null
        book.setIsbn(null);

        // Create the Book, which fails.

        restBookMockMvc.perform(post("/api/books")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(book)))
            .andExpect(status().isBadRequest());

        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPublisherIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookRepository.findAll().size();
        // set the field null
        book.setPublisher(null);

        // Create the Book, which fails.

        restBookMockMvc.perform(post("/api/books")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(book)))
            .andExpect(status().isBadRequest());

        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLanguageIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookRepository.findAll().size();
        // set the field null
        book.setLanguage(null);

        // Create the Book, which fails.

        restBookMockMvc.perform(post("/api/books")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(book)))
            .andExpect(status().isBadRequest());

        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPubyearIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookRepository.findAll().size();
        // set the field null
        book.setPubyear(null);

        // Create the Book, which fails.

        restBookMockMvc.perform(post("/api/books")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(book)))
            .andExpect(status().isBadRequest());

        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPagesIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookRepository.findAll().size();
        // set the field null
        book.setPages(null);

        // Create the Book, which fails.

        restBookMockMvc.perform(post("/api/books")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(book)))
            .andExpect(status().isBadRequest());

        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookRepository.findAll().size();
        // set the field null
        book.setPrice(null);

        // Create the Book, which fails.

        restBookMockMvc.perform(post("/api/books")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(book)))
            .andExpect(status().isBadRequest());

        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAuthorIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookRepository.findAll().size();
        // set the field null
        book.setAuthor(null);

        // Create the Book, which fails.

        restBookMockMvc.perform(post("/api/books")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(book)))
            .andExpect(status().isBadRequest());

        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = bookRepository.findAll().size();
        // set the field null
        book.setStatus(null);

        // Create the Book, which fails.

        restBookMockMvc.perform(post("/api/books")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(book)))
            .andExpect(status().isBadRequest());

        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBooks() throws Exception {
        // Initialize the database
        bookRepository.saveAndFlush(book);

        // Get all the bookList
        restBookMockMvc.perform(get("/api/books?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(book.getId().intValue())))
            .andExpect(jsonPath("$.[*].booktitle").value(hasItem(DEFAULT_BOOKTITLE)))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT)))
            .andExpect(jsonPath("$.[*].isbn").value(hasItem(DEFAULT_ISBN)))
            .andExpect(jsonPath("$.[*].publisher").value(hasItem(DEFAULT_PUBLISHER)))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())))
            .andExpect(jsonPath("$.[*].pubyear").value(hasItem(DEFAULT_PUBYEAR)))
            .andExpect(jsonPath("$.[*].pages").value(hasItem(DEFAULT_PAGES)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE)))
            .andExpect(jsonPath("$.[*].author").value(hasItem(DEFAULT_AUTHOR)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].dateAdded").value(hasItem(DEFAULT_DATE_ADDED.toString())))
            .andExpect(jsonPath("$.[*].dateModified").value(hasItem(DEFAULT_DATE_MODIFIED.toString())));
    }
    
    @Test
    @Transactional
    public void getBook() throws Exception {
        // Initialize the database
        bookRepository.saveAndFlush(book);

        // Get the book
        restBookMockMvc.perform(get("/api/books/{id}", book.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(book.getId().intValue()))
            .andExpect(jsonPath("$.booktitle").value(DEFAULT_BOOKTITLE))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT))
            .andExpect(jsonPath("$.isbn").value(DEFAULT_ISBN))
            .andExpect(jsonPath("$.publisher").value(DEFAULT_PUBLISHER))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()))
            .andExpect(jsonPath("$.pubyear").value(DEFAULT_PUBYEAR))
            .andExpect(jsonPath("$.pages").value(DEFAULT_PAGES))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE))
            .andExpect(jsonPath("$.author").value(DEFAULT_AUTHOR))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.dateAdded").value(DEFAULT_DATE_ADDED.toString()))
            .andExpect(jsonPath("$.dateModified").value(DEFAULT_DATE_MODIFIED.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBook() throws Exception {
        // Get the book
        restBookMockMvc.perform(get("/api/books/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBook() throws Exception {
        // Initialize the database
        bookRepository.saveAndFlush(book);

        int databaseSizeBeforeUpdate = bookRepository.findAll().size();

        // Update the book
        Book updatedBook = bookRepository.findById(book.getId()).get();
        // Disconnect from session so that the updates on updatedBook are not directly saved in db
        em.detach(updatedBook);
        updatedBook
            .booktitle(UPDATED_BOOKTITLE)
            .text(UPDATED_TEXT)
            .isbn(UPDATED_ISBN)
            .publisher(UPDATED_PUBLISHER)
            .language(UPDATED_LANGUAGE)
            .pubyear(UPDATED_PUBYEAR)
            .pages(UPDATED_PAGES)
            .price(UPDATED_PRICE)
            .author(UPDATED_AUTHOR)
            .status(UPDATED_STATUS)
            .dateAdded(UPDATED_DATE_ADDED)
            .dateModified(UPDATED_DATE_MODIFIED);

        restBookMockMvc.perform(put("/api/books")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBook)))
            .andExpect(status().isOk());

        // Validate the Book in the database
        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeUpdate);
        Book testBook = bookList.get(bookList.size() - 1);
        assertThat(testBook.getBooktitle()).isEqualTo(UPDATED_BOOKTITLE);
        assertThat(testBook.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testBook.getIsbn()).isEqualTo(UPDATED_ISBN);
        assertThat(testBook.getPublisher()).isEqualTo(UPDATED_PUBLISHER);
        assertThat(testBook.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testBook.getPubyear()).isEqualTo(UPDATED_PUBYEAR);
        assertThat(testBook.getPages()).isEqualTo(UPDATED_PAGES);
        assertThat(testBook.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testBook.getAuthor()).isEqualTo(UPDATED_AUTHOR);
        assertThat(testBook.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBook.getDateAdded()).isEqualTo(UPDATED_DATE_ADDED);
        assertThat(testBook.getDateModified()).isEqualTo(UPDATED_DATE_MODIFIED);
    }

    @Test
    @Transactional
    public void updateNonExistingBook() throws Exception {
        int databaseSizeBeforeUpdate = bookRepository.findAll().size();

        // Create the Book

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBookMockMvc.perform(put("/api/books")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(book)))
            .andExpect(status().isBadRequest());

        // Validate the Book in the database
        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBook() throws Exception {
        // Initialize the database
        bookRepository.saveAndFlush(book);

        int databaseSizeBeforeDelete = bookRepository.findAll().size();

        // Delete the book
        restBookMockMvc.perform(delete("/api/books/{id}", book.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
