package com.example.kaurtowersofhanoi;

import android.widget.TextView;

public class Disk
{
    private int size;
    private TextView theView;
    private Disk nextDisk;

    public Disk(int size, TextView theView)
    {
        this.size = size;
        this.theView = theView;
        this.nextDisk = null;
    }

    public Disk getNextDisk()
    {
        return nextDisk;
    }

    public void setNextDisk(Disk nextDisk)
    {
        this.nextDisk = nextDisk;
    }

    public int getSize()
    {
        return size;
    }

    public TextView getTheView()
    {
        return theView;
    }
}