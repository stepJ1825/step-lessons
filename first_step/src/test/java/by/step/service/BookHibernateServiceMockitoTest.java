package by.step.service;

import by.step.entity.Author;
import by.step.entity.Book;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaFunction;
import org.hibernate.query.criteria.JpaPredicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookHibernateServiceMockitoTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private BookHibernateService service;

    @Test
    void findBooksByTitleWithHql_returnsQueryResult() {
        Session session = mock(Session.class);
        @SuppressWarnings("unchecked")
        Query<Book> query = mock(Query.class);

        when(entityManager.unwrap(Session.class)).thenReturn(session);
        when(session.createQuery("FROM Book WHERE title LIKE :title", Book.class)).thenReturn(query);
        when(query.setParameter(eq("title"), eq("%Ocean%"))).thenReturn(query);

        List<Book> expected = List.of(Book.builder().id(1).title("The Silent Ocean").build());
        when(query.list()).thenReturn(expected);

        List<Book> actual = service.findBooksByTitleWithHQL("%Ocean%");

        Assertions.assertEquals(expected, actual);
        verify(query).setParameter("title", "%Ocean%");
    }

    @Test
    void findBooksByTitleWithNative_returnsQueryResult() {
        Session session = mock(Session.class);
        @SuppressWarnings("unchecked")
        Query query = mock(NativeQuery.class);

        when(entityManager.unwrap(Session.class)).thenReturn(session);
        when(session.createNativeQuery(
                "SELECT * FROM books WHERE title LIKE :title",
                Book.class
        )).thenReturn((NativeQuery<Book>) query);
        when(query.setParameter(eq("title"), eq("%Ocean%"))).thenReturn(query);

        List<Book> expected = List.of(Book.builder().id(2).title("Eternal Dreams").build());
        when(query.getResultList()).thenReturn(expected);

        List<Book> actual = service.findBooksByTitleWithNative("%Ocean%");

        Assertions.assertEquals(expected, actual);
        verify(query).getResultList();
    }

    @Test
    void findBooksByTitleWithNamed_returnsQueryResult() {
        Session session = mock(Session.class);
        @SuppressWarnings("unchecked")
        Query query = mock(Query.class);

        when(entityManager.unwrap(Session.class)).thenReturn(session);
        when(session.getNamedQuery("Book.findByTitle")).thenReturn(query);
        when(query.setParameter(eq("title"), eq("The Silent Ocean"))).thenReturn(query);

        List<Book> expected = List.of(Book.builder().id(1).title("The Silent Ocean").build());
        when(query.list()).thenReturn(expected);

        List<Book> actual = service.findBooksByTitleWithNamed("The Silent Ocean");

        Assertions.assertEquals(expected, actual);
        verify(query).list();
    }

    @Test
    void searchBooks_allNullParams_returnsEntityManagerResult() {
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        CriteriaQuery<Book> cq = mock(CriteriaQuery.class);
        Root<Book> root = mock(Root.class);
        @SuppressWarnings("unchecked")
        TypedQuery<Book> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(cb);
        when(cb.createQuery(Book.class)).thenReturn(cq);
        when(cq.from(Book.class)).thenReturn(root);
        when(entityManager.createQuery(cq)).thenReturn(typedQuery);
        when(typedQuery.setMaxResults(anyInt())).thenReturn(typedQuery);

        List<Book> expected = List.of(Book.builder().id(1).title("X").build());
        when(typedQuery.getResultList()).thenReturn(expected);

        List<Book> actual = service.searchBooks(null, null, null, null, null, null);

        Assertions.assertEquals(expected, actual);
        verify(entityManager).createQuery(cq);
    }

    //    @Test
    //    void searchBooks_whenFiltersProvided_appliesPredicatesAndReturnsResult() {
    //        CriteriaBuilder cb = mock(CriteriaBuilder.class);
    //        CriteriaQuery<Book> cq = mock(CriteriaQuery.class);
    //        Root<Book> root = mock(Root.class);
    //
    //        Path<Object> authorPath = mock(Path.class);
    //        Path<Object> authorIdPath = mock(Path.class);
    //        Path<Object> genrePath = mock(Path.class);
    //        Path<Object> genreIdPath = mock(Path.class);
    //
    //        Path<Object> titlePath = mock(Path.class);
    //        Path<Object> releaseYearPath = mock(Path.class);
    //        Path<Object> ratingPath = mock(Path.class);
    //
    //        Predicate p1 = mock(Predicate.class);
    //        Predicate p2 = mock(Predicate.class);
    //        Predicate p3 = mock(Predicate.class);
    //        Predicate p4 = mock(Predicate.class);
    //        Predicate p5 = mock(Predicate.class);
    //
    //        @SuppressWarnings("unchecked")
    //        TypedQuery<Book> typedQuery = mock(TypedQuery.class);
    //
    //        when(entityManager.getCriteriaBuilder()).thenReturn(cb);
    //        when(cb.createQuery(Book.class)).thenReturn(cq);
    //        when(cq.from(Book.class)).thenReturn(root);
    //
    //        when(root.get("title")).thenReturn(titlePath);
    //        when(cb.lower(titlePath)).thenReturn(mock(Path.class));
    //        when(cb.like(any(), anyString())).thenReturn(p1);
    //
    //        when(root.get("author")).thenReturn(authorPath);
    //        when(authorPath.get("id")).thenReturn(authorIdPath);
    //        when(cb.equal(authorIdPath, 102)).thenReturn(p2);
    //
    //        when(root.get("genre")).thenReturn(genrePath);
    //        when(genrePath.get("id")).thenReturn(genreIdPath);
    //        when(cb.equal(genreIdPath, 2)).thenReturn(p3);
    //
    //        when(root.get("releaseYear")).thenReturn(releaseYearPath);
    //        when(cb.greaterThanOrEqualTo(releaseYearPath, 2018)).thenReturn(p4);
    //        when(cb.lessThanOrEqualTo(releaseYearPath, 2021)).thenReturn(p5);
    //
    //        when(root.get("rating")).thenReturn(ratingPath);
    //        when(cb.ge(ratingPath, 4.3f)).thenReturn(mock(Predicate.class));
    //
    //        when(entityManager.createQuery(cq)).thenReturn(typedQuery);
    //        when(typedQuery.setMaxResults(anyInt())).thenReturn(typedQuery);
    //
    //        List<Book> expected = List.of(Book.builder().id(1).title("Winter").build());
    //        when(typedQuery.getResultList()).thenReturn(expected);
    //
    //        List<Book> actual = service.searchBooks("Winter", 102, 2, 2018, 2021, 4.3f);
    //
    //        Assertions.assertEquals(expected, actual);
    //        verify(cb).equal(authorIdPath, 102);
    //        verify(cb).equal(genreIdPath, 2);
    //        verify(cb).greaterThanOrEqualTo(releaseYearPath, 2018);
    //        verify(cb).lessThanOrEqualTo(releaseYearPath, 2021);
    //    }
    //
    //    @Test
    //    void getAverageRatingByAuthor_returnsAggregatedMap() {
    //        CriteriaBuilder cb = mock(CriteriaBuilder.class);
    //        CriteriaQuery<Object[]> cq = mock(CriteriaQuery.class);
    //        Root<Book> root = mock(Root.class);
    //        Path<Object> authorPath = mock(Path.class);
    //        Path<Object> ratingPath = mock(Path.class);
    //
    //        @SuppressWarnings("unchecked")
    //        TypedQuery<Object[]> typedQuery = mock(TypedQuery.class);
    //
    //        when(entityManager.getCriteriaBuilder()).thenReturn(cb);
    //        when(cb.createQuery(Object[].class)).thenReturn(cq);
    //        when(cq.from(Book.class)).thenReturn(root);
    //
    //        when(root.get("author")).thenReturn(authorPath);
    //        when(root.get("rating")).thenReturn(ratingPath);
    //
    //        when(cq.multiselect(any(), any())).thenReturn(cq);
    //        when(cq.groupBy(any())).thenReturn(cq);
    //        when(cq.having(any())).thenReturn(cq);
    //
    //        List<Object[]> results = List.of(new Object[]{
    //                Author.builder().id(102).firstName("Michael").surname("Brown").build(),
    //                4.0d
    //        });
    //        when(entityManager.createQuery(cq)).thenReturn(typedQuery);
    //        when(typedQuery.getResultList()).thenReturn(results);
    //
    //        Map<Author, Double> map = service.getAverageRatingByAuthor();
    //
    //        Assertions.assertEquals(1, map.size());
    //        Assertions.assertEquals(4.0d, map.values().iterator().next());
    //    }
    //
    //    @Test
    //    void findWithGraph_loadsResultUsingFetchGraphHint() {
    //        @SuppressWarnings("unchecked")
    //        EntityGraph<Book> graph = mock(EntityGraph.class);
    //        @SuppressWarnings("unchecked")
    //        TypedQuery<Book> typedQuery = mock(TypedQuery.class);
    //
    //        when(entityManager.createEntityGraph(Book.class)).thenReturn(graph);
    //        when(entityManager.createQuery("SELECT b FROM Book b WHERE b.id = :id", Book.class)).thenReturn(typedQuery);
    //        when(typedQuery.setParameter(eq("id"), eq(1))).thenReturn(typedQuery);
    //        when(typedQuery.setHint(eq("javax.persistence.fetchgraph"), any())).thenReturn(typedQuery);
    //        List<Book> expected = List.of(Book.builder().id(1).title("T").build());
    //        when(typedQuery.getResultList()).thenReturn(expected);
    //
    //        List<Book> actual = service.findWithGraph(1);
    //
    //        Assertions.assertEquals(expected, actual);
    //        verify(typedQuery).setParameter("id", 1);
    //    }
    //
//    @Test
//    @SuppressWarnings({"unchecked", "rawtypes"})
//    void searchWithHibernateCriteria_matchesTitleOrAuthorSurname() {
//        Session session = mock(Session.class);
//        CriteriaBuilder cb = mock(CriteriaBuilder.class); // Стандартный интерфейс
//        CriteriaQuery<Book> cq = mock(CriteriaQuery.class);
//        Root<Book> root = mock(Root.class);
//        jakarta.persistence.Query query = mock(jakarta.persistence.Query.class);
//
//        Join authorJoin = mock(Join.class);
//        Path titlePath = mock(Path.class);
//        Path surnamePath = mock(Path.class);
//
//        Predicate titlePredicate = mock(Predicate.class);
//        Predicate surnamePredicate = mock(Predicate.class);
//        Predicate orPredicate = mock(Predicate.class);
//
//        Expression lowerTitle = mock(Expression.class);
//        Expression lowerSurname = mock(Expression.class);
//
//        when(entityManager.unwrap(Session.class)).thenReturn(session);
//        when(session.getCriteriaBuilder()).thenReturn((HibernateCriteriaBuilder) cb); // Без приведения
//        when(cb.createQuery(Book.class)).thenReturn(cq);
//        when(cq.from(Book.class)).thenReturn(root);
//
//        when(root.get("title")).thenReturn(titlePath);
//        when(root.join("author")).thenReturn(authorJoin);
//        when(authorJoin.get("surname")).thenReturn(surnamePath);
//
//        when(cb.lower(titlePath)).thenReturn(lowerTitle);
//        when(cb.lower(surnamePath)).thenReturn(lowerSurname);
//        when(cb.like(lowerTitle, "%ocean%")).thenReturn(titlePredicate);
//        when(cb.like(lowerSurname, "%ocean%")).thenReturn(surnamePredicate);
//        when(cb.or(titlePredicate, surnamePredicate)).thenReturn(orPredicate);
//
//        cq.where(orPredicate);
//
//        when(session.createQuery(cq)).thenReturn((Query<Book>) query);
//        List<Book> expected = List.of(Book.builder().id(1).title("Ocean").build());
//        when(query.getResultList()).thenReturn(expected);
//
//        List<Book> actual = service.searchWithHibernateCriteria("Ocean");
//
//        Assertions.assertEquals(expected, actual);
//        verify(session).createQuery(cq);
//    }

    @Test
    void searchAllBooksNew_throwsRuntimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> service.searchAllBooksNew());
    }
}

