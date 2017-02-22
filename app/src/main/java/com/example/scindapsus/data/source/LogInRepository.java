package com.example.scindapsus.data.source;

/**
 * Created by ej on 2/22/2017.
 */

public class LoginRepository {

    private static LoginRepository INSTANCE = null;

    public static LoginRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoginRepository();
        }
        return INSTANCE;
    }

}
