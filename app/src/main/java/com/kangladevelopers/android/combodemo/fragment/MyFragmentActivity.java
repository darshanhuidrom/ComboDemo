package com.kangladevelopers.android.combodemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.kangladevelopers.android.combodemo.R;

import java.util.List;

public class MyFragmentActivity extends AppCompatActivity {


    private FrameLayout fl1;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment1,fragment2;

    // We can add fragments. The last which is added in the list will be visible in the UI

    // if we use add  the fragment will be added and if we try to add the same fragment
    // which is already added it will throw exception
    // if we use replace, it will replace the current fragment with request fragment.
    // And it wont throw exceptio evn if you add it for the first time.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment);
        fl1= (FrameLayout) findViewById(R.id.fl_1);
        fragment1 = new Fragment1();
        fragment2= new Fragment2();



    }

    public void onButtonClick(View view){
        Button b= (Button) view;

        String commnd = b.getText().toString();

        if(commnd.equals(getResources().getString(R.string.btn_str1))){
            Toast.makeText(getApplicationContext(), commnd, Toast.LENGTH_SHORT).show();
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            if(!(fragments==null||fragments.isEmpty())){
                if(fragments.contains(fragment1)){
                    Toast.makeText(getApplicationContext(),"Fragment1 is already created",Toast.LENGTH_SHORT).show();
                    fragmentTransaction= getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.slide_out);
                    fragmentTransaction.replace(R.id.fl_1,fragment1).commit();
                }
                else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_1,fragment1).commit();
                }
            }
            else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_1,fragment1).commit();
            }

        }
        else if(commnd.equals(getResources().getString(R.string.btn_str2))){
            Toast.makeText(getApplicationContext(), commnd, Toast.LENGTH_SHORT).show();


            fragmentTransaction=getSupportFragmentManager().beginTransaction();

            /*fragmentTransaction.setCustomAnimations(R.animator.fragment_slide_left_enter,R.animator.fragment_slide_left_exit,
                    R.animator.fragment_slide_right_enter,R.animator.fragment_slide_right_exit);*/
            fragmentTransaction.setCustomAnimations(R.anim.slide_in,R.anim.slide_out);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.fl_1,fragment2).commit();
        }
        else if(commnd.equals(getResources().getString(R.string.btn_str3))){
            Toast.makeText(getApplicationContext(), commnd, Toast.LENGTH_SHORT).show();

        }
        else if(commnd.equals(getResources().getString(R.string.btn_str4))){
            Toast.makeText(getApplicationContext(), commnd, Toast.LENGTH_SHORT).show();
        }
        else if(commnd.equals(getResources().getString(R.string.btn_str5))){
            Toast.makeText(getApplicationContext(), commnd, Toast.LENGTH_SHORT).show();
        }
        else if(commnd.equals(getResources().getString(R.string.btn_str6))){
            Toast.makeText(getApplicationContext(), commnd, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "default", Toast.LENGTH_SHORT).show();
        }


        /*switch (commnd) {

            case "Add Fragment1":
                break;
            case "":
                break;
            case "":
                break;
            case "":
                break;
            case "":
                break;
            default:
                Toast.makeText(getApplicationContext(), "DEfault", Toast.LENGTH_SHORT).show();

        }*/
    }
}
