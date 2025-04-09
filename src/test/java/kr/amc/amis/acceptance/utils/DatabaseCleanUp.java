package kr.amc.amis.acceptance.utils;

import groovy.util.logging.Slf4j;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Profile("test")
@Component
@Slf4j
public class DatabaseCleanUp implements InitializingBean {

    @PersistenceContext
    private EntityManager em;
    private List<String> tableNames;
    private List<String> notGeneratedIdTableNames;


    @Override
    public void afterPropertiesSet() throws Exception {
        tableNames = em.getMetamodel().getEntities().stream()
                .filter(entityType -> entityType.getJavaType().getAnnotation(Entity.class) != null)
                .map(entityType -> entityType.getJavaType().getAnnotation(Table.class).name())
                .toList();

        notGeneratedIdTableNames = List.of("community_user_auth", "community_user_relation", "community_like");
    }

    @Transactional
    public void execute() {
        em.flush();
        em.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        for (String tableName : tableNames) {
            em.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            if (!notGeneratedIdTableNames.contains(tableName)) {
                em.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
            }
        }
        em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}
