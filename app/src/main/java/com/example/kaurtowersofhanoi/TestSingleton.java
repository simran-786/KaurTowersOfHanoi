package com.example.kaurtowersofhanoi;

public class TestSingleton
{
    private static final TestSingleton ourInstance = new TestSingleton();

    static TestSingleton getInstance()
    {
        return ourInstance;
    }

    private TestSingleton()
    {

    }

    public MainActivity ma;
}
