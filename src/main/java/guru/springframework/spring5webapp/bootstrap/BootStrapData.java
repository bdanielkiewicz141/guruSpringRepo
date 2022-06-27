package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        Book wiedzmin = new Book("Ostatnie Życzenie", "11111");
        Book ddd = new Book("Domain Driven Design", "123123");
        Author eric = new Author("Eric", "Evans");
        Author sapkowski = new Author("Andrzej", "Sapkowski");

        Publisher publisher = new Publisher();
        publisher.setName("SuperNowa");
        publisher.setCity("Warszawa");
        publisher.setState("Mazowsze");
        publisher.setZip("00-000");


        Publisher andrzej = new Publisher("andrzej", "lotnicza 4", "20-000", "Lubelski");
        publisherRepository.save(andrzej);


        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        sapkowski.getBooks().add(wiedzmin);
        wiedzmin.getAuthors().add(sapkowski);


        authorRepository.save(eric);
        bookRepository.save(ddd);
        authorRepository.save(eric);
        bookRepository.save(ddd);
        authorRepository.save(sapkowski);
        bookRepository.save(wiedzmin);



        System.out.println("startet in bootstrap");
        System.out.println("Number of books " + bookRepository.count());

        publisher.getBooks().add(wiedzmin);
        publisher.getBooks().add(ddd);

publisherRepository.save(publisher);
ddd.setPublisher(publisher);
wiedzmin.setPublisher(publisher);
bookRepository.save(ddd);
bookRepository.save(wiedzmin);



        System.out.println("liczba publisherów: " + publisherRepository.count());
        System.out.println("liczba ksiazek wydawcy: "+ publisher.getBooks().stream().count());

    }
}


