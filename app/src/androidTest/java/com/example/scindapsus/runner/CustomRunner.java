package com.example.scindapsus.runner;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

@RunWith(android.support.test.runner.AndroidJUnit4.class) public class CustomRunner {

    protected Context context;

    @Before
    public void setUp() throws Exception {
        this.context = InstrumentationRegistry.getContext();
    }

    @Test
    public void context_is_valid() throws Exception {
        assertNotNull(context);
    }
}
