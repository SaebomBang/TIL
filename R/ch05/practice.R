
#01
mid_exam <- read_excel("../HelloR/Data/mid_exam.xlsx")
mid_exam <- as.data.frame(mid_exam)
mid_exam <- rename(mid_exam, MATH_MID=MATH, ENG_MID=ENG)
mid_exam

#02
final_exam <- read_excel("../HelloR/Data/final_exam.xlsx")
finam_exam <- as.data.frame(finam_exam)
final_exam <- rename(final_exam, MATH_FINAL=MATH, ENG_FINAL=ENG)
final_exam

#03
total_exam <- full_join(mid_exam, final_exam, by="ID")
total_exam

#04
total_exam$MATH_AVG <- rowMeans(total_exam %>% select(MATH_MID, MATH_FINAL), na.rm = TRUE)
total_exam$ENG_AVG <- rowMeans(total_exam %>% select(ENG_MID, ENG_FINAL), na.rm = TRUE)
total_exam

#05
total_exam$TOTAL_AVG <- rowMeans(total_exam %>% select(MATH_AVG, ENG_AVG), na.rm = TRUE)
total_exam

#06
total_avg <- total_exam %>% summarise(MATH=mean(MATH_AVG, na.rm=TRUE), ENG=mean(ENG_AVG, na.rm=TRUE))
total_avg

#07
student <- total_exam %>% filter(MATH_MID >= 80 & ENG_MID >= 90)
student

#08
hist(total_exam$MATH_AVG)
hist(total_exam$ENG_AVG)
