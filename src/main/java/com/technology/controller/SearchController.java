package com.technology.controller;

import com.technology.model.Employee;
import com.technology.model.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Slf4j
@Controller
public class SearchController {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("search") String search, ModelMap model) {
        try {
            rebuildIndexes();
            List<Employee> employees = search(search, Employee.class, "firstName", "lastName");
            List<User> users = search("admin", User.class, "username");

            model.put("employees", employees);
            model.put("result1", users);
        } catch (InterruptedException e) {
            log.error("InterruptedException", e);
        }

        return "index";
    }

    private void rebuildIndexes() throws InterruptedException {
        EntityManager em = entityManagerFactory.createEntityManager();
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
        fullTextEntityManager.createIndexer().startAndWait();
        fullTextEntityManager.close();
    }

    private <T> List<T> search(String word, Class<T> distributor, String... fields) throws InterruptedException {
        EntityManager em = entityManagerFactory.createEntityManager();
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
        em.getTransaction().begin();

        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(distributor).get();

        org.apache.lucene.search.Query luceneQuery = qb
                .keyword()
                .onFields(fields)
                .matching(word)
                .createQuery();

        javax.persistence.Query jpaQuery =
                fullTextEntityManager.createFullTextQuery(luceneQuery, distributor);

        List<T> result = jpaQuery.getResultList();

        em.getTransaction().commit();
        em.close();

        return result;
    }
}