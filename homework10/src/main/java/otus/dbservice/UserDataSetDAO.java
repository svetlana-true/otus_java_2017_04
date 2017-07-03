package otus.dbservice;

import org.hibernate.Session;

import java.util.List;

/**
 * Created by Светлана on 02.07.2017.
 */
import org.hibernate.query.Query;
import otus.base.UserDataSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserDataSetDAO {
        private Session session;

        public UserDataSetDAO(Session session) {
            this.session = session;
        }

        public void save(UserDataSet dataSet) {
            session.save(dataSet);
        }

        public UserDataSet read(long id) {
            return session.load(UserDataSet.class, id);
        }

        public UserDataSet readByName(String name) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
            Root<UserDataSet> from = criteria.from(UserDataSet.class);
            criteria.where(builder.equal(from.get("name"), name));
            Query<UserDataSet> query = session.createQuery(criteria);
            return query.uniqueResult();
        }

        public List<UserDataSet> readAll() {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
            criteria.from(UserDataSet.class);
            return session.createQuery(criteria).list();
        }
    }