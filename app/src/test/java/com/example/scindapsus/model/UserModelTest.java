package com.example.scindapsus.model;

import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ej on 2/24/2017.
 */

public class UserModelTest {

    @Test
    public void testUserToJson() {
        User user = User.newInstance(0, "jason", "123456");

        String json = new Gson().toJson(user);
        System.out.println(json);

        Assert.assertEquals("{\"_id\":0,\"name\":\"jason\",\"password\":\"123456\"}", json);
    }
}
