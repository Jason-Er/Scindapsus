package com.example.scindapsus.service.login;


import com.example.scindapsus.model.LoginRTN;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by ej on 2/28/2017.
 */

public interface LoginService {
    Observable<LoginRTN> login(String name, String password);
}
