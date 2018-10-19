#!/usr/bin/env python 
# -*- coding: utf-8 -*- 
# @Time : 2018/10/14 下午5:33 
# @Author : Gaoxiang Chen
# @Site :  
# @File : titanic.py 
# @Software: PyCharm
# ---------------------
import inline
import matplotlib
from IPython.display import Image

Image(
     url="https://static1.squarespace.com/static/5006453fe4b09ef2252ba068/5095eabce4b06cb305058603/5095eabce4b02d37bef4c24c/1352002236895/100_anniversary_titanic_sinking_by_esai8mellows-d4xbme8.jpg")

import pandas as pd
import numpy as np

from matplotlib import pyplot as plt

from skimage import data

random_image = np.random.random([500, 500])
print(random_image)
plt.imshow(random_image, cmap='gray')
plt.colorbar()


train = pd.read_csv('../input/train.csv')
test = pd.read_csv('../input/test.csv')

# print(train.head(5))
# print(train.info())
# print(train.isnull().sum())

print(train.head())

def bar_chart(feature):
    survived = train[train['Survived']==1][feature].value_counts()
    dead = train[train['Survived']==0][feature].value_counts()
    df = pd.DataFrame([survived,dead])
    df.index = ['Survived','Dead']
    df.plot(kind='bar',stacked=True, figsize=(10,5))

bar_chart('Sex')