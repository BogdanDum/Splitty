/*
 * Copyright 2021 Delft University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package server.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import commons.Event;
import server.database.EventRepository;

public class TestEventRepository implements EventRepository {

    private final List<Event> events = new ArrayList<>();
    private final List<String> calledMethods = new ArrayList<>();

    /**
     * @return called methods
     */
    public List<String> getCalledMethods() {
        return calledMethods;
    }

    /**
     * @param name name of call
     */
    private void call(String name) {
        calledMethods.add(name);
    }

    /**
     * @return all Events
     */
    @Override
    public List<Event> findAll() {
        calledMethods.add("findAll");
        return events;
    }

    /**
     * @param sort sorting function
     * @return sorted list
     */
    @Override
    public List<Event> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param ids to find by
     * @return list
     */
    @Override
    public List<Event> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param entities to save
     * @param <S> clas
     * @return list of saved
     */
    @Override
    public <S extends Event> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * flush
     */
    @Override
    public void flush() {
        // TODO Auto-generated method stub

    }

    /**
     * @param entity entity
     * @param <S> class
     * @return saved entity
     */
    @Override
    public <S extends Event> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param entities to save
     * @param <S> class
     * @return list
     */
    @Override
    public <S extends Event> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param entities to delete
     */
    @Override
    public void deleteAllInBatch(Iterable<Event> entities) {
        // TODO Auto-generated method stub

    }

    /**
     * @param ids to delete by
     */
    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        // TODO Auto-generated method stub

    }

    /**
     * delete
     */
    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub

    }

    /**
     * @param id id
     * @return Event
     */
    @Override
    public Event getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param id id
     * @return Event
     */
    @Override
    public Event getById(Long id) {
        call("getById");
        return find(id).get();
    }

    /**
     * @param id id
     * @return Event
     */
    @Override
    public Event getReferenceById(Long id) {
        call("getReferenceById");
        return find(id).get();
    }

    /**
     * @param id id
     * @return Event
     */
    private Optional<Event> find(Long id) {
        return events.stream().filter(q -> q.getId() == id).findFirst();
    }

    /**
     * @param example entity
     * @param <S> class
     * @return list
     */
    @Override
    public <S extends Event> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param example entity
     * @param sort sort
     * @param <S> class
     * @return list of Events
     */
    @Override
    public <S extends Event> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param pageable p
     * @return page
     */
    @Override
    public Page<Event> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Saves an entity to the database
     *
     * @param entity to save
     * @param <S> class of entity
     * @return saved entity
     */
    @Override
    public <S extends Event> S save(S entity) {
        call("save");
        entity.setId(events.size());
        events.add(entity);
        return entity;
    }

    /**
     * @param id id
     * @return Event
     */
    @Override
    public Optional<Event> findById(Long id) {
        return events.stream().filter(e -> e.getId() == id).findAny();
    }

    /**
     * @param id to search
     * @return true if present
     */
    @Override
    public boolean existsById(Long id) {
        call("existsById");
        return find(id).isPresent();
    }

    /**
     * @return the amount of Events
     */
    @Override
    public long count() {
        return events.size();
    }

    /**
     * @param id to delete by
     */
    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub

    }

    /**
     * @param entity to delete
     */
    @Override
    public void delete(Event entity) {
        // TODO Auto-generated method stub

    }

    /**
     * @param ids to delete by
     */
    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub

    }

    /**
     * @param entities to delete
     */
    @Override
    public void deleteAll(Iterable<? extends Event> entities) {
        // TODO Auto-generated method stub

    }

    /**
     * delete everything
     */
    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub

    }

    /**
     * @param example entity
     * @param <S> class
     * @return Event
     */
    @Override
    public <S extends Event> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param example entity
     * @param pageable p
     * @param <S> class
     * @return page
     */
    @Override
    public <S extends Event> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param example entity
     * @param <S> class
     * @return count
     */
    @Override
    public <S extends Event> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * @param example entity
     * @param <S> class
     * @return true iff exists
     */
    @Override
    public <S extends Event> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @param example entity
     * @param queryFunction function
     * @param <S> class
     * @param <R> a
     * @return a
     */
    @Override
    public <S extends Event, R> R findBy(Example<S> example,
                                         Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }
}