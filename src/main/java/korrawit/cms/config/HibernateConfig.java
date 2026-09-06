package korrawit.cms.config;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import korrawit.cms.infrastructure.persistence.model.ContactMethod;
import korrawit.cms.infrastructure.persistence.model.Content;
import korrawit.cms.infrastructure.persistence.model.Experiences;
import korrawit.cms.infrastructure.persistence.model.FormFields;
import korrawit.cms.infrastructure.persistence.model.Forms;
import korrawit.cms.infrastructure.persistence.model.MailTransaction;
import korrawit.cms.infrastructure.persistence.model.Project;
import korrawit.cms.infrastructure.persistence.model.SkillDomains;
import korrawit.cms.infrastructure.persistence.model.Skills;
import korrawit.cms.infrastructure.persistence.model.WorkExperiences;

@Configuration
public class HibernateConfig {

    @Bean(destroyMethod = "close")
    SessionFactory sessionFactory(DataSource dataSource) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySetting(AvailableSettings.JAKARTA_NON_JTA_DATASOURCE, dataSource)
                .applySetting(AvailableSettings.HBM2DDL_AUTO, "update")
                .applySetting(AvailableSettings.SHOW_SQL, "true")
                .applySetting(AvailableSettings.FORMAT_SQL, "true")
                .applySetting(AvailableSettings.JSON_FORMAT_MAPPER, new Jackson3JsonFormatMapper())
                .build();

        try {
            MetadataSources sources = new MetadataSources(registry)
                    .addAnnotatedClass(SkillDomains.class)
                    .addAnnotatedClass(Skills.class)
                    .addAnnotatedClass(Experiences.class)
                    .addAnnotatedClass(Project.class)
                    .addAnnotatedClass(WorkExperiences.class)
                    .addAnnotatedClass(Content.class)
                    .addAnnotatedClass(ContactMethod.class)
                    .addAnnotatedClass(Forms.class)
                    .addAnnotatedClass(FormFields.class)
                    .addAnnotatedClass(MailTransaction.class);

            return sources.buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }
    }
}
