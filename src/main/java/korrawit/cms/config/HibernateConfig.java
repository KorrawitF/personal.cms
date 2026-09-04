package korrawit.cms.config;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import korrawit.cms.infrastructure.persistence.model.Experiences;
import korrawit.cms.infrastructure.persistence.model.Project;
import korrawit.cms.infrastructure.persistence.model.SkillDomains;
import korrawit.cms.infrastructure.persistence.model.Skills;

@Configuration
public class HibernateConfig {

    @Bean(destroyMethod = "close")
    SessionFactory sessionFactory(DataSource dataSource) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySetting(AvailableSettings.JAKARTA_NON_JTA_DATASOURCE, dataSource)
                .applySetting(AvailableSettings.HBM2DDL_AUTO, "update")
                .applySetting(AvailableSettings.SHOW_SQL, "true")
                .applySetting(AvailableSettings.FORMAT_SQL, "true")
                .build();

        try {
            MetadataSources sources = new MetadataSources(registry)
                    .addAnnotatedClass(SkillDomains.class)
                    .addAnnotatedClass(Skills.class)
                    .addAnnotatedClass(Experiences.class)
                    .addAnnotatedClass(Project.class);

            return sources.buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }
    }
}
