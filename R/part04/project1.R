# 치킨집이 가장 많은 지역 찾기
library(readxl)
ck <- read_excel("치킨집_가공.xlsx")
head(ck)

# 상세주소 식제
addr <- substr(ck$소재지전체주소, 11, 16)
head(addr)

# 공백 제거
addr_num <- gsub("[0-9]", "", addr)
addr_trim <- gsub(" ", "", addr_num)
head(addr_trim)

# 데이터 프레임으로 변환
library(dplyr)
addr_count <- addr_trim %>% table() %>% data.frame()
head(addr_count)

# 트리맵으로 표현
library(treemap)
par(family="AppleGothic")
treemap(addr_count, fontfamily.title = "AppleGothic", fontfamily.labels = "AppleGothic", index = ".", vSize = "Freq", title = "서대문구 동별 치킨집 분포")

# 정렬
arrange(addr_count, desc(Freq)) %>% head()