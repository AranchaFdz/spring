package org.factoriaf5.techcamps.exceptions.country;

public class CountryException extends RuntimeException {

    public CountryException(String message) {
        super(message);
    }

    public CountryException(String message, Throwable cause) {
        super(message, cause);
    }

    /*
     * @Service
     * public class CountryService {
     * 
     * private final CountryRepository repository;
     * 
     * public CountryService(CountryRepository repository) {
     * this.repository = repository;
     * }
     * 
     * public Country getById(Long id) {
     * return repository.findById(id)
     * .orElseThrow(() -> new CountryNotFoundException("Country not found by id: " +
     * id));
     * }
     * 
     * public Country save(Country country) {
     * try {
     * return repository.save(country);
     * } catch (Exception e) {
     * throw new CountryException("Error saving country", e); // e es un objeto de tipo exception que almacena información sobre el error.
     * }
     * }
     * }
     */

}
