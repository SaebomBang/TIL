```Linux 설치

```

## 1. Linux

* Update 안하게

	```bash
	$ vi /etc/yum.repos.d/CentOS-Base.repo
	#[update] 부분 이하 삭제
	```

* Network IP

  ```bash
  $ vi /etc/sysconfig/network-scripts/gedit ifcfg-ens33
  BOOTPROTO="none"
  IPADDR="192.168.111.101"
  NETMASK="255.255.255.0"
  GATEWAY="192.168.111.2"
  DNS1="192.168.111.2"
  $ systemctl restart network
  $ ifconfig
  ```

* 보안 설정 해제

  ```bash
  $ vi /etc/sysconfig/selinux
  # SELINUX=disable 로 수정 
  ```

## 2. JDK, Tomcat, Oracle MariaDB 설치

### JDK

```bash
download jdk x64
$ tar xvf jdk...tar.gz
$ mv jdk1.8.0.11.11 jdk1.8.0
$ cp -r jdk1.8.0 /usr/local
$ java
$ /usr/bin/java x
$ cd /usr/bin
$ ln -s /usr/local/jdk1.8.0/bin/java java
$ ls -l java
$ vi /etc/profile
:52 JAVA_HOME=/usr/local/jdk1.8.0
CLASSPATH=/usr/local/jdk1.8.0/lib
export JAVA_HOME CLASSPATH
PATH=$JAVA_HOME/bin:$PATH:.
```

### Tomcat

```bash
tomcat.apache.org
$ tar xvf apachxxx.gz
$ cp -r apache-tomcat-xx /usr/local
$ cd /usr/local/apache-tomcat-xx
$ cd conf
$ vi server.xml - 69 -> 80 port
$ cd /usr/bin
$ ln -s /usr/local/apache-tomcat-9.0.37/bin/startup.sh starttomcat
$ ln -s /usr/local/apache-tomcat-9.0.37/bin/shutdown.sh stoptomcat
```

### Oracle

```bash
# oracle express edition 11g - download
$ unzip oracle-xe-11.2.0-1.0.x86_64.rpm.zip
$ cd Disk1
$ yum -y localinstall oracle-xe-11.2.0-1.0.x86_64.rpm
$ service oracle-xe configure
$ systemctl restart oracle-xe
$ systemctl status oracle-xe
```

### MariaDB

[다운로드](https://downloads.mariadb.com/MariaDB/mariadb-10.0.15/yum/centos7-amd64/rpms/) client, common, server

```bash
$ yum -y remove mariadb-libs
$ yum -y localinstall Maria*
$ systemctl restart mysql
$ systemctl status mysql
$ chkconfig mysql on
$ mysqladmin -u root password '111111'
$ mysql -h localhost  -u  root  -p
> $ use mysql;
> $ SELECT  user, host  FROM  user;
> $ GRANT   ALL   ON   *.*  TO   user1@'192.168.111.%'  IDENTIFIED  BY  '111111';
> $ CREATE   DATABASE   shopdb;
> $ use   shoppdb
```

## 3.  FTP설치

* p669

  ```bash
  # vsftpd 설치
  $ yum  -y  install  vsftpd
  $ systemctl  restart   vsftpd
  $ systemctl  enable  vsftpd
  $ vi  /etc/vsftpd/vsftpd.conf
     19, 29,  33,  35
  
  # p674
  $ chown  ftp.ftp  /var/ftp/pub
  $ systemctl  restart   vsftpd
  # window client program 설치
  # 알드라이브 설치
  # 192.168.111.101 
  # 익명으로접속
  ```

## 4. 하둡 설치

```bash
# 방화벽 해제
$ systemctl stop firewalld
$ systemctl disable firewalld

# ~/.ssh 폴더 확인
$ ssh-keygen -t dsa -P '' -f ~/.ssh/id_dsa
$ ls .ssh
id_dsa	id_dsa.pub
$ cat id_dsa.pub >> authorized_keys
$ ssh hadoopserver

#하둡 설치
$ wget https://archive.apache.org/dist/hadoop/common/hadoop-1.2.1/hadoop-1.2.1.tar.gz
$ tar xvf hadoop-1.2.1.tar.gz
$ cp -r ... /usr/local
$ vi /etc/profile
:52 JAVA_HOME=/usr/local/jdk1.8.0
CLASSPATH=/usr/local/jdk1.8.0/lib
HADOOP_HOME=/usr/local/hadoop-1.2.1
HIVE_HOME=/usr/local/hive
export JAVA_HOME CLASSPATH HADOOP_HOME HIVE_HOME
PATH=$JAVA_HOME/bin:$HADOOP_HOME/bin:$HIVE_HOME/bin:.:$PATH
```

* config

  ```bash
  $ pwd 
  /usr/local/hadoop-1.2.1/conf
  $ vi core-site.xml
  <configuration>
  	<property>
  		<name>fs.default.name</name>
  		<value>hdfs://localhost:9000</value>
  	</property>
  	<property>
  		<name>hadoop.tmp.dir</name>
  		<value>/usr/local/hadoop-1.2.1/tmp</value>
  	</property>
  </configuration>
  
  $vi hdfs-site.xml
  <configuration>
  	<property>
  		<name>dfs.replication</name>
  		<value>1</value> 
  	</property>
  	<property>
  		<name>dfs.webhdfs.enabled</name>
  		<value>true</value>
  	</property>
  	<property>
  		<name>dfs.name.dir</name>
  		<value>/usr/local/hadoop-1.2.1/name</value>
  	</property>
  	<property>
  		<name>dfs.data.dir</name>
  		<value>/usr/local/hadoop-1.2.1/data</value>
  	</property>
  </configuration>
  
  $ vi mapred-site.xml
  <configuration>
  	<property>
  		<name>mapred.job.tracker</name>
  		<value>localhost:9001</value>
  	</property>
  </configuration>
  
  $ vi hadoop-env.sh
  export JAVA_HOME=/usr/local/jdk1.8.0
  export HADOOP_HOME_WARN_SUPPRESS="TRUE"
  
  ```

  ### 하둡 실행

  ```bash
  $ hadoop namenode -format
  $ start-all.sh
  $ jps
  # /usr/local/hadoop... 에 name, data, tmp폴더 확인
  # http://hadoopserver:50070 접속
  ```

* hadoop fs (p.63)

  ```bash
  $ pwd
  /usr/local/hadoop-1.2.1
  $ hadoop fs -ls /usr
  Found 1 items
  drwsr-xr-x	-	root	supergroup	0	2020-09-22	16:05	/usr/local
  $ hadoop fs -mkdir /test
  $ hadoop fs -put README.txt /test
  $ hadoop jar hadoop-examples-1.2.1.jar wordcount /test /output
  	# wordcount클래스  input(/test) output(/result)
  	# output 폴더 자동 생성
  ```

  

## 5. HIVE 설치

```bash
# MariaDB 설치
$ mysql -h localhost -u root -p
> USE  mysql;
> GRANT ALL ON *.* TO hive@'127.0.0.1' IDENTIFIED BY '111111';
> GRANT ALL ON *.* TO hive@'localhost' IDENTIFIED BY '111111';
> GRANT ALL ON *.* TO hive@'192.168.111.120' IDENTIFIED BY '111111';
> GRANT ALL ON *.* TO hive@'hadoop' IDENTIFIED BY '111111';
> SELECT  user, host  FROM  user;


$ mysql -h localhost -u hive -p
> CREATE DATABASE hive_db;

# hive download
$ wget https://archive.apache.org/dist/hive/hive-1.0.1/apache-hive-1.0.1-bin.tar.gz
$ cp -r apache-hive-1.0.1-bin /usr/local/
$ vi /etc/profile
:52 JAVA_HOME=/usr/local/jdk1.8.0
CLASSPATH=/usr/local/jdk1.8.0/lib
HADOOP_HOME=/usr/local/hadoop-1.2.1
HIVE_HOME=/usr/local/hive
export JAVA_HOME CLASSPATH HADOOP_HOME HIVE_HOME
PATH=$JAVA_HOME/bin:$HADOOP_HOME/bin:$HIVE_HOME/bin:.:$PATH

# mariadb-java-client-1.3.5.jar 파일 다운로드
$ cp mariadb-java-client-1.3.5.jar /usr/local/hive/lib
```

```bash
$ vi hive-site.xml
<?xml version="1.0"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
    <property>
        <name>hive.metastore.local</name>
        <value>false</value>
        <description>controls whether to connect to remove metastore server or open a new metastore server in Hive Client JVM</description>
    </property>
    <property>
        <name>javax.jdo.option.ConnectionURL</name>
        <value>jdbc:mariadb://localhost:3306/hive_db?createDatabaseIfNotExist=true</value>
        <description>JDBC connect string for a JDBC metastore</description>
    </property>
    <property>
        <name>javax.jdo.option.ConnectionDriverName</name>
        <value>org.mariadb.jdbc.Driver</value>
        <description>Driver class name for a JDBC metastore</description>
    </property>
    <property>
        <name>javax.jdo.option.ConnectionUserName</name>
        <value>hive</value>
        <description>username to use against metastore database</description>
    </property>
    <property>
        <name>javax.jdo.option.ConnectionPassword</name>
        <value>111111</value>
        <description>password to use against metastore database</description>
    </property>
</configuration>
```

```bash
# HIVE DIRECTORY SETTING
$ hadoop fs -mkdir /tmp
$ hadoop fs -mkdir /user/root/warehouse
$ hadoop fs -chmod 777 /tmp
$ hadoop fs -chmod 777 /user/root/warehouse
$ hadoop fs -mkdir /tmp/hive
$ hadoop fs -chmod 777 /tmp/hive
```

## 6. HIVE 예제

* [링크](https://dataverse.harvard.edu/dataset.xhtml?persistentId=doi:10.7910/DVN/HG7NV7) 에서 '2008.csv.bz2'다운로드

```bash
$ bzip2 -d *.bz2
$ ls
2008.csv
$ hadoop dfs -mkdir airline_delay
$ hadoop dfs -put 2008.csv airline_delay

# 1. 테이블 생성
hive> CREATE TABLE airline_delay(Year INT, Month INT, DayofMont INT, DayOfWeek INT, DepTime INT, CRSDepTime INT, ArrTime INT, CRSArrTime INT, UniqueCarrier STRING, FlightNum INT, TaiNum STRING, ActualElapsedTime INT, CRSElapsedTime INT, AirTime INT, ArrDelay INT, DepDelay INT, Origin STRING, Dest STRING, Distance INT, TaxiIn INT, TaxiOut INT, Cancelled INT, CancellationCode STRING COMMENT 'A=carrier, B=weather, C=NAS, D=security', Diverted INT COMMENT '1=yes, 0=no', CarrierDelay STRING, WeatherDelay STRING, NASDelay STRING, SecurityDelay STRING, LateAircraftDelay STRING) 
COMMENT 'The data consitsts of filght arrival and departure details for all commercial flights within the USA, froom October 1987 to April 2008' 
PARTITIONED BY (delayYear INT) 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n' 
STORED AS TEXTFILE;

# 2. 테이블 목록 조회
hive> SHOW TABLES;
OK
airline_delay
Time taken: 0.218 seconds, Fetched: 1 row(s)

# 3. 데이터 업로드
hive> load data local inpath '2008.csv'
> overwrite into table airline_delay
> partition (delayYear='2008');


# hdi
# 1. HIVE에 데이터 테이블 작성 및 파일 업로드
hive> CREATE TABLE HDI(id INT, country STRING, hdi FLOAT, lifeex INT, mysch INT, eysch INT, gni INT) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;
hive> load data local inpath 'hdi-data.csv' into table hdi;
hive> select * from hdi;

# 2. Java Application 연동
# hive 서버 실행
$ hive --service hiveserver2


```

* java Program

```java
Class.forName("org.apache.hive.jdbc.HiveDriver");
Connection conn = DriverManager.getConnection("jdbc:hive2://localhost:10000/default","root","111111");
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM HDI");
   while(rs.next()) {
     System.out.println(rs.getString(1));
   }
conn.close();
System.out.println("Success....");
```





## log4j

* pom.xml

  ```xml
  <dependency>
  	<groupId>log4j</groupId>
  	<artifactId>log4j</artifactId>
  	<version>1.2.17</version>
  </dependency> 
  ```

* web.xml

  ```xml
  <listener>
        <listener-class>org.springframework.web.util.Log4jConfigListener</listener-class>
  </listener>
  <context-param>
        <param-name>log4jConfigLocation</param-name>
        <param-value>/WEB-INF/config/log4j.properties</param-value>
  </context-param>
  ```

*  /WEB-INF/config/log4j.properties 에 Log4j properties 파일 다운로드

* src/com.log/Logger.java

  ```java
  package com.log;
  
  import org.apache.log4j.Logger;
  import org.aspectj.lang.JoinPoint;
  import org.aspectj.lang.annotation.Aspect;
  import org.aspectj.lang.annotation.Before;
  import org.springframework.stereotype.Service;
  
  @Service
  @Aspect
  public class Loggers {
  	private Logger work_log = Logger.getLogger("work");
  	private Logger user_log = Logger.getLogger("user");
  	private Logger data_log = Logger.getLogger("data");
  
  // before
  	@Before("execution(* com.*ShopController.*(..))")
  	public void logging1(JoinPoint jp) {
  		data_log.debug(jp.getSignature().getName());
  	}
  
  	@Before("execution(* com.MainController.*(..))")
  	public void logging2(JoinPoint jp) {
  		work_log.debug(jp.getSignature().getName());
  	}
  
  //	// before
  //	@Before("execution(* com.hive.*Controller.*(..))")
  //	public void logging(JoinPoint jp) {
  //		work_log.debug(jp.getSignature().getName());
  //		user_log.debug(jp.getSignature().getName());
  //		data_log.debug(jp.getSignature().getName());
  //	}
  }
  ```

  

  











```sql
create table shopclick( date string, fn string, id string, item string, price int,  age int, gender String ) partitioned by (logdate string) row format delimited fields terminated by ',' lines terminated by '\n' stored as textfile;
```





