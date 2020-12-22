package com.example.movieapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText editTextDate;
    Button button;
    ListView listView;
    LinearLayout container;

    ArrayList<Movie> movieArrayList = new ArrayList<>();
    MovieAdapter movieAdapter;

    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieAdapter = new MovieAdapter();

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

        listView = findViewById(R.id.listView);
        listView.setAdapter(movieAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movie = movieArrayList.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle(movie.getName());
                builder.setMessage("이름: " + movie.getName() + "\n랭킹: " + movie.getRank());

                builder.show();
            }
        });

        container = findViewById(R.id.container);
        editTextDate = findViewById(R.id.editTextDate);
        editTextDate.setText(getYesterday());
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 24 * 60 * 60 * 1000L);
                datePickerDialog.show();
            }
        });
    }

    public String getYesterday() {
        Date date = new Date();
        date = new Date(date.getTime() - 1000*60*60*24-1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        String yesterday = sdf.format(date);
        return yesterday;
    }

    //get data from website
    public void getData() {
        String myDate = editTextDate.getText().toString();
        String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=" + myDate;

        HttpAsync httpAsync = new HttpAsync();
        httpAsync.execute(url);
    }

    // make data to json object
    public void setJsonData(String jsonData) {
        movieArrayList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject boxOfficeResult = jsonObject.getJSONObject("boxOfficeResult");
            JSONArray dailyBoxOfficeList = boxOfficeResult.getJSONArray("dailyBoxOfficeList");

            for (int i = 0; i < dailyBoxOfficeList.length(); i++) {
                JSONObject jsonMovie = dailyBoxOfficeList.getJSONObject(i);

                int rank = jsonMovie.getInt("rank");
                String name = jsonMovie.getString("movieNm");
                String openDate = jsonMovie.getString("openDt");

                movieArrayList.add(new Movie(rank, name, openDate));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // get data from website with asynctask
    class HttpAsync extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Loading");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0].toString();
            String result = HttpConnect.getString(url);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();

            setJsonData(s);
            movieAdapter.notifyDataSetChanged();

        }
    }

    class MovieAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return movieArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return movieArrayList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.movie, container, false);
            }

            TextView textViewName = convertView.findViewById(R.id.textViewName);
            TextView textViewRank = convertView.findViewById(R.id.textViewRank);
            TextView textViewOpenDate = convertView.findViewById(R.id.textViewOpenDate);

            Movie movie = movieArrayList.get(i);
            textViewName.setText(movie.getName());
            textViewRank.setText(movie.getRank() + "");
            textViewOpenDate.setText(movie.getOpenDate());


            return convertView;
        }
    }


    //calendar
    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    //make calendar format
    private void updateLabel() {
        String myFormat = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        editTextDate = (EditText) findViewById(R.id.editTextDate);
        editTextDate.setText(sdf.format(myCalendar.getTime()));
    }

}