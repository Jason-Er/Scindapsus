package com.example.scindapsus.model;

import com.example.scindapsus.model.adapter.UserAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ej on 2/24/2017.
 */

public class UserModelTest {

    @Test
    public void testUserToJson() {
        User user = User.newInstance(0, "Jason", "123456");

        String json = new Gson().toJson(user);
        System.out.println(json);

        Assert.assertEquals("{\"_id\":0,\"name\":\"Jason\",\"password\":\"123456\"}", json);
    }

    @Test
    public void testUserParseFromJson() {
        String json = "{\"_id\":0,\"name\":\"Jason\",\"password\":\"123456\"}";

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(UserAdapterFactory.create()).create();

        User user = gson.fromJson(json, User.class);
        System.out.println(user);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.password(), "123456");
        Assert.assertEquals(user.name(), "Jason");
        Assert.assertEquals(user._id(), 0);

    }

}
