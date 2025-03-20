package org.factoriaf5.techcamps.implementations;

import java.util.List;

public interface IGenericService<T, S> {

    public List<T> findAll();
    public T save(S dto);
    public T getById(Long id);

    // https://www.digitalocean.com/community/tutorials/java-generics-example-method-class-interface

}
