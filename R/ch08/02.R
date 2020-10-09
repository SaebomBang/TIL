library(KoNLP)
library(wordcloud)
library(tm)
library(Rcurl)
library(RColorBrewer)
useSystemDic()
useSejongDic()
useNIADic()

#
word_data <- readLines("애국가(가사) 복사본.txt")
word_data

# 명사 추출
word_data2 <- sapply(word_data, extractNoun, USE.NAMES = F)
word_data2

# 세종 사전에 단어 등록
add_words <- c("백두산", "남산", "철갑", "가을", "하늘", "달")
buildDictionary(user_dic = data.frame(add_words, rep("ncn", length(add_words))), replace_usr_dic = T)

# 행렬을 벡터로 변환하기
undata <- unlist(word_data2)
undata

# 사용 빈도 확인하기
word_table <- table(undata)
word_table

undata2 <- Filter(function(x){nchar(x) >= 2}, undata)
word_table2 <- table(undata2)
word_table2

sort(word_table2, decreasing = T)



library(wordcloud2)
wordcloud2(word_table2)

wordcloud2(word_table2, color="random-light", backgroundColor = "black")



wordcloud2(word_table2, fontFamily = "맑은 고딕", size=1.2, color="random-light", backgroundColor = "black", shape="star")



