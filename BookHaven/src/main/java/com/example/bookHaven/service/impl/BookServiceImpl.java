package com.example.bookHaven.service.impl;

//@Service
// implements BookService
public class BookServiceImpl {

//    private final BookRepository bookRepository;
//    private final ModelMapper modelMapper;
//
//    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
//        this.bookRepository = bookRepository;
//        this.modelMapper = modelMapper;
//    }
//
//    @Override
//    public List<Book> getAllBooks() {
//        return bookRepository.findAll();
//    }
//
//    @Override
//    public Book getBookById(Long id) {
//        return bookRepository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("Book not found"));
//    }
//
//    @Override
//    public Book saveBook(BookDto bookDto) {
//        Book book = modelMapper.map(bookDto, Book.class);
//        return bookRepository.save(book);
//    }
//
//    @Override
//    public void updateBook(BookDto bookDto) {
//        Optional<Book> optionalBook = bookRepository.findById(bookDto.getBookId());
//        if (optionalBook.isPresent()) {
//            Book book = modelMapper.map(bookDto, Book.class);
//            bookRepository.save(book);
//        } else {
//            new NoSuchElementException("Book not found");
//        }
//    }
//
//    public void deleteBook(Long id) {
//        bookRepository.deleteById(id);
//    }
}

