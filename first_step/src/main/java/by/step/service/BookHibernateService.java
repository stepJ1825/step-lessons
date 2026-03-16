package by.step.service;

import by.step.entity.Author;
import by.step.entity.Book;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookHibernateService {
    private final EntityManager entityManager;

    public List<Book> findBooksByTitleWithHQL(@Param("title") String title) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "FROM Book WHERE title LIKE :title";
        Query<Book> query = session.createQuery(hql, Book.class);
        query.setParameter("title", title);
        return query.list();
    }

    public List<Book> findBooksByTitleWithNative(@Param("title") String title) {
        Session session = entityManager.unwrap(Session.class);
        String sql = "SELECT * FROM books WHERE title LIKE :title";
        Query query = session.createNativeQuery(sql, Book.class);
        query.setParameter("title", title);
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> findBooksByTitleWithNamed(@Param("title") String title) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.getNamedQuery("Book.findByTitle");
        query.setParameter("title", title);
        List<Book> books = query.list();
        return books;
    }

    // Поиск по нескольким необязательным параметрам
    public List<Book> searchBooks(
            String title, Integer authorId, Integer genreId,
            Integer yearFrom, Integer yearTo, Float minRating
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class); // SELECT *
        Root<Book> book = cq.from(Book.class);              // FROM books

        List<Predicate> predicates = new ArrayList<>();

        if (title != null && !title.isBlank()) {
            predicates.add(cb.like(
                    cb.lower(book.get("title")),
                    "%" + title.toLowerCase() + "%"
            ));
        }
        if (authorId != null) {
            predicates.add(cb.equal(book.get("author").get("id"), authorId));
        }
        if (genreId != null) {
            predicates.add(cb.equal(book.get("genre").get("id"), genreId));
        }
        if (yearFrom != null) {
            predicates.add(cb.greaterThanOrEqualTo(book.get("releaseYear"), yearFrom));
        }
        if (yearTo != null) {
            predicates.add(cb.lessThanOrEqualTo(book.get("releaseYear"), yearTo));
        }
        if (minRating != null) {
            predicates.add(cb.ge(book.get("rating"), minRating));
        }

        cq.where(predicates.toArray(new Predicate[0]));  // WHERE ... AND ... ;
        cq.orderBy(cb.desc(book.get("rating")), cb.asc(book.get("title"))); // ORDER BY rating DESC, title ASC

        return entityManager.createQuery(cq)
                            .setMaxResults(50)
                            .getResultList();
    }

    // Агрегация: средняя оценка по автору
    public Map<Author, Double> getAverageRatingByAuthor() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Book> book = cq.from(Book.class);

        cq.multiselect(
                  book.get("author"),
                  cb.avg(book.get("rating"))
          )
          .groupBy(book.get("author"))
          .having(cb.ge(cb.avg(book.get("rating")), 4.0));

        List<Object[]> results = entityManager.createQuery(cq).getResultList();
        return results.stream()
                      .collect(Collectors.toMap(
                              r -> (Author) r[0],
                              r -> ((Number) r[1]).doubleValue()
                      ));
    }

    // Или программно:
    public List<Book> findWithGraph(Integer id) {
        EntityGraph<Book> graph = entityManager.createEntityGraph(Book.class);
        graph.addAttributeNodes("author", "genre");

        return entityManager.createQuery("SELECT b FROM Book b WHERE b.id = :id", Book.class)
                            .setParameter("id", id)
                            .setHint("javax.persistence.fetchgraph", graph)
                            .getResultList();
    }

    public List<Book> searchWithHibernateCriteria(String keyword) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> root = cq.from(Book.class);

        // Полнотекстовый поиск (если подключен hibernate-search)
        // Или простые условия:
        Predicate titleMatch = cb.like(cb.lower(root.get("title")), "%" + keyword.toLowerCase() + "%");
        Predicate authorMatch =
                cb.like(cb.lower(root.join("author").get("surname")), "%" + keyword.toLowerCase() + "%");

        cq.where(cb.or(titleMatch, authorMatch));
        return session.createQuery(cq).getResultList();
    }

    public List<Book> searchAllBooksNew() {
        throw new RuntimeException("NOT IMPLEMENTED YET");
    }
}