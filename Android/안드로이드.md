



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



# 05 프래그먼트(Fragment)이해하기

## 05-3 액션바(ActionBar)사용하ㅣ





# 07 선택위젯

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

