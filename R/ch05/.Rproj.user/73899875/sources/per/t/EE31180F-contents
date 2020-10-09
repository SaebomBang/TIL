install.packages("dplyr")

library(dplyr)

shop <- read.csv("../ch04-02/a.txt", header = F, stringsAsFactors = F)
shop
shop <- rename(shop,ID=V1, NAME=V2, AGE=V3, TEMP=V4, PRICE=V5, QT=V6)
shop
shop$TOTAL <- shop$PRICE * shop$QT
shop
shop$GRADE <- ifelse(shop$TOTAL >= 50000 , "A", "B")
shop2 <- shop %>% select(-ID, -AGE, -GRADE)
shop2
shop3 <- shop %>% filter(QT >= 2)
shop3
shop4 <- shop %>% arrange(NAME)
shop4
shop5 <- shop %% arrange(desc(GRADE))
smr <- shop %>% summarise(TOT = sum(PRICE), AGES=mean(AGE))
smr
smr2 <- shop %>% group_by(NAME) %>% summarise(TOTALAVG=mean(PRICE*QT))
smr3 <- as.data.frame(smr2)
smr3
