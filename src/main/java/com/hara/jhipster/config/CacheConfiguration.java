package com.hara.jhipster.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.hara.jhipster.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.hara.jhipster.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.hara.jhipster.domain.User.class.getName());
            createCache(cm, com.hara.jhipster.domain.Authority.class.getName());
            createCache(cm, com.hara.jhipster.domain.User.class.getName() + ".authorities");
            createCache(cm, com.hara.jhipster.domain.Book.class.getName());
            createCache(cm, com.hara.jhipster.domain.Book.class.getName() + ".isbns");
            createCache(cm, com.hara.jhipster.domain.Chapter.class.getName());
            createCache(cm, com.hara.jhipster.domain.Category.class.getName());
            createCache(cm, com.hara.jhipster.domain.Category.class.getName() + ".categoryNames");
            createCache(cm, com.hara.jhipster.domain.Subject.class.getName());
            createCache(cm, com.hara.jhipster.domain.Subject.class.getName() + ".subjectNames");
            createCache(cm, com.hara.jhipster.domain.ContactUs.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

}
