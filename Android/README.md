



# ch03 기본 위젯과 드로어블 사용하기

## 03-4 토스트 스낵바 그리고 대화상자

* 알림 대화상자(dialog)

  ```java
      public void showDialog() {
          AlertDialog.Builder builder = new AlertDialog.Builder(this);
          builder.setTitle("회원가입");
          builder.setMessage("회원가입 하시겠습니까?");
  
          builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
  
              }
          });
          builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
  
              }
          });
          AlertDialog dialog = builder.create();
          dialog.show();
      }
  ```

## 03-5 프로그레스바 사용하기

* 프로그레스바(progress bar)

  ```java
  protected void onCreate(Bundle savedInstanceState) {
  ...
  progressBar = findViewById(R.id.progressBar);
          progressBar.setIndeterminate(false);
          progressBar.setProgress(80);
  
          buttonLogin = findViewById(R.id.buttonLogin);
          buttonLogin.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  showProgressBar();
              }
          });    
  ...}
  
  public void showProgressBar() {
          ProgressDialog dialog = new ProgressDialog(MainActivity.this);
          dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
          dialog.setMessage("데이터를 확인하는 중입니다.");
  
          dialog.show();
      }
  
  ```

  



# ch04 화면 간 전환하기

## 04-1 레이아웃 인플레이션

* LayoutInflation

  ```java
  //MenuActivity.java
      LinearLayout container;
  
  @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_menu);
  
          container = findViewById(R.id.container);
  
          Button button = findViewById(R.id.button);
          button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                  inflater.inflate(R.layout.sub, container, true);
                  CheckBox checkBox = container.findViewById(R.id.checkBox);
                  checkBox.setText("로딩되었어요.");
              }
          });
      }
  ```

  



## 04-2 여러 화면 만들고 화면 간 전환하기

* 화면전환

  ```java
  //MainActivity
  public void btnclick(View view) {
          Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
          intent.putExtra("data", 100);			//데이터 전달
          startActivity(intent);
  }
  ```

  ```java
  // SecondActivity
  @Override
     	protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_second);
  
          Intent intent = getIntent();
          int result = intent.getIntExtra("data", 0);
          Toast.makeText(this, ""+result , Toast.LENGTH_SHORT).show();
      }
  ```

* permission check

  ```java
  //MainActivity onCreate
          String []permissions = {Manifest.permission.CALL_PHONE};
          ActivityCompat.requestPermissions(this, permissions, 101);
  ```

  ```java
  //thirdActivity
  public void phoneCall() {
          int chk = PermissionChecker.checkSelfPermission(
                  this, Manifest.permission.CALL_PHONE
          );
          if (chk == PackageManager.PERMISSION_GRANTED) {
              intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-0000-0000"));
              startActivity(intent);
          } else {
              Toast.makeText(this, "Not allowed", Toast.LENGTH_SHORT).show();
          }
      }
  ```

  ## 04-3 인텐트(intent)
  
  * 전화번호
  
    ```java
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
    
            editText = findViewById(R.id.editText);
    
            Button button = findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String data = editText.getText().toString();
    
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
                    startActivity(intent);
                }
            });
        }
    ```



## 04-4  플래그와 부가 데이터 이용하기

* 플래그



# ch05 프래그먼트(Fragment)이해하기

## 05-3 액션바(ActionBar)사용하기





# ch07 선택위젯

## 리사이클 뷰(RecycleView)

```java
public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }

    public void getData() {
        datas = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            datas.add("Item: " + i);
        }
        setList();
    }

    public void setList(){
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, datas
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Hi");
                builder.setMessage("Are you deleted this item" + datas.get(position));

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        datas.remove(position);
                        adapter.notifyDataSetChanged();
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
}
```



## 07-4 RecycleView

```java
public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button button;
    LinearLayout container;
    ArrayList<Person> personArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
        container = findViewById(R.id.container);
    }

    public void getData() {
        personArrayList = new ArrayList<>();
        personArrayList.add(new Person("p01", "name01", R.drawable.p3, 23));
        personArrayList.add(new Person("p02", "name02", R.drawable.p5, 24));
        personArrayList.add(new Person("p03", "name03", R.drawable.p8, 25));

        setData();
    }

    class PersonAdaptor extends BaseAdapter {
        ArrayList<Person> personArrayList;

        public PersonAdaptor( ArrayList<Person> personArrayList) {
            this.personArrayList = personArrayList;
        }

        @Override
        public int getCount() {
            return personArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return personArrayList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return personArrayList.indexOf(i);
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.person, container, true);

            ImageView im = view.findViewById(R.id.imageView);
            TextView textView1 = view.findViewById(R.id.textView);
            TextView textView2 = view.findViewById(R.id.textView2);
            TextView textView3 = view.findViewById(R.id.textView3);

            Person p = personArrayList.get(i);
            im.setImageResource(p.getImg());
            textView1.setText("사번: " + p.getId());
            textView2.setText("이름: " + p.getName());
            textView3.setText("나이: " + p.getAge());

            return view;
        }
    }

    public void setData() {
        PersonAdaptor personAdaptor = new PersonAdaptor(personArrayList);
        listView.setAdapter(personAdaptor);
    }
 
```



# ch09  Thread & Handler

## 09-1  핸들러 이해하기

* Main Activit내 이벤트 처리과정은 하나의 프로세스에서 실행됨 -> Bottleneck 발생 가능성 있음.
* -> Thread
  * 여러개의 작업이 동시 수행되는 멀티 스레드 방식 사용
  * 장점: 프로세스 안에서 메모리 리소스 공유하므로 효율적 처리 가능
  * 단점: 리소스 접근시 데드락(DeadLock) 발생 가능성 있음
* 시나리오
  1. 서비스 사용하기
  2. 스레드 사용하기
     * Handler사용

## 09-3 Thread로 메시지 전송하기

```java
public class MainActivity extends AppCompatActivity {
    TextView textView, textView2;

    KmHandler kmHandler = new KmHandler();
    RpmHandler rpmHandler = new RpmHandler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        final KmThread kmThread = new KmThread();
        kmThread.start();

        RpmThread rpmThread = new RpmThread();
        rpmThread.start();
    }

    class KmThread extends Thread {
        Random rand = new Random();

        @Override
        public void run() {
            while (true) {
                int value = rand.nextInt(200);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = kmHandler.obtainMessage();
               
              	Bundle bundle = new Bundle();
                bundle.putInt("km", value);

                message.setData(bundle);
                kmHandler.sendMessage(message);
            }
        }
    }

    class KmHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("km");
            textView.setText(value + "km");
        }
    }

    class RpmThread extends Thread {
        Random rand = new Random();

        @Override
        public void run() {
            while (true) {
                int value = rand.nextInt(5000);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message message = rpmHandler.obtainMessage();

                Bundle bundle = new Bundle();
                bundle.putInt("rpm", value);

                message.setData(bundle);

                rpmHandler.sendMessage(message);
            }

        }
    }

    class RpmHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("rpm");
            textView2.setText(value + "rpm");
        }
    }
}
```

 

## 09-4 AsyncTask

```java
public class MainActivity extends AppCompatActivity {
    Button button, button2;
    SeekBar seekBar;
    TextView textView;
    ImageView imageView;

    MyAsynch myAsynch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        button2.setEnabled(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAsynch = new MyAsynch();
                myAsynch.execute();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAsynch.cancel(true);
                myAsynch.onCancelled();
            }
        });
    }


    class MyAsynch extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            button.setEnabled(false);
            button2.setEnabled(true);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                if (isCancelled() == true)
                    break;

                sum += i;
                publishProgress(i);             //값을 넘
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int i = values[0].intValue();
            if (i <= 30) {
                imageView.setImageResource(R.drawable.down);
            } else if (i <= 70) {
                imageView.setImageResource(R.drawable.medium);
            } else if (i <= 100) {
                imageView.setImageResource(R.drawable.up);
            }

            seekBar.setProgress(i);
            textView.setText("Doing");
        }

        @Override
        protected void onPostExecute(String s) {
            //백그라운드 작업이 끝난 후
            textView.setText(s);

            button.setEnabled(true);
            button2.setEnabled(false);
        }

        @Override
        protected void onCancelled() {
            seekBar.setProgress(0);
            textView.setText("stopped");
            imageView.setImageResource(R.drawable.ic_launcher_background);

            button.setEnabled(true);
            button2.setEnabled(false);
        }
    }
}
```



# ch10 서버에 데이터 요청하고 응답받기

## 10-1 네트워킹(Networking)이란

```java
package com.example.ch10_1_networking;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnect {
    public static String getString(String urlstr) {
        String result = null;
        URL url = null;
        HttpURLConnection hcon = null;
        InputStream is = null;
        try {
            url = new URL(urlstr);
            hcon = (HttpURLConnection) url.openConnection();
            hcon.setConnectTimeout(2000);
            hcon.setRequestMethod("GET");
            is = new BufferedInputStream(hcon.getInputStream());
            result = convertStr(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String convertStr(InputStream is) {
        String result = null;
        BufferedReader bi = null;
        StringBuilder sb = new StringBuilder();
        try {
            bi = new BufferedReader(
                    new InputStreamReader(is)
            );
            String temp = "";
            while ((temp = bi.readLine()) != null) {
                sb.append(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
```

```java
public class MainActivity extends AppCompatActivity {
    EditText editTextId, editTextPwd;
    Button button;
    HttpAsync httpAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextId = findViewById(R.id.editTextId);
        editTextPwd = findViewById(R.id.editTextPwd);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClick();
            }
        });

    }

    public void onButtonClick() {
        String id = editTextId.getText().toString();
        String pwd = editTextPwd.getText().toString();

        String url = "http://192.168.0.93:8080/Android/NewFile.jsp?id="+id+"&pwd="+pwd;

        httpAsync = new HttpAsync();
        httpAsync.execute(url);
    }

    class HttpAsync extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Login");
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
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();

            if(s.equals("1"))
                showConnected();
            else if(s.equals("2"))
                showDisconnected();
        }
    }

    public void showConnected(){
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        startActivity(intent);
    }

    public void showDisconnected(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("로그인 실패");
        builder.setMessage("로그인 실패 ");

        builder.setNeutralButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
```



# CH14  GoogleMap

* 준비사항

  * Gradle > bundle.gradle 에 추가

    ```gradle
        ...
        implementation 'com.google.android.gms:play-services-maps:17.0.0'
        ...
    ```

  * manifests

    ```xml
    <meta-data
                android:name="com.google.android.geo.API_KEY"
               android:value="YOURKEY" />
    ```

    

  * Activity_main.xml

    ```xml
     <fragment
            android:id="@+id/map"
            android:name="com.google.android.gms.maps.SupportMapFragment"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
    ```

  * MainActivity.java

    ```java
    public class MainActivity extends AppCompatActivity {
        SupportMapFragment supportMapFragment;
        GoogleMap gMap;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
    
            supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    gMap = googleMap;
    
                    LatLng latlng = new LatLng(37.402456, 126.412786);
                    gMap.addMarker(new MarkerOptions().position(latlng).title("공항"));
                    gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,10));
                }
            });
            Button button = findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonClick();
                }
            });
    
            Button button2 = findViewById(R.id.button2);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButton2Click();
                }
            });
        }
    ```

    

