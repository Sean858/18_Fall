#Assignment1 R programming
library(plyr)

#load the dataset
df <- read.csv("heights_weights_genders2.csv",header = TRUE)

#1)
#a:first 10 rows
firstTenRows <- df[1:10,]

#a:last 10 rows
nrow <- nrow(df)
lastTenRows <- df[(nrow-9):nrow,]

#b:nrow and ncol
nrow <- nrow(df)
ncol <- ncol(df)

#c:type of variables
typeCol1 <- class(df[1,1])
typeCol2 <- class(df[1,2])
typeCol3 <- class(df[1,3])

#d:max and min
summary(df)
maxRows <- apply(df,2,max)
minRows <- apply(df,2,min)

#e:unwanted value
unValues1 <- df[!complete.cases(df),]
unValues2 <- df[is.na(df),]
indices <- which(!complete.cases(df))

#f: complete df
dfc <- df[complete.cases(df),]
nrowc <- nrow(dfc)
removedNumber <- nrow - nrowc

#g: sort by height
dfcSorted <- dfc[order(dfc["Height"]),]
print(dfcSorted)

#h: BMI calculate
BMI <- dfc[1:nrowc,3][1:nrowc]/(dfc[1:nrowc,2][1:nrowc]**2)*703
dfcBMI <- cbind(dfc,BMI)

#2)
HisHeight <- hist(dfc[,2],main = "Hisrogram of height",xlab = "Height",col = "red",breaks = 20)
HisWeight <- hist(dfc[,3],main = "Hisrogram of weight",xlab = "Weight",col = "green",breaks = 20)

#a: plot
plotWH <- plot(dfc[,3],dfc[,2],main = "Weight-Height",xlab = "Weight",ylab = "Height",col = "red")

#b: average and plot
dfcMale <- subset(dfc,Gender == "Male")
dfcFemale <- subset(dfc,Gender == "Female")
aveMaleHeight <- mean(dfcMale[,2])
aveFemaleHeight <- mean(dfcFemale[,2])
barPlot <-barplot(c(aveFemaleHeight,aveMaleHeight),ylim = c(0,aveMaleHeight*1.2),xlab = "Gender",ylab = "Average Height",names.arg = c("Female","Male"))

#c: colors
barPlot2 <-barplot(c(aveFemaleHeight,aveMaleHeight),ylim = c(0,aveMaleHeight*1.2),xlab = "Gender",ylab = "Average Height",names.arg = c("Female","Male"),col = c("blue","green"))
