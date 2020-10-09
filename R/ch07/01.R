library(reshape2)
names(airquality) <- tolower(names(airquality))
m_air <- melt(airquality, id.vars = c("month", "day"), measure.vars = "temp")
m_air
