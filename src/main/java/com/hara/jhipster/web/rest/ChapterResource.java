package com.hara.jhipster.web.rest;

import com.hara.jhipster.domain.Chapter;
import com.hara.jhipster.repository.ChapterRepository;
import com.hara.jhipster.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.hara.jhipster.domain.Chapter}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ChapterResource {

    private final Logger log = LoggerFactory.getLogger(ChapterResource.class);

    private static final String ENTITY_NAME = "chapter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChapterRepository chapterRepository;

    public ChapterResource(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    /**
     * {@code POST  /chapters} : Create a new chapter.
     *
     * @param chapter the chapter to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chapter, or with status {@code 400 (Bad Request)} if the chapter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/chapters")
    public ResponseEntity<Chapter> createChapter(@Valid @RequestBody Chapter chapter) throws URISyntaxException {
        log.debug("REST request to save Chapter : {}", chapter);
        if (chapter.getId() != null) {
            throw new BadRequestAlertException("A new chapter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Chapter result = chapterRepository.save(chapter);
        return ResponseEntity.created(new URI("/api/chapters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /chapters} : Updates an existing chapter.
     *
     * @param chapter the chapter to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chapter,
     * or with status {@code 400 (Bad Request)} if the chapter is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chapter couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/chapters")
    public ResponseEntity<Chapter> updateChapter(@Valid @RequestBody Chapter chapter) throws URISyntaxException {
        log.debug("REST request to update Chapter : {}", chapter);
        if (chapter.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Chapter result = chapterRepository.save(chapter);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chapter.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /chapters} : get all the chapters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chapters in body.
     */
    @GetMapping("/chapters")
    public List<Chapter> getAllChapters() {
        log.debug("REST request to get all Chapters");
        return chapterRepository.findAll();
    }

    /**
     * {@code GET  /chapters/:id} : get the "id" chapter.
     *
     * @param id the id of the chapter to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chapter, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/chapters/{id}")
    public ResponseEntity<Chapter> getChapter(@PathVariable Long id) {
        log.debug("REST request to get Chapter : {}", id);
        Optional<Chapter> chapter = chapterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(chapter);
    }

    /**
     * {@code DELETE  /chapters/:id} : delete the "id" chapter.
     *
     * @param id the id of the chapter to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/chapters/{id}")
    public ResponseEntity<Void> deleteChapter(@PathVariable Long id) {
        log.debug("REST request to delete Chapter : {}", id);
        chapterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
