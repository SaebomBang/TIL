library(readxl)
library(psych)
library(descr)
y16 <- as.data.frame(read_excel("y16.xlsx"))
y17 <- as.data.frame(read_excel("y17.xlsx"))
y16
y17

r3 <- full_join(y17, y16, by="ID")
r3

r3$SUM_AMT <- rowSums(r3 %>% select(AMT16, AMT17), na.rm = TRUE)
r3$SUM_CNT <- rowSums(r3 %>% select(Y16_CNT, Y17_CNT), na.rm = TRUE)
r3

r4 <- r3 %>% group_by(AREA) %>% summarise(AMT_AVG=mean(SUM_AMT), CNT_AVG=mean(SUM_CNT), .groups='drop')
r4 <- as.data.frame(r4)

r4$AREA <- ifelse(is.na(r4$AREA),'NONE' , r4$AREA)
r4 <- r4 %>% arrange(desc(AMT_AVG))
describe(r4)

library(readxl)
exdata1 <- read_excel("../HelloR/Data/Sample1.xlsx")
exdata1

freq_test <- freq(exdata1$AREA, plot=F)
freq_test

freq(exdata1$AREA)
