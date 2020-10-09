# 지역별 미세먼지 농도 비교하기
#데이터 가져오기
library(readxl)
dustdata <- read_excel("dustdata2.xlsx")
View(dustdata)
str(dustdata)

# 지역 데이터 추출하기
library(dplyr)
dustdata_anal <- dustdata %>% filter(area %in% c("성북구", "강남구"))
View(dustdata_anal)

# yyyymmdd에 따른 데이터 수 파악
count(dustdata_anal, yyyymmdd) %>% arrange(desc(n))
count(dustdata_anal, area) %>% arrange(desc(n))

#데이터 분리
dustdata_anal_area_sb <- subset(dustdata_anal, area == "성북구")
dustdata_anal_area_gn <- subset(dustdata_anal, area == "강남구")
dustdata_anal_area_sb
dustdata_anal_area_gn

# 통계량 도출
library(psych)
describe(dustdata_anal_area_sb$finedust)
describe(dustdata_anal_area_gn$finedust)

# 분포 차이 확인
boxplot(dustdata_anal_area_sb$finedust, dustdata_anal_area_gn$finedust, 
        main="finedust_compare", xlab="AREA", names = c("성북구", "강남구"),
        ylab="FINEDUST_PM", col=c("blue", "green"))
t.test(data = dustdata_anal, finedust ~ area, var.equal=T)
