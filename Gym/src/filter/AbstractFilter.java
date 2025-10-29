package filter;

import domain.Client;

public interface AbstractFilter <T>{
    boolean accept(T entity);
}
