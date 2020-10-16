package com.example.practice13;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ArrayList<Member> memberArrayList;
    MemberAdapter memberAdapter;
    ListView listView;
    Button button;
    LinearLayout container;
    EditText editTextName, editTextBirth, editTextPhone;
    TextView textViewTotal;

    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextBirth = findViewById(R.id.editTextBirth);
        editTextBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        textViewTotal = findViewById(R.id.textViewTotal);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMember();
                clearEditText();
                setTotalNum();

                // hide keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });

        container = findViewById(R.id.container);
        listView = findViewById(R.id.listView);

        getInitialData();
        setTotalNum();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        MenuItem item = menu.findItem(R.id.tabWifi);

        if (mWifi.isConnected()) {
            item.setVisible(true);
        } else {
            item.setVisible(false);
        }
        return true;
    }

    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";    // 출력형식   2018/11/28
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        editTextBirth = (EditText) findViewById(R.id.editTextBirth);
        editTextBirth.setText(sdf.format(myCalendar.getTime()));
    }

    public void getInitialData() {
        memberArrayList = new ArrayList<>();

        memberArrayList.add(new Member("고객#1", "1990-01-02", "010-1000-1000"));
        memberArrayList.add(new Member("고객#2", "1989-10-08", "010-2000-2000"));

        setData();
    }

    public void setTotalNum() {
        textViewTotal.setText(memberArrayList.size() + "명");
    }

    public void addMember() {
        String name = editTextName.getText().toString();
        String birth = editTextBirth.getText().toString();
        String phone = editTextPhone.getText().toString();

        Member member = new Member(name, birth, PhoneNumberUtils.formatNumber(phone));
        memberArrayList.add(member);
    }

    public void clearEditText() {
        editTextName.getText().clear();
        editTextPhone.getText().clear();
        editTextBirth.getText().clear();
    }


    public void showDialog(){

    }
    public void setData() {
        memberAdapter = new MemberAdapter(memberArrayList);
        listView.setAdapter(memberAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                Member member = memberArrayList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Hi");
                builder.setMessage("Are you deleted this item" + member.getName());

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int a) {
                        memberArrayList.remove(position);
                        memberAdapter.notifyDataSetChanged();
                        setTotalNum();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();

            }
        });
    }

    class MemberAdapter extends BaseAdapter {
        ArrayList<Member> memberArrayList;

        public MemberAdapter(ArrayList<Member> memberArrayList) {
            this.memberArrayList = memberArrayList;
        }

        @Override
        public int getCount() {
            return memberArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return memberArrayList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return memberArrayList.indexOf(i);
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.member, container, true);

            TextView textViewName = view.findViewById(R.id.textViewName);
            TextView textViewBirth = view.findViewById(R.id.textViewBirth);
            TextView textViewPhone = view.findViewById(R.id.textViewPhone);

            Member member = memberArrayList.get(i);
            textViewName.setText(member.getName());
            textViewBirth.setText(member.getBirth());
            textViewPhone.setText(member.getPhone());

            return view;
        }
    }
}