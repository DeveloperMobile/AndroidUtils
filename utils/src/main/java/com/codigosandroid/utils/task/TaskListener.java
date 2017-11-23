package com.codigosandroid.utils.task;

/**
 * Created by Tiago on 23/11/2017.
 */

public interface TaskListener<T> {

    // Executa em background e retorna o objeto
    T execute() throws Exception;

    // Atualiza a view na UI Thread
    void updateView(T response);

    // Chamado caso o método execute() lance uma exception
    void onError(Exception exception);

    // Chamado caso a task tenha sido cancelada
    void onCancelled(String cod);

}
