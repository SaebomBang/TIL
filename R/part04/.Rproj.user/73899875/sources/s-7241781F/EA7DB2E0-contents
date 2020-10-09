#지하철 주변 아파트 가격 알아보기

library(devtools)
install_github("dkahle/ggmap")
library(ggmap)
library(dplyr)

# 원시데이터 가져오기
station_data <- read.csv("역별_주소_및_전화번호.csv")
str(station_data)

# google API key등록
googleAPIKey <-  "AIzaSyCf0OaMj_2ZLeKlOZO2alwc685pjyRf-Gs"
register_google(googleAPIKey)

#문자형으로 변환
station_code <- as.character(station_data$구주소) %>% enc2utf8() %>% geocode()
#station code를 위도 경도로 변환
station_code <- geocode(station_code)

# 기존 데이터에 합치기
station_code_final <- cbind(station_data, station_code)
head(station_code_final)

# 전용면적별 거래 가격
#아파트 실거래가 데이터 가져오기
apart_data <- read.csv("아파트_실거래가.csv")
head(apart_data)

#전용면적의 값 반올림
apart_data$전용면적 = round(apart_data$전용면적)
head(apart_data)

# 전용면적을 기준으로 빈도를 구한 후 빈도에 따라 내림차순 정렬
count(apart_data, 전용면적) %>% arrange(desc(n))

# 전용면적이 85인 데이터만 추출하여 apart_data_86에 할당
apart_data_85 <- subset(apart_data, 전용면적=="85")
head(apart_data_85)

# 아파트 단지별 평균 거래금액
# 쉼표를 공백으로 대체
apart_data_85$거래금액 <- gsub(",", "", apart_data_85$거래금액)
head(apart_data_85)

# 거래금액을 integer로 변환
apart_data_85_cost <- aggregate(as.integer(거래금액) ~ 단지명, apart_data_85, mean)
head(apart_data_85_cost)
apart_data_85_cost <- rename(apart_data_85_cost, "거래금액" = "as.integer(거래금액)")

# 단지명 중복 제거
apart_data_85 <- apart_data_85[!duplicated(apart_data_85$단지명),]
head(apart_data_85)

# 단지명 기준으로 합치기
apart_data_85 <- left_join(apart_data_85, apart_data_85_cost, by="단지명")
head(apart_data_85)

# 거래금액.x 제거
apart_data_85 <- apart_data_85 %>% select("단지명", "시군구", "번지", "전용면적", "거래금액.y")
apart_data_85 <- rename(apart_data_85, "거래금액"="거래금액.y")
head(apart_data_85)

#시군구와 번지 합치기
apart_address <- paste(apart_data_85$시군구, apart_data_85$번지) %>% data.frame()
head(apart_address)
apart_address <- rename(apart_address, "주소"=".")
head(apart_address)

# 아파트 주소를 위도 경도로 변환
apart_address_code <- as.character(apart_address$"주소") %>% enc2utf8() %>% geocode()
# 데이터 세트를 합침
apart_code_final <- cbind(apart_data_85, apart_address, apart_address_code) %>% 
  select("단지명", "전용면적", "거래금액", "주소", lon, lat)
head(apart_code_final)

# 구글 지도에 지하철역과 아파트 가격 표시하기
gangnam_map <- get_googlemap("gangnamgu", maptype = "roadmap", zoom=12)
ggmap(gangnam_map)

# 지하철역 위치 표현
library(ggplot2)
ggmap(gangnam_map) + 
  geom_point(data = station_code_final, aes(x=lon, y=lat), , size=3) +
  geom_text(data=station_code_final, aes(label=역명, vjust=-1))

# 삼성역 지도 정보
samsung_map <- get_googlemap("samsung station", maptype="roadmap", zoom=15)
ggmap(samsung_map) +
  geom_point(data=station_code_final, aes(x=lon, y=lat), size=3) + 
  geom_text(data=station_code_final, aes(label=역명, vjust=-1)) +
  geom_text(data=apart_code_final, aes(label=단지명, vjust=-1)) +
  geom_text(data=apart_code_final, aes(label=거래금액, vjust=-1))
