package com.example.kaurtowersofhanoi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{
    private Tower t1;
    private Tower t2;
    private Tower t3;
    private Disk d1;
    private Disk d2;
    private Disk d3;
    private Disk temp = null;
    private ViewGroup landingZone;
    private boolean isSourceMode = true;
    private int NumberOfMoves = 0;
    private TextView MovesTV;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestSingleton.getInstance().ma = this;
        Core.ma = this;

        this.MovesTV = (TextView)this.findViewById(R.id.MovesTV);
        this.landingZone = (ViewGroup)this.findViewById(R.id.landingZone);

         d1 = new Disk(1, (TextView) this.findViewById(R.id.disk1TV));
         d2 = new Disk(2, (TextView) this.findViewById(R.id.disk2TV));
         d3 = new Disk(3, (TextView) this.findViewById(R.id.disk3TV));

         t1 = new Tower((ViewGroup)this.findViewById(R.id.tower1Layout));
         t2 = new Tower((ViewGroup)this.findViewById(R.id.tower2Layout));
         t3 = new Tower((ViewGroup)this.findViewById(R.id.tower3Layout));

        this.ResetGame();
    }

    protected void onPause()
    {
        super.onPause();
        System.out.println("OnPause");
    }
    protected void onStop()
    {
        super.onStop();
        System.out.println("OnStop");
    }
    protected void onDestroy()
    {
        super.onDestroy();
        System.out.println("OnDestroy");
    }
    protected void onStart()
    {
        super.onStart();
        if(Core.shouldReset)
        {
            this.ResetGame();
            Core.shouldReset = false;
        }
        System.out.println("OnStart");
    }

    protected void onResume()
    {
        super.onResume();
        System.out.println("OnResume");
    }

    public void onResetButtonPressed(View v)
    {
        this.ResetGame();
    }

    public void ResetGame()
    {
        this.isSourceMode = true;
        this.NumberOfMoves = 0;
        this.temp = null;
        this.t1.empty();
        this.t2.empty();
        this.t3.empty();
        this.t1.push(d3);
        this.t1.push(d2);
        this.t1.push(d1);
    }

    private void UpdateMovesTextView()
    {
        this.MovesTV.setText("Moves: " + this.NumberOfMoves);
    }

    private void towerLogic(Tower t)
    {
        if(this.isSourceMode)
        {
            if (t.Peek() != null)
            {
                this.temp = t.pop();
                this.landingZone.addView(this.temp.getTheView());
                this.isSourceMode = false;
            }
        }
        else
            {
            if((t.Peek() != null && this.temp.getSize() < t.Peek().getSize()) ||
            t.Peek() == null)
            {
                this.landingZone.removeAllViews();
                t.push(this.temp);
                this.temp = null;
                this.isSourceMode = true;
                this.NumberOfMoves++;
                this.UpdateMovesTextView();
                this.checkForWinner();
            }
        }
    }

    private void checkForWinner()
    {
        if(this.t3.getCount() == 3)
        {
            Core.shouldReset = true;
            Intent i = new Intent(this, WINNERactivity.class);
            i.putExtra("NumberOfMoves", this.NumberOfMoves);
            this.startActivity(i);
        }
    }

    public void tower1ButtonPressed(View v)
    {
      this.towerLogic(this.t1);
    }
    public void tower2ButtonPressed(View v)
    {
        this.towerLogic(this.t2);
    }
    public void tower3ButtonPressed(View v)
    {
        this.towerLogic(this.t3);
    }
}