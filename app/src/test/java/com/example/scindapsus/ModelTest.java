package com.example.scindapsus;

import com.example.scindapsus.model.Status;
import com.example.scindapsus.model.User;
import com.example.scindapsus.model.adapter.StatusAdapterFactory;
import com.example.scindapsus.model.adapter.UserAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ej on 2/24/2017.
 */

public class ModelTest {

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

    @Test
    public void testStatusToJson() {
        Status status = Status.newInstance(0, "Success");

        String json = new Gson().toJson(status);
        System.out.println(json);

        Assert.assertEquals("{\"code\":0,\"message\":\"Success\"}", json);
    }

    @Test
    public void testStatusParseFromJson() {
        String json = "{\"code\":0,\"message\":\"Success\"}";

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(StatusAdapterFactory.create()).create();

        Status status = gson.fromJson(json, Status.class);
        System.out.println(status);
        Assert.assertNotNull(status);
        Assert.assertEquals(status.code(), 0);
        Assert.assertEquals(status.message(), "Success");

    }

}
